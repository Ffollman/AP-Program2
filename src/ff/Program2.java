//This program will ask the user for 9 numbers and test if they make a Magic Square

package ff;

import java.util.*;

public class Program2 {

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		int k = 0;
		int size = 3;
		
		try {
		
			//Make a 2d array with the amount of rows and columns the user chose
			int[][] array = new int[size][size];
		
			//Make an array that we will use to ensure no double numbers were entered 
			int[] validateArray = new int[(size*size)];
		
			//Have the user fill the 2d array by entering the numbers 1 - max number for their chosen Magic Square
			System.out.println("Please enter the numbers 1 - " + (size*size) + " in any order, entering between each number.");
			System.out.println();
		
			for (int i = 0; i < size; i++) {
				System.out.println("Row " + (i + 1) + " :"); 
				for (int j = 0; j < size; j++) {
					array[i][j] = kb.nextInt();
					kb.nextLine();
					/*While loop to ensure the user's number is not out of bounds. 
					If it is, the user will have another chance to enter a number */
					while (array[i][j] < 1 || array[i][j] > (size*size)) {
						System.out.println("Error. That number was not between 1 and " + (size*size) + ". Please try again: ");
						array[i][j] = kb.nextInt();
						kb.nextLine();
					}
					validateArray[k] = (array[i][j]);
					k++;
				}
			}
	
			validateNoDoubles(validateArray, size);
		
			int sumOfRows = calculateSumOfRows(array, size);
			validateSum(sumOfRows);
		
			int sumOfCols = calculateSumOfCols(array, size);
			validateSum(sumOfCols);
		
			int sumOfDiags = calculateSumOfDiags(array, size);
			validateSum(sumOfDiags);
		
			/* Calculate if the sum of all rows, columns, and diagonals are equal. 
			If yes, congratulate the user and print the Magic Square */
			if ((sumOfRows == sumOfCols) && (sumOfRows == sumOfDiags)) {
				System.out.println("You made a Magic Square! Here is what it looks like:");
				printMagicSquare(array, size);				
			}
			else {
				System.out.println("I'm sorry. Your numbers do not make a Magic Square. To try again, please rerun the program.");
			}
		
		}
		
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Error. Your index went out of bounds. The program will terminate.");
			System.exit(0);
		}
	}
	
	/**Create a Method to calculate the sum of each row in the Magic Square. 
	 * If each row has the same sum, the sum will be returned. Otherwise, a sentinel value will be returned. */
	public static int calculateSumOfRows (int[][] magicSquare, int numOfRows) {
	
		int row = 0;
		int sum1 = 0;
		int sum2 = 0;
		int sum3 = 0;
		int col;
		
		//Make a loop to calculate the sum of row 1 
		for (col = 0; col < numOfRows; col++) {
			sum1 += magicSquare[row][col];
		}
		row++;
		
		//Make a loop to calculate the sum of row 2
		for (col = 0; col < numOfRows; col++) {
			sum2 += magicSquare[row][col];
		}
		row++;
		
		//Make a loop to calculate the sum of row 3
		for (col = 0; col < numOfRows; col++) {
			sum3 += magicSquare[row][col];
		}
		
		//Check if the sums are equal. If they are, return the sum. Otherwise, return the sentinel value
		if ((sum1 == sum2) && (sum2 == sum3)) {
			return sum1;	
		}
		else {
			return -1;
		}
	
	}
	
	/**Create a Method to calculate the sum of each column in the Magic Square. 
	 * If each column has the same sum, the sum will be returned. Otherwise, a sentinel value will be returned. */
	public static int calculateSumOfCols(int[][] magicSquare, int numOfRows) {
		int col = 0;
		int sum1 = 0;
		int sum2 = 0;
		int sum3 = 0;
		int row;
		
		//Make a loop to calculate the sum of column 1 
		for (row = 0; row < numOfRows; row++) {
			sum1 += magicSquare[row][col];
		}
		col++;
		
		//Make a loop to calculate the sum of column 2 
		for (row = 0; row < numOfRows; row++) {
			sum2 += magicSquare[row][col];
		}
		col++;
		
		//Make a loop to calculate the sum of column 3
		for (row = 0; row < numOfRows; row++) {
			sum3 += magicSquare[row][col];
		}
		
		//Check if the sums are equal. If they are, return the sum. Otherwise, return the sentinel value
		if ((sum1 == sum2) && (sum2 == sum3)) {
			return sum1;	
		}
		else {
			return -1;
		}
	}
	
	/**Create a Method to calculate the sum of each diagonal in the Magic Square. 
	 * If each diagonal has the same sum, the sum will be returned. Otherwise, a sentinel value will be returned. */
	public static int calculateSumOfDiags(int[][] magicSquare, int numOfRows) {
		int col = 0;
		int sum1 = 0;
		int sum2 = 0;
		int row;
		
		//Make a loop to calculate the sum of diagonal 1 
		for (row = 0; row < numOfRows; row++, col++) {
			sum1 += magicSquare[row][col];
		}
		
		//Make a loop to calculate the sum of diagonal 2 
		for (row = 0, col = 2; row < numOfRows; row++, col--) {
			sum2 += magicSquare[row][col];
		}
		
		//Check if the sums are equal. If they are, return the sum. Otherwise, return the sentinel value
		if (sum1 == sum2) {
			return sum1;	
		}
		else {
			return -1;
		}
	}
	
	/**Create a Method to check the returned sum in the Magic Square. 
	 * If the sentinel value was returned, the program will terminate. */
	public static void validateSum(int sum) {
		if (sum == -1) {
			System.out.println("I'm sorry. Your numbers do not make a Magic Square. To try again, please rerun the program.");
			System.exit(0);
		}
	}
	
	/**Create a method to validate that no doubles were entered into the array. 
	 * If there were, the program will terminate. */
	public static void validateNoDoubles(int[] magicSquare, int numOfRows) {
		
		for (int i = 0; i < (numOfRows*numOfRows); i++) {
			for (int j = (i + 1); j < (numOfRows*numOfRows); j++) {
				if (magicSquare[i] == magicSquare[j]) {
					System.out.println("You entered the same number twice. " +
							"The program will end. To try again, please rerun the program.");
					System.exit(0);
				}
			}
		}
	}
	
	/** Create a method to print the Magic Square that the user entered. */
	public static void printMagicSquare(int[][]magicSquare, int numOfRows) {
		
		for (int row = 0; row < numOfRows; row++) {
			for (int col = 0; col < numOfRows; col++) {
				System.out.print(magicSquare[row][col] + " ");
			}
			System.out.println();
		}
	}

}

