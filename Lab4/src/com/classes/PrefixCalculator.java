package com.classes;


import java.util.*;
import java.util.regex.*;

public class PrefixCalculator {
    private String expression;
    private LinkedList reversedExpression = new LinkedList();
    private Stack<Double> nums;
    public static String UNSIGNED_DOUBLE = "((\\d+\\.?\\d*)|(\\.\\d+))([Ee][-+]?\\d+)?.*?";
    public static String CHARACTER = "\\S.*?";

    
    
    public PrefixCalculator (){
        nums = new Stack<Double>();
        expression = "";
    }
    
    private void reverseExpression() {
         
         System.out.println("BEFORE: " + expression);      
         
         Scanner scan = new Scanner (this.expression);
         
         int i = 0;
         do {            
             reversedExpression.add(reversedExpression.size() - i, scan.next());             
             i++;
         } while (scan.hasNext());
         
         System.out.println("LISTEXP: " + reversedExpression.toString());
    }
    
    
    
    

    
    
    
    // because I used a LinkedList when reversing the expression
    // this method doesn't use the Pattern objects like the Postfix version does
    // I was unable to use the Pattern objects with LinkedList, and I didn't want
    // to convert back to string, just to use Scanner, just to use hasNext(pattern)...
    // I did reuse his naming convention for the UNSIGNED_DOUBLE and CHARACTER,
    // although they are Strings now.
    
    public void evalPrefix() {
        
        reverseExpression();
        Iterator <Object> it = reversedExpression.iterator();
        String s;
        
        do {
            s = it.next().toString();
                       
         //  if (s.matches("(?:\\d*\\.)?\\d+")) {
         //   if (s.matches("((\\d+\\.?\\d*)|(\\.\\d+))([Ee][-+]?\\d+)?.*?")) {
            if (s.matches(UNSIGNED_DOUBLE)) {
                nums.push(new Double(s));
            } else if (s.matches(CHARACTER)) {
                calculate(s);
            }
                           
        } while (it.hasNext());
        
        
    }
    
    // this is very similar to the same PostfixCalculator method,
    // op1 is popped off first in this one.
    public void calculate (String n)
    {
        if (nums.size() < 2)
            throw new IllegalArgumentException("Input expression: " + 
                                        expression + " invalid");
        
        // reversed the following:
        double op1 = nums.pop();
        double op2 = nums.pop();
        
        char op = n.charAt(0);
        switch (op)
        {
            case '+':  nums.push(op1 + op2);
              break;
            case '-':  nums.push(op1 - op2);
              break;
            case '*':  nums.push(op1 * op2);
              break;
            case '/':  nums.push(op1 / op2);
              break;
        }
    }
    
    // this is the same as PostfixCalculator
    public double getResult() {
         if (nums.size() > 1 || nums.isEmpty())
            throw new IllegalArgumentException("Input expression: " 
					+  expression + " invalid");        
        return (double)nums.pop();
    }
    
   
    public void setExpression (String e) {
        expression = e;
    }
    
    
}
