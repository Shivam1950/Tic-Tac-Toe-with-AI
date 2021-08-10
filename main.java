package tictactoe;
import java.util.Scanner;
import java.util.Random;

public class Main {
    
    static Scanner cin = new Scanner(System.in);
    static Random rand = new Random();
    
    static char[][] board3D(char[] cellsArr) { //making 3D array
        char[][] board = new char[3][3];
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = cellsArr[k];
                k++;
            }
        }
        return board;
    }
    
    static char[] to2D(char[][] board) { //to 2D array
        char[] cellsArr = new char[9];
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cellsArr[k] = board[i][j];
                k++;
            }
        }
        return cellsArr;
    }
    
    static char playerHasWon(char[][] board) { //to see if someone has won and return the winning char
        //Check each row
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
				return board[i][0];
			}
		}
        
		//Check each column
		for (int j = 0; j < 3; j++) {
			if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != ' ') {
				return board[0][j];
			}
		}
        
		//Check the diagonals
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') {
			return board[0][0];
		}
		if (board[2][0] == board[1][1] && board[1][1] ==  board[0][2] && board[2][0] != ' ') {
			return board[2][0];
		}

		//Otherwise nobody has not won yet
		return '_';
	}
    
    static char[] interactPlayer(char[] cellsArr) { //player interact with the tic tac toe
        char[][] board = board3D(cellsArr);
        while (true) {
            int x = 0;
            int y = 0;
            System.out.print("Enter the coordinates: ");
            
            try {
                x = cin.nextInt();
                y = cin.nextInt();
            } catch (Exception e) {
                cin.next();
                System.out.println("You should enter numbers!");
                continue;
            }
            
            if (x > 3 || x < 1 || y > 3 || y < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (board[x-1][y-1] == 'X' || board[x-1][y-1] == 'O') {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                board[x-1][y-1] = 'X';    
                break;
            }
        }
        return to2D(board);
    }
    
    static char[] easyLevel(char[] cellsArr) { // essy level ai
        System.out.println("Making move level \"easy\"");
        
        char[][] board = board3D(cellsArr);
        
        int choice;
        do {
            choice = rand.nextInt(9);
        } while (board[choice/3][choice%3] != ' ');
        
        board[choice / 3][choice % 3] = 'O';
        return to2D(board);
    }
    
    static void playGame(char[] cellsArr) { // runs the game between player and ai and displays results
        int i = 0;
        while (i < 9) {
            if (i % 2 == 0) {
                cellsArr = interactPlayer(cellsArr);
            } else {
                cellsArr = easyLevel(cellsArr);
            }
            printRes(cellsArr);
            if (i > 3) {
                char ch = playerHasWon(board3D(cellsArr));
                if (ch == '_') {
                    if (i == 8) {
                        System.out.println("Draw");
                    }
                } else {
                    System.out.println(Character.toString(ch) + " wins");
                    break;
                }
            }
            i++;
        }
    }
    
    static void printRes(char[] cellsArr) { // to print Tic Tac Toe matrix
        System.out.println("---------");
        int k = 0;
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                if  (cellsArr[k] == '_') {
                    cellsArr[k] = ' ';
                }
                System.out.print(cellsArr[k] + " ");
                k++;
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
    
    public static void main(String[] args) {
        char[] cellsArr = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        printRes(cellsArr);
        playGame(cellsArr);
    }
}
