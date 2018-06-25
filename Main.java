import java.awt.Color;
import java.util.Scanner;
import java.util.Dictionary;
import java.util.Hashtable;

public class Main{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		boolean valid;

		System.out.println("Welcome to the 2x2 Rubik's Cube Solver created by Brian Seidl");
		printOptions();

		do {
			System.out.print(">>>");
			String mode = scan.next();

			if (mode.charAt(0) == 'A' || mode.charAt(0) == 'a'){
				valid = true;
				automatic();
			}else if (mode.charAt(0) == 'M' || mode.charAt(0) == 'm'){
				valid = true;
				manual();
			}else if (mode.equalsIgnoreCase("help")){
				printOptions();
				valid = false;
			}else{
				System.out.println("Mode invalid. Please enter a valid mode.");
				valid = false;
			}
		} while (valid == false);
	}

	/**
	 * retruns a 2x2 solved cube object with white top and green front
	 */
	private static Cube makeSolvedCube(){
		// declare constants
		final int FRONT = 0;
		final int BACK = 1;
		final int TOP = 0;
		final int BOTTOM = 1;
		final int LEFT = 0;
		final int RIGHT = 1;
		final int SIZE = 2;

		// make a solved cube object
		Cubie[][][] theCube = new Cubie[SIZE][SIZE][SIZE];

		theCube[LEFT][TOP][FRONT] = new Cubie();
		theCube[LEFT][TOP][FRONT].setFront(Color.GREEN);
		theCube[LEFT][TOP][FRONT].setTop(Color.WHITE);
		theCube[LEFT][TOP][FRONT].setLeft(Color.ORANGE);

		theCube[RIGHT][TOP][FRONT] = new Cubie();
		theCube[RIGHT][TOP][FRONT].setFront(Color.GREEN);
		theCube[RIGHT][TOP][FRONT].setTop(Color.WHITE);
		theCube[RIGHT][TOP][FRONT].setRight(Color.RED);

		theCube[LEFT][TOP][BACK] = new Cubie();
		theCube[LEFT][TOP][BACK].setBack(Color.BLUE);
		theCube[LEFT][TOP][BACK].setTop(Color.WHITE);
		theCube[LEFT][TOP][BACK].setLeft(Color.ORANGE);

		theCube[RIGHT][TOP][BACK] = new Cubie();
		theCube[RIGHT][TOP][BACK].setBack(Color.BLUE);
		theCube[RIGHT][TOP][BACK].setTop(Color.WHITE);
		theCube[RIGHT][TOP][BACK].setRight(Color.RED);

		theCube[LEFT][BOTTOM][FRONT] = new Cubie();
		theCube[LEFT][BOTTOM][FRONT].setFront(Color.GREEN);
		theCube[LEFT][BOTTOM][FRONT].setBottom(Color.YELLOW);
		theCube[LEFT][BOTTOM][FRONT].setLeft(Color.ORANGE);

		theCube[RIGHT][BOTTOM][FRONT] = new Cubie();
		theCube[RIGHT][BOTTOM][FRONT].setFront(Color.GREEN);
		theCube[RIGHT][BOTTOM][FRONT].setBottom(Color.YELLOW);
		theCube[RIGHT][BOTTOM][FRONT].setRight(Color.RED);

		theCube[LEFT][BOTTOM][BACK] = new Cubie();
		theCube[LEFT][BOTTOM][BACK].setBack(Color.BLUE);
		theCube[LEFT][BOTTOM][BACK].setBottom(Color.YELLOW);
		theCube[LEFT][BOTTOM][BACK].setLeft(Color.ORANGE);

		theCube[RIGHT][BOTTOM][BACK] = new Cubie();
		theCube[RIGHT][BOTTOM][BACK].setBack(Color.BLUE);
		theCube[RIGHT][BOTTOM][BACK].setBottom(Color.YELLOW);
		theCube[RIGHT][BOTTOM][BACK].setRight(Color.RED);

		return new Cube(theCube);
	}

	/**
	 * automatic mode generates a scramble automatically
	 */
	private static void automatic(){
		// create solved cube object
		Cube cube = makeSolvedCube();

		// scramble the cube and print scramble
		System.out.println("\nScramble: " + cube.scramble(20));

		// print scrambled cube
		System.out.println("\n        Your Cube\n" + cube);

		// solve cube and print solution
		long startTime = System.currentTimeMillis();
		System.out.println("\nSolve: " + cube.getSolution(14));
		double estimatedTime = (System.currentTimeMillis() - startTime) / 1000.0;

		// print time taken to generate the solve
		System.out.println("Time: " + estimatedTime + "s");
	}

	/**
	 * manual mode where user enters the colors manually
	 */
	private static void manual(){
		// get all of the sides from the user
		System.out.println("\nEnter the name of the color for each side of each face");
		printManualCube(null, null, null, null, null, null);
		Color[][] top = getFace("Top Face");
		printManualCube(top, null, null, null, null, null);
		Color[][] left = getFace("Left Face");
		printManualCube(top, left, null, null, null, null);
		Color[][] front = getFace("Front Face");
		printManualCube(top, left, front, null, null, null);
		Color[][] right = getFace("Right Face");
		printManualCube(top, left, front, right, null, null);
		Color[][] back = getFace("Back Face");
		printManualCube(top, left, front, right, back, null);
		Color[][] bottom = getFace("Bottom Face");

		// make a Cube Object
		Cube cube = new Cube(top, left, front, right, back, bottom);

		// print scrambled cube
		System.out.println("\n      Scrambled cube\n" + cube);

		// solve cube and print solution
		long startTime = System.currentTimeMillis();
		System.out.println("\nSolve: " + cube.getSolution(14));
		double estimatedTime = (System.currentTimeMillis() - startTime) / 1000.0;

		// print time taken to generate the solve
		System.out.println("Time: " + estimatedTime + "s");
	}

	/**
	 * Helper method for manual
	 */
	private static Color[][] getFace(String faceName){
		Color[][] face = new Color[2][2];
		System.out.print(faceName + "\tTop Left:\t");
		face[0][0] = getColorFromUser();
		System.out.print(faceName + "\tTop Right:\t");
		face[0][1] = getColorFromUser();
		System.out.print(faceName + "\tBottom Left:\t");
		face[1][0] = getColorFromUser();
		System.out.print(faceName + "\tBottom Right:\t");
		face[1][1] = getColorFromUser();
		return face;
	}
	
	/**
	 * Helper method for getFace
	 */
	private static Color getColorFromUser(){
		Dictionary<Character, Color> colors = new Hashtable<Character, Color>();
		colors.put('B', Color.BLUE);
		colors.put('b', Color.BLUE);
		colors.put('G', Color.GREEN);
		colors.put('g', Color.GREEN);
		colors.put('O', Color.ORANGE);
		colors.put('o', Color.ORANGE);
		colors.put('R', Color.RED);
		colors.put('r', Color.RED);
		colors.put('W', Color.WHITE);
		colors.put('w', Color.WHITE);
		colors.put('Y', Color.YELLOW);
		colors.put('y', Color.YELLOW);
		boolean valid = true;
		Scanner scan = new Scanner(System.in);
		Color result;

		do{
			char curr = scan.next().charAt(0);
			result = colors.get(curr);
			if (colors.get(curr) == null){
				valid = false;
				System.out.println("Invald Color");
			}
		} while (valid == false);
		return result;
	}

	private static void printOptions(){
		System.out.println("A(utomatic)\tAutomatically generate random scramble and solve");
		System.out.println("M(anual)\tManually enter the colors on each side and solve");
	}

	/**
	* Helper method for manual
	* Prints the cube based off of 6 2D arrays
	* Arrays will not always be filled out, so if array is null print white space
	*/
	private static void printManualCube(Color[][] top, Color[][] left, Color[][] front,
								   Color[][] right, Color[][] back, Color[][] bottom){
		ColorUtils c = new ColorUtils();

		// assign new arrays of characters to be printed
		char topChar[][] = new char[2][2];
		for (int i=0; i < 2; i++)
			for (int j=0; j < 2; j++)
				if (top != null)
					topChar[i][j] = c.getColorNameFromColor(top[i][j]).charAt(0);
				else
					topChar[i][j] = ' ';
		
		char leftChar[][] = new char[2][2];
		for (int i=0; i < 2; i++)
			for (int j=0; j < 2; j++)
				if (left != null)
					leftChar[i][j] = c.getColorNameFromColor(left[i][j]).charAt(0);
				else
					leftChar[i][j] = ' ';
		
		char frontChar[][] = new char[2][2];
		for (int i=0; i < 2; i++)
			for (int j=0; j < 2; j++)
				if (front != null)
					frontChar[i][j] = c.getColorNameFromColor(front[i][j]).charAt(0);
				else
					frontChar[i][j] = ' ';
		
		char rightChar[][] = new char[2][2];
		for (int i=0; i < 2; i++)
			for (int j=0; j < 2; j++)
				if (right != null)
					rightChar[i][j] = c.getColorNameFromColor(right[i][j]).charAt(0);
				else
					rightChar[i][j] = ' ';
		
		char backChar[][] = new char[2][2];
		for (int i=0; i < 2; i++)
			for (int j=0; j < 2; j++)
				if (back != null)
					backChar[i][j] = c.getColorNameFromColor(back[i][j]).charAt(0);
				else
					backChar[i][j] = ' ';
		
		char bottomChar[][] = new char[2][2];
		for (int i=0; i < 2; i++)
			for (int j=0; j < 2; j++)
				if (bottom != null)
					bottomChar[i][j] = c.getColorNameFromColor(bottom[i][j]).charAt(0);
				else
					bottomChar[i][j] = ' ';
		
		System.out.println("\n        Your Cube");
		System.out.println("        ---------");
		System.out.println("        | " + topChar[0][0] + " | " + topChar[0][1] + " |");
		System.out.println("        | " + topChar[1][0] + " | " + topChar[1][1] + " |");
		System.out.println("|-------|-------|-------|-------|");
		System.out.print("| " + leftChar[0][0] + " | " + leftChar[0][1] + " | " + frontChar[0][0] + " | " + frontChar[0][1] + " ");
		System.out.println("| " + rightChar[0][0] + " | " + rightChar[0][1] + " | " + backChar[0][0] + " | " + backChar[0][1] + " |");
		System.out.print("| " + leftChar[1][0] + " | " + leftChar[1][1] + " | " + frontChar[1][0] + " | " + frontChar[1][1] + " ");
		System.out.println("| " + rightChar[1][0] + " | " + rightChar[1][1] + " | " + backChar[1][0] + " | " + backChar[1][1] + " |");
		System.out.println("|-------|-------|-------|-------|");
		System.out.println("        | " + bottomChar[0][0] + " | " + bottomChar[0][1] + " |");
		System.out.println("        | " + bottomChar[1][0] + " | " + bottomChar[1][1] + " |");
		System.out.println("        ---------");
		System.out.println();
	}
}