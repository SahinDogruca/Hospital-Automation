package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Helper.Item;
import Model.Appoinment;
import Model.Clinic;
import Model.Hasta;
import Model.Whour;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class HastaGUI extends JFrame {

	private JPanel contentPane;
	private static Hasta hasta = new Hasta();
	private Clinic clinic = new Clinic();
	private JTable table_doctor;
	private DefaultTableModel doctorModel;
	private Object[] doctorData = null;
	private JTable table_whour;
	private Whour whour = new Whour();
	private DefaultTableModel wHourModel;
	private Object[] wHourData = null;
	private int selectDoctorId = 0;
	private String selectDoctorName = null;
	private JTable table_appoint;
	private DefaultTableModel appointModel;
	private Object[] appointData = null;
	private Appoinment appoint = new Appoinment();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HastaGUI frame = new HastaGUI(hasta);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public HastaGUI(Hasta hasta) throws SQLException {
		
		doctorModel = new DefaultTableModel();
		Object[] colDoctor = new Object[2];
		colDoctor[0] = "ID";
		colDoctor[1] = "Ad Soyad";
		doctorModel.setColumnIdentifiers(colDoctor);
		doctorData = new Object[2];
		
		
		wHourModel = new DefaultTableModel();
		Object[] wHourCol = new Object[2];
		wHourCol[0] = "ID";
		wHourCol[1] = "Tarih";
		wHourModel.setColumnIdentifiers(wHourCol);
		wHourData = new Object[2];
		
		
		appointModel = new DefaultTableModel();
		Object[] appointCol = new Object[3];
		appointCol[0] = "ID";
		appointCol[1] = "Doktor Adý";
		appointCol[2] = "Tarih";
		appointModel.setColumnIdentifiers(appointCol);
		appointData = new Object[3];
		
		for(int i = 0; i< appoint.getHastaList(hasta.getId()).size(); i++) {
			appointData[0] = appoint.getHastaList(hasta.getId()).get(i).getId();
			appointData[1] = appoint.getHastaList(hasta.getId()).get(i).getDoctorName();
			appointData[2] = appoint.getHastaList(hasta.getId()).get(i).getAppDate();
			appointModel.addRow(appointData);
		}
		
		
		
		
		
		setTitle("Hasta Y\u00F6netim Sistemi");
		setResizable(false);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 484);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ho\u015Fgeldiniz Say\u0131n "+hasta.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 11, 292, 28);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\u00C7\u0131k\u0131\u015F Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI lgui = new LoginGUI();
				lgui.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		btnNewButton.setBounds(547, 14, 112, 27);
		contentPane.add(btnNewButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 79, 649, 365);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Randevu Sistemi", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 36, 231, 290);
		panel.add(scrollPane_1);
		
		table_doctor = new JTable(doctorModel);
		scrollPane_1.setViewportView(table_doctor);
		
		JLabel lblNewLabel_1 = new JLabel("Doktor Listesi");
		lblNewLabel_1.setBounds(10, 11, 231, 28);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Poliniklik Ad\u0131");
		lblNewLabel_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(251, 37, 131, 19);
		panel.add(lblNewLabel_1_2);
		
		JComboBox select_clinic = new JComboBox();
		select_clinic.setBounds(251, 67, 150, 28);
		select_clinic.addItem("--Poliklinik Seç--");
		for (int i =0; i< clinic.getClinic().size(); i++ ) {
			select_clinic.addItem(new Item(clinic.getClinic().get(i).getId() , clinic.getClinic().get(i).getName()));
		}
		
		select_clinic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (select_clinic.getSelectedIndex() != 0) {
					JComboBox c = (JComboBox) e.getSource();
					Item item = (Item) c.getSelectedItem();
					DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);
						
					for (int i = 0 ; i < clinic.getClinicDoctorList(item.getKey()).size() ; i++) {
						doctorData[0] = clinic.getClinicDoctorList(item.getKey()).get(i).getId();
						doctorData[1] = clinic.getClinicDoctorList(item.getKey()).get(i).getName();
						doctorModel.addRow(doctorData);
					}
				}else {
					DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);
				}
				
			}
			
		});
		
		panel.add(select_clinic);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Doktor Se\u00E7");
		lblNewLabel_1_2_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_1_2_1.setBounds(253, 133, 148, 19);
		panel.add(lblNewLabel_1_2_1);
		
		JButton btnSelect = new JButton("se\u00E7");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_doctor.getSelectedRow();
				System.out.print(row);
				if (row >= 0) {
					String value = table_doctor.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(value);
					DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
					clearModel.setRowCount(0);
					
					for (int i = 0 ; i < whour.getWhourList(id).size() ; i++) {
						wHourData[0] = whour.getWhourList(id).get(i).getId();
						wHourData[1]  = whour.getWhourList(id).get(i).getWdate();
						wHourModel.addRow(wHourData);
					
					}
					
					selectDoctorId = id;
					selectDoctorName = table_doctor.getModel().getValueAt(row, 1).toString();
					
					
				}else {
					Helper.showMsg("Lütfen bir doktor seçiniz!");
				}
			}
		});
		btnSelect.setBounds(251, 163, 150, 31);
		panel.add(btnSelect);
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(413, 36, 221, 290);
		panel.add(scrollPane_1_1);
		
		table_whour = new JTable(wHourModel);
		scrollPane_1_1.setViewportView(table_whour);
		table_whour.getColumnModel().getColumn(0).setPreferredWidth(5);
		
		JLabel lblNewLabel_1_1 = new JLabel("Doktor Listesi");
		lblNewLabel_1_1.setBounds(413, 11, 221, 28);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Randevu");
		lblNewLabel_1_2_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_1_2_1_1.setBounds(255, 226, 148, 19);
		panel.add(lblNewLabel_1_2_1_1);
		
		JButton btnAddAppoint = new JButton("Randevu Al");
		btnAddAppoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_whour.getSelectedRow();
				if (selRow >= 0) {
					String date = table_whour.getModel().getValueAt(selRow, 1).toString();
					
					try {
						boolean kontrol = hasta.addAppoinment(selectDoctorId,hasta.getId(),  selectDoctorName, hasta.getName(), date);
						if(kontrol) {
							Helper.showMsg("success");
							hasta.UpdateWhoreStatus(selectDoctorId, date);
							updateWhourModel(selectDoctorId);
							updateAppointModel(hasta.getId());
						}else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					Helper.showMsg("Lütfen geçerli bir tarih seçiniz");
				}
				
			}
		});
		btnAddAppoint.setBounds(253, 256, 150, 31);
		panel.add(btnAddAppoint);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Randevularým", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 624, 315);
		panel_1.add(scrollPane);
		
		table_appoint = new JTable(appointModel);
		scrollPane.setViewportView(table_appoint);
	}
	
	public void updateWhourModel(int doctor_id) {
		DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);
		for (int i = 0 ; i < whour.getWhourList(doctor_id).size() ; i++) {
			wHourData[0] = whour.getWhourList(doctor_id).get(i).getId();
			wHourData[1]  = whour.getWhourList(doctor_id).get(i).getWdate();
			wHourModel.addRow(wHourData);
		}
		
	}
	
	public void updateAppointModel(int hasta_id) throws SQLException{
		DefaultTableModel clearModel = (DefaultTableModel) table_appoint.getModel();
		clearModel.setRowCount(0);
		for(int i = 0; i< appoint.getHastaList(hasta_id).size(); i++) {
			appointData[0] = appoint.getHastaList(hasta_id).get(i).getId();
			appointData[1] = appoint.getHastaList(hasta_id).get(i).getDoctorName();
			appointData[2] = appoint.getHastaList(hasta_id).get(i).getAppDate();
			appointModel.addRow(appointData);
		}
		
	}
}
