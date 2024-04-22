package edu.sdccd.cisc191;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * adds a winner, loser, and YOU to player score database
 */
public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        //sql except
        try {
            ScoreDatabase db = new ScoreDatabase();
            db.createTables();
             //makes winner and loser with different scores
            Player winner = new Player("Winner",99999,"Money");
            Player loser = new Player("Loser",0,"Explore");
            //gets user data via console
            Player you = new Player();
            System.out.println("What is your name?");
            you.setName(keyboard.nextLine());
            System.out.println("What is your goal in life?");
            you.setGoal(keyboard.nextLine());
            System.out.println("From 1 to 10000000 how much are you succeeding(lol)");
            you.setScore(keyboard.nextInt());
            System.out.println("Thanks!!");

            //add players to database
            db.create(winner);
            db.create(loser);
            db.create(you);

            //print to console
            db.printScores();


        //keeps server runnign...
            while(true) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
