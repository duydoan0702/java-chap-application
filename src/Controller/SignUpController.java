package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import View.Login;
import View.SignUp;

public class SignUpController {
    
    public static void handleSignUp(String name, String pass) throws ClassNotFoundException, SQLException {
        if (name.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Some fields are empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Connection connection = JDBCConnection.getJDBCConnection();
        
        String sqlInsert = "INSERT INTO user.users(FULLNAME, PASSWORD) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, pass);
        
        preparedStatement.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "SIGN UP SUCCESSFUL", "NOTIFICATION", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void handleExit(SignUp signUpFrame) {
        signUpFrame.dispose();
        new Login();
    }
}
