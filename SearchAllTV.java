/**
 *In this class User can search particular TV
 *by entering TV name or screen size
*@author MD Mizanur Rahman
 *@version  22/03/2013
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
class SearchAllTV extends JFrame implements ActionListener
{
	private JPanel board  = new JPanel();
	private JPanel area = new JPanel();
	JMenuBar menuBar = new JMenuBar();//Creating a menu bar
	JMenu menu;//menu
	JMenuItem editTV,addMenu,quit;//Submenu
	private JCheckBox chkName  = new JCheckBox("Name");
	private JTextField txtName = new JTextField(20);
	private JCheckBox chkScreen  = new JCheckBox("Screen Size");
	private JTextField txtScreen = new JTextField(20);
	private JButton btnSearch = new JButton("Search");
	private JButton btnClear = new JButton("Clear");
	private JTextArea txtAreaList = new JTextArea(20,5);
	MDsTVList searchTV = new MDsTVList(); //Creating MDsTVList class object
	public SearchAllTV(MDsTVList searchTVIn)
	{
		searchTV = searchTVIn;
		setTitle("MD's Online TV Store");
		setLocation(300,200);//Setting the location of the frame
		setSize(500,350);//Setting size of the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.setLayout(new GridLayout(3,2));
		//Menu start
		menu = new JMenu("Menu");//Adding Menu to the JMenu
		menuBar.add(menu);//ADDING MENU INTO THE MENU BAR
		addMenu = new JMenuItem("Add New TV");//Creating submenu
		menu.add(addMenu);//adding submenu into the menu
		editTV = new JMenuItem("Delete TV");//Creating submenu
		menu.add(editTV);//adding submenu into the menu
		quit = new JMenuItem("Quit");//Creating submenu
		menu.add(quit);//adding submenu into the menu
		setJMenuBar(menuBar);//Finally adding menu to the frame
		//End of ADDING MENU
		board.add(chkName);
		board.add(txtName);
		board.add(chkScreen);
		board.add(txtScreen);
		board.add(btnSearch);
		board.add(btnClear);
		area.setLayout(new BorderLayout());//USE THIS LAYOUT FOR THE TEXTAREA ONLY
		area.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		txtAreaList.setSize(200,200);
		txtAreaList.setEditable(false);//stoping user to write into the textarea
		area.add(new JScrollPane(txtAreaList));//ADDING SCROLLBAR INTO THE TEXTAREA
		setResizable(false);//disabling the maximise button from the frame
		add(board,BorderLayout.NORTH);//adding panel into the frame
		add(area,BorderLayout.CENTER);
		//adding action listener for the buttons
		btnSearch.addActionListener(this);
		btnClear.addActionListener(this);
		addMenu.addActionListener(this);
		editTV.addActionListener(this);
		quit.addActionListener(this);
		setVisible(true);
	}
	public void clear_Fields()
	{
		//Clearing all the boxes
		txtName.setText("");
		txtScreen.setText("");
	}
	//SEARCHING ALL THE TV'S BY TV NAME
	public void SearchTV_ByName()throws MdsTVException
	{
		try
		{
			if(txtName.getText().trim().length()!=0)
			{
				for(MDsTV tv: searchTV.getTVList())//Searching all the list using enhanced loop
				{
					if(tv.getTVName().equals(txtName.getText())) //checking whether the entered value into my list
					{
						txtAreaList.append(tv.toString()); // displaying list into the text area
					}
				}
			}
		}
		catch(Exception e)
		{
			throw new MdsTVException("Error in the MDs TV Shop");
		}
	}
	//THIS METHOD WILL SEARCH ALL THE TV'S BY SCREEN SIZE
	public void SearchTV_ByScreenSize() throws MdsTVException
	{
		try
		{
			if(txtScreen.getText().trim().length()!=0)//IF SCREEN SIZE TEXT BOX IS NOT EMPTY
			{
				int ScrSize = Integer.parseInt(txtScreen.getText());//converting the screen size into the integer
				for(MDsTV tv: searchTV.getTVList())//Searching all the list using enhanced loop
				{
					if(tv.getScreenSize()==ScrSize) //checking whether the entered value into my list
					{
						txtAreaList.append(tv.toString()); // displaying list into the text area
					}
				}
			}
		}
		catch(Exception e)
		{
			throw new MdsTVException("Error in the MDs TV Shop");
		}
	}
	//THIS METHOD WILL SEARCH ALL THE TV'S BY NAME AND SCREEN SIZE
	public void Search_By_Name_ScreenSize() throws MdsTVException
	{
		try
		{
			if(txtScreen.getText().trim().length()!=0 && txtName.getText().trim().length()!=0)//IF NAME AND SCREEN SIZE ARE NOT EMPTY
			{
				int ScrSize = Integer.parseInt(txtScreen.getText());//converting the screen size into the integer
				for(MDsTV tv: searchTV.getTVList())//Searching all the list using enhanced loop
				{
					if(tv.getTVName().equals(txtName.getText()) && tv.getScreenSize()==ScrSize) //checking whether the entered value into my list
					{
						txtAreaList.append(tv.toString()); // displaying list into the text area
					}
				}
			}
		}
		catch(Exception e)
		{
			throw new MdsTVException("Error in the MDs TV Shop");
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()== btnSearch)
		{
			try
			{
				if(!chkName.isSelected() && ! chkScreen.isSelected())
				{
					JOptionPane.showMessageDialog(this,"Pleaase select any check box");
					chkName.requestFocus();
				}
				txtAreaList.setText("");//clearing the text before add anything into it
				if(chkName.isSelected() && ! chkScreen.isSelected()) //checking is the Name checkbox is checked and other checkbox is unchecked
				{
					if(txtName.getText().trim().length()==0) //if textbox is empty
					{
						JOptionPane.showMessageDialog(this,"Please input TV name");
						txtName.requestFocus();//Focus the cursor into name textbox
					}
					SearchTV_ByName();//calling the method
				}

				if(chkScreen.isSelected() && !chkName.isSelected()) //checking is the ScreenSize checkbox is checked and other checkbox unchecked
				{
					if(txtScreen.getText().trim().length()==0) //if textbox is empty
					{
						JOptionPane.showMessageDialog(this,"Please input screen size");
						txtScreen.requestFocus();//Focus the cursor into ScreenSize textbox
					}

					SearchTV_ByScreenSize();//calling the method
				}
				if(chkScreen.isSelected() && chkName.isSelected())//if both checkbox is selected
				{
					if(txtName.getText().trim().length() == 0 || txtScreen.getText().trim().length() == 0) //if any textbox is empty
					{
						JOptionPane.showMessageDialog(this,"Please input value");
						txtName.requestFocus();//Focusing the cursor into the textbox
					}
					Search_By_Name_ScreenSize();
				}
			}
			catch(Exception err)
			{
				JOptionPane.showMessageDialog(this,"Error in the MDs TV Shop. It could have been caused by a invalid input.");
			}
		}
		if(e.getSource()==btnClear)
		{
			clear_Fields();
		}
		if(e.getSource()==addMenu)
		{
			setVisible(false);
			new AddNewTV(searchTV);
		}
		if(e.getSource()==editTV)
		{
			setVisible(false);
			new ModifyMDsTV(searchTV);
		}
		if(e.getSource()==quit)
		{
			System.exit(0);//Closing the frame
		}
	}
}
