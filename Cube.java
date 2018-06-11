import java.awt.Color;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
*	Java code to solve a 2x2 Rubik's Cube
*
*	@author Brian Seidl
*/
public class Cube{
	
	/*
	 * [x][y][z]
	 * x left to right starting left
	 * y top to bottom starting top
	 * z front to back starting front
	 */
	private Cubie[][][] cube;
	private final int SIZE = 2;

	private final int FRONT = 0;
	private final int BACK = 1;
	private final int TOP = 0;
	private final int BOTTOM = 1;
	private final int LEFT = 0;
	private final int RIGHT = 1;

	/*
	        ---------
	        | W | W |
	        | W | W |
	|-------|-------|-------|-------|
	| O | O | G | G | R | R | B | B |
	| O | O | G | G | R | R | B | B |
	|-------|-------|-------|-------|
	        | Y | Y |
	        | Y | Y |
	        ---------
	*/

	/**
	 * takes in 6 2D color arrays, top left at each face should be [0][0]
	 *
	 */
	public Cube(Color[][] front, Color[][] right, Color[][] back,
				Color[][] left, Color[][] top, Color[][] bottom){
		
		// intialize the cube 3D array of Cubie objects
		for (int i=0; i < SIZE; i++)
			for (int j=0; j < SIZE; j++)
				for (int k=0; k < SIZE; k++)
					this.cube[i][j][k] = new Cubie();

		cube[LEFT][TOP][FRONT].setLeft(left[0][1]);
		cube[LEFT][TOP][FRONT].setTop(top[1][0]);
		cube[LEFT][TOP][FRONT].setFront(front[0][0]);
		cube[RIGHT][TOP][FRONT].setRight(right[0][0]);
		cube[RIGHT][TOP][FRONT].setTop(top[1][1]);
		cube[RIGHT][TOP][FRONT].setFront(front[0][1]);
		cube[LEFT][TOP][BACK].setLeft(left[0][0]);
		cube[LEFT][TOP][BACK].setTop(top[0][0]);
		cube[LEFT][TOP][BACK].setBack(back[0][1]);
		cube[RIGHT][TOP][BACK].setRight(right[0][1]);
		cube[RIGHT][TOP][BACK].setTop(top[0][1]);
		cube[RIGHT][TOP][BACK].setBack(back[0][0]);
		cube[LEFT][BOTTOM][FRONT].setLeft(left[1][1]);
		cube[LEFT][BOTTOM][FRONT].setBottom(bottom[0][0]);
		cube[LEFT][BOTTOM][FRONT].setFront(front[1][0]);
		cube[RIGHT][BOTTOM][FRONT].setRight(right[1][0]);
		cube[RIGHT][BOTTOM][FRONT].setBottom(bottom[0][1]);
		cube[RIGHT][BOTTOM][FRONT].setFront(front[1][1]);
		cube[LEFT][BOTTOM][BACK].setLeft(left[1][0]);
		cube[LEFT][BOTTOM][BACK].setBottom(bottom[1][0]);
		cube[LEFT][BOTTOM][BACK].setBack(back[1][1]);
		cube[RIGHT][BOTTOM][BACK].setRight(right[1][1]);
		cube[RIGHT][BOTTOM][BACK].setBottom(bottom[1][1]);
		cube[RIGHT][BOTTOM][BACK].setBack(back[1][0]);

		// to be tested
	}

	/**
	*	constructor takes in a 3D Cubie array. aka the this.cube
	*/
	public Cube(Cubie[][][] cube){
		this.cube = new Cubie[SIZE][SIZE][SIZE];
		for (int i=0; i < SIZE; i++)
			for (int j=0; j < SIZE; j++)
				for (int k=0; k < SIZE; k++)
					this.cube[i][j][k] = new Cubie(cube[i][j][k]);
	}

	/**
	*	copy constructor to make a new reference of a cube object
	*/
	public Cube(Cube c){
		this.cube = new Cubie[SIZE][SIZE][SIZE];
		for (int i=0; i < SIZE; i++)
			for (int j=0; j < SIZE; j++)
				for (int k=0; k < SIZE; k++)
					this.cube[i][j][k] = new Cubie(c.cube[i][j][k]);
	}

