package pl.put.poznan.json.app;

import java.io.IOException;
import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.json.logic.*;

@SpringBootApplication(scanBasePackages = { "pl.put.poznan.json.rest" })
public class JSONApplication {
    public static void main(String[] args) throws IOException {

        System.out.println("Welcome to JSON Tools, please input json file" + "\n" + "Paste Your JSON, and then press enter");

        Scanner scanner = new Scanner(System.in);

        StringBuilder json = new StringBuilder();
        String line;

        while (!(line = scanner.nextLine()).isEmpty()) {
            json.append(line).append("\n");
        }
        json.setLength(json.length() - 1);
        String jsonContent = json.toString();


        System.out.println("Pasted file:" + "\n" + jsonContent + "\n");
        System.out.println("Please chose what You want to do with JSON:");

        JSONProcessor jsonFile = new DefaultJSONProcessor();

        int action = 1;
        while (action != 0) {
            System.out.println("0: Exit");
            System.out.println("1: Minimize JSON");
            System.out.println("2: Unminify JSON");
            System.out.println("3: Filter JSON");
            System.out.println("4: Compare JSON");

            action = scanner.nextInt();

            switch (action) {
                case 0:
                    action = 0;
                    break;
                case 1:
                    jsonFile = new JSONMinifier(jsonFile);
                    System.out.println("Processed File:" + "\n" + jsonFile.processJSON(jsonContent));
                    break;
                case 2:
                    jsonFile = new JSONUnminifier(jsonFile);
                    System.out.println("Processed File:" + "\n" + jsonFile.processJSON(jsonContent));
                    break;
                case 3:
                    jsonFile = new JSONFilter(jsonFile);
                    System.out.println("Processed File:" + "\n" + jsonFile.processJSON(jsonContent));
                    break;
                case 4:
                    System.out.println("Not supported yet!");
                    break;

            }
        }
        System.out.println("Thank You for using JSON Tools!");
    }
}