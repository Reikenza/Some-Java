// Debora Urrutia    10/20/2015
// File Name: Driver.Java
import java.util.*;
public class Driver implements Runnable
{
   private static int counter;
   //private static Random R = new Random();
   private int customersDriven;
   private int ID;
   private Object someObject;
   private boolean onDuty = false;
   
   public Driver(Object someObject)
   {
      this.ID       = ++counter;
      this.someObject = someObject;
      this.customersDriven = 0;
   }
   
   boolean isOnDuty() 
   {
   return onDuty;
   }
   
   public void onDuty( boolean b)
   {
      onDuty = b;
      if(onDuty)
         System.out.println(ID + " is on duty.");
      else   
         System.out.println(ID + " is off duty.");
   }
   
   public void run()
   {
      onDuty(true);
      while(customersDriven < 4)
      {
         try
         {
            synchronized(someObject)
            {
               someObject.wait();
               System.out.println(ID + " has been notified.");
            }
            if(Dispatcher.customerRequiresPickUp())
            { 
               Customer temp = Dispatcher.Next();      
               System.out.println("Customer has been picked up by driver #" + ID);
               
               ++customersDriven;  
              // Thread.sleep(R.nextInt(50) + 1000);
               Thread.sleep(50000);
               
            }
            else  
               System.out.println("Driver #" + ID + " will continue to wait for a customer.");
               
         }
         catch(InterruptedException e)
         {
         }

      }
      onDuty(false); 
   }
}
