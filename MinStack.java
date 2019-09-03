package Design;

import java.util.Stack;

public class MinStack {

    int min = Integer.MAX_VALUE;
    Stack<Integer> stack = new Stack<Integer>();
    
    public void push(int x) {
        // only push the old minimum value when the current 
        // minimum value changes after pushing the new value x
        
    	System.out.println("x: "+x+" min: "+min+" stack: "+stack);
    	
    	if(x <= min) {          
            stack.push(min);
            min = x;
        }
        stack.push(x);
        
        System.out.println("x: "+x+" min: "+min+" stack: "+stack);
    }

    public void pop() {
        // if pop operation could result in the changing of the current minimum value, 
        // pop twice and change the current minimum value to the last minimum value.
    	
    	System.out.println("min: "+min+" stack: "+stack);
        int peek = stack.pop();
        System.out.println("peek: "+peek);
        
    	if(peek == min) { 
        	min = stack.pop();
        }
    	System.out.println("min: "+min+" stack: "+stack);
    }

    public int top() {
    	System.out.println("min: "+min+" stack: "+stack+" stack.peek(): "+stack.peek());
        return stack.peek();
    }

    public int getMin() {
    	System.out.println("min: "+min+" stack: "+stack);
        return min;
    }

	public static void main(String[] args) {
		MinStack minStack = new MinStack();
		
		minStack.push(-2);
		minStack.push(0);
		minStack.push(-3);
		System.out.println(minStack.getMin());  // --> Returns -3.
		minStack.pop();
		System.out.println(minStack.top());     // --> Returns 0.
		System.out.println(minStack.getMin());  // --> Returns -2.
	}
}