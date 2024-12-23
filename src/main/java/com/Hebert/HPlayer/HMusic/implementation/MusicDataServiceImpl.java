package com.Hebert.HPlayer.HMusic.implementation;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
    
}
