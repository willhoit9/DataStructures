package classes;

//import com.sun.corba.se.spi.monitoring.MonitoringConstants;

import java.util.Arrays;


public class BST<E extends Comparable<E>>
{
    private BTNode<E> root;    
    int count = 0;
    
    public BST(E data)
    {
        root = new BTNode(data, null, null);
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
    
    public int lessThan(BTNode<E> current,  E val) 
    {
      //  int count = 0;
        E d1 = current.getData();
        E value = val;
        
        System.out.println("current.getData: " + d1);
        System.out.println("searching for values < " + value);
   


        if ( value.compareTo(d1) <= 0 ) {
            System.out.println("current.getData " + d1 + " is greater than  "+ value + ".");
                        
            if (current.getLeft() != null) {
                System.out.println("@@   " +current.getLeft().getData());
                lessThan(current.getLeft(), value);
            } 
//            if (current.getRight() !=null) {
//                System.out.println("##   " +current.getRight().getData());
//                lessThan(current.getRight(), value);
//            }
            
        } else {
         
            System.out.println("current.getData " + d1 + " is less than  "+ value + ".");
            count++;
            if (current.getLeft() != null) {
                System.out.println("$$   " + current.getLeft().getData());
                lessThan(current.getLeft(), value);
            } 
            if (current.getRight() !=null) {
                System.out.println("%%   " +current.getRight().getData());
                lessThan(current.getRight(), value);
            }
        }

        return count;
    } //end lessThan(.)
    

    
    public int greaterThan(BTNode<E> current, E val) 
    {
        
        E d1 = current.getData();
        E value = val;
        
        System.out.println("current.getData: " + d1);
        System.out.println("searching for values > " + value);
   
//        if (value.compareTo(d1) == 0) {
//            int s = (int)BTNode.treeSize(current.getLeft());
//            return s;
//        }

        if ( value.compareTo(d1) < 0 ) {
           
            count++;
            System.out.println("current.getData " + d1 + " is greater than  "+ value + ".");
            
           
            if (current.getRight() !=null) {
                System.out.println("##   " +current.getRight().getData());
                greaterThan(current.getRight(), value);
            }
            if (current.getLeft() != null) {
                System.out.println("@@   " +current.getLeft().getData());
                greaterThan(current.getLeft(), value);
            } 
 
            
        } else {
            System.out.println("current.getData " + d1 + " is less than  "+ value + ".");

            if (current.getRight() !=null) {
                System.out.println("%%   " +current.getRight().getData());
                greaterThan(current.getRight(), value);
            }
//            if (current.getLeft() != null) {
//                System.out.println("$$   " + current.getLeft().getData());
//                greaterThan(current.getLeft(), value);
//            } 
        }
        
        return this.count;
    } //end greaterThan(.) 
    
    
    public int inBetween(BTNode<E> current, E val1, E val2) 
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
        System.out.println("Lower limit: " + lower);
        System.out.println("Upper limit: " + upper);
        System.out.println("current.getData: " + d1);
        System.out.println("searching for values less than: " + upper + " and greater than: " + lower);
   


        if ( upper.compareTo(d1) > 0  && lower.compareTo(d1) < 0 ) {
            System.out.println("IN RANGE:");
            System.out.println("   current.getData " + d1 + " is less than  "+ upper + ".");
            System.out.println("   current.getData " + d1 + " is greater than  "+ lower + ".");
            count++;            
            if (current.getLeft() != null) {
                System.out.println("@@   " +current.getLeft().getData());              
                inBetween(current.getLeft(), lower, upper);
            } 
            if (current.getRight() !=null) {
                System.out.println("##   " +current.getRight().getData());
                inBetween(current.getRight(), lower, upper);
            }
            
        } else {
         
            System.out.println("OUT OF RANGE:");
            if (upper.compareTo(d1) <= 0) {
                System.out.println("   current.getData " + d1 + " is greater than  "+ upper + ".");
            }
            if (lower.compareTo(d1) >= 0) {
                 System.out.println("   current.getData " + d1 + " is lower than  "+ lower + ".");
            }
            
            
            if (current.getLeft() != null) {
                System.out.println("$$   " + current.getLeft().getData());
                inBetween(current.getLeft(), lower, upper);
            } 
            if (current.getRight() !=null) {
                System.out.println("%%   " +current.getRight().getData());
                inBetween(current.getRight(), lower, upper);
            }
        }
       return count; 
    } //end inBetween(..)
       
    public void print()
    {
        root.print(0);
    }
    
    
    

    
    
    public BST(E[] array)
    {
        E[] ar = array;
        Arrays.sort(ar);
        
        System.out.println(ar.getClass());
        
        System.out.println("\nSorted:\n"); 
        for (Object object : ar) {
            System.out.println(object.toString());
        }
        
        root = new BTNode(ar[ar.length/2 ], null, null);
        
        builder(ar);

    }
    
