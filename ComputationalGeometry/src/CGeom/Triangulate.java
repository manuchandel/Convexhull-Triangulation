package CGeom;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
* The triangulate class triangulates the monotone polygon
*
* @author  Manu Chandel
* @version 1.0
* @since   2017-04-02 
*/
public class Triangulate {

	private List<Point> polygon;
	
	/**
	 * Constructor to initialize polygon list
	 * @param list points of a monotone polygon
	 * 
	 */
	public Triangulate(List<Point> list){
		
		this.polygon=list;
	}
	
	/**
	  * This method sorts the polygon points according to y coordinate
	  * It uses bubble sort
	  * @param void
	  * 
	  */
	private void sortPoints(boolean reflex[], boolean leftChain[], int n){
		
		for(int i=0;i<n;i++){	
			for(int j=i+1;j<n;j++){
				Point p=this.polygon.get(i);
				Point q=this.polygon.get(j);
				if(p.getY()>q.getY()){
					this.polygon.set(i,q);
					this.polygon.set(j,p);
					
					boolean reflexTemp=reflex[i];
					reflex[i]=reflex[j];
					reflex[j]=reflexTemp;
					
					boolean leftChainTemp=leftChain[i];
					leftChain[i]=leftChain[j];
					leftChain[j]=leftChainTemp;
					
					
				}
				else if(p.getY()==q.getY() && p.getX()>q.getX()){
					this.polygon.set(i,q);
					this.polygon.set(j,p);
					
					boolean reflexTemp=reflex[i];
					reflex[i]=reflex[j];
					reflex[j]=reflexTemp;
					
					boolean leftChainTemp=leftChain[i];
					leftChain[i]=leftChain[j];
					leftChain[j]=leftChainTemp;
				}
			}
		}
		
		
	}
	/**
	  * This method triangulates the monotone polygon
	  * @param void
	  * @return list of diagonals
	  * 
	  */
	public List<Point> triangulate(){
		
		List<Point> list=new ArrayList<Point>();
		
		int n=this.polygon.size();
		
		boolean reflexVertex[]=new boolean[n];
		boolean leftChain[]=new boolean[n];
		
		int lowestPointIndex=0;
		int highestPointIndex=0;
		
		for(int i=1;i<n;i++){
			
			Point a=this.polygon.get(i);
			Point lowest=this.polygon.get(lowestPointIndex);
			Point highest=this.polygon.get(highestPointIndex);
			
			if(a.getY()>lowest.getY()){
				lowestPointIndex=i;
			}
			
			if(a.getY()<highest.getY()){
				highestPointIndex=i;
			}
			
		}
		
		
		for(int i=lowestPointIndex;i<highestPointIndex;i%=n){
			
			leftChain[i]=false;
			int previousi=(i-1+n)%n;
			int nexti=(i+n)%n;
			
			Point previousP=this.polygon.get(previousi);
			Point p=this.polygon.get(i);
			Point nextP=this.polygon.get(nexti);
			
			if(previousP.orientation(p, nextP)==-1)
				reflexVertex[i]=true;
			else reflexVertex[i]=false;
			
			i=i+1;
			
		}
		for(int i=(highestPointIndex+1)%n;i<lowestPointIndex;i%=n){
			
			leftChain[i]=true;
			int previousi=(i-1+n)%n;
			int nexti=(i+n)%n;
			
			Point previousP=this.polygon.get(previousi);
			Point p=this.polygon.get(i);
			Point nextP=this.polygon.get(nexti);
			
			if(previousP.orientation(p, nextP)==-1)
				reflexVertex[i]=true;
			else reflexVertex[i]=false;
			
			i=i+1;
			
		}
		
		this.sortPoints(reflexVertex, leftChain, n);
		
		
		Stack<Integer> stack=new Stack<Integer>();
	
		
		stack.push(0);
		stack.push(1);
		
		for(int i=2;i<n;i++){
			
			
			int topIndex=stack.peek(); 
			Point pi=this.polygon.get(i);		
			Point topP=this.polygon.get(topIndex);
			
			
			if(leftChain[i]==leftChain[topIndex]){
				
				while(stack.size()>1 && !reflexVertex[topIndex]){
					
					stack.pop();
					topIndex=stack.peek(); 		
					topP=this.polygon.get(topIndex);
					
					list.add(pi);
					list.add(topP);
					
				}
				stack.push(i);
			}else{
				
				while(!stack.empty()){
					
					list.add(pi);
					list.add(this.polygon.get(stack.pop()));
				}
				
				stack.push(topIndex);
				stack.push(i);	
				
			}
		}
		return list;
	}
	
}
