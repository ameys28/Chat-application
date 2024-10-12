import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class ChatUI extends JFrame {

	// Database Connection
	Connection con;

	// GUI Components
	private Container c;
	private TextArea taChats;
	private JTextField txtMessage;
	private JButton btnSend;

	// String to take input msg and Display the text
	private String display = "";

	// Store the current user's username
	private String currentUser;

	ChatUI(Connection con, String currentUser) {
		this.con = con;
		this.currentUser = currentUser; // Set the current user

		// Getting the Access of the Content Pane in a Variable
		c = getContentPane();
		c.setLayout(null);

		// Instantiating the objects
		taChats = new TextArea(10, 30);
		txtMessage = new JTextField(15);
		btnSend = new JButton("Send");

		// Formating Text
		Font f = new Font("Arial", Font.BOLD, 20);
		taChats.setFont(f);
		txtMessage.setFont(f);
		btnSend.setFont(f);

		// Defining layout
		taChats.setBounds(20, 20, 540, 300);
		txtMessage.setBounds(20, 350, 400, 50);
		btnSend.setBounds(450, 350, 100, 50);

		// Adding Components into ContentPane
		c.add(taChats);
		c.add(txtMessage);
		c.add(btnSend);

		// Action Listener for sending messages
		ActionListener sendAction = ae -> {
			String msg = txtMessage.getText();
			if (!msg.isEmpty()) {
				insertMessage(msg, currentUser); // Use currentUser for username
				display += "YOU: " + msg + "\n";
				taChats.setText(display);
				txtMessage.setText("");
			}
		};
		btnSend.addActionListener(sendAction);

		// Start polling for new messages
		startPollingForMessages();

		// Setting text Editable to false so that user can't change it
		taChats.setEditable(false);

		loadMessages();

		setTitle("Chat Application");
		setSize(600, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	// Keeping this method private so that only this can access this
	private void insertMessage(String message, String username) {
		try {
			String sql = "INSERT INTO messages (username, message, timestamp) VALUES (?, ?, ?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, username); // Set the username
			pst.setString(2, message); // Set the message
			pst.setTimestamp(3, new Timestamp(System.currentTimeMillis())); // Set the timestamp

			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error sending message: " + e.getMessage());
		}
	}

	private void loadMessages() {
		try {
			// Query to fetch messages along with the username
			String sql = "SELECT username, message FROM messages ORDER BY timestamp";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			StringBuilder newDisplay = new StringBuilder();
			while (rs.next()) {
				String username = rs.getString("username");
				String msg = rs.getString("message");

				// Check if the username is the current user (for "YOU" label)
				if (username.equals(currentUser)) {
					newDisplay.append("YOU: ").append(msg).append("\n");
				} else {
					newDisplay.append(username).append(": ").append(msg).append("\n");
				}
			}

			// Update the chat display with the new messages
			display = newDisplay.toString();
			taChats.setText(display);

		} catch (SQLException e) {
			System.out.println("Error loading messages: " + e.getMessage());
		}
	}

	private void startPollingForMessages() {
		// Swing Timer for polling messages every 2 seconds
		Timer timer = new Timer(2000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadMessages();
			}
		});
		timer.start();
	}
}
