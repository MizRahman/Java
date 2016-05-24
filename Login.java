
/**
 *This is user login frame
*@author MD Mizanur Rahman
 *@version  28/03/2013
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Login extends JFrame implements ActionListener
{
	private JPanel board  = new JPanel();
	private JLabel lblUser = new JLabel("UserID");
	private JTextField txtUser = new JTextField(10);
	private JLabel lblPassword = new JLabel("Password");
	private JPasswordField pass = new JPasswordField(10);
	private JButton btnLogin = new JButton("Login");
	private JButton btnClear = new JButton("Clear");
	char[] pwd ;//getting the password as a char array
	String myPass; //converting char to string using this variable
	String myUser; //storing user id
	public Login()
	{
		setTitle("MD's Online TV Store");
		setLocation(300,200);//Setting the location of the frame
		setSize(250,120);//Setting size of the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.setLayout(new GridLayout(3,2));
		//Adding all the control into the panel called board
		board.add(lblUser);
		board.add(txtUser);
		board.add(lblPassword);
		board.add(pass);
		board.add(btnLogin);
		board.add(btnClear);
		setResizable(false);//disabling the maximise button from the frame
		add(board);//adding panel into the frame
		//adding action listener for the button
		btnLogin.addActionListener(this);
		btnClear.addActionListener(this);
		setVisible(true);
	}
	private boolean inputValidation()//checking whether any textfield empty or not
	{
		pwd = pass.getPassword();//getting the password as a char array
		myPass = new String(pwd).trim();  //converting char to string
		myUser = txtUser.getText().trim();//Storing the userid into a variable
		if(myUser.length()==0)
		{
			JOptionPane.showMessageDialog(this,"Please input UserID");
			txtUser.requestFocus();
			return false;
		}
		if(myPass.length()==0)
		{
			JOptionPane.showMessageDialog(this,"Please input password");
			pass.requestFocus();
			return false;
		}
		return true;//if there is no empty textfield
	}
	private boolean checkUser()
	{
		final String myID,myPassword;//using fix userid and password
		myID = "admin";
		myPassword = "123";
		myUser = txtUser.getText().trim();//Storing the userid into a variable
		pwd = pass.getPassword();//getting the password as a char array
		myPass = new String(pwd).trim();  //converting char to string
		if(!myUser.equals(myID))//IF my fix userid and the inputed user are not same
		{
			JOptionPane.showMessageDialog(this,"Invalid user id");
			txtUser.requestFocus();
			return false;
		}
		if(!myPass.equals(myPassword))//if my fix password is not equal to inputed password
		{
			JOptionPane.showMessageDialog(this,"Invalid password");
			pass.requestFocus();
			return false;
		}
		return true;//if user id and password match
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btnLogin)
		{
			if(inputValidation())//calling the inputvalidation method here to check is any empty box or not
			{
				if(checkUser())//if userid and password match it will go to the main frame
				{
					new MyMenu();
					setVisible(false);
				}
			}
		}
		//clearing the text boxes
		if(e.getSource()==btnClear)
		{
			txtUser.setText("");
			pass.setText("");
		}
	}
}
