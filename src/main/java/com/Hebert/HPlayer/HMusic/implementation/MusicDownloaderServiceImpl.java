//package com.Hebert.HPlayer.HMusic.implementation;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.LinkedList;
//import java.util.Queue;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.Hebert.HPlayer.HMusic.MusicDownloadThread;
//import com.Hebert.HPlayer.HMusic.MusicDownloaderService;
//import com.Hebert.HPlayer.HMusic.MusicRepository;
//import com.Hebert.HPlayer.HMusic.requests.DownloadMusicRequest;
//import com.Hebert.HPlayer.HMusic.results.DownloadMusicResult;
//
//
//@Deprecated
////@Service
//public class MusicDownloaderServiceImpl implements MusicDownloaderService{
//
//    @Autowired
//    MusicRepository musicRepository;
//
//    Thread downloadThread;
//
//
//    private volatile Queue<DownloadMusicRequest> musicDownloadQueue = new LinkedList<>();
//
//
//
//    @Override
//    public DownloadMusicResult requestMusic(DownloadMusicRequest request) {
//
//        DownloadMusicResult result = new DownloadMusicResult();
//
//        String currentDirectory = System.getProperty("user.dir");
//        String cleanCode = YoutubeUtil.linkCodeGetter(YoutubeUtil.linkStandardization(request.getYoutubeLink())) + ".mp3";
//        File checkFile = new File(currentDirectory + "/tempMusics/" + cleanCode);
//
//        if (checkFile.exists()){
//            System.out.println("mp3 exists");
//
//            result.setSuccess(true);
//            result.setMessage("mp3 exists");
//
//            return result;
//        }
//
//
//        if (musicDownloadQueue.isEmpty()){
//            musicDownloadQueue.add(request);
//            System.out.println("add and start thread");
//            try{
//                downloadMusic();
//            }catch(IOException e){
//                System.out.println(e.toString());
//            }catch (InterruptedException e){
//                System.out.println(e.toString());
//            }
//
//            result.setMessage("add and start thread");
//
//        }else{
//            if(downloadThread.isAlive()){
//                musicDownloadQueue.add(request);
//                System.out.println("only add");
//                result.setMessage("only add");
//            }else{
//                musicDownloadQueue.add(request);
//                try {
//                    downloadMusic();
//                } catch (IOException | InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("only add and make new thread");
//                result.setMessage("only add and make new thread");
//            }
//        }
//
//
//        result.setSuccess(true);
//
//        return result;
//
//    }
//
//
//    @Override
//    public void downloadMusic() throws IOException, InterruptedException{
//
//        DownloadMusicRequest request = musicDownloadQueue.peek();
//
//        String currentDirectory = System.getProperty("user.dir");
//        String cleanCode = YoutubeUtil.linkCodeGetter(YoutubeUtil.linkStandardization(request.getYoutubeLink())) + ".mp3";
//        File checkFile = new File(currentDirectory + "/tempMusics/" + cleanCode);
//
//        if (checkFile.exists()){
//            System.out.println("mp3 exists, aborting thread creation...");
//            return;
//        }
//
//        MusicDownloadThread musicDownloadThread = new MusicDownloadThread(request, this, musicRepository);
//
//        downloadThread = new Thread(musicDownloadThread);
//
//        downloadThread.start();
//
//        // musicDownloadQueue.remove();
//
//        // System.out.println("removing music from queue");
//    }
//
//    public Boolean queueIsEmpty(){
//        return musicDownloadQueue.isEmpty();
//    }
//
//    public void removeQueue(){
//        musicDownloadQueue.remove();
//    }
//
//
//
//
//
//}
//