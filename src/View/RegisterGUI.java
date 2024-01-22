package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Hasta;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtTC;
	private JPasswordField txtPassword;
	private Hasta hasta = new Hasta();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterGUI() {
		setResizable(false);
		setTitle("Hastane Y\u00F6netim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 276, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Kullan\u0131c\u0131 Ad\u0131");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 11, 250, 19);
		contentPane.add(lblNewLabel_1);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(10, 41, 239, 20);
		contentPane.add(txtName);
		
		JLabel lblNewLabel_1_1 = new JLabel("TC no");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 84, 250, 19);
		contentPane.add(lblNewLabel_1_1);
		
		txtTC = new JTextField();
		txtTC.setColumns(10);
		txtTC.setBounds(10, 114, 239, 20);
		contentPane.add(txtTC);
		
		JLabel lblNewLabel_1_2 = new JLabel("\u015Eifre");
		lblNewLabel_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(10, 157, 250, 19);
		contentPane.add(lblNewLabel_1_2);
		
		JButton btnRegister = new JButton("Kay\u0131t Ol");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtName.getText().length() == 0 || txtPassword.getText().length() == 0 || txtTC.getText().length() == 0) {
					Helper.showMsg("fill");
					
				}else {
					try {
						boolean kontrol = hasta.register(txtName.getText(), txtPassword.getText(), txtTC.getText());
						if (kontrol) {
							Helper.showMsg("success");
							LoginGUI gui = new LoginGUI();
							gui.setVisible(true);
							dispose();
						}else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		btnRegister.setBounds(10, 231, 239, 29);
		contentPane.add(btnRegister);
		
		JButton btnBack = new JButton("Geri D\u00F6n");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI gui = new LoginGUI();
				gui.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(10, 271, 239, 29);
		contentPane.add(btnBack);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(10, 184, 239, 20);
		contentPane.add(txtPassword);
	}
}