	/*
	 *	recursice alg
	 */
	public String getSolution(int min){
		if(isSolved())
			return "Already Solved!";
		String res = "";
		
		Cube r = new Cube(this.cube);
		r.R();
		String a = 'R' + r.recursiveSolve('R', '0', 1, min);
		if (a.charAt(a.length()-1) == '1'){	
			res = a;
			min = a.length() - 2;
		}

		Cube rInv = new Cube(this.cube);
		rInv.RInv();
		//System.out.println(rInv);
		a = 'r' + rInv.recursiveSolve('r', '0', 1, min);
		if (a.charAt(a.length()-1) == '1'){
			res = a;
			min = a.length() - 2;
		}
		
		Cube u = new Cube(this.cube);
		u.U();
		a = 'U' + u.recursiveSolve('U', '0', 1, min);
		if (a.charAt(a.length()-1) == '1'){
			res = a;
			min = a.length() - 2;
		}

		Cube uInv = new Cube(this.cube);
		uInv.UInv();
		a = 'u' + uInv.recursiveSolve('u', '0', 1, min);
		if (a.charAt(a.length()-1) == '1'){
			res = a;
			min = a.length() - 2;
		}
		
		Cube f = new Cube(this.cube);
		f.F();
		a = 'F' + f.recursiveSolve('F', '0', 1, min);
		if (a.charAt(a.length()-1) == '1'){
			res = a;
			min = a.length() - 2;
		}

		Cube fInv = new Cube(this.cube);
		fInv.FInv();
		a = 'f' + fInv.recursiveSolve('f', '0', 1, min);
		if (a.charAt(a.length()-1) == '1'){
			res = a;
			min = a.length() - 2;
		}
		
		if (res.equals(""))
			return "No Solution";
		return formatString(res.substring(0, res.length() - 1));
	}

	// format string formats inverse moves
	// changes it from lower-case to upper-case
	// and adds the '
	// update. adds the double moves
	private static String formatString(String s){
		String result = "";
		char curr, lastCurr;
		int i;
		for(i = 1; i < s.length(); i++){
			lastCurr = s.charAt(i-1);
			curr = s.charAt(i);
			
			if ((curr == 'l' && lastCurr == 'l') || (curr == 'L' && lastCurr == 'L')){
				result += "L2";
				i++;
			}
			else if ((curr == 'r' && lastCurr == 'r') || (curr == 'R' && lastCurr == 'R')){
				result += "R2";
				i++;
			}
			else if ((curr == 'u' && lastCurr == 'u') || (curr == 'U' && lastCurr == 'U')){
				result += "U2";
				i++;
			}
			else if ((curr == 'd' && lastCurr == 'd') || (curr == 'D' && lastCurr == 'D')){
				result += "D2";
				i++;
			}
			else if ((curr == 'f' && lastCurr == 'f') || (curr == 'F' && lastCurr == 'F')){
				result += "F2";
				i++;
			}
			else if ((curr == 'b' && lastCurr == 'b') || (curr == 'B' && lastCurr == 'B')){
				result += "B2";
				i++;
			}
			else if (lastCurr == 'l')
				result += "L'";
			else if (lastCurr == 'r')
				result += "R'";
			else if (lastCurr == 'u')
				result += "U'";
			else if (lastCurr == 'd')
				result += "D'";
			else if (lastCurr == 'f')
				result += "F'";
			else if (lastCurr == 'b')
				result += "B'";
			else
				result += lastCurr;

			result += " ";
		}

		if (i == s.length()){
			if (s.charAt(i-1) == 'l')
				result += "L'";
			else if (s.charAt(i-1) == 'r')
				result += "R'";
			else if (s.charAt(i-1) == 'u')
				result += "U'";
			else if (s.charAt(i-1) == 'd')
				result += "D'";
			else if (s.charAt(i-1) == 'f')
				result += "F'";
			else if (s.charAt(i-1) == 'b')
				result += "B'";
			else
				result += s.charAt(i-1);
		}

		return result;
	}

