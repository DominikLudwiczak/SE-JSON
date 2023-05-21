package pl.put.poznan.json.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JSONReader {
    private String filePath;

    public JSONReader(String filePath) {
        this.filePath = filePath;
    }

    public String readJSON() throws IOException {
        BufferedReader reader = null;
        try {

            reader = new BufferedReader(new FileReader(filePath));
            StringBuilder jsonData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonData.append(line);
            }
            return jsonData.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
}
