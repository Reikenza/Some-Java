public class RunPingPong2
{
   public static void main (String[] args)
   {
      Ping ping = new Ping();
      Pong pong = new Pong();
      new Thread(ping).start();
      new Thread(pong).start();
   }
}

class SomeResource
{
   public static SomeResource resource = new SomeResource();
} 
  
class Ping implements Runnable
{
   private String word = "Ping";
   private int delay = 250;
   
   public void run() 
   {
      synchronized(SomeResource.resource){
      try 
      {
        for (;;) {
          Thread.sleep(delay);
          System.out.println(word + " ");
          SomeResource.resource.notifyAll();
          SomeResource.resource.wait();
        }
      } catch (InterruptedException e) {
          return;
        }
      }  
   }
}

class Pong implements Runnable
{
   private String word = "Pong";
   private int delay = 1000;

   public void run() {
      synchronized(SomeResource.resource){
      try {
        for (;;) {
          Thread.sleep(delay);
          System.out.println(word + " ");
          SomeResource.resource.notifyAll();
          SomeResource.resource.wait();
        }
      } catch (InterruptedException e) {
          return;
        }
      }  
   }
}

