package com.Hebert.HPlayer.HMusic.implementation;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import com.Hebert.HPlayer.HMusic.Storage.MinioService;
import com.Hebert.HPlayer.HMusic.ThumbnailQuality;
import com.Hebert.HPlayer.HMusic.results.GetPresignedMusicUrlResult;
import com.Hebert.HPlayer.HMusic.results.GetPresignedThumbnailUrlResult;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Hebert.HPlayer.HMusic.MusicDO;
import com.Hebert.HPlayer.HMusic.MusicDataService;
import com.Hebert.HPlayer.HMusic.MusicRepository;

@Service
public class MusicDataServiceImpl implements MusicDataService{

    private final MusicRepository musicRepository;

    private final MinioService minioService;

    public MusicDataServiceImpl(MusicRepository musicRepository, MinioService minioService){
        this.musicRepository = musicRepository;
        this.minioService = minioService;
    }

    @Override
    public ResponseEntity<MusicDO> getMusicData(String link) {
        Optional<MusicDO> result = musicRepository.queryMusicDetails(link);

        if (result.isPresent()){
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<MusicDO>> getAllMusicData() {
        return new ResponseEntity<>(musicRepository.findAllMusic(), HttpStatus.OK);
    }

    @Override
    public GetPresignedThumbnailUrlResult getThumbnailPresignedUrl(String musicId, ThumbnailQuality quality) {

        GetPresignedThumbnailUrlResult result = new GetPresignedThumbnailUrlResult();

        try{
            if (!minioService.fileExists("thumbnail/" + quality.toString().toLowerCase() + "/" + musicId + ".jpg")){
                result.setFail("File not found", 404);
                return result;
            }

            String url = minioService.getThumbnailPresignedUrl(musicId, 3600 * 72, quality);

            result.setSuccess(null, 200);

            result.setPresignedUrl(url);

            return result;

        } catch (Exception e) {
            System.out.println("Error getting presigned thumbnail url: " + e.getMessage());

            result.setFail(e.getMessage(), 500);

            return result;
        }

    }

    @Override
    public GetPresignedMusicUrlResult getMusicPresignedUrl(String youtubeCode) {

        GetPresignedMusicUrlResult result = new GetPresignedMusicUrlResult();

        try{
            if(!minioService.fileExists("file/" + youtubeCode + ".mp3")){
                result.setFail("File not found", 404);
                return result;
            }

            String url = minioService.getMusicPresignedUrl( youtubeCode, 7200); // Duration 2 hours

            result.setSuccess(null, 200);

            result.setPresignedUrl(url);

            return result;

        } catch (Exception e) {
            System.out.println("Error getting presigned music url: " + e.getMessage());

            result.setFail(e.getMessage(), 500);

            return result;
        }
    }

    @Override
    public Boolean addMusic(MusicDO music) {
        try{
            musicRepository.addMusic(music);

            return true;
        } catch (DuplicateKeyException e) {
            System.out.println("Music record already exists, returning as true");
            return true;
        }
        catch (Exception e) {
            System.out.println("Error adding music: " + e.getMessage());

            return false;
        }
    }

}
