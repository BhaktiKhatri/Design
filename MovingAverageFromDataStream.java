package Design;

import java.util.LinkedList;
import java.util.Queue;

public class MovingAverageFromDataStream {

	Queue<Integer> queue;
    double sum = 0;
    int size;
	
	/** Initialize your data structure here. */
    public MovingAverageFromDataStream(int size) {
    	queue = new LinkedList<>();
        this.size = size;
    }
    
    public double next(int val) {
    	System.out.println("val: "+val+" size: "+size+" queue: "+queue+" sum: "+sum);
    	
    	if(queue.size() == size) { 
            sum = sum - queue.poll();
        }
    	
    	queue.offer(val);
        sum = sum + val;
        
        System.out.println("queue: "+queue+" sum: "+sum);
        
        return sum/queue.size();
    }
	
	public static void main(String[] args) {
		MovingAverageFromDataStream m = new MovingAverageFromDataStream(3);
		
		System.out.println(m.next(1)); //= 1
		System.out.println(m.next(10)); //= (1 + 10) / 2
		System.out.println(m.next(3)); //= (1 + 10 + 3) / 3
		System.out.println(m.next(5)); //= (10 + 3 + 5) / 3
	}
}