package com.security.demo;

import com.security.demo.entity.User;
import com.security.demo.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

//	@Bean
//	CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder encoder){
//		return args -> {
//			userRepository.save(new User("user", encoder.encode("password"), "ROLE_USER"));
//			userRepository.save(new User("admin", encoder.encode("password"), "ROLE_USER,ROLE_ADMIN"));
//		};
//	}
}
