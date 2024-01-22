package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Hasta extends User {


	public Hasta() {
	}

	public Hasta(int id, String tcno, String password, String name, String type) {
		super(id, tcno, password, name, type);

	}

	public boolean register(String name, String password, String tcno) throws SQLException {

		String query = "INSERT INTO user (tcno,password,name,type) VALUES (?,?,?,?)";
		boolean key = false;
		boolean kontrol = false;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE tcno='" + tcno + "'");
			while (rs.next()) {
				kontrol = true;
				break;
			}
			if (!kontrol) {
				ps = con.prepareStatement(query);
				ps.setString(1, tcno);
				ps.setString(2, password);
				ps.setString(3, name);
				ps.setString(4, "hasta");
				ps.executeUpdate();
			}

			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (key)
			return true;
		else
			return false;
	}

	public boolean addAppoinment(int doctor_id, int hasta_id, String doctor_name, String hasta_name, String app_date)
			throws SQLException {

		String query = "INSERT INTO appoinment (doctor_id,doctor_name,hasta_id,hasta_name,app_date) VALUES (?,?,?,?,?)";
		boolean key = false;
		try {

			ps = con.prepareStatement(query);
			ps.setInt(1, doctor_id);
			ps.setString(2, doctor_name);
			ps.setInt(3, hasta_id);
			ps.setString(4, hasta_name);
			ps.setString(5, app_date);
			ps.executeUpdate();

			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (key)
			return true;
		else
			return false;
	}
	
	
	
	public boolean UpdateWhoreStatus(int doctor_id, String app_date) throws SQLException {

		String query = "UPDATE whour SET status=? WHERE doctor_id=? AND wdate=?";
		boolean key = false;
		try {

			ps = con.prepareStatement(query);
			ps.setString(1, "p");
			ps.setInt(2, doctor_id);
			ps.setString(3, app_date);
			ps.executeUpdate();

			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (key)
			return true;
		else
			return false;
	}

}
