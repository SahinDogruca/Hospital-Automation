package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BasHekim extends User{
	
	
	public BasHekim(int id, String tcno, String username, String name, String type) {
		super(id, tcno, username, name, type);
	}
	
	public BasHekim() {}
	
	
	public ArrayList<User> getDoctorList(){
		ArrayList<User> list = new ArrayList<>();
		User obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE type = 'doktor'");
			while(rs.next()) {
				obj = new User(rs.getInt("id"),rs.getString("tcno"),rs.getString("password"),rs.getString("name"),rs.getString("type"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	
	public ArrayList<User> getClinicDoctorList(int clinic_id){
		ArrayList<User> list = new ArrayList<>();
		User obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT u.id,u.tcno,u.name,u.type,u.password FROM worker w LEFT JOIN user u ON w.user_id = u.id WHERE clinic_id="+clinic_id);
			while(rs.next()) {
				obj = new User(rs.getInt("u.id"),rs.getString("u.tcno"),rs.getString("u.password"),rs.getString("u.name"),rs.getString("u.type"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	public boolean addDoctor(String tcno,String Password,String Name) throws SQLException {
		
		
		String query = "INSERT INTO user (tcno,password,name,type) VALUES (?,?,?,?)";
		boolean key = false;
		try {
			st = con.createStatement();
			ps = con.prepareStatement(query);
			ps.setString(1,tcno);
			ps.setString(2, Password);
			ps.setString(3, Name);
			ps.setString(4, "doktor");
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
	
	
	
	public boolean deleteDoctor(int id) throws SQLException {
		
		
		String query = "DELETE FROM user WHERE id = ?";
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
	
	
	public boolean updateDoctor(int id,String tcno,String Password,String Name) throws SQLException {
		
		
		String query = "UPDATE user SET name=?,tcno=?,password=? WHERE id=?";
		boolean key = false;
		try {
			st = con.createStatement();
			ps = con.prepareStatement(query);
			ps.setString(1,Name);
			ps.setString(2,tcno);
			ps.setString(3,Password);
			ps.setInt(4,id);
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
	
public boolean addWorker(int user_id,int clinic_id) throws SQLException {
		
		
		String query = "INSERT INTO worker (user_id,clinic_id) VALUES (?,?)";
		boolean key = false;
		int count = 0;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM worker WHERE user_id="+user_id+" AND clinic_id="+clinic_id);
			while(rs.next()) {
				count++;
			}
			if(count==0) {
				ps = con.prepareStatement(query);
				ps.setInt(1,user_id);
				ps.setInt(2, clinic_id);
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

}
