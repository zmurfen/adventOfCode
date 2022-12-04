package problems;

import setup.Reader;

import java.util.ArrayList;
import java.util.List;

public class Day4 {
    private final Reader reader;
    private List<String> fileInput;

    public Day4() {
        this.reader = new Reader();
        this.fileInput = reader.readFile("day4");
    }

    public String solveDay4() {
        long total = fileInput.stream().map(input -> input.split(",")).filter(tasks -> {
            List<Integer> task1 = getStartAndFinish(tasks[0]);
            List<Integer> task2 = getStartAndFinish(tasks[1]);
            return task1.get(0) >= task2.get(0) && task1.get(1) <= task2.get(1) ||
                    task1.get(0) <= task2.get(0) && task1.get(1) >= task2.get(1);
        }).count();

        return Long.toString(total);
    }

    public String solveDay4Part2() {
        long total = fileInput.stream().map(input -> input.split(",")).filter(tasks -> {
            List<Integer> task1 = getStartAndFinish(tasks[0]);
            List<Integer> task2 = getStartAndFinish(tasks[1]);
            return (task1.get(0) >= task2.get(0) && task1.get(1) <= task2.get(1)) ||
                    task1.get(0) <= task2.get(0) && task1.get(1) >= task2.get(1) ||
                    task1.get(0) <= task2.get(0) && task1.get(1) >= task2.get(0) ||
                    task2.get(0) <= task1.get(0) && task2.get(1) >= task1.get(0);
        }).count();

        return Long.toString(total);
    }

    private List<Integer> getStartAndFinish(String task) {
        String[] taskStartFinish = task.split("-");
        ArrayList<Integer> tasks = new ArrayList<>();
        tasks.add(Integer.parseInt(taskStartFinish[0]));
        tasks.add(Integer.parseInt(taskStartFinish[1]));
        return tasks;
    }
}