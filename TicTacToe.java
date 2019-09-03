package Design;

import java.util.Arrays;

/*
 * 348. Design Tic-Tac-Toe
 * https://leetcode.com/problems/design-tic-tac-toe/description/
 * Design a Tic-tac-toe game that is played between two players on a n x n grid.

You may assume the following rules:

A move is guaranteed to be valid and is placed on an empty block.
Once a winning condition is reached, no more moves is allowed.
A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
Example:
Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.

TicTacToe toe = new TicTacToe(3);

toe.move(0, 0, 1); -> Returns 0 (no one wins)
|X| | |
| | | |    // Player 1 makes a move at (0, 0).
| | | |

toe.move(0, 2, 2); -> Returns 0 (no one wins)
|X| |O|
| | | |    // Player 2 makes a move at (0, 2).
| | | |

toe.move(2, 2, 1); -> Returns 0 (no one wins)
|X| |O|
| | | |    // Player 1 makes a move at (2, 2).
| | |X|

toe.move(1, 1, 2); -> Returns 0 (no one wins)
|X| |O|
| |O| |    // Player 2 makes a move at (1, 1).
| | |X|

toe.move(2, 0, 1); -> Returns 0 (no one wins)
|X| |O|
| |O| |    // Player 1 makes a move at (2, 0).
|X| |X|

toe.move(1, 0, 2); -> Returns 0 (no one wins)
|X| |O|
|O|O| |    // Player 2 makes a move at (1, 0).
|X| |X|

toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
|X| |O|
|O|O| |    // Player 1 makes a move at (2, 1).
|X|X|X|
Follow up: Could you do better than O(n2) per move() operation?
Explanation and Code from: @bdwalker https://leetcode.com/problems/design-tic-tac-toe/discuss/81898/Java-O(1)-solution-easy-to-understand
Microsoft, Google
 */

public class TicTacToe {
	
	/*
	 * Initially, I had not read the Hint in the question and came up with an O(n) solution. After reading the extremely helpful hint; 
	 * a much easier approach became apparent. The key observation is that in order to win Tic-Tac-Toe you must have the entire row or column.
	 * Thus, we don't need to keep track of an entire n^2 board. We only need to keep a count for each row and column. If at any time a row or column
	 * matches the size of the board then that player has won.

		To keep track of which player, I add one for Player1 and -1 for Player2. There are two additional variables to keep track of the count of the
		diagonals. Each time a player places a piece we just need to check the count of that row, column, diagonal and anti-diagonal.

		Also see a very similar answer that I believe had beaten me to the punch. We came up with our solutions independently but they are very 
		similar in principle.
	 */
	
	/*
	 	Follow up let us use a more efficient method, then according to the hint, we create a one-dimensional array of size n rows and cols, 
	 	as well as variable diagonal diag and inverse diagonal rev_diag, the idea of ​​this method Yes, if player 1 puts a child in a column in the first row,
	 	then rows[0] increments by 1. If player 2 puts a child in a column in the first row, rows[0] is decremented by 1, then only When rows[0] is 
	 	equal to n or -n, it means that the first row of children is a player, then the game will return to the player, the other rows and columns, 
	 	the diagonal and the inverse diagonal
	 */
		private int[] rows;
		private int[] cols;
		private int diagonal;
		private int antiDiagonal;

		/** Initialize your data structure here. */
		public TicTacToe(int n) {
		    rows = new int[n];
		    cols = new int[n];
		}

		/** Player {player} makes a move at ({row}, {col}).
		    @param row The row of the board.
		    @param col The column of the board.
		    @param player The player, can be either 1 or 2.
		    @return The current winning condition, can be either:
		            0: No one wins.
		            1: Player 1 wins.
		            2: Player 2 wins. */
		public int move(int row, int col, int player) {
		    System.out.println("row: "+row+" col: "+col+" player: "+player);
		    int size = rows.length;
		    
			int toAdd = player == 1 ? 1 : -1;
		    System.out.println("toAdd: "+toAdd+" rows[row]: "+rows[row]+" cols[col]: "+cols[col]);
			
		    rows[row] = rows[row] + toAdd;
		    cols[col] = cols[col] + toAdd;
		    
		    System.out.println("rows: "+Arrays.toString(rows)+" cols: "+Arrays.toString(cols));
		    System.out.println("diagonal: "+diagonal);
		    
		    if(row == col)
		    {
		        diagonal = diagonal + toAdd;
		    }
		    System.out.println("diagonal: "+diagonal+" cols.length: "+cols.length);
		    
		    if(row + col == size - 1)
		    {
		        antiDiagonal = antiDiagonal + toAdd;
		    }
		    System.out.println("antiDiagonal: "+antiDiagonal);
		    
		    System.out.println("size: "+size);
		    
		    System.out.println("rows[row]: "+rows[row]+" cols[col]: "+cols[col]+" diagonal: "+diagonal+" antiDiagonal: "+antiDiagonal);
		    
		    if(Math.abs(rows[row]) == size || Math.abs(cols[col]) == size || Math.abs(diagonal) == size  || Math.abs(antiDiagonal) == size)
		    {
		    	System.out.println("player: "+player);
		        return player;
		    }
		    
		    return 0;
	}
	
	public static void main(String[] args) {
		int n = 3;
		TicTacToe toe = new TicTacToe(n);
		int result = 0;
		
		result = toe.move(0, 0, 1);
		System.out.println("result: "+result);
		
		result = toe.move(0, 2, 2);
		System.out.println("result: "+result);
		
		result = toe.move(2, 2, 1);
		System.out.println("result: "+result);
		
		result = toe.move(1, 1, 2);
		System.out.println("result: "+result);
		
		result = toe.move(2, 0, 1);
		System.out.println("result: "+result);
		
		result = toe.move(1, 0, 2);
		System.out.println("result: "+result);
		
		result = toe.move(2, 1, 1);
		System.out.println("result: "+result);
	}
}