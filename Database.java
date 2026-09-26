import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:postgresql://localhost:5432/shooter_game";
    private static final String USER = "postgres";
    private static final String PASSWORD = "8911";

    public static void main(String[] args){
        try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD)){
            String sql = "SELECT * FROM players";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int score = resultSet.getInt("score");
                int coins = resultSet.getInt("coins");

                System.out.println(id + " | " + name + " | " + score + " | " + coins);
            }
        }
        catch (SQLException e){
            System.out.println("Error:" + e.getMessage());
        }

    }

}
