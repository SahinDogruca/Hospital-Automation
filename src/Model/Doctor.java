package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DbConnection;

public class Doctor extends User{
	
	public Doctor() {
		super();
		
	}
	public Doctor(int id, String tcno, String password, String name, String type) {
		super(id, tcno, password, name, type);
		
	}
	
	
	
public boolean addWHour(int doctor_id,String doctor_name , String whour) throws SQLException {
		
		
		String query = "INSERT INTO whour (doctor_id,doctor_name,wdate) VALUES (?,?,?)";
		boolean key = false;
		int count=0;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM whour WHERE status='a' AND doctor_id="+doctor_id+" AND wdate='"+whour+"'");
			while(rs.next()) {
				count++;
				break;
			}
			if (count==0) {
				ps = con.prepareStatement(query);
				ps.setInt(1, doctor_id);
				ps.setString(2, doctor_name);
				ps.setString(3, whour);
				ps.executeUpdate();	
			}
			
			key = true;
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		if(key)
			return true;
		else
			return false;
	}


	public ArrayList<Whour> getWhourList(int doctor_id){
		ArrayList<Whour> list = new ArrayList<>();
		Whour obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM whour WHERE status = 'a' AND doctor_id="+doctor_id);
			while(rs.next()) {
				obj = new Whour();
				obj.setId(rs.getInt("id"));
				obj.setDoctor_id(rs.getInt("doctor_id"));
				obj.setDoctor_name(rs.getString("doctor_name"));
				obj.setStatus(rs.getString("status"));
				obj.setWdate(rs.getString("wdate"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	
	public boolean deleteWhour(int id) throws SQLException {
		
		
		String query = "DELETE FROM whour WHERE id = ?";
		boolean key = false;
		try {
			st = con.createStatement();
			ps = con.prepareStatement(query);
			ps.setInt(1,id);
			ps.executeUpdate();	
			key = true;
		}catch (Exception e) {
			e.printStackTrace();
		}
			
		if(key)
			return true;
		else
			return false;
	}
	

}
