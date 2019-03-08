//-----------------------------------------------------------------------------
// IntegerListInterface.java
// interface for the IntegerList ADT
//-----------------------------------------------------------------------------

public interface IntegerListInterface{

   // isEmpty
   // pre: none
   // post: returns true if this IntgerList is empty, false otherwise
   public boolean isEmpty();

   // size
   // pre: none
   // post: returns the number of elements in this IntegerList
   public int size();

   // get
   // pre: 1 <= index <= size()
   // post: returns item at position index
   public int get(int index) throws ListIndexOutOfBoundsException;

   // add
   // inserts newItem in this IntegerList at position index
   // pre: 1 <= index <= size()+1
   // post: !isEmpty(), items to the right of newItem are renumbered
   public void add(int index, int newItem) throws ListIndexOutOfBoundsException;

   // remove
   // deletes item from position index
   // pre: 1 <= index <= size()
   // post: items to the right of deleted item are renumbered
   public void remove(int index) throws ListIndexOutOfBoundsException;

   // removeAll
   // pre: none
   // post: isEmpty()
   public void removeAll();

}