 //Set Class

//Andrew Willhoit - 3/12/14
 
package program2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
 
 
public class Set implements StringSet, Cloneable {
 
    private int size;
    private Node head;
 
    public Set() {
        head = new Node(null);
        size = 0;
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
                //System.out.println("----TARGET FOUND");
                //System.out.println("----TARGET? " + c2.getData());
 
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
        //System.out.println("----RANDOM # " + random);
 
        Node current = head.getLink();
        for (int i = 1; i < random; i++) {
 
            if (current.getLink() == null) {
                return null;
            }
 
            current = current.getLink();
        }
 
        String removedString = current.getData();
        remove(removedString);
        //System.out.println("---- INVENTORY: " + inventory() );
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
       // Since using a Link List, getCapacity would return the same as inventory()
       // I decided to use this method to return the private int variable   'size'
       // which I have incremented and decremented with adds and removes
       // return inventory();
        return size;
    }
    
    // this prints the Set vertically.
    // I chose to not demonstrate this in the main method due to its length
    // toString() displays the Set horizontally and used in main's demo
    public void printSet() {
        Node current = head;
        while (current != null) {
            System.out.println(current.getData());
            current = current.getLink();
        }
    }
 
    // toString() displays the Set horizontally 
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
 
    public Set getClone() {
        try {
            return (Set)super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e.toString());
            return null;
        }       
    }
    
    public static void main(String[] args) {
 
        System.out.println("-Demonstration of Set() class - Andrew Willhoit");
        
        System.out.println("\n-Set 'set' was created");
        Set set = new Set();
        
        //FYI: is_empty() method demo
        System.out.println("\n-set.is_empty() returns:");
        System.out.println(set.is_empty());
        
        boolean fileNotOpen = true;
        String input;
        Scanner inFile;
        FileInputStream in = null;
               
        while (fileNotOpen){
            try {
 
              // if class is run insided Netbeans program strucutre, use line below:
                in = new FileInputStream("src\\program2\\Set.java");
              // if class is run inside BlueJ, use line below:
              //  in = new FileInputStream("Set.java");
                fileNotOpen = false;
                System.out.println("\n-This current file (Set.java) was found...");
            } catch (FileNotFoundException e) {
                System.out.println("-Couldn't open file; Goodbye");
                System.exit(1);
            } // end catch
        } // end while
        inFile = new Scanner(in);
                
        while (inFile.hasNext()) {
           
            inFile.useDelimiter("[\\]\\[\\\\ .&,-?'!^/{}_();+*@:\n\"]+");
            input = inFile.next();           
            
            if (input.length() > 1) {
                
                // FYI: insert() method demo
                set.insert(input.trim());
            }
        } // end while
       
        inFile.close();
        
     
      
        // FYI: toString() method demo
        System.out.println("\n-All words in this file where read in.\n"
                + "-All punctuation was stripped.\n"
                + "-Each word was placed into the Set");
        System.out.println("\n-set.toString() is demonstrated below:");
        System.out.println(set);
        
        //FYI: is_empty() method demo
        System.out.println("\n-set.is_empty() now returns:");
        System.out.println(set.is_empty());
        
        // FYI: getCapacity() method demo
        System.out.println("\n-set.getCapacity() returns:");
        System.out.println(set.getCapacity());
       
        // FYI: inventory() method demo - (replaced with a return of "size")
        System.out.println("\n-inventory() method would return the same as getCapcity()."
                + "\n-inventory() is now used to return the private int variable size.");
        System.out.println("-set.inventory() returns:");     
        System.out.println(set.inventory());
        
        
        //FYI: contains() method demo 
        System.out.println("\n-set.contains(\"Andrew\") returns: ");
        System.out.println(set.contains("Andrew"));
                
        //FYI: remove() method demo
        System.out.println("\n-Removing the word \"Andrew\" from the set");
        set.remove("Andrew");
        System.out.println("-The word \"Andrew\" was removed from the set");        
        System.out.println(set);
              
        //FYI: contains() method demo 
        System.out.println("\n-set.contains(\"Andrew\") now returns: ");
        System.out.println(set.contains("Andrew"));
        
        //FYI: getFirstItem() method demo
        System.out.println("\n-Retrieving and removing the first string in set");
        System.out.println(set.getFirstItem() + " -has been removed");
        System.out.println(set);
        
        
        //FYI: getRandomItem() method demo
        System.out.println("\n-Retrieving and removing a random string in set");
        System.out.println(set.getRandomItem()+ " -has been removed");
        System.out.println(set);
        
        
        //FYI: getFirstItem() method demo
        System.out.println("\n-Retrieving and removing the new first string in set");
        System.out.println(set.getFirstItem() + " -has been removed");
        System.out.println(set);
        
        //FYI: getRandomItem() method demo
        System.out.println("\n-Retrieving and removing another random string in set");
        System.out.println(set.getRandomItem()+ " -has been removed");
        System.out.println(set);        
        
        
        // FYI: getCapacity() method demo
        System.out.println("\n-set.getCapacity() now returns:");
        System.out.println(set.getCapacity());
        
        
        System.out.println("\n-Removing 10 random items");
        for (int i = 0; i < 10; i++) {
            System.out.println("removed: " + set.getRandomItem());
        }
        System.out.println("\n-set.toString():");
        System.out.println(set);     
        
        // FYI: getCapacity() method demo
        System.out.println("\n-set.getCapacity() now returns:");
        System.out.println(set.getCapacity());
        
        
        System.out.println("\n-Creating new Set: setCopy");
        Set setCopy = new Set();
       
        // FYI: getClone() method demo
        System.out.println("-copying set into setCopy: set.getClone");
        setCopy = set.getClone();
        
        System.out.println("\nPrinting out setCopy");
        System.out.println("\n-setCopy.toString():");
        System.out.println(setCopy);
 
        //FYI: getRandomItem() method demo
        System.out.println("\n-Retrieving and removing a random string in setCopy");
        System.out.println(setCopy.getRandomItem()+ " -has been removed");
        
        System.out.println("\n-setCopy.toString():");
        System.out.println(setCopy);  
        
        // FYI: getCapacity() method demo
        System.out.println("\n-setCopy.getCapacity() now returns:");
        System.out.println(setCopy.getCapacity());
        
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