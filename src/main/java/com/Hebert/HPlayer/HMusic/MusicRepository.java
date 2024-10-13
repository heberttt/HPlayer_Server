package com.Hebert.HPlayer.HMusic;

import java.util.List;
import java.util.Optional;

public interface MusicRepository {
    
    public void addMusic(MusicDO music);

    public void deleteMusicByCode(MusicDO music);

    public List<MusicDO> findAllMusic();

    public Optional<MusicDO> queryMusicDetails(String code);
}
