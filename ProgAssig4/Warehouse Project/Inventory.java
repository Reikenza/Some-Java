import java.util.*;
import java.text.*;

/**
 * This class represents an inventory of items that each have a quantity and an expiration
 * date. The list explicitly keeps track of both the quantity and expiration date for 
 * every item. No item in the inventory ever has a null date or non-positive quantity. 
 * In the inventory, items are identified by both the item object and their date and
 * .equals is used to compare items and dates. (Items that are the same but have 
 * different expiration dates are kept separately in the inventory.)
 */
public class Inventory<I extends Item>
{
    protected List<DatedItem> inventory;

    /**
     * Builds an empty inventory.
     */
    public Inventory ()
    {
			inventory = new ArrayList<DatedItem>();
    }
	 
    /**
     * Adds an item to the inventory with the specified expiration date and quantity. The item and 
     * date must be non-null and the quantity must be positive or no action is taken. If the item 
     * already exists with the specified expiration date in the inventory, the quantity for that 
     * item (and date) will be increased by the specified quantity.
     * 
     * @param item
     *            an item
     * @param expirationDate
     *            a date
     * @param quantity
     *            a positive item count
     * 
     */
    public void addItem(I item, GregorianCalendar expirationDate, int quantity)
    {
        // Check for invalid parameters.
        if (item == null || expirationDate == null || quantity < 1)
            return;
        
        // Make a dated item for these parameters.
        DatedItem d = new DatedItem (item, expirationDate, quantity);
        
        // Try to find it in the inventory.
        for (DatedItem i : inventory)
            if (i.isSameItemAndDate(d))
            {
                // Found it - increase quantity and quit.
                i.quantity += quantity;
                return;
            }
        
        // Didn't find it - add it to inventory.
        inventory.add(d);
    }
    
    /**
    * Reduces the quantity of an item (specified by item object and date) in the inventory by the 
	* specified quantity. If the item's quantity is reduced to or below 0, the item is removed 
	* from the inventory. If the quantity is not positive, or if the item or date is null, 
	* no action is taken.
    * 
    * @param item
    *            an item
	*
    * @param expirationDate
    *            a date
    * @param quantity
    *            a positive item count
    */
    public void removeItem(I item, GregorianCalendar expirationDate, int quantity) 
    {
        // Check for invalid parameters.
        
        if (item == null || expirationDate == null || quantity < 1)
            return;
        
        // Make a dated item for these parameters.
        
        DatedItem d = new DatedItem (item, expirationDate, quantity);
        
        // Try to find it in the inventory.
        
        for (DatedItem i : inventory)
            if (i.isSameItemAndDate(d))
            {
                // Found it - decrease quantity and remove it if quantity falls below 0.
                
                i.quantity -= quantity;
                if (i.quantity < 1)
                    inventory.remove(i);
                
                return;
            }        
    }
    
    /**
     * This method returns the quantity of this item (with the specified expiration date) in the inventory.
     * 
     * @param item
     *            an item
     * @param expirationDate
     *            a date
     * @return the quantity of that item with that date in the inventory
     */
    public int getQuantity(I item, GregorianCalendar expirationDate)
    {
        // Check for invalid parameters.
        
        if (item == null || expirationDate == null)
            return 0;
        
        // Make a dated item for these parameters.
        
        DatedItem d = new DatedItem (item, expirationDate, 0);
        
        // Try to find it in the inventory.
        
        for (DatedItem i : inventory)
            if (i.isSameItemAndDate(d))
                return i.quantity;// Found it - return the quantity
        
        // Not found.
        
        return 0;
    }
    
    /**
    * This method returns the oldest expiration date for the specified item in the inventory.
	* If multiple matching items are in the inventory with different expiration dates,
	* the oldest expiration date for that kind of item is returned.  If no such item
	* is found in the inventory, null is returned.
    * 
    * @param item an item
    * @return the oldest expiration date for this item
    */
    public GregorianCalendar getDate(I item)
    {
        GregorianCalendar result = null;
        
        // Optimization loop on item dates for the specified item
        
        for (DatedItem i : inventory)
            if (i.item.equals(item) && (result == null || i.expirationDate.before(result)))
                result = i.expirationDate;
                
        return result;
        
    }
    
    /**
     * This method returns an ArrayList of items whose expiration date is before the specified date. 
     * 
     * @param specifiedDate a date
     * @return a list of items whose expiration date is before the specified date
     */
    public ArrayList<I> getItemsExpiredBeforeDate(GregorianCalendar specifiedDate)
    {
        ArrayList<I> result = new ArrayList<I>();
        
        // Find all the past due items.
        
        for (DatedItem i : inventory)
            if (i.expirationDate.before(specifiedDate))
                result.add(i.item);
        
        return result;
    }

    /**
     * Programmer debugging method - not for general use.  Outputs the contents of this inventory
     * to the screen with a title label.
     */
    void outputToConsole (String inventoryName)
    {
        DateFormat formatter = DateFormat.getDateInstance();
        
        System.out.println();
        String s = "Contents of Inventory: " + inventoryName;
        System.out.println (s);
        for (int i = 0; i < s.length(); i++)
            System.out.print ('-');
        System.out.println();
        for (DatedItem i : inventory)
            System.out.printf ("Item: %-40s Item Type: %-10s Critical date: %-13s  Qty: %3s\n", i.item.getUPC(), i.item.getClass().getSimpleName(), formatter.format(i.expirationDate.getTime()), i.quantity);        
        System.out.println();
    }
    
    /**
     * Nested class to represent items with dates and quantities.
     * 
     * Equality is defined by == only.
     */    
    private class DatedItem
    {
        // Instance variables
        
        private I item;
        public GregorianCalendar expirationDate;
        private int quantity;
        
        /**
         * Builds a dated item with the specified item, date, and quantity.
         * 
         * @param item
         *            an item of type I
         * @param expirationDate
         *            the expiration date for this item
         * @param quantity
         *            the quantity of this item
         */
        public DatedItem (I item, GregorianCalendar expirationDate, int quantity)
        {
            this.item = item;
            this.expirationDate = expirationDate;
            this.quantity = quantity;
        }
        
        /**
         * Returns true if this dated item and the other dated item share the same items and dates.
         * 
         * @param other
         *            some other dated item
         * @return true if they are equivalent items and dates
         */
        public boolean isSameItemAndDate (DatedItem other)
        {
            return item.getUPC().equals(other.item.getUPC()) && expirationDate.equals(other.expirationDate);
        }        
    }
    
}