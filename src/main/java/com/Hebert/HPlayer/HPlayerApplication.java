package com.Hebert.HPlayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.Hebert.HPlayer.login.HUserRepositoryImpl;

@SpringBootApplication
public class HPlayerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HPlayerApplication.class, args);
	}



	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup(HUserRepositoryImpl hUserRepositoryImpl) {
		
	}
}
