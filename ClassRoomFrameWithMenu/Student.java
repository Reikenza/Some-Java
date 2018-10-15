import java.io.*;
import java.util.*;
public class Student implements Serializable
{
	private static final long serialVersionUID = 123l;
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

