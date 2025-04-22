package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    
    public static Connection connection;
    
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crypticshelldb?useSSL=false", "root", "admin?99");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // public static void main(String[] args) {
    //     Connection conn = MyConnection.getConnection();
    //     if (conn != null) {
    //         System.out.println("Connection established successfully!");
    //     } else {
    //         System.out.println("Failed to establish connection.");
    //     }
    //     MyConnection.closeConnection();
    //     System.out.println("Connection closed.");
    // }
}
