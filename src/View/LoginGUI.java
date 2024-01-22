package View;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import Helper.*;
import Model.BasHekim;
import Model.Doctor;
import Model.Hasta;

public class LoginGUI extends JFrame {

	private JPanel w_login;
	private JTextField txtTC;
	private JTextField txtDoctorTC;
	private JPasswordField txtDoctorPassword;
	private DbConnection conn = new DbConnection();
	private JPasswordField txtHastaPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setTitle("Hastane Otomasyonu");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 466);
		w_login = new JPanel();
		w_login.setBackground(Color.WHITE);
		w_login.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_login);
		w_login.setLayout(null);
		
		JLabel lblLogo = new JLabel(new ImageIcon(getClass().getResource("logo2.png")));
		lblLogo.setBounds(116, 11, 253, 179);
		w_login.add(lblLogo);
		
		JLabel lblNewLabel = new JLabel("Hastane Y\u00F6netim Sistemine Ho\u015Fgeldiniz");
		lblNewLabel.setBounds(13, 187, 458, 34);
		lblNewLabel.setFont(new Font("Victor Mono SemiBold", Font.PLAIN, 22));
		w_login.add(lblNewLabel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(23, 232, 431, 179);
		w_login.add(tabbedPane);
		
		JPanel w_Hasta = new JPanel();
		w_Hasta.setBackground(Color.WHITE);
		tabbedPane.addTab("Hasta Giriþi", null, w_Hasta, null);
		w_Hasta.setLayout(null);
		
		JLabel lblTcKimlik = new JLabel("T.C. Kimlik :");
		lblTcKimlik.setFont(new Font("Victor Mono SemiBold", Font.PLAIN, 22));
		lblTcKimlik.setBounds(10, 23, 174, 34);
		w_Hasta.add(lblTcKimlik);
		
		JLabel lblifre = new JLabel("\u015Eifre :");
		lblifre.setFont(new Font("Victor Mono SemiBold", Font.PLAIN, 22));
		lblifre.setBounds(10, 57, 174, 34);
		w_Hasta.add(lblifre);
		
		txtTC = new JTextField();
		txtTC.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		txtTC.setBounds(201, 23, 181, 27);
		w_Hasta.add(txtTC);
		txtTC.setColumns(10);
		
		JButton btnRegister = new JButton("Kay\u0131t Ol");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI gui = new RegisterGUI();
				gui.setVisible(true);
				dispose();
			}
		});
		btnRegister.setFont(new Font("Fira Code Medium", Font.PLAIN, 15));
		btnRegister.setBounds(38, 102, 124, 38);
		w_Hasta.add(btnRegister);
		
		JButton btnHastaLogin = new JButton("Giri\u015F Yap");
		btnHastaLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txtTC.getText().length() == 0 || txtHastaPassword.getText().length() == 0) {
					Helper.showMsg("fill");
				}else {
					boolean key = true;
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM User");
						while(rs.next()) {
							if(txtTC.getText().equals(rs.getString("tcno")) ||  txtHastaPassword.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("hasta")) {
									Hasta hasta = new Hasta();
									hasta.setPassword("password");
									hasta.setTcno(rs.getString("tcno"));
									hasta.setName(rs.getString("name"));
									hasta.setType(rs.getString("type"));
									HastaGUI bhgui = new HastaGUI(hasta);
									bhgui.setVisible(true);
									dispose();
									key = false;
								}
								
							}
						}
					}catch(Exception e1) {
						e1.printStackTrace();
					}
					
					if(key) {
						Helper.showMsg("böyle bir kullanýcý yok");
					}
					
				}
					
				
			}
		});
		btnHastaLogin.setFont(new Font("Fira Code Medium", Font.PLAIN, 15));
		btnHastaLogin.setBounds(224, 102, 124, 38);
		w_Hasta.add(btnHastaLogin);
		
		txtHastaPassword = new JPasswordField();
		txtHastaPassword.setBounds(201, 62, 181, 30);
		w_Hasta.add(txtHastaPassword);
		
		JPanel w_Doctor = new JPanel();
		w_Doctor.setBackground(Color.WHITE);
		tabbedPane.addTab("Doctor Giriþi", null, w_Doctor, null);
		w_Doctor.setLayout(null);
		
		JLabel lblTcKimlik_1 = new JLabel("T.C. Kimlik :");
		lblTcKimlik_1.setFont(new Font("Victor Mono SemiBold", Font.PLAIN, 22));
		lblTcKimlik_1.setBounds(28, 11, 174, 34);
		w_Doctor.add(lblTcKimlik_1);
		
		txtDoctorTC = new JTextField();
		txtDoctorTC.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		txtDoctorTC.setColumns(10);
		txtDoctorTC.setBounds(219, 11, 181, 27);
		w_Doctor.add(txtDoctorTC);
		
		JLabel lblifre_1 = new JLabel("\u015Eifre :");
		lblifre_1.setFont(new Font("Victor Mono SemiBold", Font.PLAIN, 22));
		lblifre_1.setBounds(28, 45, 174, 34);
		w_Doctor.add(lblifre_1);
		
		JButton btnDoctorLogin = new JButton("Giri\u015F Yap");
		btnDoctorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtDoctorTC.getText().length() == 0 || txtDoctorPassword.getText().length() == 0 ) {
					Helper.showMsg("fill");
				}else {
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM User");
						while(rs.next()) {
							if(txtDoctorTC.getText().equals(rs.getString("tcno")) ||  txtDoctorPassword.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("bashekim")) {
									BasHekim bHekim = new BasHekim();
									bHekim.setPassword("password");
									bHekim.setTcno(rs.getString("tcno"));
									bHekim.setName(rs.getString("name"));
									bHekim.setType(rs.getString("type"));
									BasHekimGUI bhgui = new BasHekimGUI(bHekim);
									bhgui.setVisible(true);
									dispose();
								}
								else if (rs.getString("type").equals("doktor")) {
									Doctor doctor = new Doctor();
									doctor.setId(rs.getInt("id"));
									doctor.setPassword("password");
									doctor.setTcno(rs.getString("tcno"));
									doctor.setName(rs.getString("name"));
									doctor.setType(rs.getString("type"));
									DoctorGUI dGUI = new DoctorGUI(doctor);
									dGUI.setVisible(true);
									dispose();
								}
								
							}
						}
					}catch(Exception e1) {
						e1.printStackTrace();
					}
					
				}
				
			}
		});
		btnDoctorLogin.setFont(new Font("Fira Code Medium", Font.PLAIN, 15));
		btnDoctorLogin.setBounds(55, 90, 311, 38);
		w_Doctor.add(btnDoctorLogin);
		
		txtDoctorPassword = new JPasswordField();
		txtDoctorPassword.setBounds(219, 49, 181, 30);
		w_Doctor.add(txtDoctorPassword);
	}
}
