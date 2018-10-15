// Debora Urrutia    10/20/2015
// File Name: Customer.Java
import java.util.*;
public class Customer
{
   private String Name;
	private String PickLocation;
	private String DropLocation;

	public Customer(String name, String PickLocation, String DropLocation)
	{
		this.Name = Name;
		this.PickLocation = PickLocation;
		this.DropLocation = DropLocation;

	}
	public String getName()
	{
		return Name;
	}
	public String getPickLocationt()
	{
		return PickLocation;
	}
	public String getDropLocationt()
	{
		return DropLocation;
	}


}


