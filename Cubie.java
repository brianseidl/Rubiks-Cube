/*

Think of a cubie as the individual pieces of a Rubik's cube
In this case, one cubie has 6 attributes:
front, right, back, left, top, bottom.

Most of the attributes will be null because the max it can have at once is 3

 */

import java.awt.Color;

public class Cubie{
	private Color front, right, back, left, top, bottom;
	private boolean initialized;

	public Cubie(){
		// cubie is not set up yet
		this.initialized = false;
	}

	public Cubie(Cubie c){
		this.front = c.front;
		this.back = c.back;
		this.left = c.left;
		this.right = c.right;
		this.top = c.top;
		this.bottom = c.bottom;
		this.initialized = true;
	}

	public void setTop(Color c){
		this.top = c;
		this.initialized = true;
	}

	public void setBottom(Color c){
		this.bottom = c;
		this.initialized = true;
	}

	public void setFront(Color c){
		this.front = c;
		this.initialized = true;
	}

	public void setRight(Color c){
		this.right = c;
		this.initialized = true;
	}

	public void setBack(Color c){
		this.back = c;
		this.initialized = true;
	}

	public void setLeft(Color c){
		this.left = c;
		this.initialized = true;
	}

	// getter methods to get the color of the side
	public Color getFront()  {return this.front;}
	public Color getTop()    {return this.top;}
	public Color getBottom() {return this.bottom;}
	public Color getRight()  {return this.right;}
	public Color getLeft()   {return this.left;}
	public Color getBack()   {return this.back;}

	/**
	*	rotation of the cube up 90 degrees of the x-axis
	*/
	public void up(){
		if(!initialized)
			throw new NullPointerException("must set the colors!!");

		Color temp = this.front;
		this.front = this.bottom;
		this.bottom = this.back;
		this.back = this.top;
		this.top = temp;
	}

	/**
	*	rotation of the cube down 90 degrees of the x-axis
	*/
	public void down(){
		if(!initialized)
			throw new NullPointerException("must set the colors!!");

		Color temp = this.front;
		this.front = this.top;
		this.top = this.back;
		this.back = this.bottom;
		this.bottom = temp;
	}

	public void right(){
		if(!initialized)
			throw new NullPointerException("must set the colors!!");

		Color temp = this.front;
		this.front = this.left;
		this.left = this.back;
		this.back = this.right;
		this.right = temp;
	}

	public void left(){
		if(!initialized)
			throw new NullPointerException("must set the colors!!");

		Color temp = this.front;
		this.front = this.right;
		this.right = this.back;
		this.back = this.left;
		this.left = temp;
	}

	public void clock(){
		if(!initialized)
			throw new NullPointerException("must set the colors!!");

		Color temp = this.top;
		this.top = this.left;
		this.left = this.bottom;
		this.bottom = this.right;
		this.right = temp;
	}

	// counter clockwise
	public void cClock(){
		if(!initialized)
			throw new NullPointerException("must set the colors!!");

		Color temp = this.top;
		this.top = this.right;
		this.right = this.bottom;
		this.bottom = this.left;
		this.left = temp;
	}
}