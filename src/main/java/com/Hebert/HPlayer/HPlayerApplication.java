package com.Hebert.HPlayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.Hebert.HPlayer.HMusic.MusicDownloaderService;
import com.Hebert.HPlayer.HMusic.Controller.HMusicController;
import com.Hebert.HPlayer.HMusic.requests.DownloadMusicRequest;
import com.google.api.services.youtube.YouTube.Captions.Download;


@SpringBootApplication
public class HPlayerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HPlayerApplication.class, args);
	}

	

	@EventListener(ApplicationReadyEvent.class)
	public void startup() throws InterruptedException, IOException {

		// String currentDirectory = System.getProperty("user.dir");

		// List<String> cleanCommand = List.of("rm", "-r", currentDirectory + "/tempMusics/");
            
        // ProcessBuilder cleanProcessBuilder = new ProcessBuilder(cleanCommand);

        // cleanProcessBuilder.redirectErrorStream(true);
        // cleanProcessBuilder.inheritIO();

        // Process cleanProcess = cleanProcessBuilder.start();
                    
        // cleanProcess.waitFor();

		// List<String> addDirectoryCommand = List.of("mkdir" , currentDirectory + "/tempMusics");

		// List<String> addEmptyFileCommand = List.of("touch" , currentDirectory + "/tempMusics/.empty");

		// ProcessBuilder addDirectoryProcessBuilder = new ProcessBuilder(addDirectoryCommand);

		// ProcessBuilder addEmptyFileProcessBuilder = new ProcessBuilder(addEmptyFileCommand);

		// addDirectoryProcessBuilder.redirectErrorStream(true);
		// addDirectoryProcessBuilder.inheritIO();

		// addEmptyFileProcessBuilder.redirectErrorStream(true);
		// addEmptyFileProcessBuilder.inheritIO();

		// Process addDirectoryProcess = addDirectoryProcessBuilder.start();

		// addDirectoryProcess.waitFor();


		// Process addEmptyFileProcess = addEmptyFileProcessBuilder.start();

		// addEmptyFileProcess.waitFor();

		
		// String[] youtubeLinks = new String[] {"https://www.youtube.com/watch?v=mV0QcGcgt3o&pp=ygUFc29uZ3M%3D", "https://www.youtube.com/watch?v=DDyr3DbTPtk&pp=ygUFc29uZ3M%3D", "https://www.youtube.com/watch?v=AdBzzpq3xV4&pp=ygUFc29uZ3M%3D", "https://www.youtube.com/watch?v=Jq2YI3nKhLM&pp=ygUFc29uZ3M%3D", "https://www.youtube.com/watch?v=2ydCvkxuNm4&pp=ygUFc29uZ3M%3D"};
		// List<DownloadMusicRequest> requests = new ArrayList<DownloadMusicRequest>();

		// for (int i = 0; i < youtubeLinks.length; i++){
		// 	DownloadMusicRequest request = new DownloadMusicRequest();
		// 	request.setYoutubeLink(youtubeLinks[i]);
		// 	requests.add(request);
		// }

		// requestSongOnStartUp(requests);

		// System.out.println("Added requests on startup");

	}


	// public void requestSongOnStartUp(List<DownloadMusicRequest> requests){
	// 	for (int i = 0; i < requests.size(); i++){
	// 		musicDownloaderService.requestMusic(requests.get(i));
	// 	}
	// }

}
