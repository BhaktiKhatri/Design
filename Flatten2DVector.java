package Design;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/*
 * https://leetcode.com/problems/flatten-2d-vector/discuss/67652/7-9-lines-added-Java-and-C%2B%2B-O(1)-space.	
 */

public class Flatten2DVector {

    private Iterator<List<Integer>> i;	//row iterator
    private Iterator<Integer> j;		//col iterator

    public Flatten2DVector(List<List<Integer>> vec2d) {
    	System.out.println("i: "+i+" j: "+j);
        i = vec2d.iterator();	        //if row iterator i doesn't have next, it means vec2d is empty
        System.out.println("i: "+i);
    }

    public int next() {
        hasNext();	        //we won't call next() unless we are sure that hasNext() is true, meaning col iterator j.hasNext() is true
        System.out.println("i: "+i+" j: "+j);
        return j.next();
    }

    public boolean hasNext() {
    	System.out.println("i: "+i+" j: "+j);
        while ((j == null || !j.hasNext()) && i.hasNext())	{        //check whether vec2d is empty
            j = i.next().iterator();	//important!!! we do update here in hasNext() //update col_itr and row_itr until col_itr.hasNext() or row_itr.hasNext() is false
        }	
        System.out.println("i: "+i+" j: "+j);
        return j != null && j.hasNext();
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
		
		Flatten2DVector iterator = new Flatten2DVector(list);	//{{1,2},{3},{4}}

		System.out.println(iterator.next()); 	// return 1
		System.out.println(iterator.next()); 	// return 2
		System.out.println(iterator.next()); 	// return 3
		System.out.println(iterator.hasNext()); // return true
		System.out.println(iterator.hasNext()); // return true
		System.out.println(iterator.next()); 	// return 4
		System.out.println(iterator.hasNext()); // return false
	}
}