	private String recursiveSolve(char lastMove, char lastLastMove, int numMoves, int max){
		if (this.isSolved())
			return "1";
		if (numMoves >= max)
			return "0";

		String res = "0";
		String a = "";
		
		if (lastMove != 'r' && !(lastLastMove == 'R' && lastMove == 'r')){
			Cube r = new Cube(this.cube);
			r.R();
			a = 'R' + r.recursiveSolve('R', lastMove, numMoves + 1, max);
			if (a.charAt(a.length()-1) == '1'){
				res = a;
				max = a.length()-2;
			}
		}

		if (lastMove != 'R' && !(lastLastMove == 'r' && lastMove == 'r')){
			Cube rInv = new Cube(this.cube);
			rInv.RInv();
			a = 'r' + rInv.recursiveSolve('r', lastMove, numMoves + 1, max);
			if (a.charAt(a.length()-1) == '1'){
				res = a;
				max = a.length()-2;
			}
		}
		
		if (lastMove != 'u' && !(lastLastMove == 'U' && lastMove == 'U')){
			Cube u = new Cube(this.cube);
			u.U();
			a = 'U' + u.recursiveSolve('U', lastMove, numMoves + 1, max);
			if (a.charAt(a.length()-1) == '1'){
				res = a;
				max = a.length()-2;
			}
		}

		if (lastMove != 'U' && !(lastLastMove == 'u' && lastMove == 'u')){
			Cube uInv = new Cube(this.cube);
			uInv.UInv();
			a = 'u' + uInv.recursiveSolve('u', lastMove, numMoves + 1, max);
			if (a.charAt(a.length()-1) == '1'){
				res = a;
				max = a.length()-2;
			}
		}

		if (lastMove != 'f' && !(lastLastMove == 'F' && lastMove == 'F')){
			Cube f = new Cube(this.cube);
			f.F();
			a = 'F' + f.recursiveSolve('F', lastMove, numMoves + 1, max);
			if (a.charAt(a.length()-1) == '1'){
				res = a;
				max = a.length()-2;
			}
		}

		if (lastMove != 'F' && !(lastLastMove == 'f' && lastMove == 'f')){
			Cube fInv = new Cube(this.cube);
			fInv.FInv();
			a = 'f' + fInv.recursiveSolve('f', lastMove, numMoves + 1, max);
			if (a.charAt(a.length()-1) == '1'){
				res = a;
				max = a.length()-2;
			}
		}

		return res;
	}

	public boolean isSolved(){
		// top
		Color top = cube[LEFT][TOP][FRONT].getTop();
		for (int i=0; i < SIZE; i++)
			for (int j=0; j < SIZE; j++)
				if (!cube[i][TOP][j].getTop().equals(top))
					return false;
		// left
		Color left = cube[LEFT][TOP][FRONT].getLeft();
		for (int i=0; i < SIZE; i++)
			for (int j=0; j < SIZE; j++)
				if (!cube[LEFT][i][j].getLeft().equals(left))
					return false;
		// front
		Color front = cube[LEFT][TOP][FRONT].getFront();
		for (int i=0; i < SIZE; i++)
			for (int j=0; j < SIZE; j++)
				if (!cube[i][j][FRONT].getFront().equals(front))
					return false;
		// right
		Color right = cube[RIGHT][BOTTOM][BACK].getRight();
		for (int i=0; i < SIZE; i++)
			for (int j=0; j < SIZE; j++)
				if (!cube[RIGHT][i][j].getRight().equals(right))
					return false;
		// bottom
		Color bottom = cube[RIGHT][BOTTOM][BACK].getBottom();
		for (int i=0; i < SIZE; i++)
			for (int j=0; j < SIZE; j++)
				if (!cube[i][BOTTOM][j].getBottom().equals(bottom))
					return false;
		// back
		Color back = cube[RIGHT][BOTTOM][BACK].getBack();
		for (int i=0; i < SIZE; i++)
			for (int j=0; j < SIZE; j++)
				if (!cube[i][j][BACK].getBack().equals(back))
					return false;

		return true;
	}

