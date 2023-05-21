package pl.put.poznan.json.app;

import java.io.IOException;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import pl.put.poznan.json.logic.FileLogger;

@SpringBootApplication(scanBasePackages = { "pl.put.poznan.json.rest" })
public class JSONApplication {
    public static void main(String[] args) throws IOException {
        System.out.println(":c");

        FileLogger.logger.info("Starting JSON Application...");
    }
}
