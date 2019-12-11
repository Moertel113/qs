package helpers;
public class Vector {
	double x, y, d;

	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getDistanceTo(Vector v) {
		d = Math.sqrt(Math.pow((v.getX() - x), 2) + Math.pow((v.getY() - y), 2));
		return d;
	}

	public void add(Vector v) {
		x += v.getX();
		y += v.getY();
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}
	
	public void addToX(double x){
		this.x+=x;
	}

	public double getY() {
		return y;
	}
	

	public void setY(double y) {
		this.y = y;
	}
	public void addToY(double y){
		this.y+=y;
	}

}
