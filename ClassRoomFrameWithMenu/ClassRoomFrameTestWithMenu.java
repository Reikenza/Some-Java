import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.util.*;
import java.io.*;

public class ClassRoomFrameTestWithMenu
{
	public static void main(String[] args)
	{
		ClassRoomFrameWithMenu frame = new ClassRoomFrameWithMenu();
		frame.setSize(600,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
      frame.setVisible(true);
	}
}
class ClassRoomFrameWithMenu extends JFrame implements ActionListener
{
	private JPanel mainPanel = new JPanel();
	private JPanel deptPanel = new JPanel(new GridLayout(2,3));
	private JPanel studentPanel = new JPanel(new GridLayout(2,5));
	private JPanel displayPanel = new JPanel(new BorderLayout());
   private JPanel buttonPanel  = new JPanel(new GridLayout(1,2));
	
	private JTextArea  textArea = new JTextArea();
	private JScrollPane scrollPane = new JScrollPane(textArea);

	private JLabel classLocationLabel = new JLabel("Class Location");
	private JLabel classRoomLabel     = new JLabel("Class Room Number");
	private JLabel classCapacityLabel = new JLabel("Class Capacity");
	private JTextField classLocationField = new JTextField();
	private JTextField classRoomField     = new JTextField();
	private JTextField classCapacityField = new JTextField();

	private JLabel studentFNameLabel = new JLabel("First name");
	private JLabel studentLNameLabel = new JLabel("Last name");
	private JLabel studentIDLabel = new JLabel("ID Number");
	private JLabel studentMajorLabel = new JLabel("Major");
	private JLabel studentCreditsLabel = new JLabel("Credits");
	private JTextField studentFNameField = new JTextField();
	private JTextField studentLNameField = new JTextField();
	private JTextField studentIDField = new JTextField();
	private JTextField studentMajorField = new JTextField();
	private JTextField studentCreditsField = new JTextField();

	private JButton addButton     = new JButton("Add");
	private JButton displayButton = new JButton("Display");

	Classroom room = null;

// Add Menu
	private JMenuBar menuBar   = new JMenuBar();
	private JMenu    fileMenu  = new JMenu("File");
	private JMenuItem openItem = new JMenuItem("Open");
	private JMenuItem saveItem = new JMenuItem("Save");
   private JFileChooser    fc = new JFileChooser();

//	
	
	
	public ClassRoomFrameWithMenu()
	{
		deptPanel.setPreferredSize(new Dimension(600,50));
		deptPanel.setBorder(new EmptyBorder(new Insets(5,15,5,15)));


		deptPanel.add(classLocationLabel);
		deptPanel.add(classRoomLabel);
		deptPanel.add(classCapacityLabel);
		deptPanel.add(classLocationField);
		deptPanel.add(classRoomField);
		deptPanel.add(classCapacityField);
		
		studentPanel.setBorder(new EmptyBorder(new Insets(5,15,5,15)));
		studentPanel.setPreferredSize(new Dimension(600,50));
		studentPanel.setBorder(new EmptyBorder(new Insets(5,15,5,15)));

		studentPanel.add(studentFNameLabel);
		studentPanel.add(studentLNameLabel);
		studentPanel.add(studentIDLabel);
		studentPanel.add(studentMajorLabel);
		studentPanel.add(studentCreditsLabel);
		studentPanel.add(studentFNameField);
		studentPanel.add(studentLNameField);
		studentPanel.add(studentIDField);
		studentPanel.add(studentMajorField);
		studentPanel.add(studentCreditsField);

		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED));
		scrollPane.setPreferredSize(new Dimension(600,450));
      textArea.setBorder(new EmptyBorder(new Insets(5,15,5,15)));

		buttonPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
		buttonPanel.setPreferredSize(new Dimension(600, 50));
		buttonPanel.add(addButton);
		buttonPanel.add(displayButton);
		addButton.addActionListener(this);
		addButton.setActionCommand("Add");
		displayButton.addActionListener(this);
		displayButton.setActionCommand("Display");
		
