package com.tracker.expense_tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpenseTrackerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseTrackerApplication.class, args);
	}

	@Value("${profile}")
	String value ;

	@Override
	public void run(String... args) throws Exception {


		System.out.println("running" +value);
	}
}
