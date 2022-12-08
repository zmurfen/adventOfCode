package problems;

import setup.Reader;
import java.util.List;

public class Day8 {

    private final Reader reader;
    private List<String> fileInput;
    private int[][] treeMatrix;
    private int length;
    private int height;
    private Integer totalVisibleTrees = 0;

    public Day8() {
        this.reader = new Reader();
        this.fileInput = reader.readFile("day8");
        this.length = fileInput.get(0).length();
        this.height = fileInput.size();
        this.treeMatrix = new int[length][height];
    }

    public String solveDay8() {
        fillUpMatrix();
        findTrees();
        return totalVisibleTrees.toString();
    }

    public String solveDay8Part2() {
        fillUpMatrix();
        return String.valueOf(findHouseLocation());
    }

    private Integer findHouseLocation() {
        int best = 0;
        int prod;
        for(int i = 0; i<length; i++) {
            for (int j = 0; j<height; j++) {
                prod = findTreeBlockersRightValue(i, j);
                prod *= findTreeBlockersLeftValue(i, j);
                prod *= findTreeBlockersUpValue(i, j);
                prod *= findTreeBlockersDownValue(i, j);
                if(prod>best) {
                    best = prod;
                }
            }
        }
        return best;
    }

    private int findTreeBlockersDownValue(int i, int j) {
        if(i==length) return 0;
        int currentTree = treeMatrix[i][j];
        int iterations = 0;
        for (int a = i+1; a<length; a++) {
            iterations++;
            if(currentTree<=treeMatrix[a][j]) {
                return iterations;
            }
        }
        return iterations;
    }

    private int findTreeBlockersUpValue(int i, int j) {
        if(i==0) return 0;
        int currentTree = treeMatrix[i][j];
        int iterations = 0;
        for (int a = i-1; a>=0; a--) {
            iterations++;
            if(currentTree<=treeMatrix[a][j]) {
                return iterations;
            }
        }
        return iterations;
    }

    private int findTreeBlockersLeftValue(int i, int j) {
        if(j==0) return 0;
        int currentTree = treeMatrix[i][j];
        int iterations = 0;
        for (int a = j-1; a>=0; a--) {
            iterations++;
            if(currentTree<=treeMatrix[i][a]) {
                return iterations;
            }
        }
        return iterations;
    }

    private int findTreeBlockersRightValue(int i, int j) {
        if(j==length) return 0;
        int currentTree = treeMatrix[i][j];
        int iterations = 0;
        for (int a = j+1; a<length; a++) {
            iterations++;
            if(currentTree<=treeMatrix[i][a]) {
                return iterations;
            }
        }
        return iterations;
    }

    private void findTrees() {
        for(int i = 0; i<length; i++) {
            for (int j = 0; j < height; j++) {
                if(findTreeBlockersRight(i, j)) {
                    totalVisibleTrees++;
                    continue;
                }
                if(findTreeBlockersLeft(i, j)) {
                    totalVisibleTrees++;
                    continue;
                }
                if(findTreeBlockersUp(i, j)) {
                    totalVisibleTrees++;
                    continue;
                }
                if(findTreeBlockersDown(i, j)) {
                    totalVisibleTrees++;
                }
            }
        }
    }

    private boolean findTreeBlockersDown(int i, int j) {
        if(i==length) return true;
        int currentTree = treeMatrix[i][j];
        for (int a = i+1; a<length; a++) {
            if(currentTree<=treeMatrix[a][j]) {
                return false;
            }
        }
        return true;
    }

    private boolean findTreeBlockersUp(int i, int j) {
        if(i==0) return true;
        int currentTree = treeMatrix[i][j];
        for (int a = i-1; a>=0; a--) {
            if(currentTree<=treeMatrix[a][j]) {
                return false;
            }
        }
        return true;
    }

    private boolean findTreeBlockersLeft(int i, int j) {
        if(j==0) return true;
        int currentTree = treeMatrix[i][j];
        for (int a = j-1; a>=0; a--) {
            if(currentTree<=treeMatrix[i][a]) {
                return false;
            }
        }
        return true;
    }

    private boolean findTreeBlockersRight(int i, int j) {
        if(j==length) return true;
        int currentTree = treeMatrix[i][j];
        for (int a = j+1; a<length; a++) {
            if(currentTree<=treeMatrix[i][a]) {
                return false;
            }
        }
        return true;
    }

    private void fillUpMatrix() {
        for(int i = 0; i<fileInput.size(); i++) {
            char[] temp = fileInput.get(i).toCharArray();
            for (int j = 0; j< temp.length; j++) {
                treeMatrix[i][j] = Integer.parseInt(String.valueOf(temp[j]));
            }
        }
    }
}