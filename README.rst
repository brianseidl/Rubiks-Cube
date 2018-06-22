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

	$ javac *.java

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

	Scramble: L F L' F' U L' U' R' D' B' D' L B' U' B' R' B2 L' F'

	        Your Cube
	        ---------
	        | O | G |
	        | Y | Y |
	|-------|-------|-------|-------|
	| G | R | G | O | B | W | O | Y |
	| Y | R | G | R | W | W | O | B |
	|-------|-------|-------|-------|
	        | W | B |
	        | R | B |
	        ---------

	Solve: F' U' F' U F R'
	Time: 3.678s

Manual::

	Enter the name of the color for each side of each face

	        Your Cube
	        ---------
	        |   |   |
	        |   |   |
	|-------|-------|-------|-------|
	|   |   |   |   |   |   |   |   |
	|   |   |   |   |   |   |   |   |
	|-------|-------|-------|-------|
	        |   |   |
	        |   |   |
	        ---------

	Top Face	Top Left:	o
	Top Face	Top Right:	g
	Top Face	Bottom Left:	y
	Top Face	Bottom Right:	y

	        Your Cube
	        ---------
	        | O | G |
	        | Y | Y |
	|-------|-------|-------|-------|
	|   |   |   |   |   |   |   |   |
	|   |   |   |   |   |   |   |   |
	|-------|-------|-------|-------|
	        |   |   |
	        |   |   |
	        ---------

	Left Face	Top Left:	g
	Left Face	Top Right:	r
	Left Face	Bottom Left:	y
	Left Face	Bottom Right:	r

	        Your Cube
	        ---------
	        | O | G |
	        | Y | Y |
	|-------|-------|-------|-------|
	| G | R |   |   |   |   |   |   |
	| Y | R |   |   |   |   |   |   |
	|-------|-------|-------|-------|
	        |   |   |
	        |   |   |
	        ---------

	Front Face	Top Left:	

	...

	Bottom Face	Top Left:	w
	Bottom Face	Top Right:	b
	Bottom Face	Bottom Left:	r
	Bottom Face	Bottom Right:	b

	      Scrambled cube
	        ---------
	        | O | G |
	        | Y | Y |
	|-------|-------|-------|-------|
	| G | R | G | O | B | W | O | Y |
	| Y | R | G | R | W | W | O | B |
	|-------|-------|-------|-------|
	        | W | B |
	        | R | B |
	        ---------

	Solve: F' U' F' U F R'
	Time: 3.056s