		mainPanel.add(deptPanel);		
		mainPanel.add(studentPanel);		
		mainPanel.add(scrollPane);

// add menu
		openItem.addActionListener(this);
		openItem.setActionCommand("open");
		saveItem.addActionListener(this);
		saveItem.setActionCommand("save");
		
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		menuBar.add(fileMenu);		
		add(menuBar, BorderLayout.NORTH);
      fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
//
		add(mainPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);		
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Add"))
		{
			if(room == null)
			{
				room = new Classroom(classLocationField.getText(),
				       Integer.parseInt(classRoomField.getText()),
						 Integer.parseInt(classCapacityField.getText()));
				room.addStudent(
				       new Student(studentFNameField.getText(),		 
						 studentLNameField.getText(),
						 studentIDField.getText(),
						 studentMajorField.getText(),
                   Integer.parseInt(studentCreditsField.getText())));

				classLocationField.setEditable(false);
				classRoomField.setEditable(false);
				classCapacityField.setEditable(false);
				studentFNameField.setText("");		 
				studentLNameField.setText("");
				studentIDField.setText("");
				studentMajorField.setText("");
            studentCreditsField.setText("");
				textArea.setText("Class and first student added.");
			}
			else
			{
				room.addStudent(
				       new Student(studentFNameField.getText(),		 
						 studentLNameField.getText(),
						 studentIDField.getText(),
						 studentMajorField.getText(),
                   Integer.parseInt(studentCreditsField.getText())));
				textArea.setText("Next student added.");

				studentFNameField.setText("");		 
				studentLNameField.setText("");
				studentIDField.setText("");
				studentMajorField.setText("");
            studentCreditsField.setText("");
			}			
		}
		else if(e.getActionCommand().equals("Display"))
		{
			if (room != null)
			{
				textArea.setText(room.toString());
			}
			else
			{
				textArea.setText("Nothing to display");
			}	
		}
      // Add menu functionality
		else if(e.getActionCommand().equals("open"))
		{
			textArea.setText("opening file.");
         int returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION)
         {
            File file = fc.getSelectedFile();
				try
		  		{
					FileInputStream fis = new FileInputStream(file);
					ObjectInputStream ois = new ObjectInputStream(fis);
					room = (Classroom)ois.readObject();
					ois.close(); 
        		} 
				catch(Exception ex){textArea.setText(ex.toString());}	 
			}
         else
         {
            textArea.setText("Open command cancelled by user.\n");
         }
        textArea.setCaretPosition(textArea.getDocument().getLength());
		}
		else if(e.getActionCommand().equals("save"))
		{
			textArea.setText("saving file.");
         int returnVal = fc.showSaveDialog(this);
         if (returnVal == JFileChooser.APPROVE_OPTION)
         {
            File file = fc.getSelectedFile();
            //This is where a real application would save the file.
     			try
      		{
			      FileOutputStream fos = new FileOutputStream(file);
 			      ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(room);
					oos.close();
				} catch(Exception ex){textArea.setText(ex.toString());}	 
         } 
         else
         {
            textArea.append("Save command cancelled by user.\n");
         }
         textArea.setCaretPosition(textArea.getDocument().getLength());
      }
	}
}

class Student implements Serializable
{
	private String firstName, lastName, studentIdNumber, studentMajor;
	private int totalCourseCredits;
	
	//-----------------------------------------------------------------
	// Create an empty studentusing a default constructor.
	//-----------------------------------------------------------------
	public Student ()
	{
	}
	//-----------------------------------------------------------------
	// Creates a Student with the specified information.
	//-----------------------------------------------------------------
	public Student (String name1, String name2, String identification, 
	                String myMajor, int myTotalCredits)
	{
		firstName = name1;
		lastName = name2;
		studentIdNumber = identification;
		studentMajor = myMajor;
		totalCourseCredits = myTotalCredits;
	}

	//-----------------------------------------------------------------
	// Gets and sets first name.
	//-----------------------------------------------------------------
	public void setFirstName()
	{
		Scanner scan = new Scanner (System.in);
		
		System.out.println ("Enter your First Name: ");
		firstName = scan.nextLine();
	}

	//-----------------------------------------------------------------
	// Gets and sets last name.
	//-----------------------------------------------------------------
	public void setLastName()
	{
		Scanner scan = new Scanner (System.in);
		
		System.out.println ("Enter your Last Name: ");
		lastName = scan.nextLine();
	}

