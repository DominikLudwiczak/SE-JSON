package pl.put.poznan.json.app;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pl.put.poznan.json.logic.DefaultJSONTools;
import pl.put.poznan.json.logic.JSONMinifier;
import pl.put.poznan.json.logic.JSONReader;

@SpringBootApplication(scanBasePackages = { "pl.put.poznan.json.rest" })
public class JSONToolsApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(JSONToolsApplication.class, args);
        String filePath = "generated.json";
        JSONReader jsonReader = new JSONReader(filePath);

        String jsonData = jsonReader.readJSON();
        System.out.println("JSON Data: " + jsonData);

        // Instantiate the JSONTools implementation
        DefaultJSONTools jsonTools = new DefaultJSONTools();

        // Minify JSON
        JSONMinifier jsonMinifier = new JSONMinifier(jsonTools);
        String minifiedJson = jsonMinifier.processJSON(jsonData);
        System.out.println("Minified JSON: " + minifiedJson);
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("minified.json"), "utf-8"))) {
            writer.write(minifiedJson);
        }
        System.out.println("Minified JSON saved to file: minified.json");
    }
}
