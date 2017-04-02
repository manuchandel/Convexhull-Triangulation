package CGeom;
import java.util.Random;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
/**
* The point class generates random points
*
* @author  Manu Chandel
* @version 1.0
* @since   2017-04-01 
*/

public class RandomPoints {
	
	public List<Point> pointList;
	public int size;
	private int range;
	
	/**
	 * Constructor to initialize a 2D point with integer coordinates
	 * range by default is 400
	 * @param n number of points
	 * 
	 */
	public RandomPoints(int n){
		
		this.size=n;
		this.pointList=new ArrayList<Point>();
		this.range=450;
		this.generate();
		
	}
	
	/**
	 * Constructor to initialize a 2D point with integer co ordinates within given range
	 * @param n number of random points to generate
	 * @param r range of integer co ordinates
	 */
	public RandomPoints(int n, int r){
		
		this.size=n;
		this.pointList=new ArrayList<Point>();
		this.range=r;
		this.generate();
		
	}
	
	/**
	  * This method generates random 2D points within given range, all distinct  
	  * 
	  * @param void
	  */ 
	private void generate(){
		
		System.out.println("Status => Random points generation initialized with "+ this.size+" points");
		Random random=new Random();
		
		Set<Point> pointSet= new HashSet<Point>();
		
		int loopi=0;
		while(loopi<this.size){
			
			Point point=new Point(random.nextInt(this.range)+20,random.nextInt(this.range)+20);
			
			if(!pointSet.contains(point)){
				
				pointSet.add(point);
				this.pointList.add(point);
				loopi=loopi+1;
			}
			
		}
		
	}
	
}
