package Design;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * Design and implement an iterator to flatten a 2d vector. It should support the following operations: next and hasNext.
 * https://leetcode.com/problems/flatten-2d-vector/
 * Notes:Please remember to RESET your class variables declared in Vector2D, as static/class variables are persisted across multiple test cases. 
 * Please see here for more details.
 * You may assume that next() call will always be valid, that is, there will be at least a next element in the 2d vector when next() is called.
 * Follow up: As an added challenge, try to code it using only iterators in C++ or iterators in Java.
 * Explanation and Code from: https://leetcode.com/problems/flatten-2d-vector/discuss/67669/Java-Iterator-Solution-Explained
 * 
 */

//Refer this
/*
 * I first hold the 2D List inside a Iterator of List this allows me to operate on the subsequent list once needed. 
 * I then remove the first list from the 2D List and store as row which is evaluated when next() & hasNext() are called. 
 * I then want to ensure row iterator is pointing to a none empty list so i call the getNextRow() method. next() and hashNext() are now very simple. 
 * next() returns the next element of the current list then ensures row isn't empty. hasNext() checks row isn't null and has a next value.
 */
class Vector2D {
    Iterator<List<Integer>> itrs;
    Iterator<Integer> row;
    
    public Vector2D(List<List<Integer>> vec2d) {
        if(vec2d == null || vec2d.size() == 0) 
        	return;
        
        itrs = vec2d.iterator();
        row = itrs.next().iterator();
        getNextRow();
    }
    
    private void getNextRow(){
        while(!row.hasNext() && itrs.hasNext()) { 
        	row = itrs.next().iterator();
        }
    }

    public int next() {
        int next = row.next();
        getNextRow();
        return next;
    }

    public boolean hasNext() {
        return row != null && row.hasNext();
    }
    
    public static void main(String[] args) {
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(2);
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(3);
		List<Integer> list3 = new ArrayList<Integer>();
		list3.add(4);
		List<List<Integer>> list = new ArrayList<>();
		list.add(list1);
		list.add(list2);
		list.add(list3);
		
		Vector2D iterator = new Vector2D(list);	//{{1,2},{3},{4}}

		System.out.println(iterator.next()); 	// return 1
		System.out.println(iterator.next()); 	// return 2
		System.out.println(iterator.next()); 	// return 3
		System.out.println(iterator.hasNext()); // return true
		System.out.println(iterator.hasNext()); // return true
		System.out.println(iterator.next()); 	// return 4
		System.out.println(iterator.hasNext()); // return false
	}
}