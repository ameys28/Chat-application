import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class CreateUser extends JFrame{
	private Container c;
	private JTextField txtUsername;
	private JPasswordField txtPassword, txtConfirmPassword;
	private JButton btnRegister;
	private JLabel labUsername, labPassword,labConfirmPassword,labDetail;

	private DatabaseConnection db;
	
	public CreateUser(DatabaseConnection db){
		this.db = db;
		//Getting the Access of the Content Pane in a Variable
		c= getContentPane();
		c.setLayout(null);
		
		//Instantiating the object 
		txtUsername = new JTextField(20);
		txtPassword = new JPasswordField(20);
		txtConfirmPassword = new JPasswordField(20);
		btnRegister = new JButton("Register");
		labUsername = new JLabel("Enter Username:" , JLabel.LEFT);
		labPassword = new JLabel("Enter Password:" , JLabel.LEFT);
		labConfirmPassword = new JLabel("Confirm Password:" , JLabel.LEFT);
		labDetail = new JLabel("Please Fill your details" , JLabel.CENTER);
		
		
		//Formating Text 
		Font f = new Font("Arial" , Font.BOLD,20);
		txtUsername.setFont(f);
		txtPassword.setFont(f);
		btnRegister.setFont(f);
		labUsername.setFont(f);
		labPassword.setFont(f);
		labConfirmPassword.setFont(f);
		labDetail.setFont(new Font("Arial" , Font.BOLD , 25));		

		//Defining layout
		labDetail.setBounds(90,30,400,40);
		labUsername.setBounds(90,100,200,40);
		txtUsername.setBounds(300,100,200,40);
		labPassword.setBounds(90,150,200,40);
		txtPassword.setBounds(300,150,200,40);
		labConfirmPassword.setBounds(90,200,200,40);
		txtConfirmPassword.setBounds(300,200,200,40);
		btnRegister.setBounds(230,265,130,40);	
	
		//Adding Action Listener to Register Button
		ActionListener a = (ae) -> {
			String username = txtUsername.getText();
			String password = new String(txtPassword.getPassword());
			String confirmPassword = new String(txtConfirmPassword.getPassword());
			
			if(username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
				JOptionPane.showMessageDialog(c, "All Fields must be Filled!");
			} else if(!password.equals(confirmPassword)) {
				JOptionPane.showMessageDialog(c, "Passwords do not match!");
			} else {
				if(createUser(username,password)){
					JOptionPane.showMessageDialog(c,"User Registered Successfully!");
					dispose();
					new LoginUI(db);
				} else {
					JOptionPane.showMessageDialog(c,"User Already Exists. Try a different one.");
				}
			}
		};
		btnRegister.addActionListener(a);

		//Adding into ContentPane
		c.add(labDetail);
		c.add(labUsername);
		c.add(txtUsername);
		c.add(labPassword);
		c.add(txtPassword);
		c.add(labConfirmPassword);
		c.add(txtConfirmPassword);
		c.add(btnRegister);

		setTitle("Register");
		setSize(600,420);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setVisible(true);
	}	
	
	private boolean createUser(String username , String password){
		String queryCheck = "SELECT * FROM users WHERE username = ?";
		String queryInsert = "INSERT INTO users (username ,password) VALUES(?,?)";
		
		try{
			Connection con = db.getConnection();
			PreparedStatement checkStmt = con.prepareStatement(queryCheck);
			PreparedStatement insertStmt = con.prepareStatement(queryInsert);
			
			checkStmt.setString(1,username);
			ResultSet rs = checkStmt.executeQuery();
			if(rs.next()){
				return false; 		//username already exists
			}

			insertStmt.setString(1,username);
			insertStmt.setString(2,password);
			insertStmt.executeUpdate();
			return true;
	
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(c,"Error Creating User : " + e.getMessage());
			return false;
		}
	}

}