	public String scramble(int numMoves){
		Random gen = new Random();
		int num;
		int i = 0;
		String result = "";
		char lastMove = '0';
		char lastLastMove = '0';
		while (i < numMoves){
			num = gen.nextInt(12);
			if(num == 0 && lastMove != 'r' && lastMove != 'l' && lastMove != 'L' && !(lastMove == 'R' && lastLastMove == 'R')){
				lastLastMove = lastMove;
				lastMove = 'R';
				result += lastMove;
				this.R();
				i++;
			}
			if(num == 1 && lastMove != 'R' && lastMove != 'l' && lastMove != 'L' && !(lastMove == 'r' && lastLastMove == 'r')){
				lastLastMove = lastMove;
				lastMove = 'r';
				result += lastMove;
				this.RInv();
				i++;
			}
			if(num == 2 && lastMove != 'l' && lastMove != 'r' && lastMove != 'R' && !(lastMove == 'L' && lastLastMove == 'L')){
				lastLastMove = lastMove;
				lastMove = 'L';
				result += lastMove;
				this.L();
				i++;
			}
			if(num == 3 && lastMove != 'L' && lastMove != 'r' && lastMove != 'R' && !(lastMove == 'l' && lastLastMove == 'l')){
				lastLastMove = lastMove;
				lastMove = 'l';
				result += lastMove;
				this.LInv();
				i++;
			}
			if(num == 4 && lastMove != 'u' && lastMove != 'd' && lastMove != 'D' && !(lastMove == 'U' && lastLastMove == 'U')){
				lastLastMove = lastMove;
				lastMove = 'U';
				result += lastMove;
				this.U();
				i++;
			}
			if(num == 5 && lastMove != 'U' && lastMove != 'd' && lastMove != 'D' && !(lastMove == 'u' && lastLastMove == 'u')){
				lastLastMove = lastMove;
				lastMove = 'u';
				result += lastMove;
				this.UInv();
				i++;
			}
			if(num == 6 && lastMove != 'd' && lastMove != 'u' && lastMove != 'U' && !(lastMove == 'D' && lastLastMove == 'D')){
				lastLastMove = lastMove;
				lastMove = 'D';
				result += lastMove;
				this.D();
				i++;
			}
			if(num == 7 && lastMove != 'D' && lastMove != 'u' && lastMove != 'U' && !(lastMove == 'd' && lastLastMove == 'd')){
				lastLastMove = lastMove;
				lastMove = 'd';
				result += lastMove;
				this.DInv();
				i++;
			}
			if(num == 8 && lastMove != 'f' && lastMove != 'b' && lastMove != 'B' && !(lastMove == 'F' && lastLastMove == 'F')){
				lastLastMove = lastMove;
				lastMove = 'F';
				result += lastMove;
				this.F();
				i++;
			}
			if(num == 9 && lastMove != 'F' && lastMove != 'b' && lastMove != 'B' && !(lastMove == 'f' && lastLastMove == 'f')){
				lastLastMove = lastMove;
				lastMove = 'f';
				result += lastMove;
				this.FInv();
				i++;
			}
			if(num == 10 && lastMove != 'b' && lastMove != 'f' && lastMove != 'F' && !(lastMove == 'B' && lastLastMove == 'B')){
				lastLastMove = lastMove;
				lastMove = 'B';
				result += lastMove;
				this.B();
				i++;
			}
			if(num == 11 && lastMove != 'B' && lastMove != 'f' && lastMove != 'F' && !(lastMove == 'b' && lastLastMove == 'b')){
				lastLastMove = lastMove;
				lastMove = 'b';
				result += lastMove;
				this.BInv();
				i++;
			}
		}
		return formatString(result);
	}

	/*
		#######  #     #  ######   ##    #   ######
		   #     #     #  #     #  # #   #  #
		   #	 #     #  ######   #  #  #   #####
		   #     #     #  #    #   #   # #        #
		   #	  #####   #     #  #    ##  ######
	*/

