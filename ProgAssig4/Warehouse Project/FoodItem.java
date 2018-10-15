import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * A food item object is an Item object that has a name, a UPC code, and a shelf life.
 * FoodItem objects can be added to an inventory.
 */
public class FoodItem extends Item implements Comparable<FoodItem>
{
	private int shelfLife;

	/**
	* Builds a food item with the specified name, UPC code, and shelf life (in days).
	* 
	* @param name the name of this food item
	* @param upc the UPC code printed on the packaging
	* @param shelfLife the number of days this item will stay fresh
	*/
	public FoodItem (String name, String upc, int shelfLife)
	{
		super(upc,name);
		this.shelfLife = shelfLife;
	}

	/**
	* Returns the universal product code for this food item.
	* 
	* @return the upc for this item
	*/
	public String getUPC()
	{
		return upc;
	}

	/**
	* Returns the name code for this food item.
	* 
	* @return the name code for this item
	*/
	public String getName()
	{
		return name;
	}

	/**
	* Returns the shelf life code for this food item.
	* 
	* @return the shelf life for this item
	*/
	public int getShelfLife()
	{
		return shelfLife;
	}

	/**
	* Given the date the food item was received, this method will return the corresponding 
   * expiration date for this food item.  The item expires on the beginning of the 
   * expiration date. This method can be used repeatedly to compute expiration dates for 
   * different manufacturing dates.
	* 
	* @param dateReceived the day the item was received into the warehouse.
	* @return the expiration date
	*/
	public GregorianCalendar getExpirationDate(GregorianCalendar dateReceived)
	{
		GregorianCalendar result = (GregorianCalendar) dateReceived.clone();
		result.add(Calendar.DAY_OF_MONTH, shelfLife);
		return result;
	}
    
	/**
	* Compares this FoodItem to another FoodItem to determine relative order (for sorting
	* food items).  FoodItems are ordered first by UPC, then in the case of ties by name, and by shelf life last. 
	* Zero is returned if this food item is equal to the other food item.  A negative integer is returned if
	* this food item is less than the other food item.  A positive integer is returned if this food item is
	* greater than the other food item. 
	* 
	* @param other an item to compare this item to
	* @return -1, 0, or 1 if this item is less than, equal to, or greater than the other item
	*/
	public int compareTo(FoodItem other)
	{
		int result;
    
		result = upc.compareTo(other.upc);
		if (result != 0)
			return result;
    
		result = name.compareTo(other.name);
		if (result != 0)
			return result;
    
		if (this.shelfLife < other.shelfLife)
			return -1;
		else if (this.shelfLife > other.shelfLife)
			return 1;
		else
			return 0;
	}

	/**
	* Returns true if this FoodItem represents the same food item
	* as the other object, false otherwise.
	* 
	* @return true if these items represent the same food
	*/
	public boolean equals (Object other)
	{
		// If they are the same reference, they are the same item.
    
		if (this == other)
			return true;
    
		// If the other object is not a food item, they cannot be equal.
    
		if (!(other instanceof FoodItem))
			return false;
    
		// Use the comparison method - equal if result is 0.
    
		FoodItem food = (FoodItem) other;
		    
		return this.compareTo(food) == 0;        
	}

	/**
	* Returns a hash code for this food item.  Equal food items are
	* guaranteed to have equal hash codes.
	* 
	* @return this item's hash code
	*/
	public int hashCode ()
	{
		return upc.hashCode() + name.hashCode() + shelfLife;
	}

	/**
	* Returns a String that describes this FoodItem object.
	* 
	* @return a String describing this object
	*/
	public String toString ()
	{
 		return "FoodItem - UPC Code: " + upc + "  Shelf life: " + shelfLife + "  Name: " + name;
	}
}
