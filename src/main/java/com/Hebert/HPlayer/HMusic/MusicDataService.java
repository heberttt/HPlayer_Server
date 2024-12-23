package com.Hebert.HPlayer.HMusic;

import java.net.MalformedURLException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

public interface MusicDataService {
    public ResponseEntity<MusicDO> getMusicData(String link);

    public ResponseEntity<List<MusicDO>> getAllMusicData();

    public ResponseEntity<Resource> getMusicLowThumbnail(String youtubeCode) throws MalformedURLException;

    public ResponseEntity<Resource> getMusicMediumThumbnail(String youtubeCode) throws MalformedURLException;

    public ResponseEntity<Resource> getMusicHighThumbnail(String youtubeCode) throws MalformedURLException;
}
