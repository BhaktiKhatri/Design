package Design;

import java.util.Map;
import java.util.HashMap;

/*
 * 170. Two Sum III - Data structure design
 * https://leetcode.com/problems/two-sum-iii-data-structure-design/
 * Design and implement a TwoSum class. It should support the following operations: add and find.

		add - Add the number to an internal data structure.
		find - Find if there exists any pair of numbers which sum is equal to the value.
		
		Example 1:
		
		add(1); add(3); add(5);
		find(4) -> true
		find(7) -> false
		Example 2:
		
		add(3); add(1); add(2);
		find(3) -> true
		find(6) -> false

	Explanation and Code from: @mfcndw https://leetcode.com/problems/two-sum-iii-data-structure-design/discuss/52005/Trade-off-in-this-problem-should-be-considered

 */

public class TwoSum {

    public static Map<Integer, Integer> map;
    
    /** Initialize your data structure here. */
    public TwoSum() {
        map = new HashMap<>();
    }
    
    /** Add the number to an internal data structure.. */
    public static void add(int number) {
    	System.out.println("number: "+number+" map: "+map);
        
    	map.put(number, map.getOrDefault(number, 0) + 1);
    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public static boolean find(int value) {
    	System.out.println("value: "+value+" map: "+map);
        
    	for(Integer n: map.keySet()) {
    		System.out.println("n: "+n);
            
    		int complement = value - n;
            System.out.println("complement: "+complement);
    		
    		if(map.containsKey(complement)) {
                if(n != complement || map.get(complement) > 1) {  
                	return true;
                }
            }
        }
        return false;
    }
	
	public static void main(String[] args) {
		TwoSum two = new TwoSum();
		add(1); 
		add(3); 
		add(5);
		
		System.out.println(find(4));
		System.out.println(find(7));
	}
}