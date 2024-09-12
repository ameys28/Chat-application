import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ChatUI extends JFrame{
  	Container c;
	TextArea taChats;
	JTextField txtMessage;
	JButton btnSend;
	
		private String display = "";  // Store full chat display
   		private String show = "";
	ChatUI(){
		c = getContentPane();
		c.setLayout(null);
		
		taChats = new TextArea(10,30);
		txtMessage = new JTextField(15);
		btnSend	= new JButton("Send");
		
		Font f = new Font("Arial" , Font.BOLD,20);
		taChats.setFont(f);
		txtMessage.setFont(f);
		btnSend.setFont(f);

		taChats.setBounds(20,20,540,300);		
		txtMessage.setBounds(20,350,400,50);
		btnSend.setBounds(450,350,100,50);



		c.add(taChats);
		c.add(txtMessage);
		c.add(btnSend);
		
		ActionListener a = (ae) -> {
			String msg = txtMessage.getText();
            		show = "You: " + msg + "\n";  // Update the show variable
           		display += show;  // Append to the full chat history
        		taChats.setText(display);  // Set the full chat history in the text area
          		txtMessage.setText("");  // Clear the input field
            		txtMessage.requestFocus();  // Set focus back to the input field
		};
		btnSend.addActionListener(a);		

		taChats.setEditable(false);
		
		setTitle("Chat Application");
		setSize(600,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		setVisible(true);
	
	}
}
