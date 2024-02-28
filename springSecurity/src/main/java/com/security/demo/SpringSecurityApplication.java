package com.security.demo;

import com.security.demo.entity.User;
import com.security.demo.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository){
		return args -> {
			userRepository.save(new User("user", "password", "ROLE_USER"));
			userRepository.save(new User("admin", "password", "ROLE_USER,ADMIN_ROLE"));
		};
	}
}
