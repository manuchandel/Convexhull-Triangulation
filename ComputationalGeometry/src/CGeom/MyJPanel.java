package CGeom;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
* This Jpanel class is used to draw
*
* @author  Manu Chandel
* @version 1.0
* @since   2017-04-01 
*/
public class MyJPanel extends JPanel{
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor highlights border
	 * @param void
	 * 
	 */
	private List<Point> pointList;
	private int size;
	private boolean generate;
	private boolean hull;
	private boolean triangulate;
	public List<Point> hullList;
	public List<Point> diagonals;
	
	
	/**
	 * Constructor initializes variables and sets boundary to Draw panel
	 * @param void
	 * 
	 */
	public MyJPanel() {
	    setBorder(BorderFactory.createLineBorder(Color.black));
	    this.generate=false;   
	    this.hull=false;
	    this.triangulate=false;
	 }
	
	/**
	  * This method sets the point list
	  * 
	  * @param list[] list of points
	  * @param n number of points
	  * 
	  */ 
	public void setPointList(List<Point> list, int n){
		this.pointList=list;
		this.size=n;
	}
	
	/**
	  * This method sets the generate flag
	  * 
	  * @param void
	  * 
	  */ 
	public void setGenerateFlag(){
		this.generate=true;
	}
	/**
	  * This method sets the hull flag flag
	  * 
	  * @param void
	  * 
	  */ 
	public void setHullFlag(){
		this.hull=true;
	}
	/**
	  * This method sets the tiangulate flag
	  * 
	  * @param void
	  * 
	  */ 
	public void setTriangulateFlag(){
		this.triangulate=true;
	}
	
	@Override
    public void paintComponent(Graphics g) {
		
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getWidth());
        
        if(this.generate){
        	System.out.println("Status => Plotting points on Panel");
        	g.setColor(Color.black);
        	for(int i=0;i<this.size;i++){
        		
        		Point point=this.pointList.get(i);
        		g.fillOval(point.getX()-3, point.getY()-3, 6, 6);
        		this.generate=false;
        	}
        	
        }
        
       
        
        if(this.triangulate){
        	System.out.println("Status => Triangulating Convex Hull on Panel");
        	g.setColor(Color.BLUE);
        	
        	int n=this.diagonals.size();
        	int x[]=new int[n];
        	int y[]=new int[n];
        	
        	for(int i=0;i<n;i++){
        		
        		Point p=this.diagonals.get(i);
        		x[i]=p.getX();
        		y[i]=p.getY();
        		
        	}
        	for(int i=0;i<n-1;i+=2){
        		g.drawLine(x[i], y[i], x[i+1], y[i+1]);
        	}
        	
        	this.triangulate=false;
        	
        }
        
        if(this.hull){
        	System.out.println("Status => Drawing Convex Hull on Panel");
        	g.setColor(Color.black);
        	
        	int n=this.hullList.size();
        	int x[]=new int[n];
        	int y[]=new int[n];
        	
        	for(int i=0;i<n;i++){
        		
        		Point p=this.hullList.get(i);
        		x[i]=p.getX();
        		y[i]=p.getY();
        		g.fillOval(x[i]-3, y[i]-3, 6, 6);
        		
        	}
        	g.drawPolygon(x, y, n);
        	this.hull=false;
        	
        }
    }
}
