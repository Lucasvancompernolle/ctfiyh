package com.ctfiyh.restaurant.restaurant;

import org.springframework.boot.SpringApplication;

public class TestRestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.from(RestaurantApplication::main)
				.with(TestcontainersConfiguration.class)
				.run(args);
	}

}
