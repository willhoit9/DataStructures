package classes;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Server
{
   private int secondsForService; // Seconds for a single wash
   private int timeLeft;   // Seconds until this Server is no longer busy
   
   // added to keep track of different servers - AW
   private String name;
   // added to allow servers to take breaks - AW
   private boolean onBreak;
   // added to keep track of cost of operation - AW
   private double hourlyCost;
   private int timesRanToday;
   
   
   public Server(int s, String name, double cost)
   {
       this.secondsForService = s;
       this.timeLeft =0;
       this.name = name;
       this.hourlyCost = cost;
       this.timesRanToday = 0;
       this.onBreak = false;
   }
   
   public boolean isBusy( )
   {
      return (timeLeft > 0);
   }

    public boolean isOnBreak() {
        return onBreak;
    }

    public String getName() {
        return name;
    }

    public int getTimesRanToday() {
        return timesRanToday;
    }
    
    public void setOnBreak(boolean onBreak) {
        this.onBreak = onBreak;
    }
 
   public void reduceRemainingTime( )
   {
      if (timeLeft > 0)
         timeLeft--;
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
      
      timeLeft = secondsForService;
      
      timesRanToday++;
   }  

 
   
   
   
}