
/**
 *In this class User can add new TVs
*@author MD Mizanur Rahman
 *@version  22/03/2013
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
class AddNewTV extends JFrame implements ActionListener
{
	//All the controls that I needed for this frame
	private JPanel board  = new JPanel();
	JMenuBar menuBar = new JMenuBar();//Creating a menu bar
	JMenu menu;//menu
	JMenuItem searchTV,EditTV,quit;//Submenu
	private JLabel lblModel  = new JLabel("Model No",JLabel.CENTER);
	private JTextField txtModel = new JTextField(5);
	private JLabel lblName  = new JLabel("TV Name",JLabel.CENTER);
	private JTextField txtName = new JTextField(20);
	private JLabel lblSize  = new JLabel("Screen Size",JLabel.CENTER);
	private JTextField txtSize = new JTextField(20);
	private JLabel lblType  = new JLabel("Type(LCD/LED/3D)",JLabel.CENTER);
	private JTextField txtType = new JTextField(20);
	private JLabel lblPrice  = new JLabel("Price",JLabel.CENTER);
	private JTextField txtPrice = new JTextField(20);
	private JLabel lblGuarantee = new JLabel("Guarantee",JLabel.CENTER);
	private JTextField txtGuarantee = new JTextField(20);
	private JButton btnEdit = new JButton("Edit");
	private JButton btnSave = new JButton("Save");
	private JButton btnSearch = new JButton("Search");
	private JButton btnDelete = new JButton("Delete");
	MDsTVList myTVList = new MDsTVList(); //Creating MDsTVList class object(my collection object)
	public AddNewTV(MDsTVList TVIn)
	{
		myTVList = TVIn;
		setTitle("MD's Online TV Store");
		setLocation(200,150);//Setting the location of the frame
		setSize(450,200);//Setting size of the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.setLayout(new GridLayout(4,4));
		//Adding all the control into the panel called board
		menu = new JMenu("Menu");//Adding Menu to the JMenu
		menuBar.add(menu);//ADDING MENU INTO THE MENU BAR
		searchTV = new JMenuItem("Search TV");//Creating submenu
		menu.add(searchTV);//adding submenu into the menu
		EditTV= new JMenuItem("Delete TV");//Creating submenu
		menu.add(EditTV);
		quit = new JMenuItem("Quit");//Creating submenu
		menu.add(quit);//adding submenu into the menu
		setJMenuBar(menuBar);//Finally adding menu to the frame
		board.add(lblModel);
		board.add(txtModel);
		board.add(lblName);
		board.add(txtName);
		board.add(lblSize);
		board.add(txtSize);
		board.add(lblType);
		board.add(txtType);
		board.add(lblPrice);
		board.add(txtPrice);
		board.add(lblGuarantee);
		board.add(txtGuarantee);
		btnEdit.setVisible(false);
		board.add(btnEdit);
		board.add(btnSave);
		board.add(btnSearch);
		board.add(btnDelete);
		setResizable(false);//disabling the maximise button from the frame
		add(board);//adding panel into the frame
		//adding action listener for the button
		btnSave.addActionListener(this);
		btnSearch.addActionListener(this);
		btnDelete.addActionListener(this);
		searchTV.addActionListener(this);
		EditTV.addActionListener(this);
		quit.addActionListener(this);
		setVisible(true);
	}
	public void clear_Fields()
	{
		//Clearing all the fields
		txtModel.setText("");
		txtName.setText("");
		txtSize.setText("");
		txtType.setText("");
		txtPrice.setText("");
		txtGuarantee.setText("");
	}
	public boolean inputValidation()
	{
		//This method will check all the TextField which is empty then it will display a message and wiil ask user to input value into the certain textfield
		//Also setting the cursor into the empty box
		if(txtModel.getText().trim().length()==0)
		{
			JOptionPane.showMessageDialog(this,"Please input TV Model number");
			txtModel.requestFocus();//Focusing the cursor into this TextField
			return false;
		}
		if(txtName.getText().trim().length()==0)
		{
			JOptionPane.showMessageDialog(this,"Please input TV Name");
			txtName.requestFocus();
			return false;
		}
		if(txtSize.getText().trim().length()==0)
		{
			JOptionPane.showMessageDialog(this,"Please input a valid screen size");
			txtSize.requestFocus();
			return false;
		}
		if(txtType.getText().trim().length()==0)
		{
			JOptionPane.showMessageDialog(this,"Please input TV type");
			txtType.requestFocus();
			return false;
		}
		if(txtPrice.getText().trim().length()==0)
		{
			JOptionPane.showMessageDialog(this,"Please input a valid price");
			txtPrice.requestFocus();
			return false;
		}
		if(txtGuarantee.getText().trim().length()==0)
		{
			JOptionPane.showMessageDialog(this,"Please input guarantee");
			txtGuarantee.requestFocus();
			return false;
		}
		try
		{
			//Stoping user input 0 into these boxes
			double  price;
			int size,guarantee;
			price=Double.parseDouble(txtPrice.getText().trim().toString());
			size=Integer.parseInt(txtSize.getText().trim().toString());
			guarantee=Integer.parseInt(txtGuarantee.getText().trim().toString());
			if(price<=0)
			{
				JOptionPane.showMessageDialog(this,"Please input valid price");
				txtPrice.requestFocus();
				return false;
			}
			if(size<22 || size>100)
			{
				JOptionPane.showMessageDialog(this,"MDs TV Shop only do 22 to 100 inchs tv");
				txtSize.requestFocus();
				return false;
			}
			if(size%2 !=0)//STOPING USER TO INPUT ANY ODD NUMBER IN SCREEN SIZE BOX
			{
				JOptionPane.showMessageDialog(this,"Sorry! MDs TV shop don't do odd screen size");
				txtSize.requestFocus();
				return false;
			}
			if(guarantee<=0 || guarantee>5)
			{
				JOptionPane.showMessageDialog(this,"MD Tv shop only do 1-5 years of guarantee");
				txtGuarantee.requestFocus();
				return false;
			}
		}
		catch(NumberFormatException err)
		{
		}
		return true;//if there is no empty boxes
	}
	public void saveTV()throws MdsTVException
	{
		try
		{
			String modelNo,tvName,tvType;
			int scrSize,guarantee;
			double tvPrice;
			//Storing the values into those variables
			modelNo=txtModel.getText().trim();//removing space if there any using trim
			tvName=txtName.getText().trim();//removing space if there any using trim
			tvType=txtType.getText().trim();//removing space if there any using trim
			scrSize=Integer.parseInt(txtSize.getText().trim());//converting Screen Size into Integer
			guarantee=Integer.parseInt(txtGuarantee.getText().trim());//converting Guarantee into Integer
			tvPrice=Double.parseDouble(txtPrice.getText().trim()); //Converting the price into double
			myTVList.addTV(new MDsTV(modelNo,tvName,scrSize,tvType,tvPrice,guarantee));//Adding the values
			JOptionPane.showMessageDialog(this,"Record has been added to the collection");//Displaying a Message box
			txtModel.requestFocus();//Focusing the cursor into mODEL NO
		}
		catch(NumberFormatException e)
		{
			throw new MdsTVException("Invalid number");
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btnSave)
		{
			if(inputValidation())//If there is no empty box
			{
				try
				{
					saveTV();//calling the method
					clear_Fields();//clearing all the fields after adding them to the collection
				}
				catch (NumberFormatException err)
				{
					JOptionPane.showMessageDialog(this,"Please enter valid number");
				}
				catch (Exception err)
				{
					JOptionPane.showMessageDialog(this,"!Error in the MDs TV Shop.It could have been caused by an invalid input.");
				}
			}
		}
		if(e.getSource()==btnSearch)
		{
			setVisible(false);
			new SearchAllTV(myTVList);//passing the "myTVList Object" as a parameter to the other class
		}
		if(e.getSource()==searchTV)
		{
			setVisible(false);
			new SearchAllTV(myTVList);//passing the "myTVList Object" as a parameter to the other class
		}
		if(e.getSource()==EditTV)
		{
			setVisible(false);
			new ModifyMDsTV(myTVList);//passing the "myTVList Object" as a parameter to the other class
		}
		if(e.getSource()==quit)
		{
			System.exit(0);
		}
		if(e.getSource()==btnDelete)
		{
			setVisible(false);
			new ModifyMDsTV(myTVList);//passing the "myTVList Object" as a parameter to the other class
		}
	}
}


