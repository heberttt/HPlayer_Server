package com.Hebert.HPlayer.HMusic.implementation;

import java.io.FileNotFoundException;

import com.Hebert.HPlayer.HMusic.MusicRepository;
import com.Hebert.HPlayer.HMusic.Storage.MinioService;
import com.Hebert.HPlayer.HMusic.results.GetPresignedMusicUrlResult;
import org.springframework.kafka.core.KafkaTemplate;

import com.Hebert.HPlayer.HMusic.MusicDownloaderService;
import com.Hebert.HPlayer.HMusic.Kafka.messages.YoutubeDownloadRequestMessage;
import com.Hebert.HPlayer.HMusic.requests.DownloadMusicRequest;
import com.Hebert.HPlayer.HMusic.results.DownloadMusicResult;
import org.springframework.stereotype.Service;

@Service
public class MusicDownloaderQueueServiceImpl implements MusicDownloaderService {

    private final KafkaTemplate<String, YoutubeDownloadRequestMessage> kafkaTemplate;

    private final MusicRepository musicRepository;

    public MusicDownloaderQueueServiceImpl(KafkaTemplate<String, YoutubeDownloadRequestMessage> kafkaTemplate, MusicRepository musicRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.musicRepository = musicRepository;
    }

    @Override
    public DownloadMusicResult requestMusic(DownloadMusicRequest request) {

        DownloadMusicResult result = new DownloadMusicResult();

        if (request.getYoutubeLink() == null || request.getYoutubeLink().isEmpty()){
            result.setSuccess(false);
            result.setStatusCode(400);
            result.setMessage("Youtube link is empty");
            
            return result;
        }

        if (musicRepository.queryMusicDetails(YoutubeUtil.linkCodeGetter(YoutubeUtil.linkStandardization(request.getYoutubeLink()))).isPresent()){
            result.setFail("Music already exists", 400);

            return result;
        }
        
        YoutubeDownloadRequestMessage message = new YoutubeDownloadRequestMessage();
        message.setYoutubeUrl(request.getYoutubeLink());

        kafkaTemplate.send("music-download-requests", message);
        
        result.setSuccess(true);
        result.setStatusCode(200);
        result.setMessage("Message added to queue");

        return result;
    }




}
