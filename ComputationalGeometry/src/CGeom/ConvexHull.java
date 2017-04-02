package CGeom;

import java.util.ArrayList;
import java.util.List;

/**
* The ConvexHull program implements an application that returns
* a convex hull of points.
*
* @author  Manu Chandel
* @version 1.0
* @since   2017-03-30 
*/

public class ConvexHull {
	
	private List<Point> points;
	private int size;
	
	/**
	 * Constructor to initialize a points array
	 * @param list given set of points
	 * @param n number of points
	 */
	public ConvexHull(List<Point> list, int n){
		
		this.points=list;
		this.size=n;
		
	}
	
	/**
	 * method to compute convexhull of points
	 * @param a left point
	 * @param b right point
	 * @param s current set of points
	 * @return set of convex hull
	 * 
	 */
	
	private List<Point> quickHull(Point a, Point b, List<Point> array){
		
		if(array.size()==0)
			return null;
		
		int n=array.size();
		Point c=array.get(0);
		double distance=c.pointDistance(a, b);
		
		for(int i=1;i<n;i++){
			
			double currentDist=array.get(i).pointDistance(a, b);
			if(currentDist>distance){
				
				distance=currentDist;
				c=array.get(i);
			}
				
		}
		
		System.out.println();
		List<Point> rightAC=new ArrayList<Point>();
		List<Point> rightCB=new ArrayList<Point>();
		List<Point> hull=new ArrayList<Point>();
		
		for(int i=0;i<n;i++){
				
			if(a.orientation(c,array.get(i))==-1){
				
				rightAC.add(array.get(i));	
			}else if(c.orientation(b, array.get(i))==-1){
				
				rightCB.add(array.get(i));
			}
			
		}
		
		rightAC= this.quickHull(a,c,rightAC);
		rightCB=this.quickHull(c, b, rightCB);
		
		
		for(int i=0;rightAC!=null && i<rightAC.size();i++){
			
			hull.add(rightAC.get(i));
		}
		
		hull.add(c);
		for(int i=0;rightCB!=null && i<rightCB.size();i++){
			
			hull.add(rightCB.get(i));
		}
		
		return hull;
		
	}
	/**
	 * method to compute convexhull of points it makes use of quick hull
	 * @param a left point
	 * @param b right point
	 * @param s current set of points
	 * @return set of convex hull
	 * 
	 */
	public List<Point> convexHull(){
		
		List<Point> list=new ArrayList<Point>();
		
		Point b=this.points.get(0),a=this.points.get(0);
		
		for(int i=1;i<this.size;i++){
			
			Point point=this.points.get(i);
			if(point.getX()>a.getX())
				a=point;
			
			if(point.getX()<b.getX())
				b=point;
			
		}
		
		List<Point> above=new ArrayList<Point>();
		List<Point> below=new ArrayList<Point>();
		
		for(int i=0;i<this.size;i++){
			
			Point point=this.points.get(i);
			if(a.orientation(b, point)==-1){
				above.add(point);	
			}else if(a.orientation(b, point)==1){
				below.add(point);
			}
				
		}
		above=this.quickHull(a, b, above);
		below=this.quickHull(b, a, below);
		
		list.add(a);
		for(int i=0;above!=null && i<above.size();i++){
			
			list.add(above.get(i));
		}
		list.add(b);
		for(int i=0;below!=null && i<below.size();i++){
			
			list.add(below.get(i));
		}
		
		return list;
	}
	
}
