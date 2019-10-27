package com.altran.desafio;

import com.altran.desafio.repos.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioApplication {

    @Autowired
    ItemRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(DesafioApplication.class, args);
    }
}
