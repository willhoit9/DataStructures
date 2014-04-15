package classes;

public class BST<E extends Comparable<E>>
{
    private BTNode<E> root;    
    
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
    
    public void print()
    {
        root.print(0);
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
       
       System.out.println("Building binary search tree with random values");
       tree = new BST<Integer>((int)(Math.random()*50));
       for (int x=0; x<14; x++)
            tree.insert(tree.getRoot(),(int)(Math.random()*50));
       tree.print();
    }
}