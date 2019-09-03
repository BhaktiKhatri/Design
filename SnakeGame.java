package Design;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class SnakeGame {

    //2D position info is encoded to 1D and stored as two copies 
    Set<Integer> set; // this copy is good for fast loop-up for eating body case
    Deque<Integer> body; // this copy is good for updating tail
    int score;	//snake length
    int[][] food;
    int foodIndex;
    int width;
    int height;
    
    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = food;
        set = new HashSet<>();
        set.add(0); //intially at [0][0]
        body = new LinkedList<>();
        body.offerLast(0);
    }
  
    public int move(String direction) {
    	System.out.println("direction: "+direction+" score: "+score);
    	
    	//case 0: game already over: do nothing
        if(score == -1) {
            return -1;
        }
        
        System.out.println("body: "+body+" body.peekFirst(): "+body.peekFirst()+" width: "+width);
        
        // compute new head
        int rowHead = body.peekFirst() / width;
        int colHead = body.peekFirst() % width;
        
        System.out.println("rowHead: "+rowHead+" colHead: "+colHead);
        
        switch (direction) {
            case "U" : rowHead--;
                       break;
            case "D" : rowHead++;
                       break;
            case "L" : colHead--;
                       break;
            default :  colHead++;
        }
        
        System.out.println("rowHead: "+rowHead+" colHead: "+colHead);
        
        int head = rowHead * width + colHead;
        
        System.out.println("head: "+head+" set: "+set+" body.peekLast(): "+body.peekLast());
        
        //case 1: out of boundary or eating body
        set.remove(body.peekLast()); // new head is legal to be in old tail's position, remove from set temporarily 
        
        System.out.println("set: "+set);
        
        if (rowHead < 0 || rowHead == height || colHead < 0 || colHead == width || set.contains(head)) {
            return score = -1;
        }
        
        // add head for case2 and case3
        set.add(head); 
        body.offerFirst(head);
        
        System.out.println("set: "+set+" body: "+body);
        
        System.out.println("foodIndex: "+foodIndex+" food: "+Arrays.toString(food)+" food[foodIndex][0]: "+food[foodIndex][0]+" food[foodIndex][1]: "+food[foodIndex][1]);
        
        //case2: eating food, keep tail, add head
        if (foodIndex < food.length && rowHead == food[foodIndex][0] && colHead == food[foodIndex][1]) {
            set.add(body.peekLast()); // old tail does not change, so add it back to set
            foodIndex++;
            return ++score;
        }

        System.out.println("set: "+set+" body: "+body);
        
        //case3: normal move, remove tail, add head
        body.pollLast();
        return score;
    }
    
    public static void main(String[] args) {
    	int width = 3;
    	int height = 2;
    	int[][] food = {{1,2},{0,1}};

    	SnakeGame snake = new SnakeGame(width, height, food);

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

    	snake.move("U"); //-> Returns -1 (Game over because snake collides with border)
    }
}
