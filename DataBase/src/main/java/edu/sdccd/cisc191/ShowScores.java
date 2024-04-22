package edu.sdccd.cisc191;

/**
 * ShowSCores object with score ad name
 */
public class ShowScores {
    private int score;
    private String name;

    /**
     * default ShowScores obj
     */
    public ShowScores(){
        score =0;
        name = "None";
    }

    /**
     * showscores obj with score and anme
     * @param score
     * @param name
     */
    public ShowScores(int score, String name){
        this.score =score;
        this.name = name;
    }

    /**
     * print the ShowScore to console
     */
    public void printToConsole(){
        System.out.println(name + " scored "+score+" points!");
    }
}
