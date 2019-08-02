package com.stackroute.muzix;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class TrackServiceApplication extends SpringBootServletInitializer implements CommandLineRunner {

	TrackService trackService;

	@Autowired
	public void setTrackService(TrackService trackService) {
		this.trackService = trackService;
	}

	//For command line runner
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TrackServiceApplication.class);
	}

    //main method to run the application
	public static void main(String[] args) {
		SpringApplication.run(TrackServiceApplication.class, args);
	}

	//For saving the information
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Track sevice"+trackService);
		trackService.saveTrack(new Track(1,"tum hi ho","bollywood"));
		trackService.saveTrack(new Track(2,"summer of 69","hollywood"));

	}

}
