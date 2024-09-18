import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class LoginUI extends JFrame{
	//GUI Components
	private Container c;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JButton btnLogin,btnCreateUser;
	private JLabel labUsername, labPassword,labWelcome,labCreateUser;

	private UserAuthentication userAuth;

	public LoginUI(DatabaseConnection db){
		userAuth = new UserAuthentication(db);

		//Getting the Access of the Content Pane in a Variable
		c = getContentPane();
		c.setLayout(null);
		
		//Instantiating the object 
		txtUsername = new JTextField(20);
		txtPassword = new JPasswordField(20);
		btnLogin = new JButton("Login");
		btnCreateUser = new JButton("Register");
		labUsername = new JLabel("Enter Username:" , JLabel.LEFT);
		labPassword = new JLabel("Enter Password:" , JLabel.LEFT);
		labWelcome = new JLabel("Welcome to Our ChatApp!" , JLabel.CENTER);
		labCreateUser = new JLabel("New to our ChatApp?" , JLabel.LEFT);

		//Formating Text 
		Font f = new Font("Arial" , Font.BOLD,20);
		txtUsername.setFont(f);
		txtPassword.setFont(f);
		btnLogin.setFont(f);
		labUsername.setFont(f);
		labPassword.setFont(f);
		labWelcome.setFont(new Font("Arial",Font.BOLD , 35));
		btnLogin.setFont(new Font("Arial",Font.BOLD , 15));
		labCreateUser.setFont(new Font("Arial",Font.BOLD , 15));

		//Defining layout
		labWelcome.setBounds(70,10,440,50);
		labUsername.setBounds(90,70,400,50);
		txtUsername.setBounds(90,110,400,50);
		labPassword.setBounds(90,160,400,50);
		txtPassword.setBounds(90,200,400,50);
		btnLogin.setBounds(242,265,100,40);	
		labCreateUser.setBounds(145,325,170,30);
		btnCreateUser.setBounds(325,325,100,30);

		//Adding ActionListener to Login Button
		ActionListener a = (ae)->{
			String username = txtUsername.getText();
			String password = new String(txtPassword.getPassword());
		
			User user = new User(username , password);
			if(userAuth.validateUser(user)) {
				JOptionPane.showMessageDialog(c,"Login Successfull!");
				dispose();
				new ChatUI(db.getConnection());
			} else {
				JOptionPane.showMessageDialog(c,"Invalid Username or Password");
			}
		};
		btnLogin.addActionListener(a);	
		ActionListener b = (ae)-> {
			new CreateUser(db);
			dispose();
		};	
		btnCreateUser.addActionListener(b);

		//Adding into ContentPane
		c.add(labWelcome);
		c.add(labUsername);
		c.add(txtUsername);
		c.add(labPassword);
		c.add(txtPassword);
		c.add(btnLogin);
		c.add(labCreateUser);
		c.add(btnCreateUser);

		setTitle("Login");
		setSize(600,420);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setVisible(true);
	}
}

