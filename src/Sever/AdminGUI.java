package Sever;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class AdminGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField ipTF;
    private JTextField portTF;
    private static JLabel countUser;
    private static JTextArea textArea;
    private SeverHandler server;
    
    public static void updateMessage(String message) {
    	textArea.append(message + "\n");
    }
    
    public static void updateNumberClient() {
    	int number = Integer.parseInt(countUser.getText());
    	countUser.setText(Integer.toString(number + 1));
    }
    
    public static void decreaseNumberClient() {
    	int number = Integer.parseInt(countUser.getText());
    	countUser.setText(Integer.toString(number - 1));
    }

    public AdminGUI() {
        setBounds(100, 100, 833, 553);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel title = new JLabel("SERVER MANAGEMENT");
        title.setForeground(new Color(30, 144, 255));
        title.setFont(new Font("Tahoma", Font.BOLD, 16));
        title.setBounds(277, 10, 250, 44);
        contentPane.add(title);

        JLabel ipLB = new JLabel("IP ADDRESS");
        ipLB.setFont(new Font("Tahoma", Font.BOLD, 14));
        ipLB.setBounds(41, 80, 113, 13);
        contentPane.add(ipLB);

        ipTF = new JTextField();
        ipTF.setBounds(164, 64, 164, 34);
        contentPane.add(ipTF);
        ipTF.setColumns(10);
        
        try {
        	ipTF.setText(InetAddress.getLocalHost().getHostAddress());
		} catch (Exception e) {
			e.printStackTrace();
		}

        JLabel portLB = new JLabel("PORT");
        portLB.setFont(new Font("Tahoma", Font.BOLD, 14));
        portLB.setBounds(451, 64, 99, 34);
        contentPane.add(portLB);

        portTF = new JTextField("8080");
        portTF.setBounds(540, 64, 164, 29);
        contentPane.add(portTF);
        portTF.setColumns(10);
        

        JLabel statusLB = new JLabel("STATUS");
        statusLB.setFont(new Font("Tahoma", Font.BOLD, 14));
        statusLB.setBounds(41, 120, 99, 29);
        contentPane.add(statusLB);

        JLabel status = new JLabel("OFF");
        status.setForeground(new Color(165, 42, 42));
        status.setFont(new Font("Tahoma", Font.BOLD, 14));
        status.setBounds(164, 117, 99, 34);
        contentPane.add(status);

        JLabel useronlineLB = new JLabel("USER ONLINE");
        useronlineLB.setFont(new Font("Tahoma", Font.BOLD, 14));
        useronlineLB.setBounds(41, 159, 113, 13);
        contentPane.add(useronlineLB);

        countUser = new JLabel("0");
        countUser.setFont(new Font("Tahoma", Font.BOLD, 14));
        countUser.setBounds(164, 161, 45, 13);
        contentPane.add(countUser);

        JLabel logLB = new JLabel("LOG");
        logLB.setFont(new Font("Tahoma", Font.BOLD, 14));
        logLB.setBounds(41, 197, 45, 13);
        contentPane.add(logLB);

        JButton startBT = new JButton("START");
        startBT.setForeground(new Color(30, 144, 255));
        startBT.setFont(new Font("Times New Roman", Font.BOLD, 16));
        startBT.setBounds(442, 144, 108, 34);
        contentPane.add(startBT);

        JButton stopBT = new JButton("STOP");
        stopBT.setForeground(new Color(220, 20, 60));
        stopBT.setFont(new Font("Times New Roman", Font.BOLD, 16));
        stopBT.setBounds(619, 144, 108, 34);
        contentPane.add(stopBT);

        textArea = new JTextArea();
        textArea.setForeground(new Color(0, 255, 0));
        textArea.setBackground(Color.BLACK);
        textArea.setBounds(10, 220, 799, 286);
        textArea.setEditable(false);
        contentPane.add(textArea);

        this.setLocationRelativeTo(null);
        this.setTitle("SERVER MANAGEMENT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        startBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int port = Integer.parseInt(portTF.getText());
                server = new SeverHandler(port);
                AdminGUI.updateMessage("Server started on port: " + port + "\n");
                status.setText("RUNNING...");
                status.setForeground(Color.GREEN);
            }
        });

        stopBT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countUser.setText("0");
                try {
                	server.stop();
                	AdminGUI.updateMessage("STOP SERVER");
                	status.setText("OFF");
                	status.setForeground(Color.RED);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
                
            }
        });
    }
    
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminGUI adminGUI = new AdminGUI();
					adminGUI.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    

	public JTextArea getTextArea() {
		return textArea;
	}

    
}