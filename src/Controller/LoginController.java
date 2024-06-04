package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import Sever.AdminGUI;
import Tags.Encode;
import Tags.Tags;
import View.Login;
import View.MainGUI;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController {
    private static int portServer = 8080;
    private static int portClient = 8080;

    public static void handleLogin(String username, String password, Login loginFrame) throws ClassNotFoundException {
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Some Fields Are Empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Socket socketClient = null;
        try {
            connection = JDBCConnection.getJDBCConnection();
            String sql = "SELECT * FROM user.users WHERE FULLNAME = ? AND PASSWORD = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                JOptionPane.showMessageDialog(null, "Login Successfully");
                String ipTF = InetAddress.getLocalHost().getHostAddress();
                InetAddress ipServer = InetAddress.getByName(ipTF);
                socketClient = new Socket(ipServer, portServer);

                String message = Encode.getCreateAccount(username, password);
                ObjectOutputStream output = new ObjectOutputStream(socketClient.getOutputStream());
                output.writeObject(message);
                output.flush();

                ObjectInputStream input = new ObjectInputStream(socketClient.getInputStream());
                message = (String) input.readObject();

                if (message.equals(Tags.SESSION_DENY_TAG)) {
                    JOptionPane.showMessageDialog(loginFrame, "TURN ON SERVER BEFORE START");
                    return;
                }
                new MainGUI(portClient, username, message);
                loginFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SocketException se) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, "Connection reset: " + se.getMessage(), se);
            JOptionPane.showMessageDialog(loginFrame, "Connection to server was reset. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (socketClient != null) {
                try {
                    socketClient.close();
                } catch (Exception ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
