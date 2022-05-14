package com.example.finallauncherrefactored.Projects.Wordle;

import com.example.finallauncherrefactored.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

class Wordle
{
    int guesses;
    String secretWord;
    String[] playerGuesses;
    Wordle()
    {
        this.guesses = 6;

        try {
            this.secretWord = chooseWord();
        } catch (FileNotFoundException e) {
            System.out.println("Word list file not found.");
        }

        this.playerGuesses = new String[6];
    }
    
    boolean checkWin()
    {
        for (String g : playerGuesses)
        {
            if(g != null && g.equals(secretWord))
            {
                return true;
            }
        }
        return false;
    }
    
    void guessWord() throws FileNotFoundException
    {
        Scanner s = new Scanner(System.in);
        
        System.out.println("Enter a guess");
        
        String guess = s.nextLine();
        
        while (guess.length() != 5 || isInList(guess) == false)
        {
            if (guess.length() != 5)
            {
                System.out.println("5 letters please!");
            }
            if (isInList(guess) == false)
            {
                System.out.println("What is that word?");
            }
            System.out.println("Guess again.");
            
            guess = s.nextLine();
        }
        
        playerGuesses[6 - guesses] = guess;
        guesses--;
    }
    
    void guessWordApp(String guess) throws FileNotFoundException
    {
        if (guess.length() != 5)
        {
            System.out.println("5 letters please!");
        }
        else if (isInList(guess) == false)
        {
            System.out.println("What is that word?");
        }
        else
        {
            playerGuesses[6 - guesses] = guess;
            guesses--;
        }
    }

    static void wordList() throws FileNotFoundException
    {
        int number = 0;
        File f = new File(Main.class.getResource("Wordle/wordlelist.txt").toExternalForm());
        Scanner s = new Scanner(f);

        while(s.hasNextLine())
        {
            String data = s.nextLine();
            number++;
            if (data.length() == 5)
            {
                System.out.println(data);
            }
        }

        System.out.println(number);

        s.close();
    }

    static void typeLetters()
    {
        Scanner s = new Scanner(System.in);

        String keyboard = s.next();

        if (keyboard.length() == 5)
        {
            System.out.println(keyboard);
        }

        s.close();
    }

    static boolean isInList(String word) throws FileNotFoundException
    {
        File f = new File(System.getProperty("user.dir") + "/wordlist.txt");
        Scanner s = new Scanner(f);
        
        while(s.hasNextLine())
        {
            String data = s.next();

            if(data.equals(word.toUpperCase()))
            {
                return true;
            }
        }
        return false;
    }

    static String chooseWord() throws FileNotFoundException
    {
        Random r = new Random();
        int randIndex = r.nextInt(5757);
        int number = 0;

        File f = new File(System.getProperty("user.dir") + "/wordlelist.txt");
        Scanner s = new Scanner(f);

        while(s.hasNextLine())
        {
            String data = s.nextLine();
            number++;
            if (number == randIndex)
            {
                return data.toUpperCase();
            }
        }

        System.out.println(number);

        s.close();

        return null;
    }
    
    void showHints(String lastGuess)
    {
        for (int i = 0; i < 5; i++)
        {
            if (secretWord.charAt(i) == lastGuess.charAt(i))
            {
                System.out.println("The letter " + lastGuess.charAt(i) + 
                " is good, it's in the right spot.");
            }
            else if (secretWord.contains("" + lastGuess.charAt(i))) // right letter, wrong spot
            {
                System.out.println("The letter " + lastGuess.charAt(i) + 
                " is ok, it's in the wrong spot.");
            }
            else
            {
                System.out.println("The letter " + lastGuess.charAt(i) + 
                " is bad, it's not in the word.");
            }
        }
    }
    
    void play() throws FileNotFoundException
    {
        for (int i = 0; i < 6; i++)
        {
            guessWord();
            String lastGuess = playerGuesses[i];
            System.out.println("You guessed " + lastGuess);
            showHints(lastGuess);
            if (secretWord.equals(lastGuess))
            {
                System.out.println("Congratulations, the word is : " + secretWord);
                return;
            }
        }
        if (guesses == 0)
        {
            System.out.println("Game Over, the word is : " + secretWord);
        }
    }
}
