package com.Hebert.HPlayer.HMusic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;


public interface MusicStreamService {
    
    public ResponseEntity<Resource> streamMusic(String linkCode, String rangeHeader) throws FileNotFoundException, IOException;
}
