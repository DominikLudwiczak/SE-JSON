package pl.put.poznan.json.app;

import java.io.IOException;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "pl.put.poznan.json.rest" })
public class JSONToolsApplication {
    public static void main(String[] args) throws IOException {
        System.out.println(":c");
    }
}
