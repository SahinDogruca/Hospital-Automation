package Helper;
import java.sql.*;

public class DbConnection {
	Connection c;
	
	public DbConnection() {}
	
	public Connection connDb() {
		try {
			this.c = DriverManager.getConnection("jdbc:mariadb://localhost:3315/hospital?user=root&password=12345");
			return c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	}

}
