import java.sql.*;
public class DatabaseConnection {
	public DatabaseConnection(){
		try{
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			String url = "jdbc:mysql://localhost:3306/chat_app";
			Connection con = DriverManager.getConnection(url,"root" , "abc123");
	
			if(con!=null) {
				System.out.println("Connection successfull");
			}
		} catch(SQLException e) {
			System.out.println("issues " + e);
		} 
	}
}
