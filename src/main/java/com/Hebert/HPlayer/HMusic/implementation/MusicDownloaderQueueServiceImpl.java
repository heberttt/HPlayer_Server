package com.Hebert.HPlayer.HMusic.implementation;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.Hebert.HPlayer.HMusic.MusicRepository;
import com.Hebert.HPlayer.HMusic.Storage.MinioService;
import com.Hebert.HPlayer.HMusic.results.GetPresignedMusicUrlResult;
import org.springframework.http.ResponseEntity;
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

    private final MinioService minioService;

    public MusicDownloaderQueueServiceImpl(KafkaTemplate<String, YoutubeDownloadRequestMessage> kafkaTemplate, MusicRepository musicRepository, MinioService minioService) {
        this.kafkaTemplate = kafkaTemplate;
        this.musicRepository = musicRepository;
        this.minioService = minioService;
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
        
        YoutubeDownloadRequestMessage message = new YoutubeDownloadRequestMessage();
        message.setYoutubeUrl(request.getYoutubeLink());

        kafkaTemplate.send("music-download-requests", message);
        
        result.setSuccess(true);
        result.setStatusCode(200);
        result.setMessage("Message added to queue");

        return result;
    }

    @Override
    public GetPresignedMusicUrlResult getPresignedMusicUrl(String youtubeCode) {

        GetPresignedMusicUrlResult result = new GetPresignedMusicUrlResult();

        try{
            if(!minioService.fileExists("file/" + youtubeCode + ".mp3")){
                throw new FileNotFoundException("File does not exist in minio");
            }

            String url = minioService.getPresignedUrl("file/" + youtubeCode + ".mp3", 7200); // Duration 2 hours

            result.setSuccess(null, 200);

            result.setPresignedUrl(url);

            return result;

        } catch (Exception e) {
            System.out.println("Error getting presigned music url: " + e.getMessage());

            result.setFail(e.getMessage(), 500);

            return result;
        }
    }


}
