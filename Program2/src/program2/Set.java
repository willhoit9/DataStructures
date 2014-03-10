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
       
    
    @Override
    public void resize(int larger) {
        if (larger <= size) {
            throw new IllegalArgumentException("\rAn Illegal Argument Exeption has Occured. \n"
					+  " The list may only be resized larger than its current size.");
        }    
    }

    @Override
    public void insert(String entry) {
        
    }

    @Override
    public void remove(String target) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean is_empty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int inventory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getCapacity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    

    public static void main(String[] args) {
        
        
        
        boolean fileNotOpen = true;
      //  Scanner kb = new Scanner(System.in);
        String input;
        Scanner inFile;
        FileInputStream in = null;
        
       
        while (fileNotOpen){
        try {
         //   This reads in the current program source code in my Netbeans 
         //   project. If it it run outside of a project structure, replace with: in = new FileInputStream("src\\program2\\Set.java");
            
            in = new FileInputStream("src\\program2\\Set.java");
            fileNotOpen = false;
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't open file; Goodbye");
            System.exit(1);
        }
        } // end while
        inFile = new Scanner(in);
                
//        while (inFile.hasNextLine()) {
//            input = inFile.nextLine();
//            int end = input.length()-1;
//            if (end > 0 && input.charAt(end) == ';') {
//                System.out.println(input);
//                
//            }
//        } // end while
        
        String [] words = null;
        while (inFile.hasNext()) {
            input = inFile.next().replaceAll("[^a-zA-Z ]", "");
            int end = input.length() -1;
            
            words = new String[300];
            int i = 0;
            if (end > 0) {
                System.out.println(input);
                words[i] = input;
                i = i + 1;
            }
        } // end while
        
        System.out.println(words.length);
        
      // if (words.length > 0) {
            for (int i = 0; i < words.length; i++) {
                System.out.println(words[i] + " -");
            }
      //  }

        
        
        
        inFile.close();

    } // end main()
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
        private class Node {
        
        Node next;
        String data;
        
        // Node constructor
        public Node(String _data) {
            next = null;
            data = _data;
        }
        
        // Node constructor for pointing to specific node
        public Node(String _data, Node _next) {
            next = _next;
            data = _data;
        }
        
        public String getData() {
            return data;
        }
        
        public void setData(String _data) {
            data = _data;
        }
        
        public Node getNext() {
            return next;
        }
        
        public void setNext(Node _next) {
            next = _next;
        }
        
                
    } // end of private inner class Node
    
    
    
    
} // end class Set
