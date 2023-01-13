package edu.monash.commandle;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static edu.monash.commandle.GameBoard.Status.correct;
import static edu.monash.commandle.GameBoard.Status.wrong;
import static edu.monash.commandle.GameBoard.Status.partial;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameBoardUnitTest {
    private GameBoard gameBoard;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

   /* @Test
    void isInTarget() {
    }

    @Test
    void hasWon() {
        gameBoard = new GameBoard(new ArrayList<>());

        GameBoard.Status[] result = new GameBoard.Status[]{correct, correct, correct, correct, correct};
        assertTrue(gameBoard.hasWon(result), "game is won");
    }

    @Test
    void containsCorrectWord() {
        ArrayList<String> list = new ArrayList<>();
        String word = "prone";
        list.add(word);
        gameBoard = new GameBoard(list);

        assertTrue(gameBoard.containsWord(word), "contains word");
    }*/


    @Test
    // Test ID 1
    void charCheck1(){
        // define target word as well as user inputted word
        ArrayList<String> list = new ArrayList<>();
        String target = "swear";
        String userInput = "check";
        // add target word to word list, user inputted word into character array, and then load the game board
        list.add(target);
        char[] input = userInput.toLowerCase().trim().toCharArray();
        gameBoard = new GameBoard(list);
        gameBoard.startGame();
        // check the status of each character
        GameBoard.Status[] inputArray = gameBoard.isInTarget(input);
        GameBoard.Status[] result = {wrong, wrong, correct, wrong, wrong};
        for (int i = 0; i < 5; i++){
        assertEquals(inputArray[i], result[i]);
        }
    }

    @Test
        // Test ID 2
    void charCheck2(){
        // define target word as well as user inputted word
        ArrayList<String> list = new ArrayList<>();
        String target = "query";
        String userInput = "heart";
        // add target word to word list, user inputted word into character array, and then load the game board
        list.add(target);
        char[] input = userInput.toLowerCase().trim().toCharArray();
        gameBoard = new GameBoard(list);
        gameBoard.startGame();
        // check the status of each character
        GameBoard.Status[] inputArray = gameBoard.isInTarget(input);
        GameBoard.Status[] result = {wrong, partial, wrong, correct, wrong};
        // for loop to compare the character status between target and input word
        for (int i = 0; i < 5; i++){
            assertEquals(inputArray[i], result[i]);
        }
    }

    @Test
        // Test ID 3
    void charCheck3(){
        // define target word as well as user inputted word
        ArrayList<String> list = new ArrayList<>();
        String target = "times";
        String userInput = "times";
        // add target word to word list, user inputted word into character array, and then load the game board
        list.add(target);
        char[] input = userInput.toLowerCase().trim().toCharArray();
        gameBoard = new GameBoard(list);
        gameBoard.startGame();
        // check the status of each character
        GameBoard.Status[] inputArray = gameBoard.isInTarget(input);
        GameBoard.Status[] result = {correct, correct, correct, correct, correct};
        // for loop to compare the character status between target and input word
        for (int i = 0; i < 5; i++){
            assertEquals(inputArray[i], result[i]);
        }
    }

    @Test
        // Test ID 4
        //failed test
    void charCheck4(){
        // define target word as well as user inputted word
        ArrayList<String> list = new ArrayList<>();
        String target = "valid";
        String userInput = "hello";
        // add target word to word list, user inputted word into character array, and then load the game board
        list.add(target);
        char[] input = userInput.toLowerCase().trim().toCharArray();
        gameBoard = new GameBoard(list);
        gameBoard.startGame();
        // check the status of each character
        GameBoard.Status[] inputArray = gameBoard.isInTarget(input);
        GameBoard.Status[] result = {wrong, wrong, correct, wrong, wrong};
        // for loop to compare the character status between target and input word
        for (int i = 0; i < 5; i++){
            assertEquals(inputArray[i], result[i]);
        }
    }

    @Test
        // Test ID 12
    void charCheck5(){
        // define target word as well as user inputted word
        ArrayList<String> list = new ArrayList<>();
        String target = "hello";
        String userInput = "HELlo";
        // add target word to word list, user inputted word into character array, and then load the game board
        list.add(target);
        char[] input = userInput.toLowerCase().trim().toCharArray();
        gameBoard = new GameBoard(list);
        gameBoard.startGame();
        // check the status of each character
        GameBoard.Status[] inputArray = gameBoard.isInTarget(input);
        GameBoard.Status[] result = {correct, correct, correct, correct, correct};
        // for loop to compare the character status between target and input word
        for (int i = 0; i < 5; i++){
            assertEquals(inputArray[i], result[i]);
        }
    }
}