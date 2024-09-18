import java.sql.*;
public class ChatApp {
    public static void main(String[] args) {
	DatabaseConnection db = new DatabaseConnection();
	Connection con = db.getConnection();
	new LoginUI(db);
    }
}
