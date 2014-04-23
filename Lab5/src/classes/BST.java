package classes;

//Andrew Willhoit - Data Structures 
//Lab 5- BST.java  -  4/23/14
//Based on Main and Sheller's code
//adds: 
//    lessThan(BTNode<E> current,  E val) - a helper method that does counting
//    lessThanRecur(BTNode<E> current,  E val) - does the real work
//    greaterThan(BTNode<E> current,  E val) - a helper method that does counting
//    greaterThanRecur(BTNode<E> current,  E val) - does the real work
//    inBetween(BTNode<E> current, E val1, E val2)  - a helper method that does counting
//    inBetweenRecur(BTNode<E> current,  E val) - does the real work
// methods
//
//adds:
//   BST(E[] array)
//constructor
//
//adds:
//   builder(E[] array)
//method



import java.util.Arrays;

public class BST<E extends Comparable<E>>
{
    private BTNode<E> root;    
    private int count = 0;
    
    public BST(E data)
    {
        root = new BTNode(data, null, null);
    }
    
    // constructor to build a BST from an array of comparables
    public BST(E[] array)
    {
        E[] ar = array;
        Arrays.sort(ar);

//        System.out.println("\nSorted:\n"); 
//        for (Object object : ar) {
//            System.out.println(object.toString());
//        }
        
        root = new BTNode(ar[ar.length/2 ], null, null);        
        builder(ar);
    }
    
    // takes in an array, splits it, inserts the middle into the tree, 
    // and splits it agian, ad nauseam... er recursively 
    private void builder(E[] array) 
    {
        //System.out.println("ENTERING BUILDER");
        E[] arrayLeft = Arrays.copyOfRange(array, 0, array.length/2);
        E[] arrayRight = Arrays.copyOfRange(array, (array.length/2) + 1, array.length);
        
        if (arrayLeft.length > 0)
        {
            //System.out.println(arrayLeft[arrayLeft.length/2]);
            insert(root, (arrayLeft[arrayLeft.length/2]));
            builder(arrayLeft);
        }
        if (arrayRight.length > 0)
        {
           //System.out.println(arrayRight[arrayRight.length/2]);
            insert(root, (arrayRight[arrayRight.length/2]));
            builder(arrayRight);
        }
    }
    
    public BTNode<E> getRoot()
    {
        return root;
    }
    
    public void insert(BTNode<E> current, E data)
    {
        E d1 = current.getData();
        E d2 = data;         
        if (d2.compareTo(d1)<=0) {
            if (current.getLeft() == null)
                current.setLeft(new BTNode(data,null,null));
            else
                insert(current.getLeft(),data);
        }
        else {
            if (current.getRight() == null)
                current.setRight(new BTNode(data,null,null));
            else
                  insert(current.getRight(), data);  
              
        }
    }  
    
    
    
    // This "helper", public method was added to assist with counting.
    // The method below this one (now called lessThanRecur) was the original.
    // The count is keep outside of the methods, so it had to be reset before
    // everytime the lessThan or greaterThan methods were called. This is
    // awkward to do from the main method.
    // adding this (lessThan) method takes care of that for you.
    // It resets the count variable to 0. It calls the method that does the counting
    // It then resets the count variable back to 0 and returns the number of values found
    public int lessThan(BTNode<E> current,  E val) 
    {
        this.count = 0;
        int numberLessThan = lessThanRecur(current, val);
        this.count = 0;
        return numberLessThan;
    }
    
    private int lessThanRecur(BTNode<E> current,  E val) 
    {
        E d1 = current.getData();
        E value = val;
        if ( value.compareTo(d1) <= 0 ) {
            if (current.getLeft() != null) {
                lessThanRecur(current.getLeft(), value);
            } 
        } else {
            count++;
            if (current.getLeft() != null) {
                lessThanRecur(current.getLeft(), value);
            } 
            if (current.getRight() !=null) {
                lessThanRecur(current.getRight(), value);
            }
        }
        return count;
    } //end lessThanRecur(.)
    

    public int greaterThan(BTNode<E> current,  E val) 
    {
        this.count = 0;
        int numberGreaterThan = greaterThanRecur(current, val);
        this.count = 0;
        return numberGreaterThan;
    }
    
    
    private int greaterThanRecur(BTNode<E> current, E val) 
    {       
        E d1 = current.getData();
        E value = val;
        
        if ( value.compareTo(d1) < 0 ) {         
            count++;
            if (current.getRight() !=null) {
                greaterThanRecur(current.getRight(), value);
            }
            if (current.getLeft() != null) {
                greaterThanRecur(current.getLeft(), value);
            } 
        } else {
            if (current.getRight() !=null) {
                greaterThanRecur(current.getRight(), value);
            }
        }
        return this.count;
    } //end greaterThanRecur(.) 
    
    
    public int inBetween(BTNode<E> current,  E val1, E val2) 
    {
        this.count = 0;
        int numberinBetween = inBetweenRecur(current, val1, val2);
        this.count = 0;
        return numberinBetween;
    }
        
