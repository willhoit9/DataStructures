

package com.classes;


import java.util.Scanner;

public class FixIt {
    
   // public PostfixCalculator postCalc = new PostfixCalculator();
    private double answer; 
    private String input;


    public FixIt() {
        input = "";
        
    }
     
    public void setAnswer(double answer) {
        this.answer = answer;
    }
    
    public double getAnswer() {
        return this.answer;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
    

     private void doPostFix() throws IllegalArgumentException{        
        PostfixCalculator postCalc = new PostfixCalculator();
        postCalc.setExpression(getInput());        
        postCalc.evalPostfix();
        
        this.setAnswer(postCalc.getResult());
    } 
   
    private void doPreFix() throws IllegalArgumentException {
        PrefixCalculator preCalc = new PrefixCalculator();      
        preCalc.setExpression(getInput());
        preCalc.evalPrefix();
        
        this.setAnswer(preCalc.getResult());
        
    }
    
    public static void main(String[] args) {
   
        FixIt fixIt = new FixIt();
        boolean done = false;
        boolean goodInput = true;
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
                System.out.print("Please enter your prefix expression: ");
                String preInput = scan.nextLine();
                fixIt.setInput(preInput);                
                try {
                    fixIt.doPreFix();
                    System.out.println("\nThe expression: '" + preInput + "' is a valid postfix expression.");
                } catch (IllegalArgumentException e) {
                    goodInput = false;
                    System.out.println("\nSorry. '" + preInput + "' was not a valid postfix expression.\nPlease Try Again.");                    
                }
              
            }else if (reply.equalsIgnoreCase("2")) {
                System.out.println("You picked: " + reply);
                System.out.print("Please enter your postfix expression: ");
                String postInput = scan.nextLine();
                fixIt.setInput(postInput);
                                               
                try {
                    fixIt.doPostFix();
                  System.out.println("\nThe expression: '" + postInput + "' is a valid postfix expression.");
               
                } catch (IllegalArgumentException e) {
                    goodInput = false;
                    System.out.println("\nSorry. '" + postInput + "' was not a valid postfix expression.\nPlease Try Again.");                    
                }
                                
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
            
        
            
            
            if (goodInput) {
                System.out.println("\nYour answer is: " + fixIt.getAnswer());
            }
            
            
            
            
            
            
            
            
            
            

            
            
            System.out.println("\nHey, we had fun huh?");
            System.out.print("Wanna run again? (y/n): ");
            reply = scan.nextLine();
            if(reply.equalsIgnoreCase("n"))
            {
                done = true;
            } else {
                goodInput = true;
                fixIt.setInput("");
                fixIt.setAnswer(0);
            }
            
            
        }

    } // end of main
    
    
        
    
    
    
} // end of class FixIt