    public void builder(E[] ar) 
    {
        System.out.println("ENTERING BUILDER");
        E[] arrayLeft = Arrays.copyOfRange(ar, 0, ar.length/2);
        E[] arrayRight = Arrays.copyOfRange(ar, (ar.length/2) + 1, ar.length);
        
        if (arrayLeft.length > 0)
        {
            System.out.println(arrayLeft[arrayLeft.length/2]);
            insert(root, (arrayLeft[arrayLeft.length/2]));
            builder(arrayLeft);
        }
        if (arrayRight.length > 0)
        {
            System.out.println(arrayRight[arrayRight.length/2]);
            insert(root, (arrayRight[arrayRight.length/2]));
            builder(arrayRight);
        }

    }
    
    public static void main (String [] args)
   {
       BST<Integer> tree = new BST<Integer>(17);
       
       System.out.println("Building binary search tree with known values");
       tree.insert(tree.getRoot(), 2);
       tree.insert(tree.getRoot(),34);
       tree.insert(tree.getRoot(),5);
       tree.insert(tree.getRoot(),55);
       tree.insert(tree.getRoot(),15);
       tree.insert(tree.getRoot(),25);
       tree.insert(tree.getRoot(),9);
       tree.print();
       
       
       System.out.println("\n PostOrderPrint");
       tree.root.postorderPrint();
       System.out.println("\n");
       


        System.out.println("\nLessThan:");
        tree.count = 0;
        int answer = tree.lessThan(tree.getRoot(), 33);
        System.out.println("\nAnswer " +answer);



        tree.count = 0;

        System.out.println("\nGreaterThan:"); 
        answer = tree.greaterThan(tree.getRoot(), 3);
        System.out.println("\nAnswer " + answer);


        tree.count = 0;

        System.out.println("\nInBetween:");
        answer = tree.inBetween(tree.getRoot(), 2 , 34);
        System.out.println("\nAnswer " + answer);


        
        
//        
//        
//        
//       System.out.println("\n\nBuilding binary search tree with random values");
//       tree = new BST<Integer>((int)(Math.random()*50));
//       for (int x=0; x<14; x++)
//            tree.insert(tree.getRoot(),(int)(Math.random()*50));
//       tree.print();
//       
//       
//       
//       
//       
//       
//       
       
//       tree = new BST<Integer>(50);
//       
//       System.out.println("\nBuilding binary search tree with known values");
//       tree.insert(tree.getRoot(),20);
//       tree.insert(tree.getRoot(),14);
//       tree.insert(tree.getRoot(),97);
//       tree.insert(tree.getRoot(),100);
//       tree.insert(tree.getRoot(),2);
//       tree.insert(tree.getRoot(),45);
//       tree.insert(tree.getRoot(),36);
//       tree.insert(tree.getRoot(),44);
//       tree.insert(tree.getRoot(),72);
//       tree.insert(tree.getRoot(),76);
//       tree.print();
//       
//       
//       
//       
//       
//       
//       
//       
//        tree.count = 0;
//        System.out.println("\nLessThan:");
//        answer = tree.lessThan(tree.getRoot(), 73);
//        System.out.println("\nAnswer " + answer);
//        
//        
//        tree.count = 0;
//        
//        System.out.println("\nGreaterThan:");
//        answer = tree.greaterThan(tree.getRoot(), 51);
//        System.out.println("\nAnswer " + answer);
//       
//       
//       
//        tree.inBetween(tree.getRoot(), 100 , 100);
//        
        
        
      //  <E> [] objArr = new <E>[12];
//        Integer[] data = new Integer[10];
//        data[0] = new Integer(3);
//        data[1] = new Integer(5);
//        data[2] = new Integer(42);
//        data[3] = new Integer(87);
//        data[4] = new Integer(16);
//        data[5] = new Integer(25);
//        data[6] = new Integer(99);
//        data[7] = new Integer(1);
//        data[8] = new Integer(66);
//        data[9] = new Integer(55);

        Integer[] data = new Integer[17];
        data[0] = new Integer(1);
        data[1] = new Integer(2);
        data[2] = new Integer(3);
        data[3] = new Integer(4);
        data[4] = new Integer(5);
        data[5] = new Integer(6);
        data[6] = new Integer(7);
        data[7] = new Integer(8);
        data[8] = new Integer(9);
        data[9] = new Integer(10);
        data[10] = new Integer(11);
        data[11] = new Integer(12);
        data[12] = new Integer(13);
        data[13] = new Integer(14);
        data[14] = new Integer(15);
        data[15] = new Integer(16);
        data[16] = new Integer(17);
        
        
        System.out.println("\nArray stuff");
        int [] arrayInts = new int [12];  
        System.out.println(data.length);
        System.out.println(data[0]);
        System.out.println(data[1]);
        
        for (Integer integer : data) {
            System.out.println(integer.toString());
       }
        

//       Arrays.sort(data);
        
//       for (Integer integer : data) {
//            System.out.println(integer.toString());
//       }
       BST<Integer> treeFromArray = new BST<Integer>(data);

       System.out.println("\nPrinting Tree Built From Array:\n");
       treeFromArray.print();
       
      //  System.out.println(data[1].compareTo(data[0]));
        
        
    }
}





//        if (value.compareTo(d1) == 0) {
//            int s = (int)BTNode.treeSize(current.getLeft());
//            return s;
//        }