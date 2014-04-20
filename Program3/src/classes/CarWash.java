
package classes;

//Andrew Willhoit - Data Structures 
//Queue Simulation - CarWash.java
//Based on Main and Sheller's code
//4/14/14



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
      System.out.println("Enter your average arrival probability"
              + "\nThis will change automatically based on time of day: ");
      double ARRIVALPROB = kb.nextDouble();
      
      System.out.println("What's the high range on line length before a customer leaves: ");
      int MAXLINE = kb.nextInt();
      System.out.println("What's the lowest line number you've seen a customer leave because: ");
      int MINLINE = kb.nextInt();
      
      System.out.println("enter time for simulation: ");
      int TOTALTIME = kb.nextInt();
      
      carWashSimulate(WASHTIME, WASHCOST, SERVERNUMBER, SERVERHOURLYCOST, ARRIVALPROB, MAXLINE, MINLINE, TOTALTIME);
   }
    
   public static void carWashSimulate
   (int washTime, double washCost, int serverNumber, double serverHourlycost, double arrivalProb,int maxLine, int minLine, int totalTime)
   {
      int custID = 1;
   //   Queue<Integer> arrivalTimes = new LinkedList<Integer>( );  
      Queue<Customer> arrivalTimes = new LinkedList<>();  
      
      int next;
      Queue<Server> servers = new LinkedList<>();
       for (int i = 0; i < serverNumber; i++) {
           int name = (i + 1);
           Server s = new Server(washTime, name, serverHourlycost);
           servers.add(s);
       }
      ClientGenerator arrival = new ClientGenerator(arrivalProb);

      Server machine;
      Averager waitTimes = new Averager( );
      int currentSecond;

      double serversCostToday = 0;
    //  boolean arrived = false;
      boolean breaks = true;


      
     // int [] hasHadBreaks = new int[serverNumber];
      LinkedList<Integer> hasHadBreaks = new LinkedList<>();
      
      // Write the parameters to System.out.
      System.out.println("Seconds to wash one car: " + washTime);
      System.out.println("Number or servers: " + serverNumber);
      System.out.print("Probability of customer arrival during a second: ");
      System.out.println(arrivalProb);
      System.out.println("Total simulation seconds: " + totalTime);
   
      // Check the precondition:
      if (washTime <= 0 || arrivalProb < 0 || arrivalProb > 1 || totalTime < 0 || serverNumber < 1 || maxLine < 0 || minLine < 0 )
         throw new IllegalArgumentException("Values out of range"); 


   //   int tempServerNumber = serverNumber;
   //   tempServerNumber = 1; 
      
      
      // break times were calculated in a way so that, ideally, each server
      // will be given a break, and this break should be an appropriate length
      // depending on how long it will be running today 
      int breakTime = (int)((totalTime - (washTime * (1.5))) / serverNumber)/2 ; 
      
      // this wouldn't break the program if removed, but If the run time is so
      // short that it would cause this, breaks wouldn't really be needed.
      if (breakTime < 5) {
          System.out.println("In the current configuration, having break times"
                  + "is not viable. "); 
          breaks = false;
      } else {
          System.out.println("Breaktime is: " + breakTime);
      }
      

      
      Stack <Integer> whosBreak = new Stack();
      for (int i = serverNumber ; i >= 1 ; i--)
      {
         // System.out.println(i);
          whosBreak.push(i);
      }
      
      
      
      for (currentSecond = 0; currentSecond < totalTime; currentSecond++)
      {  // Simulate the passage of one second of time.         
        
        //System.out.println("\nCurrent Second: " + currentSecond);
        
        if (breaks == true) {

            boolean anyOnBreak = false;
            for (Server server : servers) { 
                if (server.isOnBreak() ) {
                    anyOnBreak = true;
                    //System.out.println("Server Breakin: " + server.getName());
                } 
            }

            if (anyOnBreak == false) {           
                for (Server server : servers) { 
                    if ((anyOnBreak== false) && (!whosBreak.empty()) && (server.getName() == whosBreak.peek()) && (!server.isBusy()) ){
                        server.setOnBreak(true, breakTime);
                        System.out.println("SERVER " + server.getName() + " is on BREAK!!!");
                        whosBreak.pop();
                        anyOnBreak = true;
                    }
                }
            }
        } // end if (breaks)
        
        
         // Check whether a new customer has arrived.
       //  arrived = false; // this resets back to false every second
         if (arrival.query( )){                      // if new client comes this second...
        //    arrived = true; // if someone arrives here, set to true

            int arrivedCustID = custID++;
            int arrivedCurrentSecond = currentSecond;
            System.out.println("Customer "+ (arrivedCustID)  +" arrived at " + (arrivedCurrentSecond));
            
            waitTimes.showUp(); // someone has shownup, they may get in line or leave...
            int waitTolerance = (int)(Math.random() * (maxLine - minLine)) + minLine;
            if (arrivalTimes.size() < waitTolerance) {
                
                arrivalTimes.add(new Customer(arrivedCustID, arrivedCurrentSecond));         //
                System.out.println("Customer "+ (arrivedCustID)  +" got in line at " + (arrivedCurrentSecond));
        
                
           } else {
                waitTimes.leftBecauseLongLine();
                System.out.println("Customer "+ (arrivedCustID)  +" left at "
                        + "" + (arrivedCurrentSecond) + " because the line was longer than " + waitTolerance);
                System.out.println("Line length is currently: " + arrivalTimes.size());
            }
                 
          }
         
         

          for (int i = 0; i < servers.size(); i++) {
              if (servers.peek().isOnBreak()) {
                  machine = servers.remove();
                  servers.add(machine);
              }
          }


        // Check whether we can start washing another car.
        if ( (!servers.peek().isBusy()) && (!arrivalTimes.isEmpty()) && (!servers.peek().isOnBreak())) {

            machine = servers.remove();

            Customer tempCustomer = arrivalTimes.remove();
            next = tempCustomer.getArrivalTime();
            
            waitTimes.addNumber(currentSecond - next);
            machine.start();
            System.out.println("Server " + machine.getName() + " started "
                    + "at " + currentSecond + " on Customer " + (tempCustomer.getCustomerID()) + "'s car.");

            servers.add(machine);

          } 

          // Subtract one second from the remaining time in the current wash cycle.
          for (Server server : servers) {
              if (server.isBusy()) {
                  server.reduceRemainingTime();
              }
              if (server.isOnBreak()) {
                server.reduceBreakTime();
              }
          }
         
         

      } //end for loop (for each second)
      
      
      
      
      // Write the summary information about the simulation.
      

      double moneyMade = (waitTimes.howManyNumbers() * washCost);
      double moneyNotMadeBecauseLongLine = (waitTimes.howManyLeft() * washCost);
      double moneyNotMadeBecauseStillInLine = (arrivalTimes.size() * washCost);
      
      
      
      System.out.println("\nDetails about simulation:\n");
      
      System.out.println("Customers showed up today: " + waitTimes.getTotalShowedUp()); 
    
      System.out.println("Customers served: " + waitTimes.howManyNumbers( ));
      
      System.out.println("Customers who left because long line: " + waitTimes.howManyLeft());
      
       System.out.println("Customers who were still in line at closing time: " + arrivalTimes.size());
      
      if (waitTimes.howManyNumbers( ) > 0) {
          System.out.println("Average wait: " + waitTimes.average( ) + " sec");
          
      }
      

       System.out.println("Money made: $" + moneyMade); 
       
       System.out.println("Potential not made because of impatient "
               + "customers: $" + moneyNotMadeBecauseLongLine);
       

       
       System.out.println("Potential not made because still in line "
         + "at closing time: $" + moneyNotMadeBecauseStillInLine);
       
       
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
       
       
       
   } // end Simulate()
}



// TODO - 4/17/14
// if at a certain percentage of the time left in the simulation,
// there are still cars in the line, stop letting new customers in line.
// those customers that are in line should finish before the shop closes