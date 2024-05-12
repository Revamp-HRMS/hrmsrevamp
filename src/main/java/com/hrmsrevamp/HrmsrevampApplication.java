package com.hrmsrevamp;

import com.hrmsrevamp.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackageClasses = {"com.hrmsrevamp.repository.UserRepository"} )
//@EnableJpaRepositories(basePackageClasses = {UserRepository.class})
@EnableJpaRepositories
public class HrmsrevampApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrmsrevampApplication.class, args);
	}

}
