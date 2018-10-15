import java.util.*;
import java.io.*;
import java.text.*;

/**
 * This class parses the transaction file. It calls the appropriate method to handle 
 * each transaction record.
 */
public class TransactionReader
{
	private Scanner scan;

   private HashMap<String, Inventory<FoodItem>> warehouses;
   private HashMap<String, FoodItem> foodItems;

	private Date transactionDate       = null;
	private GregorianCalendar calendar = null;
	
	
/**
 * This constructor instantiates two HashMaps; one to store the names of warehouses
 * along with their inventory, and another one to store the UPC along with the 
 * FoodItem.
 */
	public TransactionReader(File file) throws FileNotFoundException
	{
		scan = new Scanner(file);
		warehouses = new HashMap<String, Inventory<FoodItem>>();
		foodItems  = new HashMap<String, FoodItem>();
	}

	/**
	 * Reads the transaction file and calls the appropriate method. 
	 */
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
		
		/********************************************************************** 
		/*****    Debugging Output   ******************************************/ 
				Set<String> keys2 = warehouses.keySet();
				Iterator<String> iter4 = keys2.iterator();
				while(iter4.hasNext())
				{
					String warehouseName = iter4.next();
					Inventory<FoodItem> inventory = warehouses.get(warehouseName);
					inventory.outputToConsole(warehouseName);	
				}			
		//********************************************************************/ 
	}

	/**
	 * Reads and parses the food item record. Add code in this method to store
	 * the UPC code along with the FoodItem. 
	 */
	public void readFoodItem(String line)
	{
		Scanner parser = new Scanner(line);
		parser.next();
		parser.next();
		parser.next();
		parser.next();
		String upc = parser.next().trim();
		parser.next();
		parser.next();
		int shelfLife = new Integer(parser.next()).intValue();
		parser.next();
		String name = parser.nextLine().trim(); 
	}
	
	/**
	 * Reads and parses the warehouse record. Add code in this method to store the 
	 * warehouse name and a new Inventory object.
	 */
	public void readWarehouse(String line)
	{
		Scanner parser = new Scanner(line);
		parser.next();
		parser.next();
		String name = parser.next().trim(); 
	}
	
	/**
	 * Reads and parses the start date record and creates a GergorianCalendar object
	 * representing the start date. 
	 */
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
	}
	
	/**
	 * Reads and parses the receive food item record. Add code in this method to add
	 * food items to the inventory. Use the {@link Inventory#addItem} method.
	 */
	public void readReceive(String line)
	{
		Scanner parser = new Scanner(line);
		parser.next();
		String upc = parser.next().trim(); 
		int quantity = new Integer(parser.next().trim()).intValue();
		String warehouse = parser.next().trim(); 
	}
	
	/**
	 * Reads and parses the request food item record. Add code in this method to remove
	 * food items from inventory. Remember to remove oldest items first that have not
	 * already expired. Use the {@link Inventory#removeItem} method.
 	 */
	public void readRequest(String line)
	{
		Scanner parser = new Scanner(line);
		parser.next();
		String upc = parser.next().trim(); 
		int quantityRequested = new Integer(parser.next()).intValue();
		String warehouse = parser.next().trim(); 
	}
	
	/**
	 * Reads and parses the next day record and advances the current day by one day.
	 */
	public void readNextDay(String line)
	{
		Scanner parser = new Scanner(line);
		String nextday = parser.nextLine().trim(); 
		calendar.add(Calendar.DAY_OF_MONTH, 1);
	}
	
	/**
	 * Reads the end of transaction records.
	 */
	public void readEnd(String line)
	{
		Scanner parser = new Scanner(line);
		String end = parser.next().trim(); 
	}
	
	public static void main(String[] args)throws FileNotFoundException
	{
		TransactionReader tr = 	new TransactionReader(new File("data3.txt"));
		tr.readAllTransactions();
	}
}