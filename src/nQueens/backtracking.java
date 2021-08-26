package nQueens;
//uninformed search
public class backtracking
{
    public backtracking(){
        
    }
    
void printBoard(String strboard[][], int n)
    {
        for (int i = 0; i < n; i++)   
        {
            for (int j = 0; j < n; j++)
                System.out.print(" " + strboard[i][j] + " ");
            System.out.println();
        }
    }

    boolean isSafe(int board[][],String strboard[][], int row, int col, int n)
    {
        int i, j;
 
        // Check row on left side
        for (i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;
 
        // Check upper diagonal on left side
        for (i=row, j=col; i>=0 && j>=0; i--, j--)
            if (board[i][j] == 1)
                return false;
 
        // Check lower diagonal on left side
        for (i=row, j=col; j>=0 && i<n; i++, j--)
            if (board[i][j] == 1)
                return false;
 
        return true;
    }
     boolean solve(int n)
    {
        int board[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = 0;
            }
        }
        String strboard[][] = new String[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                strboard[i][j] = "O";
            }
        }       
        if (solveNQ(board, strboard, 0, n) == false)
        {
            System.out.print("No Solution");
            return false;
        }
 
        printBoard(strboard,n);
        return true;
    }
    //Using recursive calls to solve problem
    boolean solveNQ(int board[][], String strboard[][], int col, int n)
    {
        // If all queens are placed then return true
        if (col == n)
            return true;
 
        //Place queens on row one by one
        for (int i = 0; i < n; i++)
        {
            //Check if its safe (being attacked)
            if (isSafe(board,strboard, i, col,n))
            {
                // Place Queen
                board[i][col] = 1;
                strboard[i][col] = "Q";
                /* recur to place rest of the queens */
                if (solveNQ(board, strboard, col + 1, n) == true)
                    return true;
 
                // If no solution, backtrack and move initial queen.
                board[i][col] = 0; // BACKTRACK
                strboard[i][col] = "O";
            }
        }
 
        //If no solution is found, return false.
        return false;
    }
}
    