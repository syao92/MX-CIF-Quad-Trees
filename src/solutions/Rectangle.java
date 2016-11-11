package solutions;

public class Rectangle {
	double x = 0;
	double y = 0; 
	double w = 0;
	double h = 0;
	String rname = null;
	
	public Rectangle(double x, double y, double w, double h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public Rectangle(double x, double y, double w, double h, String rname) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.rname = rname;
	}
}
