/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package program2 ;

import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Andrew
 */
public class Set implements StringSet, Cloneable{

    
           
    private int size;
    private Node head;
    
    public Set () {
        head = new Node(null);
        size = 0;
    }
      
    public void printSet(){
        Node current = head;             
        while(current != null) {
                System.out.println(current.getData());
                current = current.getLink();
        }
    }
    
    @Override
    public String toString() {
        Node current = head.getLink();
        String output = "";
        while(current != null) {
            output += "[" + current.getData() + "]";     
            current = current.getLink();
        }
        return output;
    }
    
    @Override
    public void resize(int larger) {
        if (larger <= size) {
            throw new IllegalArgumentException("\rAn Illegal Argument Exeption has Occured. \n"
					+  " The list may only be resized larger than its current size.");
        }    
        size = larger;
    }

    @Override
    public void insert(String entry) {
        if (!this.contains(entry)) {
            
            Node temp = new Node(entry);
            Node current = head;
            while (current.getLink() != null) {           
                current = current.getLink();
            }
            current.setLink(temp);
            resize(size + 1);  
        }
    }

//    @Override
//    public void remove(String target) {
//        
//        Node current = head;
//      
//        while (current.getLink() != null)
//        {
//            // (current.getData() != null && 
//            // if the current node's link points to the string we want to delete, true
//            if (current.getLink().getData().equalsIgnoreCase(target))
//            {  
//                System.out.println("----TARGET FOUND");
//                System.out.println("TARGET? " + current.getData());
//                
//                // the current node is the one before the one we want to delete
//                // change the link of the current node to the one after the node
//                // we wish to delete
//                current.setLink(current.getLink().getLink());
//                
//                // size--;  - taken out of service because:
//                // i don't think i should decrement size here because we want
//                // size to be an account of all of the total size of Set
//                // I changed the link to pass over the "removed" node, but it
//                // was not really deleted. private int size keeps track of all nodes
//                // regardless of removal from the links
//            }           
//            current = current.getLink();           
//        }             
//    }

    
    
    
    
//    
//    @Override
//    public void remove(String target) {
//        for (Node cursor = head; cursor.getLink() != null; cursor = cursor.getLink()) {
//            if (cursor.getLink().getData().equalsIgnoreCase(target))
//            {  
//                System.out.println("----TARGET FOUND");
//                System.out.println("TARGET? " + cursor.getData());
//                
//                cursor.setLink(cursor.getLink().getLink());
//            }
//       
//        }
//    }

    
        // version 2
    @Override
    public void remove(String target) {
        for (Node c1 = head, c2 = head.getLink(); c2 != null; c1 = c1.getLink(), c2 = c2.getLink()) {
            if (c2.getData().equalsIgnoreCase(target))
            {  
                //System.out.println("----TARGET FOUND");
               // System.out.println("TARGET? " + c1.getData());
                
                c1.setLink(c2.getLink());
            }
       
        }
    }
    
    
    
