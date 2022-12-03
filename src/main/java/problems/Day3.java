package problems;

import setup.Reader;

import java.util.*;


public class Day3 {

    private final Reader reader;
    private List<String> fileInput;
    private ArrayList<String> backpackItems;
    private String alphabet = "1abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Day3() {
        this.reader = new Reader();
        this.fileInput = reader.readFile("day3");
    }

    public String solveDay3() {
        int sum = 0;
        backpackItems = new ArrayList<>();
        for (String backpack: fileInput) {
            String firstHalf = backpack.substring(0, backpack.length()/2);
            String secondHalf = backpack.substring(backpack.length()/2);
            char[] t = firstHalf.toCharArray();
            for (char c:t) {
                if(secondHalf.contains(String.valueOf(c))) {
                    sum += alphabet.indexOf(c);
                    break;
                }
            }
        }
        return String.valueOf(sum);
    }

    public String solveDay3Part2() {
        int sum = 0;
        for (int i=0; i<fileInput.size(); i+=3) {
            String s1 = fileInput.get(i);
            String s2 = fileInput.get(i+1);
            String s3 = fileInput.get(i+2);
            char[] t = s1.toCharArray();
            for (char c: t) {
                if(s2.contains(String.valueOf(c)) && s3.contains(String.valueOf(c))) {
                    sum += alphabet.indexOf(c);
                    break;
                }
            }
        }
        return String.valueOf(sum);
    }
}