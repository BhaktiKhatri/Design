package Design;

import java.util.Stack;

/*
 * 716. Max Stack
 * https://leetcode.com/problems/max-stack/
 * 	Design a max stack that supports push, pop, top, peekMax and popMax.

	push(x) -- Push element x onto stack.
	pop() -- Remove the element on top of the stack and return it.
	top() -- Get the element on the top.
	peekMax() -- Retrieve the maximum element in the stack.
	popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
	
	Explanation and Code : https://leetcode.com/articles/max-stack/
	
	Time Complexity: O(N) for the popMax operation, and O(1) for the other operations, where N is the number of operations performed.
	Space Complexity: O(N), the maximum size of the stack
 */

public class MaxStack {

	Stack<Integer> stack;
    Stack<Integer> maxStack;

    public MaxStack() {
        stack = new Stack<>();
        maxStack = new Stack<>();
    }

    public void push(int x) {
    	System.out.println("x: "+x+" maxStack: "+maxStack+" stack: "+stack);
        
    	int max = maxStack.isEmpty() ? x : maxStack.peek();
    	System.out.println("max: "+max+" x: "+x);
        
    	maxStack.push(max > x ? max : x);
        stack.push(x);
        
        System.out.println("maxStack: "+maxStack+" stack: "+stack);
    }

    public int pop() {
    	System.out.println("maxStack: "+maxStack+" stack: "+stack);
        
    	maxStack.pop();
        int pop = stack.pop();
        
        System.out.println("maxStack: "+maxStack+" stack: "+stack);
        
        return pop;
    }

    public int top() {
    	System.out.println("maxStack: "+maxStack+" stack: "+stack+" stack.peek(): "+stack.peek());
        return stack.peek();
    }

    public int peekMax() {
    	System.out.println("maxStack: "+maxStack+" maxStack.peek(): "+maxStack.peek()+" stack: "+stack);
        return maxStack.peek();
    }

    public int popMax() {
    	System.out.println("maxStack: "+maxStack+" stack: "+stack);
    	
        int max = peekMax();
        System.out.println("max: "+max);
        
        Stack<Integer> buffer = new Stack<>();
        
        while(top() != max) { 
        	buffer.push(pop());
        }
        System.out.println("buffer: "+buffer);
        
        pop();
        
        while(!buffer.isEmpty()) { 
        	push(buffer.pop());
        }
        System.out.println("buffer: "+buffer+" max: "+max);
        
        return max;
    }
	
	public static void main(String[] args) {
		MaxStack stack = new MaxStack();
		
		stack.push(5); 
		stack.push(1);
		stack.push(5);
		System.out.println(stack.top()); 		//-> 5
		System.out.println(stack.popMax()); 	//-> 5
		System.out.println(stack.top()); 		//-> 1
		System.out.println(stack.peekMax()); 	//-> 5
		System.out.println(stack.pop()); 		//-> 1
		System.out.println(stack.top()); 		//-> 5
	}

}
