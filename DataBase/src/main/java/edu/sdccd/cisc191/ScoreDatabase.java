package edu.sdccd.cisc191;

import org.h2.tools.Server;

import java.sql.*;
import java.util.ArrayList;

/**
 * mkaes database with player name, score, goals, and id
 */
public class ScoreDatabase {
    //database connection
    private static final String JDBC_URL = "jdbc:h2:tcp://localhost:9092/./DBDemo;DB_CLOSE_ON_EXIT=FALSE;";
    private static final String DB_USER = "scores";
    private static final String DB_PASSWORD = "yay";

    private Server server;
    private Connection conn;
    private Statement stmt;

    public ScoreDatabase() throws SQLException {
        //tcp server to be accessed in intellij
        server = Server.createTcpServer("-tcpPort", "9092", "-ifNotExists").start();
        //get JDBC driver
        conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
        //statement
        stmt = conn.createStatement();

    }

    /**
     * creates the table if it doesnt exist
     * ID NAME SCORE GOAL
     * @throws SQLException
     */
    public void createTables() throws SQLException {
        stmt.execute("CREATE TABLE IF NOT EXISTS player(id INT "
                +"PRIMARY KEY AUTO_INCREMENT, name VARCHAR(64)"
            +", score VARCHAR(24), goal VARCHAR(7))");
        //caps dont matter but makes a new player table with
        //id, name, score, goal

    }

    /**
     * adds player to table
     * @param player that will be added
     * @throws SQLException
     */
    public void create(Player player) throws SQLException {
        //add a player with ? name, ? score ?goal
        String sql = "INSERT INTO player(name, score, goal) VALUES(?, ?,?)";
        //genetates player ID
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        //prp statement puts player info in the ?'s
        ps.setString(1, player.getName());
        ps.setInt(2, player.getScore());
        ps.setString(3, player.getGoal());
        //if tehres no rows
        int numRows = ps.executeUpdate();
        if (numRows == 0) {
            throw new SQLException("No rows affected");
        }

        //go thru the set and set the id for the player's field
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
            player.setId(rs.getInt(1));
        }
    }

    /**
     * get the scores from the database
     * @return arraylist with ShowScores objects
     * @throws SQLException
     */
    public ArrayList<ShowScore> getScores() throws SQLException {
        //make new arraylist
        ArrayList<ShowScore> scores = new ArrayList<>();
        ResultSet temp;
        //get the NAME and SCORE of the player in descending order
        String sql = "SELECT name, score FROM player ORDER BY score DESC";
        PreparedStatement ps = conn.prepareStatement(sql);
        //get result set from SELECT
        temp = ps.executeQuery();
        //when there are still names/scores in the resultset, make a ShowScore with that info
        //add to showscores arraylist to be returned
        while (temp.next()) {
            ShowScore s = new ShowScore(temp.getInt("score"),
                    temp.getString("name"));
            scores.add(s);
        }
        return scores;
    }

    /**
     * uses getScores() to print out scores of players in descending order
     * @throws SQLException
     */
    public void printScores() throws SQLException {
        ArrayList<ShowScore> scores = getScores();
        for(ShowScore s : scores){
            s.printToConsole();
        }
    }
}
