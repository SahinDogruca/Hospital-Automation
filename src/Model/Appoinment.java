package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Appoinment extends User{
	private int id,doctorId,hastaId;
	private String doctorName,hastaName,appDate;
	
	
	
	public Appoinment() {
	}
	
	public Appoinment(int id, int doctorId, int hastaId, String doctorName, String hastaName, String appDate) {
		super();
		this.id = id;
		this.doctorId = doctorId;
		this.hastaId = hastaId;
		this.doctorName = doctorName;
		this.hastaName = hastaName;
		this.appDate = appDate;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public int getHastaId() {
		return hastaId;
	}
	public void setHastaId(int hastaId) {
		this.hastaId = hastaId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getHastaName() {
		return hastaName;
	}
	public void setHastaName(String hastaName) {
		this.hastaName = hastaName;
	}
	public String getAppDate() {
		return appDate;
	}
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}
	
	
	public ArrayList<Appoinment> getHastaList(int hasta_id) throws SQLException {
		ArrayList<Appoinment> list = new ArrayList<>();
		Appoinment obj;
		Connection con = conn.connDb();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM appoinment WHERE hasta_id="+hasta_id);

			while (rs.next()) {
				obj = new Appoinment();
				obj.setId(rs.getInt("id"));
				obj.setDoctorId(rs.getInt("doctor_id"));
				obj.setDoctorName(rs.getString("doctor_name"));
				obj.setHastaId(rs.getInt("hasta_id"));
				obj.setHastaName(rs.getString("hasta_name"));
				obj.setAppDate(rs.getString("app_date"));
				
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return list;

	}
	
	
	
	public ArrayList<Appoinment> getDoctorList(int doctor_id) throws SQLException {
		ArrayList<Appoinment> list = new ArrayList<>();
		Appoinment obj;
		Connection con = conn.connDb();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM appoinment WHERE hasta_id="+doctor_id);

			while (rs.next()) {
				obj = new Appoinment();
				obj.setId(rs.getInt("id"));
				obj.setDoctorId(rs.getInt("doctor_id"));
				obj.setDoctorName(rs.getString("doctor_name"));
				obj.setHastaId(rs.getInt("hasta_id"));
				obj.setHastaName(rs.getString("hasta_name"));
				obj.setAppDate(rs.getString("app_date"));
				
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return list;

	}
	
	

}
