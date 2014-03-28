
package com.lab2;

import java.util.*;


public class IntNodeTester2
{    
    public static void printList(IntNode listStart, int position)
    {
        System.out.printf("%s\n%s\n%s\n%s\n","[]listStart", " |", " |", " v");
        for (IntNode cursor = listStart; cursor != null; cursor = cursor.getLink())
            System.out.printf("[%2d|]-->", cursor.getData());
        System.out.println("null");
        if (position > 0)
        {
            int offset = (position-1) * 8 + 3;
            String format = "%" + offset + "s\n";
            System.out.printf(format+format+format+format, "^", "|", "|", "cursor[]");
        }
    }

        public static void printLinkedList(LinkedListWithNode listStart, int position)
    {
        LinkedListWithNode cursor = listStart;
        System.out.printf("%s\n%s\n%s\n%s\n","[]listStart", " |", " |", " v");        
        int x = 1;
        while (cursor.get(x) != null) {            
            int num = Integer.parseInt(cursor.get(x).toString());
       
            System.out.printf("[%2d|]-->", num);
            x++;
        }
        System.out.println("null");
        if (position > 0)
        {
            int offset = (position-1) * 8 + 3;
            String format = "%" + offset + "s\n";
            System.out.printf(format+format+format+format, "^", "|", "|", "cursor[]");
        }
        

    }
    
    
    public static void main (String [] args)
    {
        
        LinkedListWithNode list = new LinkedListWithNode();
        boolean done = false;
        Scanner kb = new Scanner(System.in);
        String reply;
        while (!done)
        {
            
        
        String data;
        for (int x = 0; x < 8; x++) {
          //  data = "" + x;
            data = "" + (int)((Math.random()*9) +1);
            
            list.add(data);
        }
        System.out.println("Original Main list:");
        
        printLinkedList(list, 0);
        
        
        System.out.print("\nAdding 3 nodes at front of list. ");
        int index = 1;
        list.add(""+(int)((Math.random()*9) +1), index);
        list.add(""+(int)((Math.random()*9) +1), index);
        list.add(""+(int)((Math.random()*9) +1), index);
        System.out.println("Cursor indicates where head used to be:");
        printLinkedList(list, 4);
        
        System.out.println("\nRemoving first node:");
        list.remove(1);        
        printLinkedList(list, 0);
        
        System.out.println("\nLength of list is: " + list.size() );
        
        
        
        System.out.println("\nLinkedListWithNode has no search method");
        System.out.println("But it can display the value at an index randomly choosen");
        
        
        int target = (int)((Math.random()* list.size()) +1);
        System.out.println("Random index generated: " + target);
        System.out.println("Value at target: " + list.get(target));
        
        printLinkedList(list, target);
        
        
        
        System.out.println("\nPrinting list from target to end:");
        LinkedListWithNode cursor = LinkedListWithNode.listCopy(list, target, list.size());
        printLinkedList(cursor , 0);
        
        
        target = (int)((Math.random()* list.size()) +1);
        System.out.println("\nGenerating new random index");
        System.out.println("Random index generated: " + target);
        String value = (list.get(target)).toString();
        System.out.println("Value at target: " + list.get(target));
        int insert = (int)((Math.random()*9) +1);
        list.add(""+ insert,target);
        System.out.println("Inserting the value " + insert + " before value " + value);
        printLinkedList(list, target);

        
     
        // list copy all
        System.out.println("\nCopying the whole list");
        LinkedListWithNode copy = LinkedListWithNode.listCopy(list);       
        printLinkedList(copy, 0);
        
        // list copy from random postition
        System.out.println("\nCopying part of the list from postition " + target);
        copy = LinkedListWithNode.listCopy(list,target, list.size());       
        printLinkedList(copy, 0);
        
        
        
        System.out.println("\nCopy main list and reverse");
        copy = LinkedListWithNode.reverseCopy(list); 
        printLinkedList(copy, 0);
        
        System.out.println("\nPrinting main list again");
        printLinkedList(list, 0);
        
        System.out.println("\nMain list size:");
        System.out.println(list.size());        
        
        
        
        System.out.println("\nCopy Main list and remove Duplicate values");
        copy = LinkedListWithNode.loseDupes(list);        
        printLinkedList(copy, 0);
        System.out.println("Main List's size, minus duplicate values: " + copy.size());
        
        
        System.out.println("\nCopy a part of a list");
        int h = 0;
        int t = 0;
        boolean badParams = true;
        while (badParams) 
        {
        
            h = (int)((Math.random()* list.size()) +1); 
            t = (int)((Math.random()* list.size()) +1); 
            
            
            try {
                copy = LinkedListWithNode.listCopy(list, h, t);
                copy.size();
                badParams = false;
            } catch (NullPointerException e) {
                System.out.println("I seem to have my tail before my head, I'll try again");
            }
            
            
            
        } // end while
       
        System.out.println("\nPrinting copy of main list from postion: " + h + " to postion " + t);
        printLinkedList(copy, 0);
        
        System.out.println("\nCopied partial list's size: " + copy.size());
        
        
        
        
        
        
            System.out.print("\nRun tests again? (y/n): ");
            reply = kb.nextLine();
            if(reply.equalsIgnoreCase("n"))
            {
                done = true;
            }
            else
            {
                
                list = null;
                list = new LinkedListWithNode();
              
            }
        }
        
        
        
  
    } // end main
} // end class