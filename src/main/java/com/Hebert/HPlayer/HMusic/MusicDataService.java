package com.Hebert.HPlayer.HMusic;

import java.util.List;

import com.Hebert.HPlayer.HMusic.results.GetPresignedMusicUrlResult;
import com.Hebert.HPlayer.HMusic.results.GetPresignedThumbnailUrlResult;
import org.springframework.http.ResponseEntity;

public interface MusicDataService {
    public ResponseEntity<MusicDO> getMusicData(String link);

    public ResponseEntity<List<MusicDO>> getAllMusicData();

    public GetPresignedThumbnailUrlResult getThumbnailPresignedUrl(String musicId, ThumbnailQuality quality);

    public GetPresignedMusicUrlResult getMusicPresignedUrl(String musicId);

    public Boolean addMusic(MusicDO music);
}
