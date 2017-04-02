package CGeom;
/**
* The point class provides necessary data structure and utility methods to hold a 2D point
*
* @author  Manu Chandel
* @version 1.0
* @since   2017-03-30 
*/
public class Point {
	
	private int x;
	private int y;
	
	/**
	 * Constructor to initialize a 2D point with integer co ordinates
	 * @param x abscissa
	 * @param y ordinate
	 */
	public Point(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	/**
	  * This method returns the value of x cordinate in present object
	  * 
	  * @param void
	  * @return value of x
	  */ 
	public int getX(){
		return this.x;
	}
	
	/**
	  * This method returns the value of y cordinate in present object
	  * 
	  * @param void
	  * @return value of y
	  */ 
	public int getY(){
		return this.y;
	}
	
	/**
	  * This method sets the value of current x cordinate
	  * 
	  * @param x new value of abscissa
	  */ 
	public void setX(int x){
		this.x=x;
	}
	
	/**
	  * This method sets the value of current y cordinate
	  * 
	  * @param x new value of ordinate
	  */ 
	public void setY(int y){
		this.y=y;
	}
	
	/**
	  * This method returns the euclidean distance between current point
	  * and the point passed as parameter  
	  * 
	  * @param Point point from which distance is to be returned
	  * @return euclidean distance
	  */ 
	public double euclidDistance(Point p){
		
		double x=p.getX()-this.getX();
		x=x*x;
		
		double y= p.getY()-this.getY();
		y=y*y;
		
		double distance=x+y;
		distance = Math.sqrt(distance);
		
		return distance;
	}
	
	/**
	  * This method returns the distance of a point from a line
	  * 
	  * @param A end point of a line
	  * @param B end point of a line
	  * @return distance from a line AB
	  * 
	  */ 
	public double pointDistance(Point a,Point b){
		
		double x1=a.getX();
		double x2=b.getX();
		double x3=this.x;
		
		double y1=a.getY();
		double y2=b.getY();
		double y3=this.y;
		
		double B=x2-x1;
		double A=y1-y2;
		double C=0-((x2-x1)*y2-(y2-y1)*x2);
		
		double distance=Math.abs(A*x3+B*y3+C);
		double mod=Math.sqrt(A*A+B*B);
		distance=distance/mod;
		
		return distance;
		
		
	
	}
	/**
	  * This method returns the orientation of the three points in order.
	  * It returns 1 if point p2 is to the left of current point and p1
	  * It returns -1 if point p2 is to the right of current point and p1
	  * It returns zero if points are collinear
	  * Remember points in JPanel have Pos x to right and Pos Y to left
	  * 
	  * @param p1 second point
	  * @param p2 third point
	  * @return orientation
	  */ 
	public int orientation(Point p1, Point p2){
		
		int x1=this.getX();
		int x2=p1.getX();
		int x3=p2.getX();
		
		int y1=this.getY();
		int y2=p1.getY();
		int y3=p2.getY();
		
		int area=x1*y2+x2*y3+x3*y1-(y1*x2+y2*x3+y3*x1);
		
		if(area>0){
			return -1;	
		}else if(area<0){
			return 1;
		}
		else return 0;
	}
	/**
	  * This method prints point
	  * @param void
	  * 
	  */
	public void print(){
		System.out.println(this.x+" "+this.y);
	}
	/**
	  * Overriding equal method to compare point objects
	  * @param o Point Object
	  * @return true if abscissa and ordinate of two points are same
	  * 
	  */
	@Override
	public boolean equals(Object o){
		
		if(this.getX()==((Point) o).getX() && this.getY()==((Point) o).getY()){
			
			return true;
		}
		
		return false;
	}
	
	/**
	  * Overriding hashcode method to hash point objects
	  * @param void
	  * @return int hashcode
	  * 
	  */
	@Override
	public int hashCode(){
		
		Integer hash=this.getX()*10000+this.getY();
		
		return hash.hashCode();
	}
	
}
