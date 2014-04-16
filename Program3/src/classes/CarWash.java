
package classes;

import java.util.*;

public class CarWash
{
   public static void main(String[ ] args)
   {
      Scanner kb = new Scanner (System.in);
      System.out.println("Enter wash time: ");
      int WASHTIME = kb.nextInt();
      System.out.println("Enter wash cost: ");
      double WASHCOST = kb.nextDouble(); 
      System.out.println("Enter the number of servers: ");
      int SERVERNUMBER = kb.nextInt();
      System.out.println("Enter how much a server cost to run an hour: ");
      double SERVERHOURLYCOST = kb.nextDouble();
      System.out.println("Enter how long server breaks last: ");
      int BREAKTIME = kb.nextInt();
      System.out.println("Enter arrival probability: ");
      double ARRIVALPROB = kb.nextDouble();
      System.out.println("enter time for simulation: ");
      int TOTALTIME = kb.nextInt();
      
      carWashSimulate(WASHTIME, WASHCOST, SERVERNUMBER, SERVERHOURLYCOST, BREAKTIME, ARRIVALPROB, TOTALTIME);
   }
    
   public static void carWashSimulate
   (int washTime, double washCost, int serverNumber, double serverHourlycost, int breakTime, double arrivalProb, int totalTime)
   {
      int custID = 1;
      Queue<Integer> arrivalTimes = new LinkedList<Integer>( );  
      int next;
      Queue<Server> servers = new LinkedList<>();
       for (int i = 0; i < serverNumber; i++) {
           String name = "" + (i + 1);
           Server s = new Server(washTime, name, serverHourlycost);
           servers.add(s);
       }
      ClientGenerator arrival = new ClientGenerator(arrivalProb);
     // Server machine = new Server(washTime);
      Server machine;
      Averager waitTimes = new Averager( );
      int currentSecond;
      double moneyMade = 0;
      double moneyNotMade = 0;
      double serversCostToday = 0;
      boolean arrived = false;
      
      // Write the parameters to System.out.
      System.out.println("Seconds to wash one car: " + washTime);
      System.out.println("Number or servers: " + serverNumber);
      System.out.print("Probability of customer arrival during a second: ");
      System.out.println(arrivalProb);
      System.out.println("Total simulation seconds: " + totalTime); 
   
      // Check the precondition:
      if (washTime <= 0 || arrivalProb < 0 || arrivalProb > 1 || totalTime < 0 || serverNumber < 1 || breakTime < 0)
         throw new IllegalArgumentException("Values out of range"); 

      for (currentSecond = 0; currentSecond < totalTime; currentSecond++)
      {  // Simulate the passage of one second of time.
         // Check whether a new customer has arrived.
         arrived = false;
         if (arrival.query( )){                      // if new client comes this second...
            arrived = true;
            arrivalTimes.add(currentSecond);         //
            System.out.println("Customer "+ (custID++)  +" arrived at " + currentSecond);
            waitTimes.showUp();
        }
         // Check whether we can start washing another car.
        if (arrived == true & (!servers.peek().isBusy( ))  &&  (!arrivalTimes.isEmpty( )))
         {
            // added arrive == true, because only 1 server can work on a 1 car at a time
            // multiple servers cannot work on the same car.
            
            machine = servers.remove();
            next = arrivalTimes.remove( );
            waitTimes.addNumber(currentSecond - next);
            
            machine.start( );
            System.out.println("Server " + machine.getName() + " started "
                    + "at " + currentSecond + " on Customer " + (custID-1) + "'s car.");
       //     moneyMade = moneyMade + washCost;
            servers.add(machine);
            // machine = null;
         } else if (!arrivalTimes.isEmpty() & arrived == true) {
             System.out.println("Customer "+ (custID-1)  +" left ");
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
      
      
      moneyMade = (waitTimes.howManyNumbers() * washCost);
      moneyNotMade = (waitTimes.howManyLeft() * washCost);
      
      System.out.println("Customers served: " + waitTimes.howManyNumbers( )); 
      if (waitTimes.howManyNumbers( ) > 0) {
          System.out.println("Average wait: " + waitTimes.average( ) + " sec");
          System.out.println("Customers who left because long line: " + waitTimes.howManyLeft());
      }
       System.out.println("Money made: $" + moneyMade);   
       System.out.println("Potential not made because of impatient "
               + "customers: $" + moneyNotMade);
       
       
       
       for (Server server : servers) {
          serversCostToday += server.costToday();
           
           System.out.println("Server " + server.getName() + " ran "
                    + "" + server.getTimesRanToday()+ " times today");
            System.out.println("Server " + server.getName() + " cost "
                   + "$" + server.costToday() + " to run today");
       }
       System.out.println("Server Cost Today: $" + serversCostToday);
       
       System.out.println("Money made today, minus the cost of running "
               + "those servers: $" + (moneyMade - serversCostToday)); 
       
       
   } 
}

