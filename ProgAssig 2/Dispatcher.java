// Debora Urrutia    10/20/2015
// File Name: Dispatcher.Java
import java.util.*;
import java.util.concurrent.*;
public class Dispatcher
{  
   static ConcurrentLinkedQueue<Customer> queue;
//   private static boolean customerNeedsPickUp = false;
   public static boolean customerRequiresPickUp()
   {
      return !queue.isEmpty();
   }
   
   public static Customer Next()
   {
      return queue.poll();
   }   
   
   static Driver[] drivers;// = new Driver[driverCount];
   static final int driverCount = 5;
   static final int CustomerMax = 4;
   static boolean someone_on_duty=true;
   public static void main(String[] args)
   {
      queue = new ConcurrentLinkedQueue<Customer>();
      Scanner s = new Scanner(System.in);
      Object o = new Object();
      String response;
   
      drivers = new Driver[driverCount];
      for(int i = 0; i < drivers.length ; ++i)
      {
      drivers[i] = new Driver(o);
       new Thread(drivers[i]).start();
      }
      System.out.println("Enter 'quit' to end");
      
     // static bool someone_on_duty=true;
  
      while(someone_on_duty)
      {     
         System.out.println("Do you need a ride?");
         response = s.next();   
         if(response.equals("quit")) System.exit(0);
         if(response.equals("yes"))
         {
            System.out.println("What is your name?");
            String res = s.next();  
      
            System.out.println("What is your destination?");
            String r1 = s.next();   
   
            System.out.println("What is your pick up address?");
            String r2 = s.next();   

            System.out.println("Thank you. A driver will be picking you up shortly");
            
            System.out.println("Customer "+res+" was driven from "+r2+" to "+r1);
            
            queue.add(new Customer(res, r1, r2));
            
            synchronized(o)
            {
               o.notifyAll();
            } 
            
            int available_drivers=0;
            someone_on_duty = false;
            for (int i=0; i < drivers.length; ++i)
            {
               if(drivers[i].isOnDuty())
                  available_drivers ++;
               if (available_drivers>0)
                  someone_on_duty = true;           
            }        
         }
      }   
   }
}

