package com.Hebert.HMusicTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.hibernate.exception.GenericJDBCException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.Hebert.HPlayer.HMusic.MusicDownloaderService;
import com.Hebert.HPlayer.HMusic.implementation.YoutubeDataApiConsumer;
import com.Hebert.HPlayer.HMusic.implementation.YoutubeUtil;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.VideoListResponse;

import java.io.IOException;

public class HMusicTest {

    @InjectMocks
    private MusicDownloaderService musicDownloaderService;


    @Test
    public void testYoutubeLinkCleaner(){
        assertEquals("1f81qXxggo8", YoutubeUtil.linkCodeGetter("https://www.youtube.com/watch?v=1f81qXxggo8"));
        assertEquals("1f81qXxggo8",YoutubeUtil.linkCodeGetter("https://youtu.be/1f81qXxggo8?feature=shared"));
        assertEquals("u5heWZ9occg", YoutubeUtil.linkCodeGetter("https://www.youtube.com/watch?v=u5heWZ9occg&list=PLbLnVwFkWyIi-4rcBuuMPT5tVZuI3ba0z"));
    }

    @Test
    public void testYoutubeDurationConverter(){
        assertEquals(120, YoutubeUtil.convertDurationIntoSeconds("PT2M"));
        assertEquals(131, YoutubeUtil.convertDurationIntoSeconds("PT2M11S"));
        assertEquals(12, YoutubeUtil.convertDurationIntoSeconds("PT12S"));
        assertEquals(3781, YoutubeUtil.convertDurationIntoSeconds("PT1H3M1S"));
        assertEquals(1, YoutubeUtil.convertDurationIntoSeconds("PT01S"));
    }

    
}
