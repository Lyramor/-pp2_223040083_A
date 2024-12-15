package PERTEMUAN7.Tugas.src.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
    private final static String DB_URL = "jdbc:mysql://localhost:3306/pp2_tugas";
    private final static String DB_USER = "root";
    private final static String DB_PASS = ""; 
    private static MySqlConnection instance;
    private Connection connection;

    private MySqlConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database");
        }
    }

    public static MySqlConnection getInstance() {
        if (instance == null) {
            instance = new MySqlConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to reconnect to the database");
        }
        return connection;
    }
    
    public boolean isConnectionClosed() throws SQLException {
        return connection == null || connection.isClosed();
    }
}
