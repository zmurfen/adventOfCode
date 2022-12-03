package setup;

import lombok.Data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class Reader {

    private HashMap<String, String> filePaths;
    private BufferedReader fileReader;

    public Reader() {
        this.filePaths = new HashMap<>();
        populateFilePaths();
    }

    public List<String> readFile(String day) {
        ArrayList<String> readFile = new ArrayList<>();
        try {
            fileReader = new BufferedReader(new FileReader(filePaths.get(day)));
            String line = fileReader.readLine();
            while (line != null) {
                readFile.add(line);
                line = fileReader.readLine();
            }
        } catch (Exception e) {
            System.out.println("failed to read file");
        } finally {
            try {
                fileReader.close();
            } catch (Exception e) {
                System.out.println("failed to close file");
            }
        }
        return readFile;
    }


    private void populateFilePaths() {
        filePaths.put("day1", "src/main/resources/day1");
        filePaths.put("day2", "src/main/resources/day2");
        filePaths.put("day3", "src/main/resources/day3");
    }
}