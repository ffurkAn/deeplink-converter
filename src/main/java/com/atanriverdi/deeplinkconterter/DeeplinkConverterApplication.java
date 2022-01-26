package com.atanriverdi.deeplinkconterter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class DeeplinkConverterApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeeplinkConverterApplication.class, args);
    }

}
