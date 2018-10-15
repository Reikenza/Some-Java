/**
 * This class overrides the equals() and hashcode() methods so that it
 * wil be properly handled by the various Collection classes.
 * All items have a upc, and all items can be compared for equality.
 * To use certain collection 
 * You will inherit from this class.
 * Items are immutable.
 */
public class Item
{
	// Instance variables
	protected String upc;   // Keeping the upcCode as a String simplifies the code.
	protected String name;  
    
	/* Constructor */

	/**
	* Constructs a general purpose item with the
	* given universal product code.
	* 
	* @param name a upc for this item
	*/
	public Item (String upc, String name)
	{
		this.upc = upc;
		this.name = name;
	}
	    
	/* Accessor method */
    
	/**
	* Returns the upc of this item as a String.
	* 
	* @return the upc of this item
	*/
	public String getUPC ()
	{
		return upc;
	}
    
	/**
	* Returns the name of this item as a String.
	* 
	* @return the name of this item
	*/
	public String getName ()
	{
		return name;
	}
    
	/* Equality methods */
  
	/**
	* Returns true if this item and the other object
	* both represent the same item.
	* Note that it is generally necessary to override the hashCode method whenever this method 
   * is overridden, so as to maintain the general contract for the hashCode method, which states 
   * that equal objects must have equal hash codes.
	* @return true if the items are equal
	*/
	public boolean equals (Object other)
	{
 		// If they are the same reference, they are the same item.
		if (this == other)
			return true;
			// If the other object is not an item, they cannot be equal.
		if (!(other instanceof Item))
			return false;
		// For simple items, the items are equal if their upc's are equal.
		Item otherItem = (Item) other;
		return this.upc.equals(otherItem.upc);
	}
    
	/**
	* Returns a hashCode that is guaranteed to be equal for items that are equal.
	* This method returns the hash code value for the object on which this method is invoked. 
   * This method returns the hash code value as an integer and is supported for the benefit 
   * of hashing based collection classes such as Hashtable, HashMap, HashSet etc. 
   * This method must be overridden in every class that overrides the equals method
	* @return the hash code for this item
	*/
	public int hashCode ()
	{
		return upc.hashCode();
	}
}
