import java.util.Arrays;

class Queens {
	public static int sum = 0;
	public static void main(String[] args) {
		int size;
		if (args.length < 1) {
			System.out.println("Usage: Queens [-v] number \n" +
				"Option: -v verbose output, print all solutions");
			System.exit(1);
		}

		if (args.length == 1) {
			try {
				int num = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				System.out.println("Usage: Queens [-v] number \n" +
					"Option: -v verbose output, print all solutions");
				System.exit(1);	
			}
			size = Integer.parseInt(args[0]) + 1;
		} else {
			size = Integer.parseInt(args[1]) + 1;
		}

		int[][] B = new int[size][size]; //(n+1) row (n + 1) chessboard
		for(int i = 0; i < size; i++){
			B[0][i] = i;
		}

		if (args[0].equals("-v"))
			System.out.println(args[1] + "-Queens has " + findSolutions(B, 1, "-v") + " solutions");
		else
			System.out.println(args[0] + "-Queens has " + findSolutions(B, 1, "") + " solutions");

	}

	static void placeQueen(int[][] B, int i, int j) { //row, column
		int row, col;
		B[i][j] = 1;
		B[i][0] = j;

		for (row = i + 1; row < B.length; row++) {
			B[row][j] -= 1;
		}
		for (row = i + 1, col = j + 1; row < B.length && col < B.length; row++, col++) {
			B[row][col] -= 1;
		}
		for (row = i + 1, col = j - 1; row < B.length && col > 0; row++, col--) {
			B[row][col] -= 1;
		}
	}

	static void removeQueen(int[][] B, int i, int j) {
		int row, col;
		B[i][j] = 0;
		B[i][0] = 0;

		for (row = i + 1; row < B.length; row++) {
			B[row][j] += 1;
		}
		for (row = i + 1, col = j + 1; row < B.length && col < B.length; row++, col++) {
			B[row][col] += 1;
		}
		for (row = i + 1, col = j - 1; row < B.length && col > 0; row++, col--) {
			B[row][col] += 1;
		}
	}

	static void printBoard(int[][] B) {
		System.out.print("(");
		for (int i = 1; i < B.length; i++) {
			if (i != B.length - 1)
				System.out.print("" + B[i][0] + ", ");
			else
				System.out.print("" + B[i][0]);
		}
		System.out.println(")");
	}

	static int findSolutions(int[][] B, int i, String mode) {
		if (i > B.length - 1) {
			if (!mode.equals("")) {
				printBoard(B);
			}
			sum++;
			return 1;
		} else {
			for (int k = 1; k < B.length; k++) {
				if (B[i][k] == 0) {
					placeQueen(B, i, k);
					if (mode.equals("-v"))
						findSolutions(B, i + 1, "-v");

					else
						findSolutions(B, i + 1, "");
					removeQueen(B, i, k);
				}
			}
		}
		return sum;
	}
}