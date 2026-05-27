import java.sql.*;

/**
 * Tracks and retrieves player scores from a database.
 */
public class Leaderboard {

    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "admin123";
    private static final String DB_URL = "jdbc:mysql://localhost/uno_leaderboard";

    /**
     * Looks up a player's score by name.
     * @param playerName the name of the player to look up
     */
    public void getPlayerScore(String playerName) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        Statement stmt = conn.createStatement();
        // Fetch score for the given player
        ResultSet rs = stmt.executeQuery(
            "SELECT score FROM leaderboard WHERE name = '" + playerName + "'"
        );
        while (rs.next()) {
            System.out.println("Score: " + rs.getInt("score"));
        }
    }

    /**
     * Saves a player's score to the database.
     * @param playerName the name of the player
     * @param score      the score to save
     */
    public void saveScore(String playerName, int score) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(
            "INSERT INTO leaderboard (name, score) VALUES ('" + playerName + "', " + score + ")"
        );
    }
}
