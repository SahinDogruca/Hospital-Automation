package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Helper.DbConnection;

public class User{
	int id;
	String tcno,password,name,type;
	
	DbConnection conn = new DbConnection();
	Connection con = conn.connDb();
	Statement st;
	ResultSet rs;
	PreparedStatement ps;

	
	
	public User(int id, String tcno, String password, String name, String type) {
		super();
		this.id = id;
		this.tcno = tcno;
		this.password = password;
		this.name = name;
		this.type = type;
	}
	
	public User() {}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTcno() {
		return tcno;
	}
	public void setTcno(String tcno) {
		this.tcno = tcno;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
