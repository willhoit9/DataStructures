
package classes;

import java.util.*;

public class CarWash
{
   public static void main(String[ ] args)
   {
      Scanner kb = new Scanner (System.in);
      System.out.println("Enter wash time: ");
      int WASHTIME = kb.nextInt();
      System.out.println("Enter the number of servers: ");
      int SERVERNUMBER = kb.nextInt();
      System.out.println("Enter arrival probability: ");
      double ARRIVALPROB = kb.nextDouble();
      System.out.println("enter time for simulation: ");
      int TOTALTIME = kb.nextInt();
      
      carWashSimulate(WASHTIME, SERVERNUMBER, ARRIVALPROB, TOTALTIME);
   }
    
   public static void carWashSimulate
   (int washTime, int serverNumber, double arrivalProb, int totalTime)
   {
      Queue<Integer> arrivalTimes = new LinkedList<Integer>( );  
      int next;
      Queue<Server> servers = new LinkedList<>();
       for (int i = 0; i < serverNumber; i++) {
           String name = "" + (i + 1);
           Server s = new Server(washTime, name);
           servers.add(s);
       }
      ClientGenerator arrival = new ClientGenerator(arrivalProb);
     // Server machine = new Server(washTime);
      Server machine;
      Averager waitTimes = new Averager( );
      int currentSecond;
  
      // Write the parameters to System.out.
      System.out.println("Seconds to wash one car: " + washTime);
      System.out.println("Number or servers: " + serverNumber);
      System.out.print("Probability of customer arrival during a second: ");
      System.out.println(arrivalProb);
      System.out.println("Total simulation seconds: " + totalTime); 
   
      // Check the precondition:
      if (washTime <= 0 || arrivalProb < 0 || arrivalProb > 1 || totalTime < 0 || serverNumber < 1)
         throw new IllegalArgumentException("Values out of range"); 

      for (currentSecond = 0; currentSecond < totalTime; currentSecond++)
      {  // Simulate the passage of one second of time.
         // Check whether a new customer has arrived.
         if (arrival.query( )){                      // if new client comes this second...
            arrivalTimes.add(currentSecond);         //
            System.out.println("Customer arrived at " + currentSecond);
        }
         // Check whether we can start washing another car.
        if ((!servers.peek().isBusy( ))  &&  (!arrivalTimes.isEmpty( )))
         {
            
            
            machine = servers.remove();
            next = arrivalTimes.remove( );
            waitTimes.addNumber(currentSecond - next);
            machine.start( );
            System.out.println("Server " + machine.getName() + " started at " + currentSecond);
            servers.add(machine);
            machine = null;
         }
         // Subtract one second from the remaining time in the current wash cycle.
        
        
          for (Server server : servers) {
            if (server.isBusy())
                {
                   server.reduceRemainingTime( ); 
                }  
          }
         
         
         
//         if ((!machine.isBusy( ))  &&  (!arrivalTimes.isEmpty( )))
//         {
//            next = arrivalTimes.remove( );
//            waitTimes.addNumber(currentSecond - next);
//            machine.start( );
//            System.out.println("Server started at " + currentSecond);
//         }
//         // Subtract one second from the remaining time in the current wash cycle.
//         machine.reduceRemainingTime( );
      }
      // Write the summary information about the simulation.
      
      
      
      
      System.out.println("Customers served: " + waitTimes.howManyNumbers( )); 
      if (waitTimes.howManyNumbers( ) > 0)
         System.out.println("Average wait: " + waitTimes.average( ) + " sec");
   } 
}