
package com.lab2;

import java.util.*;

/**
 * Test class for the IntNode class, described in Chapter 4 of
 * Data Structures and Other Objects by Michael Main
 * 
 * @author Cate Sheller 
 * @version October 8, 2012
 */
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
        
        
        
        
//        LinkedListWithNode list6 = LinkedListWithNode.listCopy(copy, 4, 10 );
//        printLinkedList(list6, index);
        
        
     //   printLinkedList(list, 0);
        
        
        
//        Random rg = new Random();
//        IntNode head = new IntNode(rg.nextInt(100), null);
//        boolean done = false;
//        Scanner kb = new Scanner(System.in);
//        String reply;
//        while (!done)
//        {
//            // build & print initial list for testing
//            for (int x=0; x<8; x++)
//            {
//                int tmp = rg.nextInt(100);
//                head.addNodeAfter(tmp);
//                //printList(head, 0);
//                //System.out.println("adding " + tmp);
//            }
//            System.out.println("initial list:");
//            printList(head, 0);
//            
//            // No provision for adding node before head in instance methods,
//            // so doing so with client code:
//            System.out.print("Adding 2 nodes at front of list; ");
//            head=new IntNode(rg.nextInt(100), head);
//            head=new IntNode(rg.nextInt(100), head);
//            System.out.println("cursor indicates where head used to be:");
//            printList(head, 3);
//            
//            // Removing head node can't be done using instance method with
//            // this implementation, so trying it in client code:
//            System.out.println("Removing first node:");
//            head = head.getLink();
//            printList(head, 0);
//            
//            // Testing static methods: listLength
//            System.out.println("length of list is: " + IntNode.listLength(head));
//            
//            // listSearch
//            int target = rg.nextInt(100);
//            System.out.println("Testing search method; searching for " + target);
//            IntNode cursor = IntNode.listSearch(head, target);
//            
//            if (cursor == null)
//                System.out.println("target value not in list");
//            else
//            {
//                System.out.println("target found; printing list from target to end");
//                printList(cursor, 0);
//            }
//            
//            // listPosition
//            int position = rg.nextInt(IntNode.listLength(head)) + 1;
//            cursor = IntNode.listPosition(head, position);
//            System.out.println(cursor.getData() + " occurs at position " + position);
//            printList(head, position);
//            
//            // adding new node in midlist; first, ensure that we're not
//            // at the head node:
//            if (position == 1)
//            {
//                position++;
//                cursor = IntNode.listPosition(head, position);
//            }
//            System.out.println("Adding 0 after " + cursor.getData());
//            cursor.addNodeAfter(0);
//            printList(head, position+1);
//            
//            // listCopy (from head & also from cursor)
//            System.out.println("Copying big list");
//            IntNode copy = IntNode.listCopy(head);
//            printList(copy, 0);
//            System.out.println("Copying list that starts at " + cursor.getData());
//            copy = IntNode.listCopy(cursor);
//            printList(copy, 0);
//            
//            // listCopyWithTail
//            System.out.println("Testing listCopyWithTail");
//            IntNode[]nodes = IntNode.listCopyWithTail(head);
//            System.out.println("Data in head node of new list: " + nodes[0].getData());
//            System.out.println("Data in tail node of new list: " + nodes[1].getData());
//            System.out.println("Whole copied list; cursor indicates position of tail node");
//            printList(nodes[0], IntNode.listLength(nodes[0]));
//            
//            // listPart
//            System.out.println("Testing listPart");
//            boolean badParams = true;
//            while (badParams) // we're going to assume this doesn't work until proven otherwise!
//            {
//                // generate semi-random node for start
//                position = rg.nextInt(IntNode.listLength(head)) + 1;
//                if (position == 1)
//                    position++;
//                IntNode ch = IntNode.listPosition(head, position);
//                System.out.println("List showing cursor at initial position to be copied:");
//                printList(head, position);
//                // generate semi-random node for end
//                position = rg.nextInt(IntNode.listLength(head)) + 1;
//                if (position == IntNode.listLength(head))
//                    position--;
//                IntNode ct = IntNode.listPosition(head, position);
//                System.out.println("List showing cursor at final position to be copied:");
//                printList(head, position);
//                // here's where the badParams might have happened - we didn't 
//                // check to see if ch was before ct in the list; so watch for exception
//                try
//                {
//                    nodes = IntNode.listPart(ch, ct);
//                    badParams = false;
//                }
//                catch (IllegalArgumentException e)
//                {
//                    System.out.println("head must come before tail!");
//                }
//            }
//            printList(nodes[0], IntNode.listLength(nodes[0])); 
//           
//            System.out.print("Run tests again? (y/n): ");
//            reply = kb.nextLine();
//            if(reply.equalsIgnoreCase("n"))
//            {
//                done = true;
//            }
//            else
//            {
//                // We're starting over, so wipe out list & start fresh:
//                head = null;
//                head = new IntNode(rg.nextInt(100), null);
//            }
//        }
    } // end main
} // end class