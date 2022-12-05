package problems;

import setup.Reader;

import java.util.*;

public class Day5 {

    private final Reader reader;
    private List<String> fileInput;
    private HashMap<Integer, List<String>> boxes;

    public Day5() {
        this.reader = new Reader();
        this.boxes = reader.readFileDay5FirstPart("day5");
        this.fileInput = reader.readFileDay5SecondPart("day5");
        for (Integer key:boxes.keySet()) {
            Collections.reverse(boxes.get(key));
        }
    }

    public String solveDay5() {
        fileInput.stream().map(in -> in.split(" ")).forEach(this::moveBoxes);
        for (Integer key:boxes.keySet()) {
            System.out.print(boxes.get(key).get(boxes.get(key).size()-1));
        }
        System.out.println();
        return "";
    }

    public String solveDay5Part2() {
        fileInput.stream().map(in -> in.split(" ")).forEach(this::moveBoxes2);
        for (Integer key:boxes.keySet()) {
            System.out.print(boxes.get(key).get(boxes.get(key).size()-1));
        }
        System.out.println();
        return "";
    }

    private void moveBoxes2(String[] input) {
        int amountToMove = Integer.parseInt(input[0]);
        int from = Integer.parseInt(input[1]);
        int to = Integer.parseInt(input[2]);
        List<String> boxesTo = boxes.get(to);
        List<String> boxesFrom = boxes.get(from);
        for (int i = amountToMove; i>0; i--) {
            boxesTo.add(boxesFrom.remove(boxesFrom.size()-i));
        }
    }

    private void moveBoxes(String[] input) {
        int amountToMove = Integer.parseInt(input[0]);
        int from = Integer.parseInt(input[1]);
        int to = Integer.parseInt(input[2]);
        List<String> boxesTo = boxes.get(to);
        List<String> boxesFrom = boxes.get(from);
        for (int i = 0; i<amountToMove; i++) {
            boxesTo.add(boxesFrom.remove(boxesFrom.size()-1));
        }
    }

}