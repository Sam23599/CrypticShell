package service;

import java.sql.SQLException;

import dao.UserDAO;
import model.User;

public class UserService {
    public static Integer saveUser(User user){
        try {
            if(UserDAO.isUserExists(user.getEmail())){
                return 0;
            }else{
                return UserDAO.saveUser(user);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
