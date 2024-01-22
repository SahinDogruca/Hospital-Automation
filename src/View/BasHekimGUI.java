package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.BasHekim;
import Model.Clinic;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import Helper.*;
import javax.swing.JComboBox;

public class BasHekimGUI extends JFrame {

	Clinic clinic = new Clinic();
	private JPanel contentPane;
	private JTextField txtAd;
	private JTextField txtTC;
	private JTextField txtPass;
	private JTextField txtDoctorID;
	private JTable table_doctor;
	private DefaultTableModel doctorModel = null;
	private Object[] doctorData = null;
	private JTextField txtClinic;
	private JTable table_clinic;
	private DefaultTableModel clinicModel = null;
	private Object[] clinicData = null;
	private JPopupMenu clinicMenu;
	private JTable table_worker;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		BasHekim bashekim = new BasHekim();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BasHekimGUI frame = new BasHekimGUI(bashekim);
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
	public BasHekimGUI(BasHekim bashekim) throws SQLException {
		
		// Doctor Model
		doctorModel = new DefaultTableModel();
		Object[] colDoctor = new Object[4];
		colDoctor[0] = "ID";
		colDoctor[1] = "Ad Soyad";
		colDoctor[2] = "TC no";
		colDoctor[3] = "Þifre";
		doctorModel.setColumnIdentifiers(colDoctor);
		
		doctorData = new Object[4];
		for (int i = 0 ; i < bashekim.getDoctorList().size() ; i++) {
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getName();
			doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}
		
		//clinic Model
		
		clinicModel = new DefaultTableModel();
		Object[] colclinic = new Object[2];
		colclinic[0] = "ID";
		colclinic[1] = "Polikliniklik adý";
		clinicModel.setColumnIdentifiers(colclinic);
		
		clinicData = new Object[4];
		
		for (int i = 0 ; i < clinic.getClinic().size() ; i++) {
			clinicData[0] = clinic.getClinic().get(i).getId();
			clinicData[1] = clinic.getClinic().get(i).getName();
			clinicModel.addRow(clinicData);
		}
		
		// Worker Model
		
		DefaultTableModel workerModel = new DefaultTableModel();
		Object[] colWorker = new Object[2];
		colWorker[0] = "ID";
		colWorker[1] = "Ad Soyad";
		workerModel.setColumnIdentifiers(colWorker);
		Object[] workerData = new Object[2];
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 484);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ho\u015Fgeldiniz Say\u0131n "+ bashekim.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 14, 292, 28);
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
		btnNewButton.setBounds(547, 17, 112, 27);
		contentPane.add(btnNewButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 69, 649, 365);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Doktor Yönetimi", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Kullan\u0131c\u0131 Ad\u0131");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(503, 11, 131, 19);
		panel.add(lblNewLabel_1);
		
		txtAd = new JTextField();
		txtAd.setBounds(503, 41, 131, 20);
		panel.add(txtAd);
		txtAd.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("T.C. no");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(503, 72, 131, 19);
		panel.add(lblNewLabel_1_1);
		
		txtTC = new JTextField();
		txtTC.setColumns(10);
		txtTC.setBounds(503, 102, 131, 20);
		panel.add(txtTC);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("\u015Eifre");
		lblNewLabel_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(503, 133, 131, 19);
		panel.add(lblNewLabel_1_1_1);
		
		txtPass = new JTextField();
		txtPass.setColumns(10);
		txtPass.setBounds(503, 161, 131, 20);
		panel.add(txtPass);
		
		JButton btnEkle = new JButton("ekle");
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtAd.getText().length() ==  0 || txtTC.getText().length() == 0 || txtPass.getText().length() == 0) {
					Helper.showMsg("fill");
				}else {
					try {
						boolean kontrol = bashekim.addDoctor(txtTC.getText(), txtPass.getText(), txtAd.getText());
						if(kontrol) {
							Helper.showMsg("success");
							txtTC.setText(null);
							txtAd.setText(null);
							txtPass.setText(null);
							
							// normalde bunu bir metot içinde yapacaktým ama anlamsýz bir hata verdi
							DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
							clearModel.setRowCount(0);
							
							for (int i = 0 ; i < bashekim.getDoctorList().size() ; i++) {
								doctorData[0] = bashekim.getDoctorList().get(i).getId();
								doctorData[1] = bashekim.getDoctorList().get(i).getName();
								doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
								doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
								doctorModel.addRow(doctorData);
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		btnEkle.setBounds(501, 192, 133, 31);
		panel.add(btnEkle);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Kullanc\u0131 ID");
		lblNewLabel_1_1_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1.setBounds(503, 234, 131, 19);
		panel.add(lblNewLabel_1_1_1_1);
		
		txtDoctorID = new JTextField();
		txtDoctorID.setEditable(false);
		txtDoctorID.setBounds(503, 264, 131, 20);
		panel.add(txtDoctorID);
		txtDoctorID.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Sil");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtDoctorID.getText().length() == 0) {
					Helper.showMsg("Lütfen geçerli bir deðer seçiniz");
				}else {
					if (Helper.confirm("sure")) {
						int selectID = Integer.parseInt(txtDoctorID.getText());
						try {
							boolean kontrol = bashekim.deleteDoctor(selectID);
							if (kontrol) {
								Helper.showMsg("success");
								
								
								// normalde bunu bir metot içinde yapacaktým ama anlamsýz bir hata verdi
								DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
								clearModel.setRowCount(0);
								txtDoctorID.setText(null);
								for (int i = 0 ; i < bashekim.getDoctorList().size() ; i++) {
									doctorData[0] = bashekim.getDoctorList().get(i).getId();
									doctorData[1] = bashekim.getDoctorList().get(i).getName();
									doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
									doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
									doctorModel.addRow(doctorData);
								}
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnNewButton_2.setBounds(503, 295, 131, 31);
		panel.add(btnNewButton_2);
		
		JScrollPane scrollPane_doctor = new JScrollPane();
		scrollPane_doctor.setBounds(20, 11, 459, 315);
		panel.add(scrollPane_doctor);
		
		table_doctor = new JTable(doctorModel);
		scrollPane_doctor.setViewportView(table_doctor);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Poliklinik Yönetimi", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 234, 315);
		panel_1.add(scrollPane);
		
		clinicMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Güncelle");
		JMenuItem deleteMenu = new JMenuItem("Sil");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);
		
		updateMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
				Clinic selectClinic = clinic.getFectch(selID);
				UpdateClinicGUI update = new UpdateClinicGUI(selectClinic);
				update.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				update.setVisible(true);
				update.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e) {
						try {
							updateClinicModel();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
			}
			
		});
		
		deleteMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Helper.confirm("sure")) {
					int selID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
					
						
						try {
							if(clinic.deleteClinic(selID)) {
								Helper.showMsg("success");
							updateClinicModel();
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				}
			
		});
		
		
		table_clinic = new JTable(clinicModel);
		table_clinic.setComponentPopupMenu(clinicMenu); // sað týklayýn güncelle ve sil butonlarýnýn çýkmasýný saðlar
		table_clinic.addMouseListener(new MouseAdapter() {
			 
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = table_clinic.rowAtPoint(point);
				table_clinic.setRowSelectionInterval(selectedRow, selectedRow);
			}
		});
		scrollPane.setViewportView(table_clinic);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(400, 11, 234, 315);
		panel_1.add(scrollPane_1);
		
		table_worker = new JTable();
		scrollPane_1.setViewportView(table_worker);
		
		JLabel lblNewLabel_1_2 = new JLabel("Poliniklik Ad\u0131");
		lblNewLabel_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(259, 11, 131, 19);
		panel_1.add(lblNewLabel_1_2);
		
		txtClinic = new JTextField();
		txtClinic.setColumns(10);
		txtClinic.setBounds(259, 41, 131, 20);
		panel_1.add(txtClinic);
		
		JButton btnClinicEkle = new JButton("ekle");
		btnClinicEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtClinic.getText().length() == 0) {
					Helper.showMsg("fill");
				}else {
					try {
						if (clinic.addClinic(txtClinic.getText())){
							Helper.showMsg("success");
							txtClinic.setText(null);
							updateClinicModel();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnClinicEkle.setBounds(257, 72, 133, 31);
		panel_1.add(btnClinicEkle);
		
		JComboBox selectDoctor = new JComboBox();
		selectDoctor.setBounds(254, 226, 136, 31);
		for (int i = 0 ; i < bashekim.getDoctorList().size() ; i++ ) {
			selectDoctor.addItem(new Item(bashekim.getDoctorList().get(i).getId() , bashekim.getDoctorList().get(i).getName()));
		}
		
		selectDoctor.addActionListener(e -> {
			JComboBox combo = (JComboBox) e.getSource();
			Item item = (Item) combo.getSelectedItem();
		});
		
		panel_1.add(selectDoctor);
		
		JButton btnAdd = new JButton("Ekle");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selRow = table_clinic.getSelectedRow();
				if (selRow >= 0) {
					String SelClinic = table_clinic.getModel().getValueAt(selRow, 0).toString();
					int SelClinicId = Integer.parseInt(SelClinic);
					Item doctorItem = (Item) selectDoctor.getSelectedItem();
					
					try {
						boolean kontrol = bashekim.addWorker(doctorItem.getKey(), SelClinicId);
						if (kontrol) {
							Helper.showMsg("success");
							DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
							clearModel.setRowCount(0);
							for (int i =0; i< bashekim.getClinicDoctorList(SelClinicId).size() ; i++) {
								workerData[0] = bashekim.getClinicDoctorList(SelClinicId).get(i).getId();
								workerData[1] = bashekim.getClinicDoctorList(SelClinicId).get(i).getName();
								workerModel.addRow(workerData);
							}
							table_worker.setModel(workerModel);
							
						}else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					Helper.showMsg("Lütfen bir poliklinik seçiniz");
				}
				
				
			}
		});
		btnAdd.setBounds(254, 279, 136, 31);
		panel_1.add(btnAdd);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Poliniklik Ad\u0131");
		lblNewLabel_1_2_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		lblNewLabel_1_2_1.setBounds(259, 127, 131, 19);
		panel_1.add(lblNewLabel_1_2_1);
		
		JButton btnWorker = new JButton("se\u00E7");
		btnWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_clinic.getSelectedRow();
				if(selRow >= 0) {
					String SelClinic = table_clinic.getModel().getValueAt(selRow, 0).toString();
					int SelClinicId = Integer.parseInt(SelClinic);
					DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
					clearModel.setRowCount(0);
					
					for (int i =0; i< bashekim.getClinicDoctorList(SelClinicId).size() ; i++) {
						workerData[0] = bashekim.getClinicDoctorList(SelClinicId).get(i).getId();
						workerData[1] = bashekim.getClinicDoctorList(SelClinicId).get(i).getName();
						workerModel.addRow(workerData);
					}
					table_worker.setModel(workerModel);
					
				}else {
					Helper.showMsg("Lütfen geçerli bir poliklinik giriniz.");
				}
			}
		});
		btnWorker.setBounds(257, 157, 133, 31);
		panel_1.add(btnWorker);
		
		table_doctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					System.out.println(table_doctor.getValueAt(table_doctor.getSelectedRow(),0).toString());
					txtDoctorID.setText(table_doctor.getValueAt(table_doctor.getSelectedRow(),0).toString());
				}catch(Exception ex) {
					
				}
				
			}
		});
		
		table_doctor.getModel().addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				
				if(e.getType() == TableModelEvent.UPDATE) {
					int selectId = Integer.parseInt(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
					String selectName = table_doctor.getValueAt(table_doctor.getSelectedRow(), 1).toString();
					String selectTcno = table_doctor.getValueAt(table_doctor.getSelectedRow(), 2).toString();
					String selectPass = table_doctor.getValueAt(table_doctor.getSelectedRow(), 3).toString();
					
					try {
						boolean kontrol = bashekim.updateDoctor(selectId, selectTcno,selectPass ,selectName);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
			
		}
	
	
	public void updateClinicModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_clinic.getModel();
		clearModel.setRowCount(0);
		for (int i = 0 ; i < clinic.getClinic().size() ; i++) {
			clinicData[0] = clinic.getClinic().get(i).getId();
			clinicData[1] = clinic.getClinic().get(i).getName();
			clinicModel.addRow(clinicData);
		}
		
	}
}
