package com.classes;

import java.util.*;
import java.util.regex.*;

public class PostfixCalculator {
    private String expression;
    private Stack<Double> nums;
    public static final Pattern CHARACTER = Pattern.compile("\\S.*?");  
    public static final Pattern UNSIGNED_DOUBLE =
        Pattern.compile("((\\d+\\.?\\d*)|(\\.\\d+))([Ee][-+]?\\d+)?.*?");
    
    public PostfixCalculator ()
    {
        nums = new Stack<Double>();
        expression = "";
    }

    public void evalPostfix ()
    {
        Scanner expression = new Scanner(this.expression);
        String next;
        do {
            if (expression.hasNext(UNSIGNED_DOUBLE))
            {
                next = expression.findInLine(UNSIGNED_DOUBLE);
                nums.push(new Double(next));
            }
            else
            {
                    next = expression.findInLine(CHARACTER);
                    calculate(next);
            }
        } while (expression.hasNext());
    }
    
    public void calculate (String n)
    {
        if (nums.size() < 2)
            throw new IllegalArgumentException("Input expression: " + 
                                        expression + " invalid");
        double op2 = nums.pop();
        double op1 = nums.pop();
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
    
    public double getResult()
    {
        if (nums.size() > 1 || nums.isEmpty())
            throw new IllegalArgumentException("Input expression: " 
					+  expression + " invalid");
        return (double)nums.pop();
    }
    
    public void setExpression (String e)
    {
        expression = e;
    }
    
    public static void main (String [] args)
    {
        PostfixCalculator pc = new PostfixCalculator();
        Scanner kb = new Scanner (System.in);
        String input;
        do {
            System.out.print ("Enter a postfix expression (or Q to quit: ");
            input = kb.nextLine();
            if (input.equalsIgnoreCase("q"))
                System.out.println ("So long, and thanks for all the fish!");
            else
            {
                pc.setExpression(input);
                pc.evalPostfix();
                System.out.println ("Your expression evaluates to: " +
                                        pc.getResult());
            }
        } while (!input.equalsIgnoreCase("q"));
    }
}

            