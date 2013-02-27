// Assignment #: 8
//         Name: Dave Hubbard
//    StudentID: 1202945271
//      Lecture: 3
//  Description: The Sorts class contains an insertion sort alogorithm to sort a given ArrayList.

import java.util.*;

public class Sorts{

	/*/////////////////////////////////////////// sort ///

	Uses an Insertion Sort algorithm to arrange a list of
	objects.
	////////////////////////////////////////////////////*/

	public static void sort(ArrayList list){

		Object key;
		int index;
		int position;


		for(index = 1; index < list.size(); index++){

			key = list.get(index);
			position = index;

			while(position > 0 && (((Comparable)list.get(position-1)).compareTo(key))> 0){

				list.set(position,(list.get(position-1)));
				position--;

			}
			list.set(position,key);
		}


	}

}//end