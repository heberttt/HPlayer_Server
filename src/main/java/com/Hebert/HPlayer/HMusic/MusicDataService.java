package com.Hebert.HPlayer.HMusic;

import org.springframework.http.ResponseEntity;

public interface MusicDataService {
    public ResponseEntity<MusicDO> getMusicData(String link);
}
