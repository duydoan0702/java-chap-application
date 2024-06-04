package View;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controller.LoginController;
import Sever.AdminGUI;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static JTextField usernameTF;
    private static JPasswordField passTF;

    private AdminGUI adminGUI;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Login login = new Login();
                login.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 612, 417);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel loginLB = new JLabel("WELCOME MEMBER LOGIN");
        loginLB.setFont(new Font("Tahoma", Font.BOLD, 16));
        loginLB.setBounds(185, 49, 236, 30);
        contentPane.add(loginLB);

        JLabel nameLB = new JLabel("Username");
        nameLB.setFont(new Font("Tahoma", Font.PLAIN, 14));
        nameLB.setBounds(57, 131, 81, 30);
        contentPane.add(nameLB);

        JLabel passLB = new JLabel("Password");
        passLB.setFont(new Font("Tahoma", Font.PLAIN, 14));
        passLB.setBounds(57, 193, 81, 30);
        contentPane.add(passLB);

        JButton signinBT = new JButton("SIGN IN");
        signinBT.setFont(new Font("Tahoma", Font.BOLD, 14));
        signinBT.setBounds(199, 269, 97, 30);
        contentPane.add(signinBT);    

        JButton signupBT = new JButton("SIGN UP");
        signupBT.setFont(new Font("Tahoma", Font.BOLD, 14));
        signupBT.setBounds(454, 269, 97, 30);
        contentPane.add(signupBT);   
        
        usernameTF = new JTextField();
        usernameTF.setBounds(185, 134, 261, 30);
        contentPane.add(usernameTF);
        usernameTF.setColumns(10);

        passTF = new JPasswordField();
        passTF.setBounds(185, 196, 261, 30);
        contentPane.add(passTF);
        passTF.setColumns(10);
        
        JCheckBox showPasswordCB = new JCheckBox("Show Password");
        showPasswordCB.setFont(new Font("Tahoma", Font.PLAIN, 8));
        showPasswordCB.setBounds(185, 232, 150, 25);
        contentPane.add(showPasswordCB);
        showPasswordCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPasswordCB.isSelected()) {
                    passTF.setEchoChar((char) 0); 
                } else {
                    passTF.setEchoChar('*');
                }
            }
        });

        this.setLocationRelativeTo(null);
        this.setTitle("LOGIN");
              
        signinBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTF.getText();
                String password = new String(passTF.getPassword());            
                try {
                    LoginController.handleLogin(username, password, Login.this);
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
        
        signupBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SignUp();
                setVisible(false);
            }
        });
    }

    public String getName() {
        return usernameTF.getText();
    }
    
    public static String getUsernameTF() {
        return usernameTF.getText();
    }

    public static String getPassTF() {
        return new String(passTF.getPassword());
    }
}
