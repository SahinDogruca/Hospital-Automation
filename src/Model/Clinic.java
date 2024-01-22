package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DbConnection;

public class Clinic extends User{
	private int id;
	private String name;



	public Clinic() {
	}

	public Clinic(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Clinic> getClinic() throws SQLException {
		ArrayList<Clinic> list = new ArrayList<>();
		Clinic obj;
		Connection con = conn.connDb();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM clinic");

			while (rs.next()) {
				obj = new Clinic();
				obj.setId(rs.getInt("id"));
				obj.setName(rs.getString("name"));
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return list;

	}
	
	
	public boolean addClinic(String name) throws SQLException {
		
		
		String query = "INSERT INTO clinic (name) VALUES (?)";
		boolean key = false;
		Connection con = conn.connDb();
		try {
			st = con.createStatement();
			ps = con.prepareStatement(query);
			ps.setString(1,name);
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


	public boolean deleteClinic(int id) throws SQLException {
	
	
		String query = "DELETE FROM clinic WHERE id = ?";
		boolean key = false;
		Connection con = conn.connDb();
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
	
	public Clinic getFectch(int id) {
		Connection con = conn.connDb();
		Clinic c = new Clinic();
		
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM clinic WHERE id="+id);
			while(rs.next()) {
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	}
	
	
	public boolean updateClinic(int id,String Name) throws SQLException {
		
		
		String query = "UPDATE clinic SET name=? WHERE id=?";
		boolean key = false;
		Connection con = conn.connDb();
		try {
			st = con.createStatement();
			ps = con.prepareStatement(query);
			ps.setString(1,Name);
			ps.setInt(2,id);
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
	
	public ArrayList<User> getClinicDoctorList(int clinic_id){
		ArrayList<User> list = new ArrayList<>();
		User obj;
		Connection con = conn.connDb();
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
	

}
