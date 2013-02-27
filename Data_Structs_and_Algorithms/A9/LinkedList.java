// Assignment #: 10
//         Name: Dave Hubbard
//    StudentID: 1202945271
//      Lecture: 3
//  Description: A linked list is a sequence of nodes with efficient
// 				element insertion and removal.
// 				This class contains a subset of the methods of the
// 				standard java.util.LinkedList class.

import java.util.NoSuchElementException;

public class LinkedList
{
   //nested class to represent a node
   private class Node
   {
          public Object data;
          public Node next;
   }

   //only instance variable that points to the first node.
   private Node first;

   // Constructs an empty linked list.
   public LinkedList()
   {
      first = null;
   }


   // Returns the first element in the linked list.
   public Object getFirst()
   {
      if (first == null)
       {
         NoSuchElementException ex
             = new NoSuchElementException();
         throw ex;
       }
      else
         return first.data;
   }

   // Removes the first element in the linked list.
   public Object removeFirst()
   {
      if (first == null)
       {
         NoSuchElementException ex = new NoSuchElementException();
         throw ex;
       }
      else
       {
         Object element = first.data;
         first = first.next;  //change the reference since it's removed.
         return element;
       }
   }

   // Adds an element to the front of the linked list.
   public void addFirst(Object element)
   {
      //create a new node
      Node newNode = new Node();
      newNode.data = element;
      newNode.next = first;
      //change the first reference to the new node.
      first = newNode;
   }

   // Returns an iterator for iterating through this list.
   public ListIterator listIterator()
   {
      return new LinkedListIterator();
   }

	// Gives the size of the current list.
   public int size(){
	   ListIterator l = listIterator();

	   int i = 0;
	   while(l.hasNext()){
		   i++;
		   l.next();
	   }
	   return i;

   }
	// Returns an element of a given index
   public Object getElement(int index){
		ListIterator l = listIterator();

		if(index > size() || index < 0){
			IndexOutOfBoundsException ex = new IndexOutOfBoundsException();
			throw ex;
		}

		for(int i=0; i<index; i++){
			l.next();
		}
		return l.next();
   }
	// Adds an element to a given index
   public void addElement(int index, Object element){
		ListIterator l = listIterator();

		if (l.hasNext()==false){
			addFirst(element);
		}else if(index <= size()){
				for(int i = 0; i < index; i++){
				l.next();
				}
			l.add(element);
		}
		else{
			IndexOutOfBoundsException ex = new IndexOutOfBoundsException();
			throw ex;
		}
   }
	// Finds the largest object lexographically
   public Object findLargest(){
	   ListIterator l = listIterator();
	   String result = (String)first.data;
	   while(l.hasNext()){
			 String temp = (String)l.next();
			 if((temp.compareTo(result)) > 0){
					result = temp;
			   }
	   }
	   return (Object)result;
   }
	// Finds a string and removes it from the list
   public int searchAndRemove(Object element){
	   ListIterator l = listIterator();
	   int index = 0;
	   while(l.hasNext()){
		   if(l.next().equals(element)){
			   l.remove();
			   return index;
		   }
		   index ++;
	   }
	   return -1;
   }
	// Creates a duplicate of each index
   public void duplicateEach(){
	   ListIterator l = listIterator();

	   while(l.hasNext()){
		   l.add(l.next());
	   }
   }
	// Removes the specified number of indicies from the front of the list
   public void removeFirstFew(int howMany){

	   if (howMany > size())
	   		howMany = size();

	   for(int i=0; i < howMany; i++){
		   removeFirst();
	   }

   }

   public String toString(){
	   ListIterator l = listIterator();
	   String result = "{ ";

	   while(l.hasNext()){

			result+=(l.next()) + " ";
	   }

	   return result + "}\n";
   }




   //nested class to define its iterator
   private class LinkedListIterator implements ListIterator
   {
      private Node position; //current position
      private Node previous; //it is used for remove() method

      // Constructs an iterator that points to the front
      // of the linked list.

      public LinkedListIterator()
      {
         position = null;
         previous = null;
      }

     // Tests if there is an element after the iterator position.
     public boolean hasNext()
      {
         if (position == null) //not traversed yet
          {
             if (first != null)
                return true;
             else
                return false;
          }
         else
           {
              if (position.next != null)
                 return true;
              else
                 return false;
           }
      }

      // Moves the iterator past the next element, and returns
      // the traversed element's data.
      public Object next()
      {
         if (!hasNext())
          {
           NoSuchElementException ex = new NoSuchElementException();
           throw ex;
          }
         else
          {
            previous = position; // Remember for remove

            if (position == null)
               position = first;
            else
               position = position.next;

            return position.data;
          }
      }

      // Adds an element before the iterator position
      // and moves the iterator past the inserted element.
      public void add(Object element)
      {
         if (position == null) //never traversed yet
         {
            addFirst(element);
            position = first;
         }
         else
         {
            //making a new node to add
            Node newNode = new Node();
            newNode.data = element;
            newNode.next = position.next;
            //change the link to insert the new node
            position.next = newNode;
            //move the position forward to the new node
            position = newNode;
         }
         //this means that we cannot call remove() right after add()
         previous = position;
      }

      // Removes the last traversed element. This method may
      // only be called after a call to the next() method.
      public void remove()
      {
         if (previous == position)  //not after next() is called
          {
            IllegalStateException ex = new IllegalStateException();
            throw ex;
          }
         else
          {
           if (position == first)
            {
              removeFirst();
            }
           else
            {
              previous.next = position.next; //removing
            }
           //stepping back
           //this also means that remove() cannot be called twice in a row.
           position = previous;
      }
      }

      // Sets the last traversed element to a different value.
      public void set(Object element)
      {
         if (position == null)
          {
            NoSuchElementException ex = new NoSuchElementException();
            throw ex;
          }
         else
          position.data = element;
      }
   } //end of LinkedListIterator class
} //end of LinkedList class
