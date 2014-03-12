package program2;

import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Andrew
 */
public class Set implements StringSet, Cloneable {

    private int size;
    private Node head;

    public Set() {
        head = new Node(null);
        size = 0;
    }

    public void printSet() {
        Node current = head;
        while (current != null) {
            System.out.println(current.getData());
            current = current.getLink();
        }
    }

    @Override
    public String toString() {
        Node current = head.getLink();
        String output = "";
        while (current != null) {
            output += "[" + current.getData() + "]";
            current = current.getLink();
        }
        return output;
    }

    @Override
    public void resize(int larger) {
//        throw new UnsupportedOperationException("This method is not supported "
//                + "due to the nature Link Lists");     
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
            size++;
        }
    }

    @Override
    public void remove(String target) {
        for (Node c1 = head, c2 = head.getLink(); c2 != null; c1 = c1.getLink(), c2 = c2.getLink()) {
            if (c2.getData().equalsIgnoreCase(target)) {
                System.out.println("----TARGET FOUND");
                System.out.println("----TARGET? " + c2.getData());

                c1.setLink(c2.getLink());
                size--;
            }
        }
    }

    
        @Override
    public String getRandomItem() {
        if (is_empty()) {
            return null;
        }
        
        int random = (int) (Math.random() * inventory() +1);
        System.out.println("----RANDOM # " + random);

        Node current = head.getLink();
        for (int i = 1; i < random; i++) {

            if (current.getLink() == null) {
                return null;
            }

            current = current.getLink();
        }

        String removedString = current.getData();
        remove(removedString);
            System.out.println("---- INVENTORY: " + inventory() );
        return removedString;
    }
    
    public String getLastItem() {
       return null;
       // this method is just for fun. might get back to it...
    }
    

    @Override
    public String getFirstItem() {
        if (is_empty()) {
            return null;
        }
        Node current = head.getLink();
        
        String removedString = current.getData();
        remove(removedString);
        return removedString;
    }

    @Override
    public boolean contains(String target) {
        boolean answer = false;
        Node cursor;
        if (head.getLink() == null) {
            return false;
        }

        for (cursor = head; cursor != null; cursor = cursor.getLink()) {
            if (cursor.data != null && cursor.data.equalsIgnoreCase(target)) {
                answer = true;
            }
        }
        return answer;
    }

    @Override
    public boolean is_empty() {
        return head.getLink() == null;
    }

    
    @Override
    public int inventory() {
        int answer = 0;
        if (head.getLink() == null) {
            return 0;
        }

        for (Node cursor = head; cursor != null; cursor = cursor.getLink()) {
            if (cursor.data != null) {
                answer++;
            }
        }
        return answer;
    }

    @Override 
    public int getCapacity() {
       // return inventory();
        return size;
    }

    public static void main(String[] args) {

        
        Set set = new Set();
        
        boolean fileNotOpen = true;
        String input;
        Scanner inFile;
        FileInputStream in = null;
               
        while (fileNotOpen){
            try {
             //   This reads in the current program source code in my Netbeans 
             //   project. If it it run outside of a project structure, 
             //   replace with: in = new FileInputStream("src\\program2\\Set.java");

                in = new FileInputStream("src\\program2\\Set.java");
                fileNotOpen = false;
            } catch (FileNotFoundException e) {
                System.out.println("Couldn't open file; Goodbye");
                System.exit(1);
            } // end catch
        } // end while
        inFile = new Scanner(in);
                
        while (inFile.hasNext()) {
           // input = inFile.next().replaceAll("[^a-zA-Z ]", "");
            inFile.useDelimiter("[\\]\\[\\\\ .&,-?!^/{}_();+*@:\n\"]+");
            input = inFile.next();           
            
            if (input.length() > 1) {
                
             //   System.out.println(input.trim());
                set.insert(input.trim());
            }
        } // end while
       
        inFile.close();
        
        set.printSet();
        System.out.println(set);
        
        System.out.println(set.getCapacity());
        System.out.println(set.inventory());
        
        System.out.println(set.size);
        
        System.out.println(set.getFirstItem());
        System.out.println(set);
        
        System.out.println(set.getFirstItem());
        System.out.println(set);
        
        System.out.println(set.getRandomItem());
        System.out.println(set);
        
        // this is where I had an "Inception" moment or maybe "Momento", some Christopher Nolan movie anyhow
        // I was testing the methods after reading in from this file. Then I wanted
        // to add a word I
        set.insert("PickleBarrel");
        System.out.println(set);
        
        
        
//        Set set = new Set();
//        System.out.println(set.getRandomItem());
//        System.out.println(set.getFirstItem()); 
//        System.out.println(set.getLastItem());
//        
//        System.out.println("\n Inventory?");
//        System.out.println(set.inventory() + " strings stored");
//
//        System.out.println("\n Capacity?");
//        System.out.println(set.getCapacity() + "  is the total size of the Set");
//
//        System.out.println("\n Is empty?");
//        System.out.println(set.is_empty());
//
//        System.out.println("\n printSet() method:");
//        set.printSet();
//
//        System.out.println("\n ToString method:");
//        System.out.println(set.toString());
//
//        System.out.println("\n Now adding strings:");
//        set.insert("john");
//        set.insert("paul");
//        set.insert("george");
//        set.insert("ringo");
//        set.insert("julian");
//        set.insert("nikolai");
//        set.insert("albert");
//        set.insert("fabrizio");
//        set.insert("paul");
//        set.insert("julian");
//
//        System.out.println("\n Is empty?");
//        System.out.println(set.is_empty());
//
//        System.out.println("\n Inventory?");
//        System.out.println(set.inventory() + " strings stored");
//
//        System.out.println("\n Capacity?");
//        System.out.println(set.getCapacity() + "  is the total size of the Set");
//
//        System.out.println("\n ToString method:");
//        System.out.println(set);
//
////        for (int i = 0; i < 10; i++) {
////            System.out.println("removed: " + set.getRandomItem() + "\n");
////            System.out.println(set + "\n");
////        }
//        
//        for (int i = 0; i < 5; i++) {
//            System.out.println("first: " + set.getFirstItem()+ "\n");
//            System.out.println(set + "\n");
//        }
//
////        System.out.println("\n firstItem() method: ");
////        System.out.println( set.getFirstItem());
////        
////        
////        System.out.println("\n ToString method:");
////        System.out.println(set);
////        
////        
////        String removeName = "john";
////        System.out.println("\n Removing: " + removeName);
////        set.remove(removeName);
////
////        System.out.println("\n ToString method:");
////        System.out.println(set);
////        
////        System.out.println("\n firstItem() method: ");
////        System.out.println( set.getFirstItem());
////
////        
//        String removeName2 = "nikolai";
//        System.out.println("\n Removing: " + removeName2);
//        set.remove(removeName2);
//
//        System.out.println("\n ToString method:");
//        System.out.println(set);
//        
//        
//        System.out.println(set.getRandomItem());
//        
//        System.out.println("\n ToString method:");
//        System.out.println(set);
//        
//        
//        
//        
//        System.out.println(set.getRandomItem());
//        
//        System.out.println("\n ToString method:");
//        System.out.println(set);
//        
//        
//        System.out.println(set.getRandomItem());
//        
//        System.out.println("\n ToString method:");
//        System.out.println(set);
//        
//        System.out.println("\n firstItem() method: ");
//        System.out.println(set.getFirstItem());
//
//        
//        String removeName3 = "paul";
//        System.out.println("\n Removing: " + removeName3);
//        set.remove(removeName3);
//
//        System.out.println("\n ToString method:");
//        System.out.println(set);
//        
//        System.out.println("\n firstItem() method: ");
//        System.out.println(set.getFirstItem());
//        
//        
//        String containsName = "paul";
//        System.out.println("\n contains() method: " + containsName + "?");
//        System.out.println(set.contains(containsName));
//
//        System.out.println("\n contains() method: " + removeName + "?");
//        System.out.println(set.contains(removeName));
//
//        System.out.println("\n Inventory?");
//        System.out.println(set.inventory());
//
//        System.out.println("\n Is empty?");
//        System.out.println(set.is_empty());
//
//        System.out.println("\n Capacity?");
//        System.out.println(set.getCapacity() + "  is the total size of the Set");

      //  set.remove("paul");

        
        
        
        
        
        
        
        
        
        
        
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

        private Node link;
        private String data;

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
