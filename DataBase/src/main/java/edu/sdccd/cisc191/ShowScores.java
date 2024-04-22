package edu.sdccd.cisc191;

public class ShowScores {
    private int score;
    private String name;

    public ShowScores(){
        score =0;
        name = "None";
    }
    public ShowScores(int score, String name){
        this.score =score;
        this.name = name;
    }

    public void printToConsole(){
        System.out.println(name + " scored "+score+" points!");
    }
}
