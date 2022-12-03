package problems;

import setup.Reader;

import java.util.Arrays;
import java.util.List;

public class Day2 {

    private final Reader reader;
    private List<String> fileInput;
    private Integer sum = 0;
    private enum game {
        ROCK,
        PAPER,
        SCISSOR
    }

    public Day2() {
        this.reader = new Reader();
        this.fileInput = reader.readFile("day2");
    }

    public String solveDay2() {
        for (String game:fileInput) {
            List<String> gameOptions = Arrays.stream(game.split(" ")).toList();
            sum += checkGame(gameOptions.get(0),gameOptions.get(1));
            sum += checkMyOption(gameOptions.get(1));
        }
        return sum.toString();
    }

    public String solveDay2Part2() {
        for (String game:fileInput) {
            List<String> gameOptions = Arrays.stream(game.split(" ")).toList();
            sum += checkGame2(gameOptions.get(0), gameOptions.get(1));
        }
        return sum.toString();
    }

    private Integer checkGame(String opponent, String me) {
        game opponentsChoice = getGameChoice(opponent);
        game myChoice = getGameChoice(me);
        return runGame(opponentsChoice, myChoice);
    }

    private Integer checkGame2(String opponent, String gameResult) {
        game opponentsChoice = getGameChoice(opponent);
        game myChoice = getGameResultChoice(opponentsChoice, gameResult);
        return runGame(opponentsChoice, myChoice);
    }

    private Integer runGame(game opponentsChoice, game myChoice) {
        if (opponentsChoice.equals(myChoice)) {
            return 3 + myOptionsPoints(myChoice);
        } else if ((opponentsChoice.equals(game.ROCK) && myChoice.equals(game.PAPER)) ||
                (opponentsChoice.equals(game.PAPER) && myChoice.equals(game.SCISSOR)) ||
                (opponentsChoice.equals(game.SCISSOR) && myChoice.equals(game.ROCK))) {
            return 6 + myOptionsPoints(myChoice);
        } else {
            return myOptionsPoints(myChoice);
        }
    }

    private game getGameChoice(String choice) {
        return switch (choice) {
            case "A", "X" -> game.ROCK;
            case "B", "Y" -> game.PAPER;
            case "C", "Z" -> game.SCISSOR;
            default -> null;
        };
    }

    private Integer checkMyOption(String choice) {
        return switch (choice) {
            case "A", "X" -> 1;
            case "B", "Y" -> 2;
            case "C", "Z" -> 3;
            default -> null;
        };
    }

    private Integer myOptionsPoints(game choice) {
        return switch (choice) {
            case ROCK -> 1;
            case PAPER -> 2;
            case SCISSOR -> 3;
        };
    }

    private game getGameResultChoice(game opponentsChoice, String option) {
        if (option.equals("X")) {
            //Loose
            return switch (opponentsChoice) {
                case ROCK -> game.SCISSOR;
                case PAPER -> game.ROCK;
                case SCISSOR -> game.PAPER;
            };
        } else if (option.equals("Y")) {
            //Draw
            return opponentsChoice;
        } else {
            //Win
            return switch (opponentsChoice) {
                case ROCK -> game.PAPER;
                case PAPER -> game.SCISSOR;
                case SCISSOR -> game.ROCK;
            };
        }
    }
}