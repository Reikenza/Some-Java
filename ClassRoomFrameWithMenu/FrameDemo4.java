/* 
  This program is SAFE. It uses the invokeLater() method of the Event Dispatch
  thread to update the GUI components.	 

*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FrameDemo4 
{

	/**
	* Create the GUI and show it.  For thread safety,
	* this method should be invoked from the
	* event-dispatching thread.
	*/

	private static void createAndShowGUI()
	{
		//Create and set up the window.
		JFrame frame = new JFrame("FrameDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Create the panel	
		final JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(400, 300));
		panel.setBackground(Color.BLACK);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		  
		//Create the menu bar.
		JMenuBar menuBar = new JMenuBar();

		//Build the first menu.
		JMenu color_menu = new JMenu("Color");
		color_menu.setMnemonic(KeyEvent.VK_C);
		menuBar.add(color_menu);

		//a group of JMenuItems
		JMenuItem menuItem_red = new JMenuItem("RED");
		JMenuItem menuItem_yel = new JMenuItem("YELLOW");
		JMenuItem menuItem_blu = new JMenuItem("BLUE");

		menuItem_red.addActionListener(new ActionListener()
		{  public void actionPerformed(ActionEvent e)
			{
				panel.setBackground(Color.RED);
			}
		}
		);
		  
		menuItem_yel.addActionListener(new ActionListener()
		{  
			public void actionPerformed(ActionEvent e)
			{
				panel.setBackground(Color.YELLOW);
			}
		}
		);
		  
		menuItem_blu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				panel.setBackground(Color.BLUE);
			}
		}
		);

		//Add the menu items to the menu.    
		color_menu.add(menuItem_red);
		color_menu.add(menuItem_yel);
		color_menu.add(menuItem_blu);

		//Build the second menu.
		JMenu auto_menu = new JMenu("AutoColor");
		auto_menu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(auto_menu);


		// Create a thread object that automatically changes the color	
		final ColorChanger t = new ColorChanger(1000, panel);
		t.start();

		//a group of JMenuItems
		JMenuItem menuItem_start = new JMenuItem("START");
		JMenuItem menuItem_stop  = new JMenuItem("STOP");


		menuItem_start.addActionListener(new ActionListener()
		{  
			public void actionPerformed(ActionEvent e)
			{
				t.threadSuspended = false;
				synchronized(panel)
				{
					panel.notify();
				}	
			}
		}
		);

		menuItem_stop.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				t.threadSuspended = true;
			}
		}
		);

		//Add the menu items to the menu.
		auto_menu.add(menuItem_start);
		auto_menu.add(menuItem_stop);


		//Add the menu mar to the frame	
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);
	
		//Display the window.
		frame.pack();
		frame.setVisible(true);
		  
	}

	public static void main(String[] args)
	{
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				createAndShowGUI();
			}
		}
		);
	}
}

class ColorChanger extends Thread
{
	public boolean threadSuspended;
	private int pause;
	private JPanel panel;

	public ColorChanger(int pause, JPanel panel)
	{
		threadSuspended = true;
		this.pause = pause;
		this.panel = panel;
	}

	public void run()
	{
		while(true)
		{
			synchronized(panel)
			{
				while(threadSuspended)
				{
					try
					{
						panel.wait();
					}
					catch(InterruptedException e){}	
				}
			}		

			javax.swing.SwingUtilities.invokeLater(new Runnable()
			{
				public void run()
				{
				if(panel.getBackground() == Color.BLUE)  
					panel.setBackground(Color.RED);
				else
					if(panel.getBackground() == Color.RED)  
						panel.setBackground(Color.YELLOW);
					else
						panel.setBackground(Color.BLUE);
				}
			}
			);

			try
			{
				Thread.sleep(pause);
			}
			catch(InterruptedException e){}	
		}
	}	
}
