import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class LoginUI extends JFrame{
	//GUI Components
	private Container c;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JButton btnLogin;
	private JLabel labUsername, labPassword;

	//private UserAuthentication userAuth;
	
	public LoginUI(){
		//Getting the Access of the Content Pane in a Variable
		c = getContentPane();
		c.setLayout(null);
		
		//Instantiating the objects
		txtUsername = new JTextField(20);
		txtPassword = new JPasswordField(20);
		btnLogin = new JButton("Login");
		labUsername = new JLabel("Enter Username:" , JLabel.LEFT);
		labPassword = new JLabel("Enter Password:" , JLabel.LEFT);

		//Formating Text 
		Font f = new Font("Arial" , Font.BOLD,20);
		txtUsername.setFont(f);
		txtPassword.setFont(f);
		btnLogin.setFont(f);
		labUsername.setFont(f);
		labPassword.setFont(f);

		//Defining layout
		labUsername.setBounds(90,30,400,50);
		txtUsername.setBounds(90,80,400,50);
		labPassword.setBounds(90,140,400,50);
		txtPassword.setBounds(90,190,400,50);
		btnLogin.setBounds(90,270,100,50);	
	
		//Adding into ContentPane
		c.add(labUsername);
		c.add(txtUsername);
		c.add(labPassword);
		c.add(txtPassword);
		c.add(btnLogin);

		setTitle("Login");
		setSize(600,420);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setVisible(true);
	}
}

