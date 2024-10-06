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
        var updated = jdbcClient.sql("INSERT INTO musics(link_code, title, duration, low_thumbnail_url, high_thumbnail_url,music_file) VALUES(?,?,?,?,?,?)")
                        .params(List.of(music.getLink_code(), music.getTitle(), music.getDuration(), music.getLowThumbnailUrl(), music.getHighThumbnailUrl(), music.getMusicFile()))
                        .update();

        Assert.state(updated == 1, "Failed to insert new music: " + music.getTitle());
    }

    @Override
    public void deleteMusicByCode(MusicDO music) {
        if (StringUtils.hasText(music.getLink_code())){
            throw new YoutubeLinkException("No link code found");
        }

        var updated = jdbcClient.sql("DELETE FROM musics WHERE link_code = :link_code")
                                .param("link_code", music.getLink_code())
                                .update();


        Assert.state(updated == 1, "Failed to delete music: " + music.getLink_code());
    }

    @Override
    public List<MusicDO> findAllMusic() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllMusic'");
    }

    @Override
    public MusicDO queryMusic(String code) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'queryMusic'");
    }

    @Override
    public Optional<MusicDO> queryMusicDetails(String code) {
        return jdbcClient.sql("SELECT link_code, title, duration, low_thumbnail_url, high_thumbnail_url FROM musics WHERE link_code = :link_code")
                        .param("link_code", code)
                        .query(MusicDO.class)
                        .optional();
    }

    
    
}