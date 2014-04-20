package classes;

//Andrew Willhoit - Data Structures 
//Queue Simulation - Averager.java
//Based on Main and Sheller's code
//4/14/14

public class Averager
{
   private int count;  // How many numbers have been given to this averager
   private double sum; // Sum of all the numbers given to this averager                  
   private int totalShowedUp; // the total amount of people that drive by
   private int leftBecauseLongLine;


   
   public Averager( )
   {
       count =0;
       sum = 0;
       totalShowedUp = 0;
   }
   
   public void addNumber(double value)
   {
      if (count == Integer.MAX_VALUE)
         throw new IllegalStateException("Too many numbers");
      count++;
      sum += value;
   }
 
   public double average( )
   {
      if (count == 0)
         return Double.NaN;
      else
         return sum/count;
   } 

   public int howManyNumbers( )
   {
      return count;
   }

    public int getTotalShowedUp() {
        return totalShowedUp;
    }
   
   
   // a car shows up to the carwash
   public void showUp()
   {
       totalShowedUp++;
   }
   
   // how many people left because line was too long?
   public int howManyLeft()
   {
       return leftBecauseLongLine;
   }
   
   public void leftBecauseLongLine()
   {
       leftBecauseLongLine++;
   }
   
}