	public void R(){
		// swap positions
		Cubie temp = cube[RIGHT][TOP][FRONT];
		cube[RIGHT][TOP][FRONT] = cube[RIGHT][BOTTOM][FRONT];
		cube[RIGHT][BOTTOM][FRONT] = cube[RIGHT][BOTTOM][BACK];
		cube[RIGHT][BOTTOM][BACK] = cube[RIGHT][TOP][BACK];
		cube[RIGHT][TOP][BACK] = temp;

		// orientate Cubies
		for(int i=0; i < 2; i++)
			for (int j=0; j < 2; j++)
				cube[RIGHT][i][j].up();
	}

	public void RInv(){
		// swap positions
		Cubie temp = cube[RIGHT][TOP][FRONT];
		cube[RIGHT][TOP][FRONT] = cube[RIGHT][TOP][BACK];
		cube[RIGHT][TOP][BACK] = cube[RIGHT][BOTTOM][BACK];
		cube[RIGHT][BOTTOM][BACK] = cube[RIGHT][BOTTOM][FRONT];
		cube[RIGHT][BOTTOM][FRONT] = temp;

		// orientate Cubies
		for(int i=0; i < 2; i++)
			for (int j=0; j < 2; j++)
				cube[RIGHT][i][j].down();
	}

	public void L(){
		// swap positions
		Cubie temp = cube[LEFT][TOP][FRONT];
		cube[LEFT][TOP][FRONT] = cube[LEFT][TOP][BACK];
		cube[LEFT][TOP][BACK] = cube[LEFT][BOTTOM][BACK];
		cube[LEFT][BOTTOM][BACK] = cube[LEFT][BOTTOM][FRONT];
		cube[LEFT][BOTTOM][FRONT] = temp;

		// orientate Cubies
		for(int i=0; i < 2; i++)
			for (int j=0; j < 2; j++)
				cube[LEFT][i][j].down();
	}

	public void LInv(){
		// swap positions
		Cubie temp = cube[LEFT][TOP][FRONT];
		cube[LEFT][TOP][FRONT] = cube[LEFT][BOTTOM][FRONT];
		cube[LEFT][BOTTOM][FRONT] = cube[LEFT][BOTTOM][BACK];
		cube[LEFT][BOTTOM][BACK] = cube[LEFT][TOP][BACK];
		cube[LEFT][TOP][BACK] = temp;

		// orientate Cubies
		for(int i=0; i < 2; i++)
			for (int j=0; j < 2; j++)
				cube[LEFT][i][j].up();
	}

	public void U(){
		// swap positions
		Cubie temp = cube[LEFT][TOP][FRONT];
		cube[LEFT][TOP][FRONT] = cube[RIGHT][TOP][FRONT];
		cube[RIGHT][TOP][FRONT] = cube[RIGHT][TOP][BACK];
		cube[RIGHT][TOP][BACK] = cube[LEFT][TOP][BACK];
		cube[LEFT][TOP][BACK] = temp;

		// orientate Cubies
		for(int i=0; i < 2; i++)
			for (int j=0; j < 2; j++)
				cube[i][TOP][j].left();
	}

	public void UInv(){
		// swap positions
		Cubie temp = cube[LEFT][TOP][FRONT];
		cube[LEFT][TOP][FRONT] = cube[LEFT][TOP][BACK];
		cube[LEFT][TOP][BACK] = cube[RIGHT][TOP][BACK];
		cube[RIGHT][TOP][BACK] = cube[RIGHT][TOP][FRONT];
		cube[RIGHT][TOP][FRONT] = temp;

		// orientate Cubies
		for(int i=0; i < 2; i++)
			for (int j=0; j < 2; j++)
				cube[i][TOP][j].right();
	}

