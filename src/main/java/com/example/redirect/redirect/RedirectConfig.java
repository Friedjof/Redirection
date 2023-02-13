package com.example.redirect.redirect;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RedirectConfig {
    @Bean
    CommandLineRunner commandLineRunner(RedirectRepository redirectRepository) {
        return args -> {
            Redirect google = new Redirect();
            google.setUrl("https://www.google.com");

            Redirect facebook = new Redirect();
            facebook.setUrl("https://www.facebook.com");

            Redirect twitter = new Redirect();
            twitter.setUrl("https://www.twitter.com");

            redirectRepository.saveAll(
                    List.of(google, facebook, twitter)
            );
        };
    }
}
