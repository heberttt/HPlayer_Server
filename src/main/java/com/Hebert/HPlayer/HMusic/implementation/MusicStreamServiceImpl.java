package com.Hebert.HPlayer.HMusic.implementation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Hebert.HPlayer.HMusic.MusicDO;
import com.Hebert.HPlayer.HMusic.MusicRepository;
import com.Hebert.HPlayer.HMusic.MusicStreamService;
import com.Hebert.HPlayer.HMusic.Exceptions.MusicNotExist;


@Service
public class MusicStreamServiceImpl implements MusicStreamService{

    @Autowired
    MusicRepository musicRepository;

    @Override
    public ResponseEntity<Resource> streamMusic(String linkCode, String rangeHeader) throws IOException {
        String currentDirectory = System.getProperty("user.dir");

        MusicDO requestedMusicDetails = new MusicDO();
        try{
            Optional<MusicDO> queriedMusicDetails = musicRepository.queryMusicDetails(linkCode);

            if (queriedMusicDetails.isEmpty()){
                throw new MusicNotExist("Music is null");
            }

            requestedMusicDetails = queriedMusicDetails.get();
            
            File musicFile = new File(currentDirectory + "/tempMusics/" + requestedMusicDetails.getLink_code() + ".mp3");

            if (!musicFile.exists()) {
                System.out.println("mp3 does not exist");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            long fileLength = musicFile.length();

            InputStreamResource resource = new InputStreamResource(new FileInputStream(musicFile));

            if (rangeHeader == null){
                return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("audio/mpeg"))
                    .contentLength(fileLength)
                    .body(resource);
            }


        String[] ranges = rangeHeader.replace("bytes=", "").split("-");
        long startByte = Long.parseLong(ranges[0]);
        long endByte = (ranges.length > 1 && !ranges[1].isEmpty()) ? Long.parseLong(ranges[1]) : fileLength - 1;

        FileInputStream inputStream = new FileInputStream(musicFile);
        inputStream.skip(startByte);  

        InputStreamResource partialResource = new InputStreamResource(inputStream);

        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .header("Content-Range", "bytes " + startByte + "-" + endByte + "/" + fileLength)
                .contentType(MediaType.parseMediaType("audio/mpeg"))
                .contentLength(endByte - startByte + 1)
                .body(partialResource);


        }catch(MusicNotExist err){
            System.out.println(err.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
}
