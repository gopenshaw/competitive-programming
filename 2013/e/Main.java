// Problem consists of an n by n board, where each square is either open or an obstacle.
// The problem simulates a game where the left side of the board is filled with n pieces, and they
// are trying to get to the right side of board. In other words, they want to fill the n squares
// in the right-most column. The pieces can move either N, S, E, or W. They move simultaneously,
// and each square can only occupied by one piece at a time. The board is guaranteed to have at
// least one open path across. The goal of the program is to determine the minimum number of turns
// to get all of the pieces from the left side of the board to the right.

// The solution will use a flow graph with n sources and n sinks. The pathfinding will use BFS, but
// will have a max path length that will represent the number of turns. The max turn variable will
// start at one, and keep testing the max flow until max flow == n. When max flow == n, we have found
// the number of turns required to move all of the pieces.

import java.util.*;

class Main {
	final int MAX_N = 25;
	final char OBSTACLE = 'X';
	final char OPEN = '.';

	
}