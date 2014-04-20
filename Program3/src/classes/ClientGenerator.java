
package classes;

//Andrew Willhoit - Data Structures 

import java.util.Calendar;
import java.util.GregorianCalendar;

//Queue Simulation - ClientGenerator.java
//Based on Main and Sheller's code
//4/14/14


public class ClientGenerator
{
   private double probability; // The approximate probability of query( ) returning true.
                      
   public ClientGenerator(double p)
   {
       if ((p < 0) || (1 < p))
           throw new IllegalArgumentException("Illegal p: " + p);
       probability = p;
   }

    public void setProbability(double p) {
        if ((p < 0) || (1 < p)) {
            throw new IllegalArgumentException("Illegal p: " + p);
        }
        this.probability = p;
    }

    

     
   public boolean query( )
   {
       Calendar now = Calendar.getInstance();
       int nowHour = now.get(Calendar.HOUR_OF_DAY);
       double prob = probability;

//       System.out.println(prob);
//       System.out.println(nowHour);
       
       // if before 7AM or after 8PM
       // things are slow
       if (nowHour < 7 || nowHour > 20) { 
           prob = prob / 2f;
           
       }
       
       // if between 4PM and 6PM
       // its busy
       if (nowHour >= 16 && nowHour <= 18) { 
           prob = prob * 2f;
           
       }

       
      return (Math.random( ) < prob);
   }
   
   
}