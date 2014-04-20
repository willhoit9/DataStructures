package classes;

import java.math.BigDecimal;
import java.math.RoundingMode;

//Andrew Willhoit - Data Structures 
//Queue Simulation - Server.java
//Based on Main and Sheller's code
//4/14/14

public class Server
{
   private int secondsForService; // Seconds for a single wash
   private int timeLeft;   // Seconds until this Server is no longer busy   
   // added to keep track of different servers - AW
   private int name;
   // added to allow servers to take breaks - AW
   private boolean onBreak;
   // added to keep track of cost of operation - AW
   private double hourlyCost;
   private int timesRanToday;
   
   private int timeLeftOnBreak;
   
   public Server(int s, int name, double cost)
   {
       this.secondsForService = s;
       this.timeLeft =0;
       this.name = name;
       this.hourlyCost = cost;
       this.timesRanToday = 0;
       this.onBreak = false;
    //   this.timeLeftOnBreak = 0;
   }

//    public int getTimeLeftOnBreak() {
//        return timeLeftOnBreak;
//    }
   
   public boolean isBusy( )
   {
      return (timeLeft > 0);
   }

    public boolean isOnBreak() {
       // return (timeLeftOnBreak > 0);
        return onBreak;
    }

    public int getName() {
        return name;
    }

    public int getTimesRanToday() {
        return timesRanToday;
    }

      
    
    public void setOnBreak(boolean onBreak, int time) {
        this.timeLeftOnBreak = time;
        this.onBreak = onBreak;
    }
    
//    public void setOnBreak() {
//        this.onBreak = true;
////        this.timeLeftOnBreak = time;
//    }
 
   public void reduceRemainingTime( )
   {
      if (timeLeft > 0)
         timeLeft--;
   } 
    
    
//   public void reduceRemainingTime( )
//   {
//       if (onBreak == false)
//       {
//            if (timeLeft > 0)
//              timeLeft--;
//       } else {
//           if (timeLeftOnBreak > 0)
//               timeLeftOnBreak--;
//       }
//      
//   } 
    
   public void reduceBreakTime()
   {
       if (timeLeftOnBreak > 0){
           timeLeftOnBreak--;
       }
               
       if (timeLeftOnBreak < 1) {
           onBreak = false;
       }
   }

   public double costToday()
   {

       double cost;
       cost = ((timesRanToday * secondsForService)/3600f) * hourlyCost;

       BigDecimal bd = new BigDecimal(cost);
       bd = bd.setScale(2, RoundingMode.CEILING);
       
       cost = bd.doubleValue();

       return cost;
   }

    
   public void start( )
   {
      if (timeLeft > 0) {
         throw new IllegalStateException("Server is already busy.");
      } 
   
      if (onBreak == true) {
         throw new IllegalStateException("Server is on break!");
      } 
      

      timeLeft = secondsForService; 
      timesRanToday++;
      

      
   }  

 
   
   
   
}