	public void D(){
		// swap positions
		Cubie temp = cube[LEFT][BOTTOM][FRONT];
		cube[LEFT][BOTTOM][FRONT] = cube[LEFT][BOTTOM][BACK];
		cube[LEFT][BOTTOM][BACK] = cube[RIGHT][BOTTOM][BACK];
		cube[RIGHT][BOTTOM][BACK] = cube[RIGHT][BOTTOM][FRONT];
		cube[RIGHT][BOTTOM][FRONT] = temp;

		// orientate Cubies
		for(int i=0; i < 2; i++)
			for (int j=0; j < 2; j++)
				cube[i][BOTTOM][j].right();
	}

	public void DInv(){
		// swap positions
		Cubie temp = cube[LEFT][BOTTOM][FRONT];
		cube[LEFT][BOTTOM][FRONT] = cube[RIGHT][BOTTOM][FRONT];
		cube[RIGHT][BOTTOM][FRONT] = cube[RIGHT][BOTTOM][BACK];
		cube[RIGHT][BOTTOM][BACK] = cube[LEFT][BOTTOM][BACK];
		cube[LEFT][BOTTOM][BACK] = temp;

		// orientate Cubies
		for(int i=0; i < 2; i++)
			for (int j=0; j < 2; j++)
				cube[i][BOTTOM][j].left();
	}

	public void F(){
		// swap positions
		Cubie temp = cube[RIGHT][TOP][FRONT];
		cube[RIGHT][TOP][FRONT] = cube[LEFT][TOP][FRONT];
		cube[LEFT][TOP][FRONT] = cube[LEFT][BOTTOM][FRONT];
		cube[LEFT][BOTTOM][FRONT] = cube[RIGHT][BOTTOM][FRONT];
		cube[RIGHT][BOTTOM][FRONT] = temp;

		// orientate Cubies
		for (int i=0; i < SIZE; i++){
			for (int j=0; j < SIZE; j++)
				cube[i][j][FRONT].clock();
		}
	}

	public void FInv(){
		// swap positions
		Cubie temp = cube[RIGHT][TOP][FRONT];
		cube[RIGHT][TOP][FRONT] = cube[RIGHT][BOTTOM][FRONT];
		cube[RIGHT][BOTTOM][FRONT] = cube[LEFT][BOTTOM][FRONT];
		cube[LEFT][BOTTOM][FRONT] = cube[LEFT][TOP][FRONT];
		cube[LEFT][TOP][FRONT] = temp;

		// orientate Cubies
		for (int i=0; i < SIZE; i++){
			for (int j=0; j < SIZE; j++)
				cube[i][j][FRONT].cClock();
		}
	}

	public void B(){
		// swap positions
		Cubie temp = cube[RIGHT][TOP][BACK];
		cube[RIGHT][TOP][BACK] = cube[RIGHT][BOTTOM][BACK];
		cube[RIGHT][BOTTOM][BACK] = cube[LEFT][BOTTOM][BACK];
		cube[LEFT][BOTTOM][BACK] = cube[LEFT][TOP][BACK];
		cube[LEFT][TOP][BACK] = temp;

		// orientate Cubies
		for (int i=0; i < SIZE; i++){
			for (int j=0; j < SIZE; j++)
				cube[i][j][BACK].cClock();
		}
	}

	public void BInv(){
		// swap positions
		Cubie temp = cube[RIGHT][TOP][BACK];
		cube[RIGHT][TOP][BACK] = cube[LEFT][TOP][BACK];
		cube[LEFT][TOP][BACK] = cube[LEFT][BOTTOM][BACK];
		cube[LEFT][BOTTOM][BACK] = cube[RIGHT][BOTTOM][BACK];
		cube[RIGHT][BOTTOM][BACK] = temp;

		// orientate Cubies
		for (int i=0; i < SIZE; i++)
			for (int j=0; j < SIZE; j++)
				cube[i][j][BACK].clock();
	}

