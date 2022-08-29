package me.creighton.scramble;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import picocli.CommandLine;

@SpringBootApplication
public class ScrambleApplication {

	public static void main(String[] args) {

		int rc = new CommandLine(new ScrambleIt()).execute(args);
		System.exit(rc);

//		SpringApplication.run(ScrambleApplication.class, args);
	}


}
