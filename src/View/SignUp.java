package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.SignUpController;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;

public class SignUp extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField nameTF;
    private JPasswordField passTF;


    public SignUp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 625, 382);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("CREATE AN ACCOUNT");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setBounds(193, 40, 236, 33);
        contentPane.add(lblNewLabel);

        JLabel nameLB = new JLabel("Username");
        nameLB.setFont(new Font("Tahoma", Font.PLAIN, 14));
        nameLB.setBounds(60, 114, 77, 33);
        contentPane.add(nameLB);

        JLabel passLB = new JLabel("Password");
        passLB.setFont(new Font("Tahoma", Font.PLAIN, 14));
        passLB.setBounds(60, 191, 77, 33);
        contentPane.add(passLB);

        nameTF = new JTextField();
        nameTF.setBounds(158, 116, 282, 33);
        contentPane.add(nameTF);
        nameTF.setColumns(10);

        passTF = new JPasswordField();
        passTF.setBounds(158, 193, 282, 33);
        contentPane.add(passTF);
        passTF.setColumns(10);

        JCheckBox showPasswordCB = new JCheckBox("Show Password");
        showPasswordCB.setFont(new Font("Tahoma", Font.PLAIN, 8));
        showPasswordCB.setBounds(158, 232, 150, 25);
        contentPane.add(showPasswordCB);
        showPasswordCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPasswordCB.isSelected()) {
                    passTF.setEchoChar((char) 0); // Hiển thị mật khẩu
                } else {
                    passTF.setEchoChar('*'); // Ẩn mật khẩu
                }
            }
        });

        JButton signUpBT = new JButton("SIGN UP");
        signUpBT.setFont(new Font("Tahoma", Font.BOLD, 14));
        signUpBT.setBounds(212, 265, 101, 33);
        contentPane.add(signUpBT);

        JButton exitBT = new JButton("EXIT");
        exitBT.setFont(new Font("Tahoma", Font.BOLD, 14));
        exitBT.setBounds(494, 265, 85, 33);
        contentPane.add(exitBT);

        signUpBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTF.getText();
                String pass = new String(passTF.getPassword());

                try {
                    SignUpController.handleSignUp(name, pass);
                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        exitBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUpController.handleExit(SignUp.this);
            }
        });

        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
