package setup;

import lombok.Data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class Reader {
    private BufferedReader fileReader;
    private String FILE_PATH = "src/main/resources/";
    private HashMap<Integer, List<String>> input = new HashMap<>();

    public Reader() { }

    public List<String> readFile(String day) {
        ArrayList<String> readFile = new ArrayList<>();
        try {
            fileReader = new BufferedReader(new FileReader(FILE_PATH+day));
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

    public HashMap<Integer, List<String>> readFileDay5FirstPart(String day) {
        populateHashMap(input);
        try {
            fileReader = new BufferedReader(new FileReader(FILE_PATH+day));
            String line = fileReader.readLine();
            int j;
            while(line != null) {
                if (line.equals(" 1   2   3   4   5   6   7   8   9 ")) break;
                j=1;
                char[] charLine = line.toCharArray();
                for (int i = 1; i < charLine.length; i+=4) {
                    if(charLine[i] != ' ') {
                        input.get((j)).add(String.valueOf(charLine[i]));
                    }
                    j++;
                }
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

        return input;
    }

    public List<String> readFileDay5SecondPart(String day) {
        ArrayList<String> readFile = new ArrayList<>();
        boolean storeData = false;
        try {
            fileReader = new BufferedReader(new FileReader(FILE_PATH+day));
            String line = fileReader.readLine();
            while (line != null) {
                if (storeData) {
                     line = line.split("move ")[1];
                     String[] lineSplitted = line.split(" from ");
                     String first = lineSplitted[0];
                     lineSplitted = lineSplitted[1].split(" to ");
                     readFile.add(first+" "+lineSplitted[0]+" "+lineSplitted[1]);
                }
                if (line.equals("")) {
                    storeData = true;
                }
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

    public void populateHashMap(HashMap<Integer, List<String>> temp) {
        for (int i = 1; i<=9; i++) {
            temp.put(i,new ArrayList<>());
        }
    }
}