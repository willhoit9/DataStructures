// add copy methods to this 

package com.lab2;

public class LinkedListWithNode
	{
		// reference to the head node.
		private Node head;
		private int listCount;
		
		// LinkedList constructor
		public LinkedListWithNode()
		{
			// this is an empty list, so the reference to the head node
			// is set to a new node with no data
			head = new Node(null);
			listCount = 0;
		}
		

                /**
                 * 
                 * @param source
                 *      A LinkedListWithNode object that you wish to copy
                 * @precondition
                 *      The LinkedListWithNode object is not null
                 * @return 
                 *     A LinkedListWithNode object that is a real copy. (not shallow)
                 */ 
                 public static LinkedListWithNode listCopy(LinkedListWithNode source)
                {  
                    // this method could be eliminated, but will keep for simplicity of full copy
                    if (source == null)
                    {
                        System.out.println("--NULL");
                        return null;
                    }

                    LinkedListWithNode copy = new LinkedListWithNode();
                    
                    int x = 1;
                    while (source.get(x) != null)
                    {
                       // System.out.println(source.get(x));
                        copy.add(source.get(x));
                        x++;
                    }
                    return copy;
                }
                
                
                
                /**
                 * 
                 * @param source
                 *      a LinkedListWithNode object that you wish to copy part or all of
                 * @param start
                 *      an integer that represents the starting point in the source list you wish to copy
                 * @param end
                 *      an integer that represents the end point in the source list you wish to copy
                 * @precondition
                 *      start is 1 or greater and end is not greater than the size of the list and start
                 *      is less than end.
                 * @return 
                 *      
                 */
                 public static LinkedListWithNode listCopy(LinkedListWithNode source, int start, int end)
                {
                    // handles empty list, start less than 1, end greater than size, start greater than end

                    if (source == null || start < 1 || end > source.size() || start > end ) {
                        return null;
                    }

                    LinkedListWithNode copy = new LinkedListWithNode();
                    
                    int x = start;
                    while (source.get(x) != null & x <= end)
                    {
                        copy.add(source.get(x));
                        x++;
                    }
                    
                    return copy;
                }
                
                 /**
                  * 
                  * @param source
                  *     A LinkedListWithNode object that you wish to copy, backwards!
                  * @precondition
                  *     The LinkedListWithNode object is not null
                  * @return 
                  *     A copy of the LinkListWithNode object, backwards!
                  */
                public static LinkedListWithNode reverseCopy(LinkedListWithNode source)
                {
                    // handles empty list
                    if (source == null)
                    {
                        System.out.println("--NULL");
                        return null;
                    }
                   
                   LinkedListWithNode revCopy = new LinkedListWithNode();
                    
                    int x = 1;
                    while (source.get(x) != null)
                    {
                       // System.out.println(source.get(x));
                        revCopy.add(source.get(x),1);
                        x++;
                    }

                    return revCopy;
                } 
                
                
                /**
                  * 
                  * @param source
                  *     A LinkedListWithNode object that you wish trim of duplicates
                  * @precondition
                  *     The LinkedListWithNode object is not null
                  * @return 
                  *     A copy of the LinkListWithNode object sans duplicate values
                  */
                public static LinkedListWithNode loseDupes(LinkedListWithNode source)
                {
                    // handles empty list
                    if (source == null)
                    {
                        System.out.println("--NULL");
                        return null;
                    }
                    
                    LinkedListWithNode copy = new LinkedListWithNode();
                    copy.add(source.get(1)); 
                    
                    int x = 2;
                    while (source.get(x) != null) {
                        boolean add = true;
                        int current = (Integer.parseInt(source.get(x).toString()));

                        for (int y = 1; y < copy.size() + 1; y++) {
                            int currentCopy = (Integer.parseInt(source.get(y).toString()));

                                if(current == currentCopy){
                                    add = false;
                                }
                        }
                        
                        if (add) {
                            copy.add(source.get(x));
                        }
                        x++;
                    }
                    return copy;
                }
                
                
                
                
		public void add(Object data)
		// post: appends the specified element to the end of this list.
		{
			Node temp = new Node(data);
			Node current = head;
			// starting at the head node, crawl to the end of the list
			while(current.getNext() != null)
			{
				current = current.getNext();
			}
			// the last node's "next" reference set to our new node
			current.setNext(temp);
			listCount++;// increment the number of elements variable
		}
		
		public void add(Object data, int index)
		// post: inserts the specified element at the specified position in this list.
		{
			Node temp = new Node(data);
			Node current = head;
			// crawl to the requested index or the last element in the list,
			// whichever comes first
			for(int i = 1; i < index && current.getNext() != null; i++)
			{
				current = current.getNext();
			}
			// set the new node's next-node reference to this node's next-node reference
			temp.setNext(current.getNext());
			// now set this node's next-node reference to the new node
			current.setNext(temp);
			listCount++;// increment the number of elements variable
		}
		
		public Object get(int index)
		// post: returns the element at the specified position in this list.
		{
			// index must be 1 or higher
			if(index <= 0)
				return null;
			
			Node current = head.getNext();
			for(int i = 1; i < index; i++)
			{
				if(current.getNext() == null)
					return null;
				
				current = current.getNext();
			}
			return current.getData();
		}
		
		public boolean remove(int index)
		// post: removes the element at the specified position in this list.
		{
			// if the index is out of range, exit
			if(index < 1 || index > size())
				return false;
			
			Node current = head;
			for(int i = 1; i < index; i++)
			{
				if(current.getNext() == null)
					return false;
				
				current = current.getNext();
			}
			current.setNext(current.getNext().getNext());
			listCount--; // decrement the number of elements variable
			return true;
		}
		
		public int size()
		// post: returns the number of elements in this list.
		{
			return listCount;
		}
		
                @Override
		public String toString()
		{
			Node current = head.getNext();
			String output = "";
			while(current != null)
			{
				output += "[" + current.getData().toString() + "]";
				current = current.getNext();
			}
			return output;
		}
		
		private class Node
		{
			// reference to the next node in the chain,
			// or null if there isn't one.
			Node next;
			// data carried by this node.
			// could be of any type you need.
			Object data;
			

			// Node constructor
			public Node(Object _data)
			{
				next = null;
				data = _data;
			}
			
			// another Node constructor if we want to
			// specify the node to point to.
			public Node(Object _data, Node _next)
			{
				next = _next;
				data = _data;
			}
			
			// these methods should be self-explanatory
			public Object getData()
			{
				return data;
			}
			
			public void setData(Object _data)
			{
				data = _data;
			}
			
			public Node getNext()
			{
				return next;
			}
			
			public void setNext(Node _next)
			{
				next = _next;
			}
		}

	/**
	 * Main method written by Cate Sheller for testing LinkedList class
	 */
	public static void main(String[] args) {
		LinkedListWithNode list = new LinkedListWithNode();
		String data;
		for (int x=0; x<8; x++){
			data = ""+x;
			list.add(data);
		}
		System.out.println("Original list:");
		System.out.println(list);

		
		int index = ((int)(Math.random()*10))%list.size() + 1;
		System.out.println("Getting item at index " + index);
		System.out.println(list.get(index)+ " retrieved");
		System.out.println("Removing item at index 4");
		list.remove(4);
		System.out.println("Revised list:");
		System.out.println(list);
		System.out.println("Removing first item:");
		list.remove(1);
		System.out.println("Revised list:");
		System.out.println(list);
		System.out.println("Removing last item");
		list.remove(list.size());
		System.out.println("Revised list:");
		System.out.println(list);
		index = ((int)(Math.random()*1000))%list.size() + 1;
		System.out.println("Adding new item at position " + index);
		list.add(""+((int)Math.random()*10), index);
		System.out.println("Revised list:");
		System.out.println(list);
                
                
                
                // start of my junk
                System.out.println(list.size());
                System.out.println(list.head.data);
                
                System.out.println("\n-Trying to copy;");
                LinkedListWithNode copiedList;
                copiedList = LinkedListWithNode.listCopy(list);
                
                
                
                System.out.println(copiedList);
                
                System.out.println(copiedList.size());
  
                
                LinkedListWithNode partCopiedList;
       //         partCopiedList = LinkedListWithNode.listCopy();
                
                
                
//                LinkedListWithNode ogCopiedList;
//                ogCopiedList = LinkedListWithNode.listCopy(list);
//                System.out.println(ogCopiedList);               
//                System.out.println(ogCopiedList.size());
                
                
         //       System.out.println("\n-Trying to copy a part;");
         //       LinkedListWithNode partList;
         //       partList = LinkedListWithNode.listCopy(list.get(3));
                
                
                
                
       //         System.out.println(partList);
                
        //        System.out.println(partList.size());
         //       System.out.println(partList.head.data);
                
                
	}
}