    @Override
    public String getRandomItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getFirstItem() {
        
        
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean contains(String target) {
        boolean answer = false;
        Node cursor;
        if (head.link == null)
            return false;
 
        for (cursor = head; cursor != null; cursor = cursor.getLink()) {           
            if (cursor.data != null && cursor.data.equalsIgnoreCase(target)){
                answer = true;
            }    
        }
        return answer;
    }

    @Override
    public boolean is_empty() {
        return head.getLink() == null;
    }

    // postcondition: returns total number of Strings currently in set
    @Override
    public int inventory() { 
        int answer = 0;
        if (head.link == null)
            return 0;
 
        for (Node cursor = head; cursor != null; cursor = cursor.getLink()) {
            if (cursor.data != null)
                answer++;
        }
        return answer;
    }

    @Override // postcondition: returns total size of Set (used & unused portions)
    public int getCapacity() {
//        int answer = 0;
// 
//        for (Node cursor = head; cursor != null; cursor = cursor.getLink()) {
//                answer++;
//        }
//        return answer;
        return size;
    }
    
    
    

    public static void main(String[] args) {
        
        
        Set set = new Set();
        System.out.println("\n Inventory?");
        System.out.println(set.inventory() + " strings stored"); 
        
        System.out.println("\n Capacity?");
        System.out.println(set.getCapacity() + "  is the total size of the Set"); 
        
        System.out.println("\n Is empty?");
        System.out.println(set.is_empty());
        
        System.out.println("\n printSet() method:");
        set.printSet();
        
        System.out.println("\n ToString method:");
        System.out.println(set.toString());
        
        System.out.println("\n Now adding strings:");
        set.insert("john");
        set.insert("paul");
        set.insert("george");
        set.insert("ringo");
        set.insert("julian");
        set.insert("nikolai");
        set.insert("albert");
        set.insert("fabrizio");
        set.insert("paul");
        set.insert("julian");
                
        
        System.out.println("\n Is empty?");
        System.out.println(set.is_empty());
        
        System.out.println("\n Inventory?");
        System.out.println(set.inventory() + " strings stored"); 
        
        System.out.println("\n Capacity?");
        System.out.println(set.getCapacity() + "  is the total size of the Set"); 
        
        
        System.out.println("\n ToString method:");
        System.out.println(set);
        

        String removeName = "fabrizio";
        System.out.println("\n Removing: " + removeName);
        set.remove(removeName);
                   
        System.out.println("\n ToString method:");
        System.out.println(set);
        
        
        String removeName2 = "nikolai";
        System.out.println("\n Removing: " + removeName2);
        set.remove(removeName2);
                   
        System.out.println("\n ToString method:");
        System.out.println(set);
  
        
        
        
        String containsName = "paul";
        System.out.println("\n contains() method: " + containsName + "?");
        System.out.println(set.contains(containsName));
        
        System.out.println("\n contains() method: " + removeName + "?");
        System.out.println(set.contains(removeName));
        
        System.out.println("\n Inventory?");
        System.out.println(set.inventory());
        
        System.out.println("\n Is empty?");
        System.out.println(set.is_empty());
        
        System.out.println("\n Capacity?");
        System.out.println(set.getCapacity() + "  is the total size of the Set");         
        
      //  set.remove("paul");
        
        
        
        
//        boolean fileNotOpen = true;
//        String input;
//        Scanner inFile;
//        FileInputStream in = null;
//        
//       
//        while (fileNotOpen){
//        try {
//         //   This reads in the current program source code in my Netbeans 
//         //   project. If it it run outside of a project structure, replace with: in = new FileInputStream("src\\program2\\Set.java");
//            
//            in = new FileInputStream("src\\program2\\Set.java");
//            fileNotOpen = false;
//        } catch (FileNotFoundException e) {
//            System.out.println("Couldn't open file; Goodbye");
//            System.exit(1);
//        }
//        } // end while
//        inFile = new Scanner(in);
//                
////        while (inFile.hasNextLine()) {
////            input = inFile.nextLine();
////            int end = input.length()-1;
////            if (end > 0 && input.charAt(end) == ';') {
////                System.out.println(input);
////                
////            }
////        } // end while
//        
//        String [] words = null;
//        while (inFile.hasNext()) {
//            input = inFile.next().replaceAll("[^a-zA-Z ]", "");
//            int end = input.length() -1;
//            
//            words = new String[300];
//            int i = 0;
//            if (end > 0) {
//                System.out.println(input);
//                words[i] = input;
//                i = i + 1;
//            }
//        } // end while
//        
//        System.out.println(words.length);
//        
//      // if (words.length > 0) {
//            for (int i = 0; i < words.length; i++) {
//                System.out.println(words[i] + " -");
//            }
//      //  }
//
//        
//        
//        
//        inFile.close();

        
        
        
        
        
        
        
        
        
//       bonus main testing
//        
//        String removeName3 = "john";
//        System.out.println("\n Removing: " + removeName3);
//        set.remove(removeName3);
//                   
//        System.out.println("\n ToString method:");
//        System.out.println(set);
//        
//        
//        String removeName4 = "paul";
//        System.out.println("\n Removing: " + removeName4);
//        set.remove(removeName4);
//                   
//        System.out.println("\n ToString method:");
//        System.out.println(set);
//        
//        String removeName5 = "george";
//        System.out.println("\n Removing: " + removeName5);
//        set.remove(removeName5);
//                   
//        System.out.println("\n ToString method:");
//        System.out.println(set);        
//        
//        String removeName6 = "ringo";
//        System.out.println("\n Removing: " + removeName6);
//        set.remove(removeName6);
//                   
//        System.out.println("\n ToString method:");
//        System.out.println(set);
//        
//        
//        
//        String removeName7 = "julian";
//        System.out.println("\n Removing: " + removeName7);
//        set.remove(removeName7);
//                   
//        System.out.println("\n ToString method:");
//        System.out.println(set);
//        
//        String removeName8 = "albert";
//        System.out.println("\n Removing: " + removeName8);
//        set.remove(removeName8);
//                   
//        System.out.println("\n ToString method:");
//        System.out.println(set);
//        
//        
//        set.insert("julian");
//        System.out.println("\n ToString method:");
//        System.out.println(set);
        
        
        
        
        
        
        
        
        
        
    } // end main()
    
    
    
    
    
    
        private class Node {
        
        Node link;
        String data;
        
        // Node constructor
        public Node(String _data) {
            data = _data;
            link = null;
        }
        
        // Node constructor for pointing to specific node
        public Node(String _data, Node _link) {
            data = _data;
            link = _link;
        }
        
        public String getData() {
            return data;
            // get the string from this node
        }
        
        public void setData(String _data) {
            data = _data;
        }
        
        public Node getLink() {
            return link;
        }
        
        public void setLink(Node _link) {
            link = _link;
        }
        
                
    } // end of private inner class Node
    
    
    
    
} // end class Set
