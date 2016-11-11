package solutions;

public class Quadrant {
	double x = 0;
	double y = 0; 
	double w = 0;
	double h = 0;
	Node x_axis;
	Node y_axis;
	int number;
	Quadrant NW;
	Quadrant NE;
	Quadrant SW;
	Quadrant SE;
	
	public Quadrant(double x, double y, double w, double h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		x_axis = null;
		y_axis = null;
		NW = null;
		NE = null;
		SW = null;
		SE = null;
		number = 0;
	}
}
