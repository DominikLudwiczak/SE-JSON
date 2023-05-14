package pl.put.poznan.json.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "pl.put.poznan.json.rest" })
public class JSONApplication {

    public static void main(String[] args) {

        SpringApplication.run(JSONApplication.class, args);
    }
}
