package com.claro.gestionrecursosapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionRecursosApiApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(GestionRecursosApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GestionRecursosApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }


}
