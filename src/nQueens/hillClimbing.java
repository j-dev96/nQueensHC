package nQueens;
//informed search
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class hillClimbing {

	public int[][] board;
	public int[] pos;

	public hillClimbing() {

		

	}

	public hillClimbing(int[][] board, int[] positions) {

		this.board = board;
		this.pos = positions;

	}

	private int[] generateQueens(int n) {

		List<Integer> randomPos = new ArrayList<>();

		Random r = new Random();
		for (int i = 0; i < n; i++) {
			randomPos.add(r.nextInt(n));
		}

		int[] randomPositions = new int[n];

		for (int i = 0; i < randomPos.size(); i++) {
			randomPositions[i] = randomPos.get(i);
		}

		return randomPositions;
	}

	public void placeQueens(int n) {

		pos = generateQueens(n);

		for (int i = 0; i < board.length; i++) {
			board[pos[i]][i] = 1;
		}

	}

	public int h() {

		int allPairs = 0;

		// check rows
		for (int i = 0; i < board.length; i++) {
			ArrayList<Boolean> pair = new ArrayList<>();
			for (int j = 0; j < board[i].length; j++) {

				if (board[i][j] == 1) {
					pair.add(true);
				}

			}
			if (!pair.isEmpty())
				allPairs = allPairs + (pair.size() - 1);
		}

		// check diagonal from top left
		int rows = board.length;
		int cols = board.length;
		int maxSum = rows + cols - 2;

		for (int sum = 0; sum <= maxSum; sum++) {
			ArrayList<Boolean> pair = new ArrayList<Boolean>();
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					if (i + j - sum == 0) {
						if (board[i][j] == 1) {
							pair.add(true);
						}
					}
				}

			}
			if (!pair.isEmpty())
				allPairs = allPairs + (pair.size() - 1);
		}

		// check diagonal from bottom left
		int pair = checkDiagonalBottomLeft();

		return allPairs + pair;
	}
	//check diagonal
	private int checkDiagonalBottomLeft() {

		int[] b1 = { board[7][0] };
		int[] b2 = { board[7][1], board[6][0] };
		int[] b3 = { board[7][2], board[6][1], board[5][0] };
		int[] b4 = { board[7][3], board[6][2], board[5][1], board[4][0] };
		int[] b5 = { board[7][4], board[6][3], board[5][2], board[4][1],
				board[3][0] };
		int[] b6 = { board[7][5], board[6][4], board[5][3], board[4][2],
				board[3][1], board[2][0] };
		int[] b7 = { board[7][6], board[6][5], board[5][4], board[4][3],
				board[3][2], board[2][1], board[1][0] };
		int[] b8 = { board[7][7], board[6][6], board[5][5], board[4][4],
				board[3][3], board[2][2], board[1][1], board[0][0] };
		int[] b9 = { board[6][7], board[5][6], board[4][5], board[3][4],
				board[2][3], board[1][2], board[0][1] };
		int[] b10 = { board[5][7], board[4][6], board[3][5], board[2][4],
				board[1][3], board[0][2] };
		int[] b11 = { board[4][7], board[3][6], board[2][5], board[1][4],
				board[0][3] };
		int[] b12 = { board[3][7], board[2][6], board[1][5], board[0][4] };
		int[] b13 = { board[2][7], board[1][6], board[0][5] };
		int[] b14 = { board[1][7], board[0][6] };
		int[] b15 = { board[0][7] };

		int allPairs = 0;

		allPairs += checkHorizontal(b1);
		allPairs += checkHorizontal(b2);
		allPairs += checkHorizontal(b3);
		allPairs += checkHorizontal(b4);
		allPairs += checkHorizontal(b5);
		allPairs += checkHorizontal(b6);
		allPairs += checkHorizontal(b7);
		allPairs += checkHorizontal(b8);
		allPairs += checkHorizontal(b9);
		allPairs += checkHorizontal(b10);
		allPairs += checkHorizontal(b11);
		allPairs += checkHorizontal(b12);
		allPairs += checkHorizontal(b13);
		allPairs += checkHorizontal(b14);
		allPairs += checkHorizontal(b15);

		return allPairs;
	}
        
	public void moveQueen(int row, int col) {

		// mark original queen as 2
		board[pos[col]][col] = 2;

		board[row][col] = 1;

	}



	private int checkHorizontal(int[] b) {

		int allPairs = 0;

		ArrayList<Boolean> pairs = new ArrayList<Boolean>();
		for (int i = 0; i < b.length; i++) {
			if (b[i] == 1)
				pairs.add(true);

		}

		if (!pairs.isEmpty())
			allPairs = (pairs.size() - 1);

		return allPairs;
	}

	public void resetQueen(int row, int col) {

		if (board[row][col] == 1)
			board[row][col] = 0;
	}

	public void resetBoard(int col) {

		for (int i = 0; i < board.length; i++) {
			if (board[i][col] == 2)
				board[i][col] = 1;
		}
	}

	public void placeBestQueen(int col, int queenPos) {

		for (int i = 0; i < board.length; i++) {
			if (board[i][col] == 1)
				board[i][col] = 2;

		}
		board[queenPos][col] = 1;
		for (int i = 0; i < board.length; i++) {
			if (board[i][col] == 2)
				board[i][col] = 0;

		}
	}

	public void printBoard() {

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

}