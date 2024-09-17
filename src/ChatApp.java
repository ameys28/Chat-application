import java.sql.*;
public class ChatApp {
    public static void main(String[] args) {
	new LoginUI();
        DatabaseConnection db = new DatabaseConnection();
	Connection con = db.getConnection();
        new ChatUI(con);
    }
}
