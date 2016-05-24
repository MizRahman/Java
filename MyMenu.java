/**
 *This is welcome page
 *
*@author MD Mizanur Rahman
 *@version  22/03/2013
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class MyMenu extends JFrame implements ActionListener
{
	JMenuBar menuBar = new JMenuBar();//Creating a menu bar
	JMenu menu;
	JMenuItem addTV,sTV,dTV,quit;
	private ImageIcon img = new ImageIcon("tv.jpg");
	private JLabel lblCaption = new JLabel();
	MDsTVList myTVList = new MDsTVList();
	public MyMenu()
	{
		setTitle("MDs Online TV Store");
		setLocation(300,200);
		setSize(600,350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lblCaption.setIcon(img);//setting the image into the label box
		lblCaption.setHorizontalAlignment(JLabel.CENTER);
        lblCaption.setVerticalAlignment(JLabel.CENTER);
        menu = new JMenu("Menu");//Adding Menu to the JMenu
		menuBar.add(menu);
		addTV = new JMenuItem("Add New TV");//Creating submenu
		menu.add(addTV);//adding submenu into the menu
		sTV = new JMenuItem("Search TV");//Creating submenu
		menu.add(sTV);//adding submenu into the menu
		dTV= new JMenuItem("Delete TV");//Creating submenu
		menu.add(dTV);//adding submenu into the menu
		quit = new JMenuItem("Quit");//Creating submenu
		menu.add(quit);//adding submenu into the menu
		setJMenuBar(menuBar);//Finally adding menu to the frame
		lblCaption.setSize(300,300);
		add(lblCaption);
		setResizable(false);//disabling the maximise button from the frame
		addTV.addActionListener(this);
		sTV.addActionListener(this);
		dTV.addActionListener(this);
		quit.addActionListener(this);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(addTV))
		{
			new AddNewTV(myTVList);
			setVisible(false);
		}
		if(e.getSource().equals(sTV))
		{
			new SearchAllTV(myTVList);
			setVisible(false);
		}
		if(e.getSource().equals(dTV))
		{
			new ModifyMDsTV(myTVList);
			setVisible(false);
		}
		if(e.getSource().equals(quit))
		{
			System.exit(0);//close the frame
		}
	}

}
