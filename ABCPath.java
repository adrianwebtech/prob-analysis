package com.wordpress.logicprog;

public class ABCPath {

	public static int length(String[] grid) {

		int gridWidth = grid[0].length();
		int gridHeight = grid.length;
		int dimension = gridWidth * gridHeight;
		int maxPathLength = 0;
		char currChar = 'A';

		int[][] coordinate = new int[dimension][2];
		int[][][] surrCoordinate = new int[dimension][9][2];
		int[][] nextCharCoordinate = new int[dimension][2];
		
		boolean is_No_A = false;

		coordinate = findA(grid, gridWidth, gridHeight);
		
		if(coordinate[0][0] == -1) {
			is_No_A = true;
		}else {
			maxPathLength++;
		}

		// Enable this code to find out all the 'A' coordinate
		/*for (int i = 0; i < dimension; i++) {
		
			System.out.print(coordinate[i][0] + " ,");
			System.out.println(coordinate[i][1]);
			if (coordinate[i + 1][0] == -1) {
				break;
			}
		}*/

		while (!is_No_A) {
			surrCoordinate = getSurroundingCoord(coordinate, dimension);
			nextCharCoordinate = findNextCharCoord((char) (currChar + 1), grid, surrCoordinate, gridWidth, gridHeight);
			
			if(nextCharCoordinate[0][0] == -1) {
				break;
			}
			coordinate = nextCharCoordinate;
			maxPathLength++;
			currChar++;
			
			// Enable this code to determine the coordinates of the next character
			/*for(int i  = 0; i < dimension; i++) {
				System.out.println(nextCharCoordinate[i][0] + " ," + nextCharCoordinate[i][1]);
			}*/
		}

		return maxPathLength;

	}

	public static int[][] findA(String[] grid, int gW, int gH) {

		int index = 0;
		int counterY = -1;
		int counterX;
		int[][] coordinate = new int[gH * gW][2];
		for (int y = 0; y < gH; y++) {
			for (int x = 0; x < gW; x++) {
				coordinate[x + y * gW][0] = -1;
				coordinate[x + y * gW][1] = -1;
			}
		}

		for (int y = 0; y < gH; y++) {
			counterX = 0;
			counterY++;
			for (int x = 0; x < gW; x++) {
				if (grid[y].charAt(x) == 'A') {
					coordinate[index][0] = counterX;
					coordinate[index][1] = counterY;
					index++;
				}
				counterX++;
			}
		}

		return coordinate;
	}

