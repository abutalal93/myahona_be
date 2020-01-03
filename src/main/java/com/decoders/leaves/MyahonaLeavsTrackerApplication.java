package com.decoders.leaves;

import com.decoders.leaves.config.DatabaseSeeder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class MyahonaLeavsTrackerApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MyahonaLeavsTrackerApplication.class, args);

		Environment env = context.getBean(Environment.class);

		if (env.getProperty("seed_database").trim().equals("true")) {
			DatabaseSeeder databaseSeeder = context.getBean(DatabaseSeeder.class);
			databaseSeeder.seed();
		}
	}

}
