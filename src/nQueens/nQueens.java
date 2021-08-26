/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nQueens;

import java.util.Scanner;

/**
 *
 * @author Joey
 */
public class nQueens {
    backtracking bt = new backtracking();
    hillClimbing hc = new hillClimbing();
    int a;
    Scanner in;
    public nQueens(){
        in = new Scanner(System.in);
        System.out.println("1. Blind Search");
        System.out.println("2. Heuristic Search");
        do{
        a = Integer.parseInt(in.next());
        if (a==1) blindSearch();
        else if (a==2) Heuristic();
        else System.out.println("Please enter valid number");
        }while (a!=1||a!=2);   
        
    }
    private void blindSearch(){//backtracking method
        System.out.println("Value of n?: ");
        int n = Integer.parseInt(in.next());
        bt.solve(n);
    }
    private void Heuristic(){ //hillclimbing restart method
        System.out.println("Value of n?: (Only 8 works.... sorry)");
        int n = Integer.parseInt(in.next());
        
        boolean climb = true;
		int climbCount = 0;
		while (climb) {
			hillClimbing board = new hillClimbing(
					new int[n][n], new int[n]);

			// randomly place queens
			board.placeQueens(n);
			System.out.println("#: " + (climbCount+1));
			System.out.println("Original board:");
			board.printBoard();
			System.out.println("# of queens attacking each other: "
					+ board.h() + "\n");

			// finds global min (solution) by comparing all iterations
			int localMin = board.h();
			boolean best = false;
			// goes row by row to see which spot is best for queen in col.
			int[] bestPos = new int[8];

			// iterate through each column 
			for (int j = 0; j < board.board.length; j++) {
				System.out.println("Iterating through COLUMN " + j + ":");
				best = false;
				//  iterate through each row
				for (int i = 0; i < board.board.length; i++) {
					
					// skip score calculated by original board
					if (i != board.pos[j]) {
						
						// move queen 
						board.moveQueen(i, j);
						board.printBoard();
						System.out.println();
						// calculate score, if best seen then store queen position
						if (board.h() < localMin) {
							best = true;
							localMin = board.h();
							bestPos[j] = i;
						}
						// reset to original queen position
						board.resetQueen(i, j);

					}
				}
				
				// change 2 back to 1
				board.resetBoard(j);
				if (best) {
					// if a best score was found, place queen in this position
					board.placeBestQueen(j, bestPos[j]);
					System.out.println("Best board found this iteration: ");
					board.printBoard();
					System.out
							.println("# pairs of queens attacking each other: "
									+ board.h() + "\n");
				} else {
					System.out.println("No better board found.");
					board.printBoard();
					System.out
							.println("# pairs of queens attacking each other: "
									+ board.h() + "\n");
				}
			}

			// if score = 0, hill climbing has solved problem
			if (board.h() == 0)
				climb = false;

			climbCount++;
                        //100 restarts
			if (climbCount == 100) {
				climb = false;
			}
			System.out.println("Done in " + (climbCount-1) + " restarts.");
		}
    }        
    public static void main(String[] args) {
        new nQueens();
    }
}
