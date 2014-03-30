

package com.classes;

import java.util.Scanner;
import java.util.*;
import java.util.regex.*;

public class FixIt {
    
    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void main(String[] args) {
   
        
        
        
        boolean done = false;
        Scanner scan = new Scanner(System.in);
        String reply;
        
        
        while (!done)
        {
            System.out.println("Choose an option: ");
            System.out.println("1) prefix - solve a prefix expression");
            System.out.println("2) postfix - solve a postfix expression");
            System.out.println("3) infix - convert a infix expression to postfix, then solve");
            System.out.println("4) quit - skedaddle");
            System.out.print("Enter 1,2,3, or 4: ");
            reply = scan.nextLine();
            if (reply.equalsIgnoreCase("1")) {
                System.out.println("You picked: " + reply);
            }else if (reply.equalsIgnoreCase("2")) {
                System.out.println("You picked: " + reply);
            }else if (reply.equalsIgnoreCase("3")) {
                System.out.println("You picked: " + reply);
                
                System.out.println("This functionality hasn't been implemented yet. Sorry!");
                
            }else if (reply.equalsIgnoreCase("4")) {
                System.out.println("You picked: " + reply);
                System.exit(0);
            } else {
                System.out.println("You picked: " + reply);
                System.out.println("That wasn't really an option, so we're done.");
                System.exit(0);
            }
            
        
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            System.out.println("\nHey, we had fun huh?");
            System.out.print("Runna run again? (y/n): ");
            reply = scan.nextLine();
            if(reply.equalsIgnoreCase("n"))
            {
                done = true;
            }
            
            
        }







    }
    
    
} // end of class FixIt


