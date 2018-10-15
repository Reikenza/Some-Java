import java.util.*;
import java.io.*;
import java.text.*;

public class TransactionReader<I extends Item>
{
	private Scanner scan;

   private HashMap<String, Inventory<FoodItem>> warehouses;
   private HashMap<String, FoodItem> foodItems;

	private Date transactionDate       = null;
	private GregorianCalendar calendar = null;
	
	
	public TransactionReader(File file) throws FileNotFoundException
	{
		scan = new Scanner(file);
		warehouses = new HashMap<String, Inventory<FoodItem>>();
		foodItems  = new HashMap<String, FoodItem>();
	}

	public void readAllTransactions()
	{
		String line;
		while(scan.hasNextLine())
		{
			line = scan.nextLine();
			if(line.startsWith("FoodItem - "))			readFoodItem(line);
			else if(line.startsWith("Warehouse - "))	readWarehouse(line);
			else if(line.startsWith("Start date: "))	readStartDate(line);
			else if(line.startsWith("Receive: "))		readReceive(line);
			else if(line.startsWith("Request: "))		readRequest(line);
			else if(line.startsWith("Next day:"))		readNextDay(line);
			else if(line.startsWith("End"))				readEnd(line);
			else System.out.println("Unrecognized Transaction");
		}	
		Set<String> keys = warehouses.keySet();
		Iterator<String> iter = keys.iterator();
		while(iter.hasNext())
		{
			String warehouseName = iter.next();
			Inventory<FoodItem> inventory = warehouses.get(warehouseName);
			inventory.outputToConsole(warehouseName);	
		}			
	}

	public void readFoodItem(String line)
	{
		Scanner parser = new Scanner(line);
		parser.next();
		parser.next();
		parser.next();
		parser.next();
		String upc = parser.next().trim();
		System.out.println(upc);
		parser.next();
		parser.next();
		int shelfLife = new Integer(parser.next()).intValue();
		System.out.println(shelfLife);
		parser.next();
		String name = parser.nextLine().trim(); 
		System.out.println(name);
		FoodItem foodItem = new FoodItem(name, upc, shelfLife);
		foodItems.put(upc,foodItem);
	}
	
	public void readWarehouse(String line)
	{
		Scanner parser = new Scanner(line);
		parser.next();
		parser.next();
		String name = parser.next().trim(); 
		System.out.println(name);
		warehouses.put(name, new Inventory<FoodItem>());
	}
	
	public void readStartDate(String line)
	{
		Scanner parser = new Scanner(line);
		parser.next();
		parser.next();
		String d = parser.next().trim();
		try{ transactionDate = new SimpleDateFormat("MM/dd/yyyy").parse(d);}
		catch(ParseException e){System.out.println("Could not parse date ");}
		calendar = new GregorianCalendar();
		calendar.setTime(transactionDate);
		System.out.println("Date is " + transactionDate);
	}
	
	public void readReceive(String line)
	{
		Scanner parser = new Scanner(line);
		parser.next();
		String upc = parser.next().trim(); 
		System.out.println(upc);
		int quantity = new Integer(parser.next().trim()).intValue();
		System.out.println(quantity);
		String warehouse = parser.next().trim(); 
		System.out.println(warehouse);
		FoodItem foodItem = foodItems.get(upc);
      Inventory<FoodItem> inventory = warehouses.get(warehouse);
		GregorianCalendar	criticalDate = foodItem.getExpirationDate(calendar);
		inventory.addItem(foodItem, criticalDate, quantity);
	}
	
	public void readRequest(String line)
	{
		Scanner parser = new Scanner(line);
		parser.next();
		String upc = parser.next().trim(); 
		System.out.println(upc);
		int quantity = new Integer(parser.next()).intValue();
		System.out.println(quantity);
		String warehouse = parser.next().trim(); 
		System.out.println(warehouse);
	}
	
	public void readNextDay(String line)
	{
		Scanner parser = new Scanner(line);
		String nextday = parser.nextLine().trim(); 
		System.out.println(nextday);
	}
	
	public void readEnd(String line)
	{
		Scanner parser = new Scanner(line);
		String end = parser.next().trim(); 
		System.out.println(end);
	}
	
	public static void main(String[] args)throws FileNotFoundException
	{
		TransactionReader tr = 	new TransactionReader(new File("data1.txt"));
		tr.readAllTransactions();
	}
}