	//-----------------------------------------------------------------
	// Gets and sets Total Course Credits.
	//-----------------------------------------------------------------
	public void setTotalCredits()
	{
		Scanner scan = new Scanner (System.in);

		System.out.println ("Enter your Total Credits: ");
		totalCourseCredits = scan.nextInt();
	}
	
	//-----------------------------------------------------------------
	// Gets and sets Student ID Number.
	//-----------------------------------------------------------------
	public void setIdNumber()
	{
		Scanner scan = new Scanner (System.in);

		System.out.println ("Enter your ID Number: ");
		studentIdNumber = scan.nextLine();
	}


	//-----------------------------------------------------------------
	// Gets and sets Student Major.
	//-----------------------------------------------------------------
	public void setMajor()
	{
		Scanner scan = new Scanner (System.in);
		
		System.out.println ("Enter your Major: ");
		studentMajor = scan.nextLine();
	}

   public String toString()
   {
		String s = "First name:     " + firstName + "\n" + 
		           "Last name:      " + lastName + "\n" + 
		           "StudentID:      " + studentIdNumber + "\n" + 
		           "Student Major:  " + studentMajor + "\n" + 
			        "Course Creidts: " + totalCourseCredits + "\n"; 
		return s;
	}
}

class Classroom implements Serializable
{

   private Student[] classRoster;
	private int index = 0;
   private int capacityStudents, roomNumber;
   private String buildingLocation;
      
   //-----------------------------------------------------------------
   // Creates an empty Classroom.
   //-----------------------------------------------------------------
	public Classroom()
	{
     	capacityStudents = 0;
      roomNumber = 0;
      buildingLocation = "";
 	}
   
   //-----------------------------------------------------------------
   // Creates a Classroom with the specified information.
   //-----------------------------------------------------------------
	public Classroom(String location, int room, int cap)
	{
     	capacityStudents = cap;
      roomNumber = room;
      buildingLocation = location;
      classRoster = new Student[capacityStudents];
 	}
   

   //-----------------------------------------------------------------
   // Gets and sets Building Location.
   //-----------------------------------------------------------------
    public void setBuilding()
   {
      Scanner scan = new Scanner (System.in);
      
      System.out.println ("Enter the Building Location: ");
      buildingLocation = scan.next();
   }
   
   //-----------------------------------------------------------------
   // Gets and sets Room Number.
   //-----------------------------------------------------------------
    public void setRoomNumber()
    {
      Scanner scan = new Scanner (System.in);
      
      System.out.println ("Enter the Room Number: ");
      roomNumber = scan.nextInt();
    }
   
   //-----------------------------------------------------------------
   // Sets Capacity of Students.
   //-----------------------------------------------------------------
    public void setCapacityStudents()
    {
      Scanner scan = new Scanner (System.in);
      
      System.out.println ("Enter The Capacity of the Classroom: ");
      capacityStudents = scan.nextInt();
      classRoster = new Student[capacityStudents];
    }
   
   //-----------------------------------------------------------------
   // Gets Capacity of Students.
   //-----------------------------------------------------------------
    public int getCapacityStudents()
    {
      return capacityStudents;
    }
   
   //-----------------------------------------------------------------
   // Adds an Individual Student to the Classroom, checking if the 
   // capacity of the clasroom is full.
   //-----------------------------------------------------------------
    public void addStudent (Student student)
    {
		if(index < capacityStudents)
		{
	 	  classRoster[index] = student;
		  index++;
		}
		else
		{
		  System.out.println("Capacity exceeded! - Student cannot be added.");
		}	
    }

   //-----------------------------------------------------------------
   // Adds an Individual Student to the Classroom, checking if the 
   // capacity of the clasroom is full.
   //-----------------------------------------------------------------
    public String toString()
    {
		StringBuffer sb = new StringBuffer
		                      ("Building:     " + buildingLocation +
		                       "\nClass room: " + roomNumber +
					              "\nCapacity:   " + capacityStudents + "\n\n"
									 ); 
		for(int i = 0; i < classRoster.length; i++)
		{
			if(classRoster[i] != null)
			  sb.append(classRoster[i].toString() + "\n");
		}
		return sb.toString();
    }
}
