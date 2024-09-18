import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.sql.*;

class app extends JFrame{
	private Container c;
	private JTextField txtUsername;
	private JPasswordField txtPassword, txtConfirmPassword;
	private JButton btnRegister;
	private JLabel labUsername, labPassword,labConfirmPassword;

	//private DatabaseConnection db;
	
	public app(){
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
		
		
		//Formating Text 
		Font f = new Font("Arial" , Font.BOLD,20);
		txtUsername.setFont(f);
		txtPassword.setFont(f);
		btnRegister.setFont(f);
		labUsername.setFont(f);
		labPassword.setFont(f);
		labConfirmPassword.setFont(f);
		
		//Defining layout
		labUsername.setBounds(90,70,200,40);
		txtUsername.setBounds(300,70,200,40);
		labPassword.setBounds(90,120,200,40);
		txtPassword.setBounds(300,120,200,40);
		labConfirmPassword.setBounds(90,170,200,40);
		txtConfirmPassword.setBounds(300,170,200,40);
		btnRegister.setBounds(230,265,130,40);	
	
		//Adding into ContentPane
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

}
class CreateUser{
	public static void main(String args[]){
		app a = new app();
	}
}