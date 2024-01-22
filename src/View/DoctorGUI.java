package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Doctor;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import com.toedter.calendar.JDateChooser;

import Helper.Helper;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class DoctorGUI extends JFrame {

	private JPanel contentPane;
	private static Doctor doctor = new Doctor();
	private JTable table_whour;
	private DefaultTableModel WhourModel;
	private Object[] whourData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorGUI frame = new DoctorGUI(doctor);
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
	public DoctorGUI(Doctor doctor) {
		// Whour Model
		WhourModel = new DefaultTableModel();
		Object[] colWhour = new Object[2];
		colWhour[0] = "ID";
		colWhour[1] = "Tarih";
		WhourModel.setColumnIdentifiers(colWhour);
		whourData = new Object[2];
		for (int i = 0 ; i< doctor.getWhourList(doctor.getId()).size(); i++) {
			whourData[0] = doctor.getWhourList(doctor.getId()).get(i).getId();
			whourData[1] = doctor.getWhourList(doctor.getId()).get(i).getWdate();
			WhourModel.addRow(whourData);
		}
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ho\u015Fgeldiniz Say\u0131n "+doctor.getName());
		lblNewLabel.setBounds(10, 11, 292, 28);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\u00C7\u0131k\u0131\u015F Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI lgui = new LoginGUI();
				lgui.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(547, 14, 112, 27);
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		contentPane.add(btnNewButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 82, 649, 332);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Çalýþma Saatleri", null, panel, null);
		panel.setLayout(null);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(28, 11, 116, 20);
		panel.add(dateChooser);
		
		JComboBox selectTime = new JComboBox();
		selectTime.setModel(new DefaultComboBoxModel(new String[] {"10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30"}));
		selectTime.setBounds(154, 11, 82, 22);
		panel.add(selectTime);
		
		JButton btnEkle = new JButton("Ekle");
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date="";
				try {
					date = sdf.format(dateChooser.getDate());
				}catch(Exception er) {
					
				}
				
				if(date.length() == 0) {
					Helper.showMsg("Lütfen bir tarih giriniz");
				}else {
					
					String time = " " + selectTime.getSelectedItem().toString()+ ":00";
					String datetime = date +time;
					try {
						boolean kontrol = doctor.addWHour(doctor.getId(), doctor.getName(), datetime);
						if(kontrol) {
							
							Helper.showMsg("success");
							updateWhourModel(doctor);
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
		btnEkle.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnEkle.setBounds(253, 11, 112, 20);
		panel.add(btnEkle);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 44, 644, 260);
		panel.add(scrollPane);
		
		table_whour = new JTable(WhourModel);
		scrollPane.setViewportView(table_whour);
		
		JButton btnSil = new JButton("Sil");
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_whour.getSelectedRow();
				if (selRow >= 0) {
					String sel = table_whour.getModel().getValueAt(selRow, 0).toString();
					int selId = Integer.parseInt(sel);
					try {
						boolean kontrol = doctor.deleteWhour(selId);
						if (kontrol) {
							Helper.showMsg("success");
							updateWhourModel(doctor);
						}else {
							Helper.showMsg("Lütfen geçerli bir tarih giriniz");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					Helper.showMsg("error");
				}
			}
		});
		btnSil.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnSil.setBounds(509, 11, 112, 20);
		panel.add(btnSil);
	}
	
	public void updateWhourModel(Doctor doctor) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);
		for (int i = 0 ; i< doctor.getWhourList(doctor.getId()).size(); i++) {
			whourData[0] = doctor.getWhourList(doctor.getId()).get(i).getId();
			whourData[1] = doctor.getWhourList(doctor.getId()).get(i).getWdate();
			WhourModel.addRow(whourData);
		}
		
	}
}