	public static int[][][] getSurroundingCoord(int[][] currCoord, int dim) {
		int[][][] surrCoord = new int[dim][9][2];

		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < 9; j++) {
				surrCoord[i][j][0] = -1;
				surrCoord[i][j][1] = -1;
			}
		}

		for (int i = 0; i < dim; i++) {
			if (currCoord[i][0] != -1) {
				int xCurrCoord = currCoord[i][0];
				int yCurrCoord = currCoord[i][1];
				//System.out.println();

				for (int y = yCurrCoord - 1; y < yCurrCoord + 2; y++) {
					for (int x = xCurrCoord - 1; x < xCurrCoord + 2; x++) {
						surrCoord[i][(x - (xCurrCoord - 1)) + (y - (yCurrCoord - 1)) * 3][0] = x;
						surrCoord[i][(x - (xCurrCoord - 1)) + (y - (yCurrCoord - 1)) * 3][1] = y;

						// Enable this code to find out the surrounding coordinates through each iteration
						//System.out.print(surrCoord[i][(x - (xCurrCoord - 1)) + (y - (yCurrCoord - 1)) * 3][0] + " ,");
						//System.out.println(surrCoord[i][(x - (xCurrCoord - 1)) + (y - (yCurrCoord - 1)) * 3][1]);
					}
				}
			} else {
				break;
			}
		}

		return surrCoord;
	}

	public static int[][] findNextCharCoord(char nextChar, String[] grid, int[][][] surrCoord, int gW, int gH) {
		int nextCharCoord[][] = new int[gH * gW][2];
		int elemNum = 0;
		
		for (int y = 0; y < gH; y++) {
			for (int x = 0; x < gW; x++) {
				nextCharCoord[x + y * gW][0] = -1;
				nextCharCoord[x + y * gW][1] = -1;
			}
		}

		for (int countDim = 0; countDim < gH * gW; countDim++) {
			for (int index = 0; index < 9; index++) {
				int x = surrCoord[countDim][index][0];
				int y = surrCoord[countDim][index][1];

				if (x > -1 && y > -1) {
					if (x < gW && y < gH) {
						if (grid[y].charAt(x) == nextChar) {
							if (!isCoordExist(nextCharCoord, surrCoord[countDim][index], gW * gH)) {
								nextCharCoord[elemNum][0] = x;
								nextCharCoord[elemNum][1] = y;
								elemNum++;
							}
							
						}
						
					}
				}
			}
		}

		return nextCharCoord;
	}

	public static boolean isCoordExist(int[][] coord, int[] addCoord, int dim) {
		for (int elemNum = 0; elemNum < dim; elemNum++) {
			if (coord[elemNum][0] == addCoord[0]) {
				if (coord[elemNum][1] == addCoord[1]) {
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {

		//String[] grid = { "ABEF", "CFGB", "BDHA", "ABCB" };
		//String[] grid = { "KCBVNRXSPVEGUEUFCODMOAXZYWEEWNYAAXRBKGACSLKYRVRKIO", "DIMCZDMFLAKUUEPMPGRKXSUUDFYETKYQGQHNFFEXFPXNYEFYEX", "DMFRPZCBOWGGHYAPRMXKZPYCSLMWVGMINAVRYUHJKBBRONQEXX", "ORGCBHXWMTIKYNLFHYBVHLZFYRPOLLAMBOPMNODWZUBLSQSDZQ", "QQXUAIPSCEXZTTINEOFTJDAOBVLXZJLYOQREADUWWSRSSJXDBV", "PEDHBZOVMFQQDUCOWVXZELSEBAMBRIKBTJSVMLCAABHAQGBWRP", "FUSMGCSCDLYQNIXTSTPJGZKDIAZGHXIOVGAZHYTMIWAIKPMHTJ", "QMUEDLXSREWNSMEWWRAUBFANSTOOJGFECBIROYCQTVEYGWPMTU", "FFATSKGRQJRIQXGAPLTSXELIHXOPUXIDWZHWNYUMXQEOJIAJDH", "LPUTCFHYQIWIYCVOEYHGQGAYRBTRZINKBOJULGYCULRMEOAOFP", "YOBMTVIKVJOSGRLKTBHEJPKVYNLJQEWNWARPRMZLDPTAVFIDTE", "OOBFZFOXIOZFWNIMLKOTFHGKQAXFCRZHPMPKGZIDFNBGMEAXIJ", "VQQFYCNJDQGJPYBVGESDIAJOBOLFPAOVXKPOVODGPFIYGEWITS", "AGVBSRLBUYOULWGFOFFYAAONJTLUWRGTYWDIXDXTMDTUYESDPK", "AAJOYGCBYTMXQSYSPTBWCSVUMNPRGPOEAVVBGMNHBXCVIQQINJ", "SPEDOAHYIDYUJXGLWGVEBGQSNKCURWYDPNXBZCDKVNRVEMRRXC", "DVESXKXPJBPSJFSZTGTWGAGCXINUXTICUCWLIBCVYDYUPBUKTS", "LPOWAPFNDRJLBUZTHYVFHVUIPOMMPUZFYTVUVDQREFKVWBPQFS", "QEASCLDOHJFTWMUODRKVCOTMUJUNNUYXZEPRHYOPUIKNGXYGBF", "XQUPBSNYOXBPTLOYUJIHFUICVQNAWFMZAQZLTXKBPIAKXGBHXX" };
		String[] grid = { "AMNOPA", "ALEFQR", "KDABGS", "AJCHUT", "AAIWVA", "AZYXAA" };
		//String[] grid = {"A"};
		//String[] grid = { "EDCCBA", "EDCCBA" };
		int maxLength = length(grid);
		System.out.print(maxLength);
	}
}
