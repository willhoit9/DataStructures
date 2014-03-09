package program2;

/**
 *
 * @author Andrew
 */
public interface StringSet
{
             // MUTATORS
             public void resize(int larger);
                  // precondition: larger is larger than current Set size
                  // postcondition: enlarges Set
             public void insert(String entry);
                  // postcondition: entry is inserted in Set if identical String
                  // not already present; if identical entry exists, takes no
                  // action.  Calls resize if necessary
             public void remove(String target);
                  // postcondition:  removes target value from Set if target is
                  // present; takes no action otherwise
             public String getRandomItem ();
                  // precondition: Set is not empty
                  // postcondition: A random String is retrieved and removed from
                  // the Set
             public String getFirstItem ();
                  // precondition: Set is not empty
                  // postcondition: the first item in the Set is retrieved and
                  // removed from the Set
                  
             // ACCESSORS
             public boolean contains(String target);
                   // postcondition: returns true if target is present, false
                   // if not
             public boolean is_empty( );
                   // postcondition: returns true if Set is empty, false if not
             public int inventory( );
                   // postcondition: returns total number of Strings currently in set
             public int getCapacity( );
                   // postcondition: returns total size of Set (used & unused portions)
}
