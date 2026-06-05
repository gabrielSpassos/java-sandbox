package com.gabrielspassos;

import org.springframework.boot.SpringApplication;

public class TestToxiproxyPocApplication {

	public static void main(String[] args) {
		SpringApplication.from(ToxiproxyPocApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
