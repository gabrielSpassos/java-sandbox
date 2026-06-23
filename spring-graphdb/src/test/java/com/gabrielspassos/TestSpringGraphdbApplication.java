package com.gabrielspassos;

import org.springframework.boot.SpringApplication;

public class TestSpringGraphdbApplication {

	public static void main(String[] args) {
		SpringApplication.from(SpringGraphdbApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
