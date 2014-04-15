package classes;

public class Server
{
   private int secondsForService; // Seconds for a single wash
   private int timeLeft;   // Seconds until this Server is no longer busy
   // added to keep track of different servers - AW
   private String name;
   // added to allow servers to take breaks - AW
   private boolean onBreak;
   
   public Server(int s, String n)
   {
       secondsForService = s;
       timeLeft =0;
       name = n;
       onBreak = false;
   }
   
   public boolean isBusy( )
   {
      return (timeLeft > 0);
   }

    public boolean isOnBreak() {
        return onBreak;
    }

    public void setOnBreak(boolean onBreak) {
        this.onBreak = onBreak;
    }
 
   public void reduceRemainingTime( )
   {
      if (timeLeft > 0)
         timeLeft--;
   } 

    public String getName() {
        return name;
    }

   public void start( )
   {
      if (timeLeft > 0)
         throw new IllegalStateException("Server is already busy.");
      timeLeft = secondsForService;
   }  
}