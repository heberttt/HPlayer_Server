package com.Hebert.HPlayer.HMusic.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.Hebert.HPlayer.HMusic.MusicDO;
import com.Hebert.HPlayer.HMusic.MusicRepository;
import com.Hebert.HPlayer.HMusic.Exceptions.YoutubeLinkException;

@Repository
public class MusicRepositoryImpl implements MusicRepository{

    private final JdbcClient jdbcClient;

    public MusicRepositoryImpl(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    @Override
    public void addMusic(MusicDO music) {
        var updated = jdbcClient.sql("INSERT INTO musics(music_id, title, channel_name ,duration) VALUES(?,?,?,?)")
                        .params(List.of(music.getMusicId(), music.getTitle(), music.getChannelName(), music.getDuration()))
                        .update();

        Assert.state(updated == 1, "Failed to insert new music: " + music.getTitle());
    }

    @Override
    public void deleteMusicByCode(MusicDO music) {
        if (StringUtils.hasText(music.getMusicId())){
            throw new YoutubeLinkException("No link code found");
        }

        var updated = jdbcClient.sql("DELETE FROM musics WHERE music_id = :music_id")
                                .param("music_id", music.getMusicId())
                                .update();


        Assert.state(updated == 1, "Failed to delete music: " + music.getMusicId());
    }

    @Override
    public List<MusicDO> findAllMusic() {
        return jdbcClient.sql("SELECT * FROM musics")
                        .query(MusicDO.class)
                        .list();
    }

    @Override
    public Optional<MusicDO> queryMusicDetails(String code) {
        return jdbcClient.sql("SELECT music_id, title, duration, channel_name FROM musics WHERE music_id = :music_id")
                        .param("music_id", code)
                        .query(MusicDO.class)
                        .optional();
    }

    
    
}