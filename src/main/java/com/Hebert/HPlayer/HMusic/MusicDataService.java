package com.Hebert.HPlayer.HMusic;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface MusicDataService {
    public ResponseEntity<MusicDO> getMusicData(String link);

    public ResponseEntity<List<MusicDO>> getAllMusicData();
}
