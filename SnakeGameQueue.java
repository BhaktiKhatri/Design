package Design;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 353. Design Snake Game
 * https://leetcode.com/problems/design-snake-game/
 * Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.
 * The snake is initially positioned at the top left corner (0,0) with length = 1 unit.
 * You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.
 * Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.
 * When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.
 * Code from: @trash https://leetcode.com/problems/design-snake-game/discuss/82668/Java-Deque-and-HashSet-design-with-detailed-comments
 */

public class SnakeGameQueue {

	    Queue<Integer> queue;   
	    int[][] foods;  
	    int foodIndex;  
	    int width, height;  
	    int row, col;  
	    
	    /** Initialize your data structure here.
	        @param width - screen width
	        @param height - screen height 
	        @param food - A list of food positions
	        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
	    public SnakeGameQueue(int width, int height, int[][] food) {
	    	System.out.println("width: "+width+" height: "+height+" food: "+Arrays.toString(food));
	        
	    	queue = new LinkedList<Integer>();
	    	queue.offer(0);  
	        foods = food;  
	        foodIndex = 0;  
	        this.width = width;  
	        this.height = height;  
	        row = 0;  
	        col = 0; 
	    }
	    
	    /** Moves the snake.
	        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
	        @return The game's score after the move. Return -1 if game over. 
	        Game over when snake crosses the screen boundary or bites its body. */
	    public int move(String direction) {
	    	System.out.println("direction: "+direction+"");
	    	
	        if(direction.equals("U")) {  
	            row--;  
	        }
	        else if(direction.equals("D")) {  
	            row++;  
	        }
	        else if(direction.equals("L")) {  
	            col--;  
	        }
	        else if(direction.equals("R")) {  
	            col++;  
	        }  
	        
	        System.out.println("row: "+row+" col: "+col);
	        
	        int head = row * width + col;  
	        System.out.println("head: "+head+" queue: "+queue+" queue.peek(): "+queue.peek());
	        
	        //case 1: out of boundary or eating body
	        if(head != queue.peek() && queue.contains(head)) {  
	            return -1;  
	        }  
	        
	        //case2: eating food, keep tail, add head
	        if(row >= 0 && row < height && col >= 0 && col < width) {  
	        	queue.offer(head);   
	        
	        	if(foodIndex < foods.length)
	        		System.out.println("queue: "+queue+" foodIndex: "+foodIndex+" foods[foodIndex][0]: "+foods[foodIndex][0]+" foods[foodIndex][1]: "+foods[foodIndex][1]);
	        	
	            if (foodIndex < foods.length && row == foods[foodIndex][0] && col == foods[foodIndex][1]) {  
	                foodIndex++;  
	            } 
	            else {  
	            	queue.poll();   	        //case3: normal move, remove tail, add head
	            }  
	            System.out.println("foodIndex: "+foodIndex+" queue: "+queue);
	            
	            return foodIndex;  
	        }  
	        return -1;
	    }
	
	public static void main(String[] args) {
		int width = 3;
    	int height = 2;
    	int[][] food = {{1,2},{0,1}};

    	SnakeGameQueue snake = new SnakeGameQueue(width, height, food);

    	//Initially the snake appears at position (0,0) and the food at (1,2).

    	//|S| | |
    	//| | |F|

    	snake.move("R"); //-> Returns 0

    	//| |S| |
    	//| | |F|

    	snake.move("D"); //-> Returns 0

    	//| | | |
    	//| |S|F|

    	snake.move("R"); //-> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )

    	//| |F| |
    	//| |S|S|

    	snake.move("U"); //-> Returns 1

    	//| |F|S|
    	//| | |S|

    	snake.move("L"); //-> Returns 2 (Snake eats the second food)

    	//| |S|S|
    	//| | |S|

    	//System.out.println(snake.move("R"));
    	System.out.println(snake.move("U"));
    	//snake.move("D"); //-> Returns -1 (Game over because snake collides with border)
    	//snake.move("R"); //-> Returns -1 (Game over because snake collides with border)
    	//System.out.println(snake.move("R"));
	}
}