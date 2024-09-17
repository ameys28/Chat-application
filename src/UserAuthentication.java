import java.sql.*;
public class UserAuthentication {
	private DatabaseConnection db;

	public UserAuthentication(DatabaseConnection db){
		this.db = db;
	}

	public boolean validateUser(User user) {
		try {
			String sql = "select * from users where username = ? and password = ?";
			Connection con = db.getConnection();
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,user.getUsername());
			pst.setString(2,user.getPassword());
			ResultSet rs = pst.executeQuery();
			return rs.next();			
		} catch(SQLException e) {
			System.out.println("Error Validating User: " + e.getMessage());
			return false;
		}
	}
}
