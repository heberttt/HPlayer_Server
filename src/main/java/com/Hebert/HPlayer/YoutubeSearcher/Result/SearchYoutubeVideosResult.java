package com.Hebert.HPlayer.YoutubeSearcher.Result;

import com.Hebert.HPlayer.YoutubeSearcher.Model.SearchedVideoModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class SearchYoutubeVideosResult extends Result{
    private List<SearchedVideoModel> searchResult;
}
