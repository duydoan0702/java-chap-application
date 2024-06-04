package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Client.User;
import Controller.MainGUIController;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField chatTF;
    private static int portClient = 0;
    private static String name = "", message = "";
    private User userNode;

    public MainGUI(int portClient, String name, String message) {
        this.portClient = portClient;
        this.name = name;
        this.message = message;
        initialize();
    }

    private void initialize() {
        setBounds(100, 100, 698, 453);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel chatLB = new JLabel("Chats");
        chatLB.setFont(new Font("Tahoma", Font.BOLD, 20));
        chatLB.setBounds(35, 8, 76, 29);
        contentPane.add(chatLB);

        chatTF = new JTextField();
        chatTF.setBounds(35, 49, 440, 38);
        chatTF.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentPane.add(chatTF);
        chatTF.setColumns(10);

        JButton chatBT = new JButton("Chat");
        chatBT.setFont(new Font("Tahoma", Font.BOLD, 14));
        chatBT.setBounds(485, 49, 85, 38);
        chatBT.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentPane.add(chatBT);

        chatBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameUser = chatTF.getText();
                if (nameUser.equals("") || User.clientArray == null) {
                    JOptionPane.showMessageDialog(MainGUI.this, "Invalid username", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int size = User.clientArray.size();
                for (int i = 0; i < size; i++) {
                    if (nameUser.equals(User.clientArray.get(i).getName())) {
                        try {
                            userNode.intialNewChat(User.clientArray.get(i).getHostClient(), User.clientArray.get(i).getPortClient(), nameUser);
                            return;
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                JOptionPane.showConfirmDialog(MainGUI.this, "Friend is not found. Please wait to update your friend list", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JList list = new JList();
        list.setBounds(10, 124, 664, 282);
        contentPane.add(list);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String value = (String) list.getModel().getElementAt(list.locationToIndex(e.getPoint()));
                chatTF.setText(value);
            }
        });

        JButton exitBT = new JButton("exit");
        exitBT.setForeground(new Color(165, 42, 42));
        exitBT.setFont(new Font("Tahoma", Font.BOLD, 14));
        exitBT.setBounds(575, 10, 99, 29);
        exitBT.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentPane.add(exitBT);

        JLabel titleLB = new JLabel("List account active now");
        titleLB.setForeground(SystemColor.textHighlight);
        titleLB.setFont(new Font("Tahoma", Font.ITALIC, 14));
        titleLB.setBounds(37, 101, 181, 13);
        contentPane.add(titleLB);

        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        exitBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUIController.handleExit(MainGUI.this);
            }
        });
    }
}

