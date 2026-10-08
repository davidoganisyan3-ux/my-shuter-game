import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RepositoryPlayer {
    private static final String URL = "jdbc:postgresql://localhost:5432/shooter_game";
    private static final String USER = "postgres";
    private static final String PASSWORD = "8911";

    public void saveScore(String name,int score,int coins) {

        String sql = "INSERT INTO players(name,score,coins) VALUES(?,?,?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, name);
                statement.setInt(2, score);
                statement.setInt(3, coins);

                statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }

    }

    public List<String> returnBestPlayers(int limit) {
        List<String> playersTop = new ArrayList<>();

        String sqlIS = "SELECT name,score FROM players ORDER BY score DESC LIMIT ?";

        try (
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement statementIS = connection.prepareStatement(sqlIS);
        ) {

            statementIS.setInt(1, limit);

            ResultSet resultSet = statementIS.executeQuery();

            while(resultSet.next()){
                String name = resultSet.getString("name");
                int score = resultSet.getInt("score");
                playersTop.add(name + "-" + score);
            }
        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }
        return playersTop;
    }
}
