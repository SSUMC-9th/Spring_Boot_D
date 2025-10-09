package com.umc9th.peter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PeterApplication {

    public static void main(String[] args) {
        SpringApplication.run(PeterApplication.class, args);
    }

}