    private int inBetweenRecur(BTNode<E> current, E val1, E val2) 
    {
        E d1 = current.getData();
        E lower;
        E upper;
        
        if ( val1.compareTo(val2) == 0  ) {
            throw new IllegalArgumentException("That's not a range bro.");
        }
        if ( val1.compareTo(val2) < 1  ) {
            lower = val1;
            upper = val2;
        } else {
            upper = val1;
            lower = val2;
        }

        if ( upper.compareTo(d1) > 0  && lower.compareTo(d1) < 0 ) {

            count++;            
            if (current.getLeft() != null) {            
                inBetweenRecur(current.getLeft(), lower, upper);
            } 
            if (current.getRight() !=null) {
                inBetweenRecur(current.getRight(), lower, upper);
            }
        } else {  
            if (upper.compareTo(d1) <= 0) {
            }
            if (lower.compareTo(d1) >= 0) {
            }
            if (current.getLeft() != null) {
                inBetweenRecur(current.getLeft(), lower, upper);
            } 
            if (current.getRight() !=null) {
                inBetweenRecur(current.getRight(), lower, upper);
            }
        }
       return count; 
    } //end inBetweenRecur(..)
       
    public void print()
    {
        root.print(0);
    }
    
    
    public static void main (String [] args)
   {
        BST<Integer> tree = new BST<>(17);

        System.out.println("Building binary search tree with known values");
        tree.insert(tree.getRoot(), 2);
        tree.insert(tree.getRoot(),34);
        tree.insert(tree.getRoot(),5);
        tree.insert(tree.getRoot(),55);
        tree.insert(tree.getRoot(),15);
        tree.insert(tree.getRoot(),25);
        tree.insert(tree.getRoot(),9);
        tree.print();

        System.out.println("\nTesting methods on tree with known values:");
        
        System.out.println("\nLessThan: 33");
        int answer = tree.lessThan(tree.getRoot(), 33);
        System.out.println("Answer: " + answer);
        
        System.out.println("\nGreaterThan: 5"); 
        answer = tree.greaterThan(tree.getRoot(), 5);
        System.out.println("Answer " + answer);
               
        System.out.println("\nInBetween: 2 and 34");
        answer = tree.inBetween(tree.getRoot(), 2 , 34);
        System.out.println("Answer " + answer);
        
            
        System.out.println("\n------------------------------------------------------------");
        
       System.out.println("\n\nBuilding binary search tree with random values:");
       tree = new BST<>((int)(Math.random()*50));
       for (int x=0; x<14; x++)
            tree.insert(tree.getRoot(),(int)(Math.random()*50));
       tree.print();

       
        System.out.println("\nTesting methods on tree with random values and random search values:");

        // test how many numbers are less than...
        int random = (int)(Math.random()*50);
        System.out.println("\nLessThan: " + random);
        answer = tree.lessThan(tree.getRoot(), random);
        System.out.println("Answer: " + answer);

        // test how many numbers are greater than...
        random = (int)(Math.random()*50);
        System.out.println("\nGreaterThan: " + random); 
        answer = tree.greaterThan(tree.getRoot(), random);
        System.out.println("Answer " + answer);

        // test how many numbers are in between...
        //(the inBetween methods have built in logic to pick which is the higher value
        // in the range. Exception will be thrown if both values are the same. So I'll catch that here.
        random = (int)(Math.random()*50);
        int random2 = (int)(Math.random()*50);

        System.out.println("\nInBetween: " + random + " and " + random2);

        try {
            answer = tree.inBetween(tree.getRoot(), random , random2);
            System.out.println("Answer " + answer);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n------------------------------------------------------------");
        
        
        

        System.out.println("\n\nTesting Building an array from known values:");

        Integer[] data = new Integer[15];
        
        for (int i = 0; i < 15; i++) {
           data[i] = new Integer(i+1);
        }


        System.out.println("\nPrinting out the known value array:");
        System.out.println(Arrays.toString(data));

        //creating a BST from the array
        BST<Integer> treeFromArray = new BST<>(data);

        System.out.println("\nPrinting Tree Built From Array of known values:\n");
        treeFromArray.print();

        System.out.println("\n------------------------------------------------------------");
        
        
        System.out.println("\n\nTesting Building an array from random values:");

        int length = ((int)(Math.random()*25)) + 10;
        System.out.println("Printing out array of random length of "+ length +", containing random values");
        Integer[] data2 = new Integer[length];
        for (int i = 0; i < length; i++) {
           data2[i] = new Integer((int)(Math.random()*50));
                   
        }
        System.out.println("\nPrinting out the random value array:");
        System.out.println(Arrays.toString(data2));
        
        
        //creating a BST from the random value array
        BST<Integer> treeFromArray2 = new BST<>(data2);

        System.out.println("\nPrinting Tree Built From Array:\n");
        treeFromArray2.print();
        
        
        
        
        System.out.println("Printing out the random value array sorted:  (for reference)");
        
        Arrays.sort(data2);
        System.out.println(Arrays.toString(data2));
        
    }
}



