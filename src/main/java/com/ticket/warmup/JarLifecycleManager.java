package com.ticket.warmup;

import jakarta.annotation.PostConstruct;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class JarLifecycleManager implements ApplicationListener<ContextClosedEvent> {

    private Process jarProcess;

    @Override
    public void onApplicationEvent(final ContextClosedEvent event) {
        if (jarProcess == null) {
            return;
        }

        destroyMockServer();
    }

    private void destroyMockServer() {
        jarProcess.destroy();

        try {
            if (!jarProcess.waitFor(10, TimeUnit.SECONDS)) {
                jarProcess.destroyForcibly();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @PostConstruct
    private void startMockServer() {
        final String jsonFilePath = "/mock/pay-server.jar";

        try {
            final File jar = new ClassPathResource(jsonFilePath).getFile();

            final ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", jar.getAbsolutePath());
            processBuilder.directory(jar.getParentFile());
            jarProcess = processBuilder.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
