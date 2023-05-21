package pl.put.poznan.json.app;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonFilter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.json.logic.*;

@SpringBootApplication(scanBasePackages = { "pl.put.poznan.json.rest" })
public class JSONApplication {
    public static void main(String[] args) throws IOException {
        System.out.println(":c");
    }
}
