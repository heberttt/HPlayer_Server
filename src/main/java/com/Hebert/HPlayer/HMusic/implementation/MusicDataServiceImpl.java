package com.Hebert.HPlayer.HMusic.implementation;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Hebert.HPlayer.HMusic.MusicDO;
import com.Hebert.HPlayer.HMusic.MusicDataService;
import com.Hebert.HPlayer.HMusic.MusicRepository;

@Service
public class MusicDataServiceImpl implements MusicDataService{

    private final MusicRepository musicRepository;

    public MusicDataServiceImpl(MusicRepository musicRepository){
        this.musicRepository = musicRepository;
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
    public ResponseEntity<Resource> getMusicLowThumbnail(String youtubeCode) throws MalformedURLException {
        String fileLocation = System.getProperty("user.dir") + "/assets/thumbnails/low/" + youtubeCode + ".jpg";
        Path path = Paths.get(fileLocation);

        Resource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);

    }

    @Override
    public ResponseEntity<Resource> getMusicMediumThumbnail(String youtubeCode) throws MalformedURLException {
        String fileLocation = System.getProperty("user.dir") + "/assets/thumbnails/medium/" + youtubeCode + ".jpg";
        Path path = Paths.get(fileLocation);

        Resource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }

    @Override
    public ResponseEntity<Resource> getMusicHighThumbnail(String youtubeCode) throws MalformedURLException {
        String fileLocation = System.getProperty("user.dir") + "/assets/thumbnails/high/" + youtubeCode + ".jpg";

        try{
            Path path = Paths.get(fileLocation);

            Resource resource = new UrlResource(path.toUri());

            if (!resource.exists()){
                throw new FileNotFoundException();
            }

            return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
                
        }catch(FileNotFoundException e){
            return ResponseEntity.notFound().build();
        }

        

        
    }
    
}
