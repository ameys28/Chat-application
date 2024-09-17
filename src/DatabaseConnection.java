import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	private Connection con;
	
	public DatabaseConnection(){
		try{
			DriverManager.registerDriver(new org.postgresql.Driver());
			String url = "jdbc:postgresql://autorack.proxy.rlwy.net:23360/railway";
			con = DriverManager.getConnection(url, "postgres" , "udmsKaiKcsPRosdfEoXMcOtQxefunszk");
			System.out.println("Server Established");
		} catch(SQLException e) {
			System.out.println("isues " + e);
		}
	}
		
	public Connection getConnection(){
		return con;
	}
}
