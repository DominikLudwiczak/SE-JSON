package pl.put.poznan.json.app;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.json.logic.*;
import java.nio.file.Files;
import java.nio.file.Paths;
@SpringBootApplication(scanBasePackages = { "pl.put.poznan.json.rest" })
public class JSONApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(JSONApplication.class, args);

        int action = 1;
        System.out.println("Welcome to JSON Tools, please input json file");

        Scanner scanner = new Scanner(System.in);
        String jsonContent = "__invalid__";
        String line;

        while (!JSONValidator.validate(jsonContent)) {

            StringBuilder json = new StringBuilder();

            System.out.println("Paste valid JSON or path to the JSON file, and then press enter:\n");
            while (!(line = scanner.nextLine()).isEmpty()) {
                json.append(line).append("\n");
            }
            json.setLength(json.length() - 1);
            jsonContent = json.toString();

            if (!jsonContent.startsWith("{")){
                jsonContent = new String(Files.readAllBytes(Paths.get(jsonContent)));
            }
        }


        System.out.println("Pasted file:" + "\n" + jsonContent + "\n");
        System.out.println("Please chose what You want to do with JSON:");

        while (action != 0) {
            JSONProcessor jsonFile = new DefaultJSONProcessor();
            System.out.println("0: Exit");
            System.out.println("1: Minimize JSON");
            System.out.println("2: Unminify JSON");
            System.out.println("3: Filter JSON (exclude keys)");
            System.out.println("4: Filter JSON (select keys)");
            System.out.println("5: Compare JSON");

            action = scanner.nextInt();

            switch (action) {
                case 0:
                    System.out.println("Chose path to save JSON");
                    String path = "";
                    Scanner a = new Scanner(System.in);
                    path = a.nextLine();
                    System.out.println(path);
                    if (path != ""){
                        FileWriter fileWriter = new FileWriter(path);
                        fileWriter.write(jsonContent);
                        fileWriter.close();
                    }
                    break;
                case 1:
                    jsonFile = new JSONMinifier(jsonFile);
                    jsonContent = jsonFile.processJSON(jsonContent);
                    System.out.println("Processed File:" + "\n" + jsonContent);
                    break;
                case 2:
                    jsonFile = new JSONUnminifier(jsonFile);
                    jsonContent = jsonFile.processJSON(jsonContent);
                    System.out.println("Processed File:" + "\n" + jsonContent);
                    break;
                case 3:
                    jsonFile = new JSONFilter(jsonFile);
                    jsonContent = jsonFile.processJSON(jsonContent);
                    System.out.println("Processed File:" + "\n" + jsonContent);
                    break;
                case 4:
                    jsonFile = new JSONSelectFilter(jsonFile);
                    jsonContent = jsonFile.processJSON(jsonContent);
                    System.out.println("Processed File:" + "\n" + jsonContent);
                    break;
                case 5:
                    jsonFile = new JSONComparator(jsonFile);
                    System.out.println(jsonFile.processJSON(jsonContent));
                    break;

            }
        }
        scanner.close();
        System.out.println("Thank You for using JSON Tools!");
        System.exit(0);
    }
}