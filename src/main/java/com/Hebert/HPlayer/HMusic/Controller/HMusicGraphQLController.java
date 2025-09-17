package com.Hebert.HPlayer.HMusic.Controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import com.Hebert.HPlayer.HMusic.MusicDO;
import com.Hebert.HPlayer.HMusic.MusicDataService;

@Controller
public class HMusicGraphQLController {

    private final MusicDataService musicDataService;

    public HMusicGraphQLController(MusicDataService musicDataService) {
        this.musicDataService = musicDataService;
    }

    @QueryMapping
    public List<MusicDO> getAllMusicData(){
        return musicDataService.getAllMusicData().getBody();
    }

    @QueryMapping
    public MusicDO musicByLinkCode(@Argument("link_code") String link){
        return musicDataService.getMusicData(link).getBody();
    }

    
}
