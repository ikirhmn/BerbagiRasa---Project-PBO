import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/db_berbagi_rasa";  
    private static final String USER = "root";  
    private static final String PASSWORD = "";  

  
    public static Connection getConnection() throws SQLException {
        try {
           
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error connecting to the database");
        }
    }
}
