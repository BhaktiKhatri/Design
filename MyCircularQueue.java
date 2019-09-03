package Design;

import java.util.Arrays;

/*
 * 622. Design Circular Queue
 * https://leetcode.com/problems/design-circular-queue/
 * Design your implementation of the circular queue. The circular queue is a linear data structure in which the operations are performed based on 
 * FIFO (First In First Out) principle and the last position is connected back to the first position to make a circle. It is also called "Ring Buffer".
 * One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. In a normal queue, once the queue becomes full,
 * we cannot insert the next element even if there is a space in front of the queue. But using the circular queue, we can use the space to store new values.
 * Your implementation should support following operations:
 * MyCircularQueue(k): Constructor, set the size of the queue to be k.
 * Front: Get the front item from the queue. If the queue is empty, return -1.
 * Rear: Get the last item from the queue. If the queue is empty, return -1.
 * enQueue(value): Insert an element into the circular queue. Return true if the operation is successful.
 * deQueue(): Delete an element from the circular queue. Return true if the operation is successful.
 * isEmpty(): Checks whether the circular queue is empty or not.
 * isFull(): Checks whether the circular queue is full or not.
 * Note:
 * All values will be in the range of [0, 1000].
 * The number of operations will be in the range of [1, 1000].
 * Please do not use the built-in Queue library.
 * Explanation and Code from: @climberig https://leetcode.com/problems/design-circular-queue/discuss/149420/Concise-Java-using-array
 */

public class MyCircularQueue {

    final int[] a;
    int front = 0;
    int rear = -1;
    int len = 0;

    //Constructor, set the size of the queue to be k
    public MyCircularQueue(int k) { 
    	System.out.println("k: "+k);
    	a = new int[k];
    }

    //Insert an element into the circular queue. Return true if the operation is successful
    public boolean enQueue(int val) {
    	System.out.println("val: "+val);
        
    	if(!isFull()) {
    		System.out.println("rear: "+rear+" a.length: "+a.length);
            
    		rear = (rear + 1) % a.length;
            System.out.println("rear: "+rear+" a[rear]: "+a[rear]);
            
            a[rear] = val;
            len++;
            System.out.println("a: "+Arrays.toString(a)+" rear: "+rear+" len: "+len);
            
            return true;
        } 
    	else { 
        	return false;
    	}
    }

    //Delete an element from the circular queue. Return true if the operation is successful
    public boolean deQueue() {
        if(!isEmpty()) {
        	System.out.println("front: "+front+" a.length: "+a.length);
        	
            front = (front + 1) % a.length;
            System.out.println("front: "+front+" a[front]: "+a[front]);
            
            len--;
            return true;
        } 
        else { 
        	return false;
        }
    }

    //Get the front item from the queue. If the queue is empty, return -1
    public int Front() {
    	System.out.println("front: "+front+" a[front]: "+a[front]);
    	return isEmpty() ? -1 : a[front];
    }

    //Get the last item from the queue. If the queue is empty, return -1
    public int Rear() {
    	System.out.println("rear: "+rear+" a[rear]: "+a[rear]);
    	return isEmpty() ? -1 : a[rear];
    }

    //Checks whether the circular queue is empty or not
    public boolean isEmpty() { 
    	System.out.println("len: "+len);
    	return len == 0;
    }

    //Checks whether the circular queue is full or not
    public boolean isFull() {
    	System.out.println("len: "+len+" a.length: "+a.length);
    	return len == a.length;
    }

	public static void main(String[] args) {
		MyCircularQueue circularQueue = new MyCircularQueue(3); // set the size to be 3
		
		System.out.println(circularQueue.enQueue(1));   // return true
		System.out.println(circularQueue.enQueue(2));   // return true
		System.out.println(circularQueue.enQueue(3));   // return true
		System.out.println(circularQueue.enQueue(4));   // return false, the queue is full
		System.out.println(circularQueue.Rear());  		// return 3
		System.out.println(circularQueue.isFull());     // return true
		System.out.println(circularQueue.deQueue());    // return true
		System.out.println(circularQueue.enQueue(4));   // return true
		System.out.println(circularQueue.Rear());  // return 4
	}
}