package com.wordpress.logicprog.aboardgame;

public class ABoardGame {
	
	public static String whoWins (String[] board) {
		
		int boardLength = board[0].length();
		int totRegionNum = boardLength / 2;
		int regionIndex = 0;
		char[][][] region = new char[totRegionNum][100][100];
		char[][] player = new char[boardLength][boardLength];
		boolean isAwin = false;
		boolean isDraw = false;
		
		// Top Row
		int tR = totRegionNum - 1;
		// Bot Row
		int bR = totRegionNum;
		
		// Initialise region with . in its respective coordinates
		for(int regionNum = 0; regionNum < totRegionNum; regionNum++) {
			for(int y = 0; y < boardLength; y++) {
				for(int x = 0; x < boardLength; x++) {
					region[regionNum][y][x] = '.';
				}
			}
		}

		// Convert the string position to character position
		for(int y = 0; y < boardLength; y++) {
			for(int x = 0; x < boardLength; x++) {
				player[y][x] = board[y].charAt(x);
			}
		}
		
		while (true) {
			int counterA = 0;
			int counterB = 0;

			// Replace '.' with the true character value for that particular region
			for(int x = tR; x <= bR; x++) {
				region[regionIndex][tR][x] = player[tR][x];
				region[regionIndex][bR][x] = player[bR][x];
			}
		
			for(int y = tR; y <= bR; y++) {
				region[regionIndex][y][tR] = player[y][tR];
				region[regionIndex][y][bR] = player[y][bR];
			}
			
			// Print out each iteration of region of the board
			for(int index = 0; index <= boardLength; index++) {
				System.out.println();
				for(int x = 0; x <= boardLength; x++) {
					System.out.print(region[regionIndex][index][x]);
				}
			}
			
			for(int y = tR; y <= bR; y++) {
				for(int x  = tR; x <= bR; x++) {
					if(region[regionIndex][y][x] == 'A') {++counterA;}
					else if(region[regionIndex][y][x] == 'B') {++counterB;}
				}
			}
			
			// 3 possible situation
			// Draw: counterA == counterB until the last of region
			// Alice win: counterA > counterB
			// Bob win: counterB > counterA
			
			// If isAwin == true, there are 2 possible situation: draw or Bob win
			// Therefore need to create isDraw flag to check whether its draw or Bob win
			if(counterA == counterB) {
				regionIndex += 1;
				tR--;
				bR++;
				if(regionIndex > totRegionNum - 1) {
					isDraw = true;
					break;
				}
			}
			else if(counterA > counterB) {isAwin = true; break;}
			else if(counterA < counterB) {isAwin = false; break;}
		}
		
		if (!isAwin) {
			if(isDraw) {
				return "Draw";
			}
			else if (!isDraw) {
				return "Bob";
			}
		}
		return "Alice";
	}
	
	public static void main(String[] args) {
		// A wins example
		//String[] board = {"A...AA","B.A.BA",".AAB..",".B..A.", "ABAAAB", "BABBAB"};
		
		// B wins example
		//String[] board = {"A...AA","B.A.BA",".AAB..",".B..A.", "ABBB.B", "BABBAB"};
		
		// Draw example
		//String[] board = {"A...AA","B.B..A","BBAB..",".B..B.", "AAAAAB", "BABBAB"};
		
		// Complex example (10x10 matrix)
		String[] board = {"A.BBA.BBA.", "...AA.B...", "BBABAAABB.", "AABBBBBABA", "A.B.BBABAA", "AAAAAAAAAA", "BBB...ABAB", "BABABBAA..", "BABABBA..A", "ABA.ABABBA"};
		String e = whoWins(board);
		System.out.println();
		System.out.println(e);
	}

}
