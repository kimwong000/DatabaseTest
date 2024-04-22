package edu.sdccd.cisc191;


/**
 * player with a name, score, goal and personal id
 */
public class Player {

    private String name;
    private int score;
    private String goal;
    private int id;

    /**
     * makes default player
     */
    public Player(){
        name= "Nothing";
        score = 0;
        goal = "Nothing";
    }

    /**
     * player with name ,score and goal
     * @param name of player
     * @param score
     * @param goal
     */
    public Player(String name, int score, String goal) {
        this.name = name;
        this.score = score;
        this.goal = goal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public int getId() {
        return id;
    }

    /**
     * for the database
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
}
