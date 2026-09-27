import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RepositoryPlayer {
    private static final String URL = "jdbc:postgresql://localhost:5432/shooter_game";
    private static final String USER = "postgres";
    private static final String PASSWORD = "8911";

    public void saveScore(String name,int score,int coins){

        String sql = "INSERT INTO players(name,score,coins) VALUES(?,?,?)";

        try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
        PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1,name);
            statement.setInt(2,score);
            statement.setInt(3,coins);

            statement.executeUpdate();

        }catch(SQLException e){
            System.out.println("Error:" + e.getMessage());
        }

    }
}
