package problems;

import models.Elf;
import setup.Reader;

import java.util.ArrayList;
import java.util.List;

public class Day1 {

    private final Reader reader;

    public Day1() {
        this.reader = new Reader();
    }

    public String solveDay1() {
        List<String> fileInput = reader.readFile("day1");
        Elf elf = new Elf();
        Elf elfMostCarried = new Elf();

        for (String callorie: fileInput) {
            if(callorie.equals("")) {
                if (elf.getCarryWeight() > elfMostCarried.getCarryWeight()) {
                    elfMostCarried = elf;
                }
                elf = new Elf();
            } else {
                elf.addCarryWeight(Integer.valueOf(callorie));
            }
        }

        if (elf.getCarryWeight() > elfMostCarried.getCarryWeight()) {
            elfMostCarried = elf;
        }

        return elfMostCarried.getCarryWeight().toString();
    }

    public String solveDay1Part2() {
        List<String> fileInput = reader.readFile("day1");
        ArrayList<Integer> elfsWeight = new ArrayList<>();
        Elf elf = new Elf();
        for (String callorie: fileInput) {
            if(callorie.equals("")) {
                elfsWeight.add(elf.getCarryWeight());
                elf = new Elf();
            } else {
                elf.addCarryWeight(Integer.valueOf(callorie));
            }
        }
        elfsWeight.add(elf.getCarryWeight());

        List<Integer> sortedWeight = elfsWeight.stream().sorted().toList();
        return String.valueOf(sortedWeight.get(sortedWeight.size()-1) + sortedWeight.get(sortedWeight.size()-2) + sortedWeight.get(sortedWeight.size()-3));
    }

}