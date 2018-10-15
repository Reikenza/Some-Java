import java.io.*;
import java.util.*;
public class Classroom implements Serializable
{
	private static final long serialVersionUID = 123l;
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
}	 

