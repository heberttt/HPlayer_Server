package com.Hebert.HPlayer;

import java.io.IOException;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;


@SpringBootApplication
public class HPlayerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HPlayerApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void startup() throws InterruptedException, IOException {

		String currentDirectory = System.getProperty("user.dir");

		List<String> cleanCommand = List.of("rm", "-r", currentDirectory + "/tempMusics/");
            
        ProcessBuilder cleanProcessBuilder = new ProcessBuilder(cleanCommand);

        cleanProcessBuilder.redirectErrorStream(true);
        cleanProcessBuilder.inheritIO();

        Process cleanProcess = cleanProcessBuilder.start();
                    
        cleanProcess.waitFor();

		List<String> addDirectoryCommand = List.of("mkdir" , currentDirectory + "/tempMusics");

		List<String> addEmptyFileCommand = List.of("touch" , currentDirectory + "/tempMusics/.empty");

		ProcessBuilder addDirectoryProcessBuilder = new ProcessBuilder(addDirectoryCommand);

		ProcessBuilder addEmptyFileProcessBuilder = new ProcessBuilder(addEmptyFileCommand);

		addDirectoryProcessBuilder.redirectErrorStream(true);
		addDirectoryProcessBuilder.inheritIO();

		addEmptyFileProcessBuilder.redirectErrorStream(true);
		addEmptyFileProcessBuilder.inheritIO();

		Process addDirectoryProcess = addDirectoryProcessBuilder.start();

		addDirectoryProcess.waitFor();


		Process addEmptyFileProcess = addEmptyFileProcessBuilder.start();

		addEmptyFileProcess.waitFor();

	}

}
