package com.example.mylibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // для того чтобы заработал TaskScheduler
public class MylibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MylibraryApplication.class, args);
	}

}
