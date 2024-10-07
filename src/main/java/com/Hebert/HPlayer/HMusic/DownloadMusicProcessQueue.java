package com.Hebert.HPlayer.HMusic;

import java.util.LinkedList;
import java.util.Queue;

import com.Hebert.HPlayer.HMusic.requests.DownloadMusicRequest;

public class DownloadMusicProcessQueue {
    
    Queue<DownloadMusicRequest> musicDownloadRequestQueue = new LinkedList<>();

    public Boolean isEmpty(){
        return musicDownloadRequestQueue.isEmpty();
    }

    public void addQueue(DownloadMusicRequest request){
        musicDownloadRequestQueue.add(request);
    }

    public void removeQueue(){
        musicDownloadRequestQueue.remove();
    }

    public Integer getQueueSize(){
        return musicDownloadRequestQueue.size();
    }
}
