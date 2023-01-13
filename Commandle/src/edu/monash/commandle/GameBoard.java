package edu.monash.commandle;

import java.util.List;
import java.util.Random;

public class GameBoard {
    public enum Status {
        correct, wrong, partial;
    }

    private String target;
    private final List<String> wordList;

    public GameBoard(List<String> wordList) {
        this.wordList = wordList;
    }

    public void startGame() {
        int size = wordList.size();
        target = wordList.get(new Random().nextInt(size)).toLowerCase();
//        System.err.println("\ntarget w = " + target);
    }

    public Status[] isInTarget(char[] word) {
        Status[] result = new Status[target.length()];

        for (int i = 0; i < target.length(); i++) {
            if (word[i] == target.charAt(i)) {
                result[i] = Status.correct;
            } else if (target.indexOf(word[i]) >= 0) {
                result[i] = Status.partial;
            } else {
                result[i] = Status.wrong;
            }
        }
        return result;
    }

    public boolean hasWon(Status[] current) {
        boolean result = true;
        for (Status st : current) {
            result &= (st == Status.correct);
        }
        return result;
    }

    public boolean containsWord(String word) {
        return wordList.contains(word);
    }
}
