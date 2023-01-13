package edu.monash.commandle;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CommandleTest {
    // See https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println and
    // https://stackoverflow.com/questions/1647907/junit-how-to-simulate-system-in-testing
    // for more information on how to test with system input & output
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private List<String> wordList;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    void setUp() {
        wordList = new ArrayList<String>();
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    void tearDown() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    private String getOutput() {
        return testOut.toString();
    }


    @Test
        // Test ID 5
    void wordError1() throws IOException {
        // set up a one-word list for easy testing
        String target = "smile";
        wordList.add(target);

        // provide an incorrect guess which is too short, then a correct guess, and then followed by "N" to signal not wanting to play again
        provideInput("arc" + "\nsmile" + "\nN");

        // simulate the gameplay start
        Commandle.start(System.in, System.out, wordList);

        // get output
        String result = getOutput();

        // verify that the output contains "Please enter a word of 5 letters"
        assertTrue(result.contains("Please enter a word of 5 letters"));

    }

    @Test
        // Test ID 6
    void wordError2() throws IOException {
        // set up a one-word list for easy testing
        String target = "smile";
        wordList.add(target);

        // provide an incorrect guess which is too long,then a correct guess, and then followed by "N" to signal not wanting to play again
        provideInput("arcing" + "\nsmile" + "\nN");

        // simulate the gameplay start
        Commandle.start(System.in, System.out, wordList);

        // get output
        String result = getOutput();

        // verify that the output contains "Please enter a word of 5 letters"
        assertTrue(result.contains("Please enter a word of 5 letters"));

    }

    @Test
        // Test ID 7
    void wordError3() throws IOException {
        // set up a one-word list for easy testing
        String target = "smile";
        wordList.add(target);

        // provide an incorrect guess which isn't a real word,then a correct guess, and then followed by "N" to signal not wanting to play again
        provideInput("fding" + "\nsmile" + "\nN");

        // simulate the gameplay start
        Commandle.start(System.in, System.out, wordList);

        // get output
        String result = getOutput();

        // verify that the output contains "Please enter a valid word"
        assertTrue(result.contains("Please enter a valid word"));

    }

    @Test
        // Test ID 8
    void wordError4() throws IOException {
        // set up a one-word list for easy testing
        String target = "smile";
        wordList.add(target);

        // provide an incorrect guess which has numbers,then a correct guess, and then followed by "N" to signal not wanting to play again
        provideInput("66666" + "\nsmile" + "\nN");

        // simulate the gameplay start
        Commandle.start(System.in, System.out, wordList);

        // get output
        String result = getOutput();

        // verify that the output contains "Please enter a valid word"
        assertTrue(result.contains("Please enter a valid word"));

    }

    @Test
        // Test ID 9
    void wordError5() throws IOException {
        // set up a one-word list for easy testing
        String target = "smile";
        wordList.add(target);

        // provide an incorrect guess which contain a special character,then a correct guess, and then followed by "N" to signal not wanting to play again
        provideInput("d@ing" + "\nsmile" + "\nN");

        // simulate the gameplay start
        Commandle.start(System.in, System.out, wordList);

        // get output
        String result = getOutput();

        // verify that the output contains "Please enter a valid word"
        assertTrue(result.contains("Please enter a valid word"));

    }

    @Test
        // Test ID 10
    void wordError6() throws IOException {
        // set up a one-word list for easy testing
        String target = "smile";
        wordList.add(target);

        // provide an incorrect guess which contains a hyphen,then a correct guess, and then followed by "N" to signal not wanting to play again
        provideInput("a-ing" + "\nsmile" + "\nN");

        // simulate the gameplay start
        Commandle.start(System.in, System.out, wordList);

        // get output
        String result = getOutput();

        // verify that the output contains "Please enter a valid word"
        assertTrue(result.contains("Please enter a valid word"));

    }

    @Test
        // Test ID 11
    void repeat1() throws IOException {
        // set up a two-word list for easy testing
        wordList.add("hello");
        wordList.add("check");
        String result = "";
        boolean repeat = true;
        // loops in case the first guess is correct
        while(repeat == true) {

            // provide the guesses, and then followed by "N" to signal not wanting to play again
            provideInput("check" + "\ncheck" + "\nhello" + "\nhello" + "\nN");

            // simulate the gameplay start
            Commandle.start(System.in, System.out, wordList);

            // get output
            result = getOutput();
            // if statement checking if the outputs contains "Please enter a new word"
            if (result.contains("Please enter a new word") == true) {
                repeat = false;
            }
        }

        // verify that the output contains "Please enter a new word"
        assertTrue(result.contains("Please enter a new word"));
    }


    @Test
        // Test ID 13
    void next1() throws IOException {
        // set up a one-word list for easy testing
        String target = "hello";
        wordList.add(target);

        // provide an incorrect guess, then a correct guess, and then followed by "N" to signal not wanting to play again
        provideInput("help" + "\nhello" + "\nN");

        // simulate the gameplay start
        Commandle.start(System.in, System.out, wordList);

        // get output
        String result = getOutput();

        // verify that the output contains "Please enter your guess"
        assertTrue(result.contains("Please enter your guess"));
    }

    @Test
        // Test ID 14
    void win1() throws IOException {
        // set up a one-word list for easy testing
        String target = "hello";
        wordList.add(target);

        // provide the correct guess, and then followed by "N" to signal not wanting to play again
        provideInput(target + "\nN");

        // simulate the gameplay start
        Commandle.start(System.in, System.out, wordList);

        // get output
        String result = getOutput();

        // verify that the output contains the word "won"
        assertTrue(result.contains("won"));
    }

    @Test
        // Test ID 15
    void lose1() throws IOException {
        String[] args = {};

        // provide 6 incorrect guesses, and then followed by "N" to signal not wanting to play again
        provideInput("check" + "\nsmile" + "\nvalid" + "\nquery" + "\ntimes" + "\nsmart" + "\nN");

        // simulate the gameplay start
        Commandle.start(System.in, System.out, args);

        // get output
        String result = getOutput();

        // verify that the output contains the word "lost"
        assertTrue(result.contains("lost"));
    }

    @Test
        // Test ID 16
    void message1() throws IOException {
        // set up a one-word list for easy testing
        String target = "hello";
        wordList.add(target);

        // provide the correct guess, and then followed by "N" to signal not wanting to play again
        provideInput(target + "\nN");

        // simulate the gameplay start
        Commandle.start(System.in, System.out, wordList);

        // get output
        String result = getOutput();

        // verify that the output contains "1"
        assertTrue(result.contains("1"));

    }

    @Test
    // Test ID 17
    void end1() throws IOException {
        // set up a one-word list for easy testing
        String target = "hello";
        wordList.add(target);

        // provide the correct guess, and then followed by "N" to signal not wanting to play again
        provideInput(target + "\nN");

        // simulate the gameplay start
        Commandle.start(System.in, System.out, wordList);

        // get output
        String result = getOutput();

        // verify that the output contains "Play again"
        assertTrue(result.contains("Play again"));
    }


    @Test
        // Test ID 18
    void end2() throws IOException {
        // set up a one-word list for easy testing
        String target = "hello";
        wordList.add(target);

        // provide the correct guess, then "Y", then the correct guess, and then followed by "N" to signal not wanting to play again
        provideInput(target + "\nY" + target + "\nN");

        // simulate the gameplay start
        Commandle.start(System.in, System.out, wordList);

        // get output
        String result = getOutput();

        // verify that the output contains the word "won"
        // assertion passes as the test completes fully without runtime error
        assertTrue(result.contains("won"));
    }

    @Test
        // Test ID 19
    void end3() throws IOException {
        // set up a one-word list for easy testing
        String target = "hello";
        wordList.add(target);

        // provide the correct guess, and then followed by "N" to signal not wanting to play again
        provideInput(target + "\nN");

        // simulate the gameplay start
        Commandle.start(System.in, System.out, wordList);

        // get output
        String result = getOutput();

        // verify that the output contains "See you next time!"
        assertTrue(result.contains("See you next time!"));
    }

    @Test
        // Test ID 20
        // failed test
    void end4() throws IOException {
        // set up a one-word list for easy testing
        String target = "hello";
        wordList.add(target);

        // provide the correct guess, and then followed by "d"
        provideInput(target + "\nd");

        // simulate the gameplay start
        Commandle.start(System.in, System.out, wordList);

        // get output
        String result = getOutput();

        // verify that the output contains "Invalid input"
        assertTrue(result.contains("Invalid input"));
    }

    @Test
        // Test ID 21
        // failed test
    void checkTarget1() throws IOException {
        // set up a one-word list for easy testing
        String target = "hello";
        wordList.add(target);

        // provide the correct guess, then "Y", then the correct guess, and then followed by "N" to signal not wanting to play again
        provideInput(target + "\nY" + target + "\nN");

        // simulate the gameplay start
        Commandle.start(System.in, System.out, wordList);

        // get output
        String result = getOutput();

        // verify that the output contains the word "hello" twice
        // assertion fails as test runs successfully with target inputted twice without a runtime error
        assertFalse(result.contains(target), "hello");
    }

    private void provideInput(String guess) {
        System.setIn(new ByteArrayInputStream(guess.getBytes()));
    }
}