package com.Hebert.HPlayer.YoutubeSearcher.Service.Impl;

import com.Hebert.HPlayer.HMusic.implementation.YoutubeUtil;
import com.Hebert.HPlayer.YoutubeSearcher.Model.SearchedVideoModel;
import com.Hebert.HPlayer.YoutubeSearcher.Result.SearchYoutubeVideosResult;
import com.Hebert.HPlayer.YoutubeSearcher.Service.YoutubeSearchService;
import com.Hebert.HPlayer.YoutubeSearcher.Util.YoutubeDataApiConsumer;
import com.google.api.services.youtube.model.Video;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class YoutubeSearchServiceImpl implements YoutubeSearchService {

    @Override
    public SearchYoutubeVideosResult searchYoutubeVideos(String keyword) {

        SearchYoutubeVideosResult result = new SearchYoutubeVideosResult();

        if (keyword.isEmpty()){
            result.setFail("Keyword is blank", 400);
            return result;
        }

        try{
            List<Video> searchedVideos = YoutubeDataApiConsumer.searchVideosWithStatistics(keyword);
            List<SearchedVideoModel> videoModels = new ArrayList<>();

            for (Video video : searchedVideos){
                SearchedVideoModel videoModel = new SearchedVideoModel();
                videoModel.setVideoTitle(video.getSnippet().getTitle());
                videoModel.setChannelTitle(video.getSnippet().getChannelTitle());
                videoModel.setViews(video.getStatistics().getViewCount().longValue());
                videoModel.setCreatedAt(video.getSnippet().getPublishedAt().getValue());
                videoModel.setYoutubeCode(video.getId());
                videoModel.setDuration(YoutubeUtil.convertDurationIntoSeconds(video.getContentDetails().getDuration()));
                videoModel.setThumbnailHigh(video.getSnippet().getThumbnails().getHigh().getUrl());
                videoModel.setThumbnailMedium(video.getSnippet().getThumbnails().getMedium().getUrl());
                videoModel.setThumbnailLow(video.getSnippet().getThumbnails().getDefault().getUrl());

                videoModels.add(videoModel);
            }

            result.setSuccess(null, 200);
            result.setSearchResult(videoModels);

            return result;
        } catch (Exception e) {
            System.out.println("Youtube Search API error: " + e.getMessage());
            result.setFail(e.getMessage(), 500);

            return result;
        }
    }
}
