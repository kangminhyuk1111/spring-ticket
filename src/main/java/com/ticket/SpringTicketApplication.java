package com.ticket;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

// TODO JPA Implement
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringTicketApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTicketApplication.class, args);
    }

    @PostConstruct
    public void init() {
        final String jsonFilePath = "/mock/pay-server.jar";

        try {
            final File jar = new ClassPathResource(jsonFilePath).getFile();

            final ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", jar.getAbsolutePath());
            processBuilder.directory(jar.getParentFile());
            processBuilder.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
