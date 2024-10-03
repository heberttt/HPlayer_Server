package com.Hebert.HMusicTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.Hebert.HPlayer.HMusic.MusicDownloaderService;
import com.Hebert.HPlayer.HMusic.implementation.YoutubeCodeCleaner;

public class HMusicTest {

    @InjectMocks
    private MusicDownloaderService musicDownloaderService;


    @Test
    public void testYoutubeLinkCleaner(){
        assertEquals("1f81qXxggo8", YoutubeCodeCleaner.linkCodeGetter("https://www.youtube.com/watch?v=1f81qXxggo8"));
        assertEquals("1f81qXxggo8",YoutubeCodeCleaner.linkCodeGetter("https://youtu.be/1f81qXxggo8?feature=shared"));
        assertEquals("u5heWZ9occg", YoutubeCodeCleaner.linkCodeGetter("https://www.youtube.com/watch?v=u5heWZ9occg&list=PLbLnVwFkWyIi-4rcBuuMPT5tVZuI3ba0z"));
    }
}