	public String toString(){
		ColorUtils c = new ColorUtils();

		/*
		        ---------
		        | W | W |
		        | W | W |
		|-------|-------|-------|-------|
		| O | O | G | G | R | R | B | B |
		| O | O | G | G | R | R | B | B |
		|-------|-------|-------|-------|
		        | Y | Y |
		        | Y | Y |
		        ---------
		*/
		String result = "";
		result += "        ---------\n";
		result += "        | " + c.getColorNameFromColor(cube[LEFT][TOP][BACK].getTop()).charAt(0) + " | " + c.getColorNameFromColor(cube[RIGHT][TOP][BACK].getTop()).charAt(0) + " |\n";
		result += "        | " + c.getColorNameFromColor(cube[LEFT][TOP][FRONT].getTop()).charAt(0) + " | " + c.getColorNameFromColor(cube[RIGHT][TOP][FRONT].getTop()).charAt(0) + " |\n";
		result += "|-------|-------|-------|-------|\n";
		
		result += "| " + c.getColorNameFromColor(cube[LEFT][TOP][BACK].getLeft()).charAt(0) + " | " + c.getColorNameFromColor(cube[LEFT][TOP][FRONT].getLeft()).charAt(0) + 
				  " | " + c.getColorNameFromColor(cube[LEFT][TOP][FRONT].getFront()).charAt(0) + " | " + c.getColorNameFromColor(cube[RIGHT][TOP][FRONT].getFront()).charAt(0) + 
				  " | " + c.getColorNameFromColor(cube[RIGHT][TOP][FRONT].getRight()).charAt(0) + " | " + c.getColorNameFromColor(cube[RIGHT][TOP][BACK].getRight()).charAt(0) + 
				  " | " + c.getColorNameFromColor(cube[RIGHT][TOP][BACK].getBack()).charAt(0) + " | " + c.getColorNameFromColor(cube[LEFT][TOP][BACK].getBack()).charAt(0) + " |\n";
		
		result += "| " + c.getColorNameFromColor(cube[LEFT][BOTTOM][BACK].getLeft()).charAt(0) + " | " + c.getColorNameFromColor(cube[LEFT][BOTTOM][FRONT].getLeft()).charAt(0) + 
				  " | " + c.getColorNameFromColor(cube[LEFT][BOTTOM][FRONT].getFront()).charAt(0) + " | " + c.getColorNameFromColor(cube[RIGHT][BOTTOM][FRONT].getFront()).charAt(0) + 
				  " | " + c.getColorNameFromColor(cube[RIGHT][BOTTOM][FRONT].getRight()).charAt(0) + " | " + c.getColorNameFromColor(cube[RIGHT][BOTTOM][BACK].getRight()).charAt(0) + 
				  " | " + c.getColorNameFromColor(cube[RIGHT][BOTTOM][BACK].getBack()).charAt(0) + " | " + c.getColorNameFromColor(cube[LEFT][BOTTOM][BACK].getBack()).charAt(0) + " |\n";

		result += "|-------|-------|-------|-------|\n";
		result += "        | "+ c.getColorNameFromColor(cube[LEFT][BOTTOM][FRONT].getBottom()).charAt(0) + " | " + c.getColorNameFromColor(cube[RIGHT][BOTTOM][FRONT].getBottom()).charAt(0) + " |\n";
		result += "        | "+ c.getColorNameFromColor(cube[LEFT][BOTTOM][BACK].getBottom()).charAt(0) + " | " + c.getColorNameFromColor(cube[RIGHT][BOTTOM][BACK].getBottom()).charAt(0) + " |\n";
		result += "        ---------";

		return result;
	}
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);

		// create solved cube object
		Cube cube = makeSolvedCube();

		// scramble the cube and print scramble
		System.out.println("\nScramble: " + cube.scramble(20));

		// print scrambled cube
		System.out.println("\n      Scrambled cube\n" + cube);

		//solve cube and print solution
		long startTime = System.currentTimeMillis();
		System.out.println("\nSolve: " + cube.getSolution(14));
		double estimatedTime = (System.currentTimeMillis() - startTime) / 1000.0;

		// print time taken to generate the solve
		System.out.println("Time: " + estimatedTime + "s");
	}

	// retruns a 2x2 solved cube object with white top and green front
	public static Cube makeSolvedCube(){
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
}
