package problems;

import models.Directory;
import setup.Reader;
import java.util.ArrayList;
import java.util.List;

public class Day7 {

    private final Reader reader;
    private List<String> fileInput;
    List<Integer> result = new ArrayList<>();
    private Directory fileTree = new Directory("/", null);
    private Integer closest = Integer.MAX_VALUE;
    private int totalFolderDiscSpaceToFind;
    private int MAX_DISC_SPACE = 70000000;
    private int DISC_SPACE_REC = 30000000;
    private int FOLDER_SIZE = 100000;

    public Day7() {
        this.reader = new Reader();
        this.fileInput = reader.readFile("day7");
    }

    public String solveDay7Part2() {
        buildTree();
        populateDirectoryTotalSize(fileTree);
        int totalDiscSpaceUsed = fileTree.getTotalSize();
        int totalAvailableSpace = MAX_DISC_SPACE - totalDiscSpaceUsed;
        totalFolderDiscSpaceToFind = DISC_SPACE_REC - totalAvailableSpace;
        findClosestDirectorySize(fileTree);
        return closest.toString();
    }

    private void findClosestDirectorySize(Directory directory) {
        if (directory == null) return;
        if (directory.getTotalSize() >= totalFolderDiscSpaceToFind && closest > directory.getTotalSize()) {
            closest = directory.getTotalSize();
        }
        directory.getDirectories().forEach(this::findClosestDirectorySize);
    }

    public String solveDay7() {
        buildTree();
        populateDirectoryTotalSize(fileTree);
        findDirectoriesWithTotal(fileTree);
        return result.stream().reduce(0, Integer::sum).toString();
    }

    private void findDirectoriesWithTotal(Directory directory) {
        if (directory == null) return;
        if (directory.getTotalSize()<=FOLDER_SIZE) {
            result.add(directory.getTotalSize());
        }
        directory.getDirectories().forEach(this::findDirectoriesWithTotal);
    }

    private Integer populateDirectoryTotalSize(Directory directory) {
        if (directory == null) return 0;
        int sum = directory.getFiles().stream().reduce(0, Integer::sum);
        for (Directory dir: directory.getDirectories()) {
            sum += populateDirectoryTotalSize(dir);
        }
        directory.setTotalSize(sum);
        return sum;
    }

    private void buildTree() {
        Directory currentDir = fileTree;
        for (String line:fileInput) {
            String[] lineInput = line.split(" ");
            switch (lineInput[0]) {
                case "$":
                    if (lineInput[1].equals("cd")) {
                        if (lineInput[2].equals("/")) {
                            currentDir = fileTree;
                        } else if (lineInput[2].equals("..")) {
                            if (currentDir.getName().equals("/")) {
                                break;
                            }
                            currentDir = currentDir.getSuperFolder();
                        } else {
                            currentDir = currentDir.getDirectories().stream().filter(dir -> dir.getName().equals(lineInput[2])).findFirst().orElseThrow();
                        }
                    }
                    break;
                case "dir":
                    currentDir.getDirectories().add(new Directory(lineInput[1], currentDir));
                    break;
                default:
                    currentDir.getFiles().add(Integer.parseInt(lineInput[0]));
                    break;
            }
        }
    }
}