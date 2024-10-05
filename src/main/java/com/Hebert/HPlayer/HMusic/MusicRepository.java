package com.Hebert.HPlayer.HMusic;

import java.util.List;

public interface MusicRepository {
    
    public void addMusic(MusicDO music);

    public void deleteMusicByCode(MusicDO music);

    public List<MusicDO> findAllMusic();

    public MusicDO findMusicByCode(String code);
}
