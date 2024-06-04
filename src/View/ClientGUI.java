package View;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class ClientGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField messageTF;
	private JTextField nameTF;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientGUI frame = new ClientGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ClientGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 798, 581);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 89, 764, 279);
		contentPane.add(textArea);
		
		messageTF = new JTextField();
		messageTF.setBounds(10, 378, 565, 51);
		messageTF.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.add(messageTF);
		messageTF.setColumns(10);
		
		JButton sendBT = new JButton("");
		sendBT.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		sendBT.setBounds(709, 390, 65, 39);
		sendBT.setBorder(new EmptyBorder(0, 0, 0, 0));
		sendBT.setContentAreaFilled(false);
		contentPane.add(sendBT);
		sendBT.setIcon(new javax.swing.ImageIcon(ClientGUI.class.getResource("/Images/send.png")));
		
		JLabel lblClientIP = new JLabel("");
		lblClientIP.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblClientIP.setBounds(27, 24, 41, 40);
		lblClientIP.setIcon(new javax.swing.ImageIcon(ClientGUI.class.getResource("/Images/user.png")));
		this.getContentPane().add(lblClientIP);
		
		JButton leaveBT = new JButton("Leave Chat");
		leaveBT.setForeground(SystemColor.textHighlight);
		leaveBT.setFont(new Font("Tahoma", Font.BOLD, 16));
		leaveBT.setBounds(599, 24, 153, 40);
		leaveBT.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.add(leaveBT);
		
		JLabel iconLB = new JLabel("ICON :");
		iconLB.setFont(new Font("Tahoma", Font.BOLD, 14));
		iconLB.setBounds(10, 454, 98, 32);
		contentPane.add(iconLB);
		
		JButton heartIconBT = new JButton("");
		heartIconBT.setIcon(new ImageIcon(ClientGUI.class.getResource("/Images/heart_eye.png")));
		heartIconBT.setBounds(62, 447, 71, 39);
		heartIconBT.setContentAreaFilled(false);
		heartIconBT.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.add(heartIconBT);
		
		JButton smilebigconBT = new JButton("");
		smilebigconBT.setIcon(new ImageIcon(ClientGUI.class.getResource("/Images/smile_big.png")));
		smilebigconBT.setBounds(143, 447, 71, 39);
		smilebigconBT.setBorder(new EmptyBorder(0, 0, 0, 0));
		smilebigconBT.setContentAreaFilled(false);
		contentPane.add(smilebigconBT);
		
		JButton cryingIconBT = new JButton("");
		cryingIconBT.setIcon(new ImageIcon(ClientGUI.class.getResource("/Images/crying.png")));
		cryingIconBT.setBounds(224, 447, 71, 39);
		cryingIconBT.setBorder(new EmptyBorder(0, 0, 0, 0));
		cryingIconBT.setContentAreaFilled(false);
		contentPane.add(cryingIconBT);
		
		JButton saidIconBT = new JButton("");
		saidIconBT.setIcon(new ImageIcon(ClientGUI.class.getResource("/Images/sad.png")));
		saidIconBT.setBounds(305, 447, 71, 39);
		saidIconBT.setBorder(new EmptyBorder(0, 0, 0, 0));
		saidIconBT.setContentAreaFilled(false);
		contentPane.add(saidIconBT);
		
		JButton scaredIconBT = new JButton("");
		scaredIconBT.setIcon(new ImageIcon(ClientGUI.class.getResource("/Images/scared.png")));
		scaredIconBT.setBounds(386, 447, 71, 39);
		scaredIconBT.setBorder(new EmptyBorder(0, 0, 0, 0));
		scaredIconBT.setContentAreaFilled(false);
		contentPane.add(scaredIconBT);
		
		JButton smilecryIconBT = new JButton("");
		smilecryIconBT.setIcon(new ImageIcon(ClientGUI.class.getResource("/Images/smile_cry.png")));
		smilecryIconBT.setBounds(467, 447, 71, 39);
		smilecryIconBT.setBorder(new EmptyBorder(0, 0, 0, 0));
		smilecryIconBT.setContentAreaFilled(false);
		contentPane.add(smilecryIconBT);
		
		JButton smileIconBT = new JButton("");
		smileIconBT.setIcon(new ImageIcon(ClientGUI.class.getResource("/Images/smile.png")));
		smileIconBT.setBounds(548, 447, 71, 39);
		smileIconBT.setBorder(new EmptyBorder(0, 0, 0, 0));
		smileIconBT.setContentAreaFilled(false);
		contentPane.add(smileIconBT);
		
		JButton likeIconBT = new JButton("");
		likeIconBT.setIcon(new ImageIcon(ClientGUI.class.getResource("/Images/like.png")));
		likeIconBT.setBounds(639, 390, 85, 36);
		likeIconBT.setBorder(new EmptyBorder(0, 0, 0, 0));
		likeIconBT.setContentAreaFilled(false);
		contentPane.add(likeIconBT);
		
		JButton pathBT = new JButton("");
		pathBT.setIcon(new ImageIcon(ClientGUI.class.getResource("/Images/attachment.png")));
		pathBT.setBounds(584, 393, 65, 36);
		pathBT.setBorder(new EmptyBorder(0, 0, 0, 0));
		pathBT.setContentAreaFilled(false);
		contentPane.add(pathBT);
		
		nameTF = new JTextField();
		nameTF.setBounds(76, 24, 138, 39);
		nameTF.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.add(nameTF);
		nameTF.setColumns(10);
		
	}

	public String getMessageTF() {
		return messageTF.getText();
	}

	public void setMessageTF(JTextField messageTF) {
		this.messageTF = messageTF;
	}
}
