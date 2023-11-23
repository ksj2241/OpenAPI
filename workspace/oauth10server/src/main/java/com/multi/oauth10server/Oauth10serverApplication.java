package com.multi.oauth10server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class Oauth10serverApplication {

	public static void main(String[] args) {
		SpringApplication.run(Oauth10serverApplication.class, args);
	}
	
	@Scheduled(fixedDelay = 500000)
	public void test() {
		System.out.println("## tick");
	}

}
