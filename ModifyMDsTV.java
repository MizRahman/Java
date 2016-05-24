/**
 *In this class User can delete s particular tv
*@author MD Mizanur Rahman
 *@version  11/04/2013
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class ModifyMDsTV extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//All the controls that I needed for this frame
	int index = 0;//tracking the list when moving next or previous
	private JPanel board  = new JPanel();
	private JPanel navigate = new JPanel();
	JMenuBar menuBar = new JMenuBar();//Creating a menu bar
	JMenu menu;//menu
	JMenuItem searchTV,addMenu,quit;//Submenu
	JLabel lblList = new JLabel("Delete any item from the list");
	JTextArea myTVlist = new JTextArea();
	private JButton btnPrevious = new JButton("<<");
	private JButton btnNext = new JButton(">>");
	private JButton btnDelete = new JButton("Delete");
	MDsTVList editTVList = new MDsTVList(); //Creating MDsTVList class object
	public ModifyMDsTV(MDsTVList tvIn)
	{
		editTVList = tvIn;
		setTitle("MD's Online TV Store");
		setLocation(300,200);//Setting the location of the frame
		setSize(400,250);//Setting size of the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.setLayout(new GridLayout(1,3));
		setResizable(false);//disabling the maximise button from the frame
		//start menu bar
		menu = new JMenu("Menu");//Adding Menu to the JMenu
		menuBar.add(menu);//ADDING MENU INTO THE MENU BAR
		addMenu = new JMenuItem("Add New TV");//Creating submenu
		menu.add(addMenu);//adding submenu into the menu
		searchTV = new JMenuItem("Search TV");//Creating submenu
		menu.add(searchTV);//adding submenu into the menu
		quit = new JMenuItem("Quit");//Creating submenu
		menu.add(quit);//adding submenu into the menu
		setJMenuBar(menuBar);//Finally adding menu to the frame
		//End of adding menu bar into the frame
		//Adding all the control into the panel called board
		myTVlist.setEditable(false);//stoping user to write into the textarea
		board.add(btnPrevious);
		board.add(btnNext);
		board.add(btnDelete);
		add(board);//adding panel into the frame
		navigate.setLayout(new BorderLayout());//adding border layout for the button
		navigate.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));//setting the size of navigate panel
		myTVlist.setEditable(false);//stoping user to write into the textarea
		navigate.add(new JScrollPane(myTVlist));//ADDING SCROLLBAR INTO THE TEXTAREA
		add(board,BorderLayout.NORTH);//adding panel into the frame
		add(navigate,BorderLayout.CENTER);
		//adding action listener for the buttons
		btnPrevious.addActionListener(this);
		btnNext.addActionListener(this);
		btnDelete.addActionListener(this);
		addMenu.addActionListener(this);
		searchTV.addActionListener(this);
		quit.addActionListener(this);
		setVisible(true);
		//Displaying the first TV into the text area
		if(editTVList.getTVList().size()>0)
		{
			myTVlist.append(editTVList.getTVList().get(index).toString());//displaying the first item into the textarea
		}
		btnPrevious.setEnabled(false);//disabling the previous button
		if(editTVList.getTVList().size()==1)//Disabling botth button if there is only 1 TVlist
		{
			btnNext.setEnabled(false);
			btnPrevious.setEnabled(false);
		}
		if(editTVList.getTVList().size()==0)//Disabling botth button if there is NO TV
		{
			btnNext.setEnabled(false);
			btnDelete.setEnabled(false);
		}
	}

	//Through this method user can move to the next item of the list
	public void moveNext() throws MdsTVException
	{
		try
		{
			if(editTVList.getTVList().size()>1)
			{
				btnPrevious.setEnabled(true);
			}
			if(index<editTVList.getTVList().size())
			{
				myTVlist.setText("");//clearing the textarea first
				myTVlist.append(editTVList.getTVList().get(index+1).toString());//displayng list into the textarea
				index++;//increasing the index by 1
			}
			//if there is only 1 record in the list then there is no need to move next and previous
			if(editTVList.getTVList().size()==1)
			{
				btnNext.setEnabled(false);
				btnPrevious.setEnabled(false);
			}

			if(editTVList.getTVList().size()==index+1)
			{
				btnNext.setEnabled(false);
			}
		}
		catch(NumberFormatException e)
		{
			throw new MdsTVException("Error in the MDs TV Shop");
		}
	}
	//Through this method user can go the the previous item of the list
	public void movePrevious() throws MdsTVException
	{
		try
		{
			if(editTVList.getTVList().size()>1)
			{
				btnNext.setEnabled(true);//Enabling the Next Button
			}

			if(index>=0)
			{
				myTVlist.setText("");
				if(index>0)index--;
				myTVlist.append(editTVList.getTVList().get(index).toString());
			}

			if(editTVList.getTVList().size()==1)
			{
				myTVlist.setText("");
				myTVlist.append(editTVList.getTVList().get(index).toString());
				btnNext.setEnabled(false);
				btnPrevious.setEnabled(false);
			}
			if(index==0)
			{
				btnPrevious.setEnabled(false);
			}
		}
		catch(NumberFormatException e)
		{
			throw new MdsTVException("Error in the MDs TV Shop");
		}

	}
	public void removeTV() throws MdsTVException
	{
		try
		{
			if(index<editTVList.getTVList().size())
			{
				if(myTVlist.getText().trim().length()!=0)
				{
					editTVList.getTVList().remove(index);//deleting the tv from the list
					index--; //decresing the index by 1 as I delete the item from the list
					myTVlist.setText("");////clearing the textarea
					editTVList.writeTVFile(); //rewriting the text file
					JOptionPane.showMessageDialog(this,"The TV has been deleted from the list");
				}
			}
		}
		catch(NumberFormatException e)
		{
			throw new MdsTVException("Error in the MDs TV Shop");
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btnNext)
		{
			try
			{
				moveNext();//calling the method to go the next record
			}
			catch(Exception err)
			{
				JOptionPane.showMessageDialog(this,"Error in the MDs TV Shop");
			}

		}
		if(e.getSource()==btnPrevious)
		{
			try
			{
				movePrevious();//calling the method to go the previous record
			}
			catch(Exception err)
			{
				JOptionPane.showMessageDialog(this,"Error in the MDs TV Shop");
			}
		}
		if(e.getSource()==btnDelete)
		{
			try
			{
				removeTV();//calling the method to delete tv
			}
			catch(Exception err)
			{
				JOptionPane.showMessageDialog(this,"Error in the MDs TV Shop");
			}
		}
		if(e.getSource()==addMenu)
		{
			setVisible(false);//Hiding the Current frame
			new AddNewTV(editTVList);//Goto the AddNewTV Frame
		}
		if(e.getSource()==searchTV)
		{
			setVisible(false);//Hiding the Current frame
			new SearchAllTV(editTVList);//Goto the SearchAllTV Frame
		}
		if(e.getSource()==quit)
		{
			System.exit(0);//Closing the frame;
		}
	}
}




