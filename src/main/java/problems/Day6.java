package problems;

import setup.Reader;

import java.util.ArrayList;
import java.util.List;

public class Day6 {

    private final Reader reader;
    private List<String> fileInput;
    private int result = 1;

    public Day6() {
        this.reader = new Reader();
        this.fileInput = reader.readFile("day6");
    }

    public String solveDay6() {
        char[] input = fileInput.get(0).toCharArray();
        ArrayList<Character> marker = new ArrayList<>();
        for (char c : input) {
            if (marker.size() < 13) {
                result++;
                marker.add(c);
                continue;
            }
            if (checkIfInMarker(marker, c)) break;

        }
        return String.valueOf(result);
    }

    private boolean checkIfInMarker(List<Character> marker, char input) {
        if(marker.contains(input) || (marker.stream().distinct().count()<13)) {
            result++;
            marker.remove(0);
            marker.add(input);
            return false;
        }
        return true;
    }
}