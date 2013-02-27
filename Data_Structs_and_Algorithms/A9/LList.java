import java.util.*;
import java.io.*;

public class LList
{
   //nested class to represent a node
   private class Node
   {
          public int data;
          public Node next;
   }

   //only instance variable that points to the first node.
   private Node first;

   // Constructs an empty linked list.
   public LList()
   {
      first = null;
   }

   public void addElem(int element)
   {
       Node newNode = new Node();
       newNode.data = element;
       newNode.next = first;
       first = newNode;
   }

   public void printList(PrintWriter pw){
	   Node n = first;
	   while(n != null){
		   pw.print(n.data + " ");
		   n = n.next;
	   }
	   pw.println();
   }

   public void printKey(int key,PrintWriter pw){
   	   Node n = first;
   	   do{
   		   pw.print(n.data + " ");
   		   n = n.next;
   	   }while(n.data != key);
   	   pw.println(n.data);
   }

   public boolean searchElem(int key){
	   Node n = first;
	   while(n != null){
		   if(n.data == key){
		        return true;
			}else{
				n = n.next;
			}
		}
		return false;
   }

}//end class

