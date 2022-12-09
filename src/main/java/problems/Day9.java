package problems;

import setup.Reader;

import java.util.List;

public class Day9 {

    private final Reader reader;
    private List<String> fileInput;
    private int MAX_SIZE = 600;
    private String[][] posVisited = new String[MAX_SIZE][MAX_SIZE];
    private String[][] testFilePosVisited = new String[20][20];
    private int headPosX;
    private int tailPosX;
    private int headPosY;
    private int tailPosY;
    private Integer total = 0;
    public Day9() {
        this.reader = new Reader();
        this.fileInput = reader.readFile("day9");
    }

    public String solveDay9() {
        /*
        testFilePosVisited[0][0] = "X";
        testFilePosVisited[9][9] = "Y";
        testFilePosVisited[0][9] = "A";
        testFilePosVisited[9][0] = "Z";

        X.H......A
        ...T......
        ..........
        Z........Y
         */

        headPosX = MAX_SIZE/2;
        headPosY = MAX_SIZE/2;
        tailPosX = MAX_SIZE/2;
        tailPosY = MAX_SIZE/2;
        for (String line: fileInput) {
            String[] movement = line.split(" ");
            switch (movement[0]) {
                case "U":
                    moveStepByStepUp(Integer.parseInt(movement[1]));
                    break;
                case "D":
                    moveStepByStepDown(Integer.parseInt(movement[1]));
                    break;
                case "R":
                    moveStepByStepRight(Integer.parseInt(movement[1]));
                    break;
                case "L":
                    moveStepByStepLeft(Integer.parseInt(movement[1]));
            }
        }

        countTotalVisited();
        return total.toString();
    }


    private void countTotalVisited() {
        for(int i = 0; i<MAX_SIZE; i++) {
            for (int j = 0; j<MAX_SIZE; j++) {
                if (posVisited[i][j] != null) {
                    total++;
                }
            }
        }
    }

    private void moveStepByStepUp(Integer steps) {
        int tempHeadPos = headPosY;
        for (int i = headPosY; i>headPosY-steps; i--) {
            tempHeadPos--;
            if(tailPosY-tempHeadPos > 1) {
                tailPosY = tempHeadPos+1;
                if (tailPosX - headPosX != 0) {
                    tailPosX = headPosX;
                }
            }
            updateTailPosVisited();
        }
        headPosY = tempHeadPos;
    }

    private void moveStepByStepDown(Integer steps) {
        int tempHeadPos = headPosY;
        for (int i = headPosY; i<headPosY+steps; i++) {
            tempHeadPos++;
            if(tempHeadPos-tailPosY > 1) {
                tailPosY = tempHeadPos-1;
                if (tailPosX - headPosX != 0) {
                    tailPosX = headPosX;
                }
            }
            updateTailPosVisited();
        }
        headPosY = tempHeadPos;
    }

    private void moveStepByStepRight(Integer steps) {
        int tempHeadPos = headPosX;
        for (int i = headPosX; i<headPosX+steps; i++) {
            tempHeadPos++;
            if(tempHeadPos-tailPosX > 1) {
                tailPosX = tempHeadPos-1;
                if (tailPosY - headPosY != 0) {
                    tailPosY = headPosY;
                }
            }
            updateTailPosVisited();
        }
        headPosX = tempHeadPos;
    }

    private void moveStepByStepLeft(Integer steps) {
        int tempHeadPos = headPosX;
        for (int i = headPosX; i>headPosX-steps; i--) {
            tempHeadPos--;
            if(tailPosX-tempHeadPos > 1) {
                tailPosX = tempHeadPos+1;
                if (tailPosY - headPosY != 0) {
                    tailPosY = headPosY;
                }
            }
            updateTailPosVisited();
        }
        headPosX = tempHeadPos;
    }


    private void updateTailPosVisited() {
        posVisited[tailPosY][tailPosX] = "X";
    }

}