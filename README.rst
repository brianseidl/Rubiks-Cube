Rubiks-Cube
===========

This is a program that is used to solve a 2x2 Rubik's Cube.

Installing
----------

To install, first clone the repository::

	$ git clone git@github.com:brianseidl/Rubiks-Cube.git

Change directory::

	$ cd Rubiks-Cube/

Then compile all of the classes::

	$ javac Cube.java Cubie.java ColorUtils.java

Running
-------

To run the application, execute the main method of the Cube class::

	$ java Cube

You will see options **Automatic** and **Manual**::

	A(utomatic)	Automatically generate random scramble and solve
	M(anual)	Manually enter the colors on each side and solve
	>>>

If you have a physical 2x2 Rubik's Cube and would like to solve it, *Manual* will let you enter the colors on each side and solve. If you do not have a Rubik's cube you would like to solve and just want to see application run, choose *Automatic*. *Automatic* will generate a random scramble and solve that scramble.

Example Output
--------------

Automatic::

	Scramble: R' F U' B L' U B D R' D' F U F U B L D' R B' R'

	      Scrambled cube
	        ---------
	        | G | W |
	        | Y | Y |
	|-------|-------|-------|-------|
	| Y | R | G | B | R | B | O | O |
	| G | W | B | R | G | B | O | W |
	|-------|-------|-------|-------|
	        | R | W |
	        | O | Y |
	        ---------

	Solve: R' U F U' R F2 U' F' U
	Time: 43.123s

Manual::

	Enter the name of the color for each side of each face

	Top Face	Top Left:	green
	Top Face	Top Right:	white
	Top Face	Bottom Left:	yellow
	Top Face	Bottom Right:	yellow
	Left Face	Top Left:	yellow
	Left Face	Top Right:	red
	Left Face	Bottom Left:	green
	Left Face	Bottom Right:	white
	Front Face	Top Left:	green
	Front Face	Top Right:	blue
	Front Face	Bottom Left:	blue
	Front Face	Bottom Right:	red
	Right Face	Top Left:	red
	Right Face	Top Right:	blue
	Right Face	Bottom Left:	green
	Right Face	Bottom Right:	blue
	Back Face	Top Left:	orange
	Back Face	Top Right:	orange
	Back Face	Bottom Left:	orange
	Back Face	Bottom Right:	white
	Bottom Face	Top Left:	red
	Bottom Face	Top Right:	white
	Bottom Face	Bottom Left:	orange
	Bottom Face	Bottom Right:	yellow

	      Scrambled cube
	        ---------
	        | G | W |
	        | Y | Y |
	|-------|-------|-------|-------|
	| Y | R | G | B | R | B | O | O |
	| G | W | B | R | G | B | O | W |
	|-------|-------|-------|-------|
	        | R | W |
	        | O | Y |
	        ---------

	Solve: R' U F U' R F2 U' F' U
	Time: 42.604s