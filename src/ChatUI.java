import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class ChatUI extends JFrame{

	//Database Connection
	Connection con;

	//GUI Components
  	private Container c;
	private TextArea taChats;
	private JTextField txtMessage;
	private JButton btnSend;
	
	//String to take input msg and Display the text 
	private String display = "";

	ChatUI(Connection con){
		
		this.con = con;
		
		//Getting the Access of the Content Pane in a Variable
		c = getContentPane();
		c.setLayout(null);
		
		//Instantiating the objects	
		taChats = new TextArea(10,30);
		txtMessage = new JTextField(15);
		btnSend	= new JButton("Send");
		
		//Formating Text 
		Font f = new Font("Arial" , Font.BOLD,20);
		taChats.setFont(f);
		txtMessage.setFont(f);
		btnSend.setFont(f);

		//Defining layout
		taChats.setBounds(20,20,540,300);		
		txtMessage.setBounds(20,350,400,50);
		btnSend.setBounds(450,350,100,50);


		//Adding Components into ContentPane
		c.add(taChats);
		c.add(txtMessage);
		c.add(btnSend);
		
		ActionListener a = (ae) -> {
			String msg = txtMessage.getText();
			if(!msg.isEmpty()){
				//Inserting into database
				insertMessage("User",msg);
			
				//To Update in the GUI				
           			display += "You: " + msg + "\n";
        			taChats.setText(display);
          			txtMessage.setText("");
            			txtMessage.requestFocus();	
			}				
		};
		btnSend.addActionListener(a);		

		//Setting text Editable to false so that user cant change it
		taChats.setEditable(false);
		
		loadMessages();

		setTitle("Chat Application");
		setSize(600,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setVisible(true);
	}
	
	//Keeping this method private so that only this can access this
	private void insertMessage(String username , String msg){
		try{
			String sql = "INSERT INTO messages (username, message) VALUES (?, ?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,username);
			pst.setString(2,msg);
			pst.executeUpdate();
			
		} catch(SQLException e) {
			System.out.println("Error Inserting Message: " + e.getMessage());
		}
	}

	private void loadMessages(){
		try {
			String sql = "select * from messages order by timestamp";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				String username = rs.getString("username");
				String msg = rs.getString("message");
				Timestamp t = rs.getTimestamp("timestamp");
				display += username + " ( " + t + " ):" + msg + "\n";
			}
			taChats.setText(display);
		} catch(SQLException e) {
			System.out.println("Error Loading Messages: " + e.getMessage());
		}
	}	
}
