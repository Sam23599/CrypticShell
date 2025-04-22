package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.MyConnection;
import model.User;

public class UserDAO {

    public static boolean isUserExists(String email) throws SQLException{
        Connection connection = MyConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT email FROM users WHERE email = ?");

        ps.setString(1, email);        
        ResultSet rls = ps.executeQuery();
        
        return rls.next();
    }

    public static int saveUser(User user) throws SQLException{
        Connection connection = MyConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("INSERT INTO users (id, name, email) VALUES (default, ?, ?)");

        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());

        return ps.executeUpdate();
    }
}
