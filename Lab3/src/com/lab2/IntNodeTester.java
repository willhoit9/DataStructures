
package com.lab2;

import java.util.*;

/**
 * Test class for the IntNode class, described in Chapter 4 of
 * Data Structures and Other Objects by Michael Main
 * 
 * @author Cate Sheller 
 * @version October 8, 2012
 */
public class IntNodeTester
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
    
    public static void main (String [] args)
    {
        Random rg = new Random();
        IntNode head = new IntNode(rg.nextInt(100), null);
        boolean done = false;
        Scanner kb = new Scanner(System.in);
        String reply;
        while (!done)
        {
            // build & print initial list for testing
            for (int x=0; x<8; x++)
            {
                int tmp = rg.nextInt(100);
                head.addNodeAfter(tmp);
                //printList(head, 0);
                //System.out.println("adding " + tmp);
            }
            System.out.println("initial list:");
            printList(head, 0);
            
            // No provision for adding node before head in instance methods,
            // so doing so with client code:
            System.out.print("Adding 2 nodes at front of list; ");
            head=new IntNode(rg.nextInt(100), head);
            head=new IntNode(rg.nextInt(100), head);
            System.out.println("cursor indicates where head used to be:");
            printList(head, 3);
            
            // Removing head node can't be done using instance method with
            // this implementation, so trying it in client code:
            System.out.println("Removing first node:");
            head = head.getLink();
            printList(head, 0);
            
            // Testing static methods: listLength
            System.out.println("length of list is: " + IntNode.listLength(head));
            
            // listSearch
            int target = rg.nextInt(100);
            System.out.println("Testing search method; searching for " + target);
            IntNode cursor = IntNode.listSearch(head, target);
            
            if (cursor == null)
                System.out.println("target value not in list");
            else
            {
                System.out.println("target found; printing list from target to end");
                printList(cursor, 0);
            }
            
            // listPosition
            int position = rg.nextInt(IntNode.listLength(head)) + 1;
            cursor = IntNode.listPosition(head, position);
            System.out.println(cursor.getData() + " occurs at position " + position);
            printList(head, position);
            
            // adding new node in midlist; first, ensure that we're not
            // at the head node:
            if (position == 1)
            {
                position++;
                cursor = IntNode.listPosition(head, position);
            }
            System.out.println("Adding 0 after " + cursor.getData());
            cursor.addNodeAfter(0);
            printList(head, position+1);
            
            // listCopy (from head & also from cursor)
            System.out.println("Copying big list");
            IntNode copy = IntNode.listCopy(head);
            printList(copy, 0);
            System.out.println("Copying list that starts at " + cursor.getData());
            copy = IntNode.listCopy(cursor);
            printList(copy, 0);
            
            // listCopyWithTail
            System.out.println("Testing listCopyWithTail");
            IntNode[]nodes = IntNode.listCopyWithTail(head);
            System.out.println("Data in head node of new list: " + nodes[0].getData());
            System.out.println("Data in tail node of new list: " + nodes[1].getData());
            System.out.println("Whole copied list; cursor indicates position of tail node");
            printList(nodes[0], IntNode.listLength(nodes[0]));
            
            // listPart
            System.out.println("Testing listPart");
            boolean badParams = true;
            while (badParams) // we're going to assume this doesn't work until proven otherwise!
            {
                // generate semi-random node for start
                position = rg.nextInt(IntNode.listLength(head)) + 1;
                if (position == 1)
                    position++;
                IntNode ch = IntNode.listPosition(head, position);
                System.out.println("List showing cursor at initial position to be copied:");
                printList(head, position);
                // generate semi-random node for end
                position = rg.nextInt(IntNode.listLength(head)) + 1;
                if (position == IntNode.listLength(head))
                    position--;
                IntNode ct = IntNode.listPosition(head, position);
                System.out.println("List showing cursor at final position to be copied:");
                printList(head, position);
                // here's where the badParams might have happened - we didn't 
                // check to see if ch was before ct in the list; so watch for exception
                try
                {
                    nodes = IntNode.listPart(ch, ct);
                    badParams = false;
                }
                catch (IllegalArgumentException e)
                {
                    System.out.println("head must come before tail!");
                }
            }
            printList(nodes[0], IntNode.listLength(nodes[0])); 

            System.out.print("Run tests again? (y/n): ");
            reply = kb.nextLine();
            if(reply.equalsIgnoreCase("n"))
            {
                done = true;
            }
            else
            {
                // We're starting over, so wipe out list & start fresh:
                head = null;
                head = new IntNode(rg.nextInt(100), null);
            }
        }
    }
}