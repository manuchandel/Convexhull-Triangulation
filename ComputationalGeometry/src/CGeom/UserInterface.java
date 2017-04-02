package CGeom;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
* The point class embroids everything into a user interface
*
* @author  Manu Chandel
* @version 1.0
* @since   2017-04-01 
*/
public class UserInterface {
	
	private JFrame jframe;
	private JLabel jlabel; 
	public JOptionPane jOptionPane;
	public JButton generateButton;
	public JButton hullButton;
	public JButton triangulateButton;
	public JTextField jtextField;
	public MyJPanel jpanel;
	public RandomPoints randomPoints;
	public ConvexHull convexHull;
	public Triangulate triangulate;
	
	/**
	 * Constructor to initialize Java swing objects
	 * @param random Object of random points
	 * 
	 */
	public UserInterface(){
		
		this.randomPoints=null;
		Date date=new Date();
		@SuppressWarnings("deprecation")
		String year=(new Integer(date.getYear()+1900)).toString();
		this.jframe=new JFrame("Convex Hull Generation and Triangulation, "+"\u00a9 "+year+" Manu Chandel");
		this.jlabel=new JLabel();
		this.jOptionPane=new JOptionPane();
		this.generateButton=new JButton("Generate");
		this.hullButton=new JButton("Hull");
		this.triangulateButton=new JButton("Triangulate");
		this.jtextField=new JTextField();
		this.jpanel=new MyJPanel();
		
	}
	
	/**
	  * This method positions creates complete UI
	  * 
	  * @param void
	  * 
	  */ 
	private void positionElements(){
		
		this.jlabel.setBounds(10,10,370,20);
		this.jtextField.setBounds(370,10,40,25);
		this.generateButton.setBounds(10, 40, 100, 30);
		this.hullButton.setBounds(130, 40, 100, 30);
		this.triangulateButton.setBounds(250, 40, 100, 30);
		this.jpanel.setBounds(10, 85, 550,500);
	}
	/**
	  * This method implements event listeners for all buttons
	  * 
	  * @param void
	  * 
	  */ 
	private void implementEventListeners(){
		
		UserInterface currentObj=this;
		this.generateButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						
						String numberText=currentObj.jtextField.getText();
						int number=0;
						try{
							number=Integer.parseInt(numberText);
							if(number>100 || number <3){
								number=0;
								throw new Exception();
							}
						}catch(Exception exception){
							
							String errorMessage="Enter a number between 3 to 100";
							JOptionPane.showMessageDialog(currentObj.jframe,errorMessage,"Input Error", JOptionPane.ERROR_MESSAGE);
							currentObj.jtextField.setText("");
						}
						
						currentObj.randomPoints=new RandomPoints(number);
						currentObj.convexHull=null;
						currentObj.triangulate=null;
						currentObj.jpanel.setPointList(currentObj.randomPoints.pointList, currentObj.randomPoints.size);
						currentObj.jpanel.setGenerateFlag();
						currentObj.jpanel.repaint();
					}
				}
		);
		
		this.hullButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
							
						if(currentObj.randomPoints==null){
							
							String errorMessage="Generate Points First";
							JOptionPane.showMessageDialog(currentObj.jframe,errorMessage,"Input Error", JOptionPane.ERROR_MESSAGE);
							
						}else{
						
							if(currentObj.randomPoints.size>2 && currentObj.randomPoints.size<=100){
								currentObj.convexHull=new ConvexHull(currentObj.randomPoints.pointList,currentObj.randomPoints.size);
								currentObj.jpanel.hullList=currentObj.convexHull.convexHull();
								currentObj.jpanel.setHullFlag();
								currentObj.jpanel.setGenerateFlag();
								currentObj.jpanel.repaint();
							}
						}
					}
				}
		);
		
		this.triangulateButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						
						if(currentObj.randomPoints==null){
							
							String errorMessage="Generate Points First";
							JOptionPane.showMessageDialog(currentObj.jframe,errorMessage,"Input Error", JOptionPane.ERROR_MESSAGE);
							
						}else if(currentObj.convexHull==null){
							
							String errorMessage="Draw Convex Hull First";
							JOptionPane.showMessageDialog(currentObj.jframe,errorMessage,"Input Error", JOptionPane.ERROR_MESSAGE);		
						}else{
							if(currentObj.randomPoints.size>2 && currentObj.randomPoints.size<=100){
								
								List<Point> hullClone=new ArrayList<Point>();
								for(int i=0;i<currentObj.jpanel.hullList.size();i++){
									hullClone.add(currentObj.jpanel.hullList.get(i));
								}
								
								currentObj.triangulate=new Triangulate(hullClone);
								currentObj.jpanel.diagonals=currentObj.triangulate.triangulate();
								currentObj.jpanel.setHullFlag();
								currentObj.jpanel.setTriangulateFlag();
								currentObj.jpanel.repaint();
							}
						}
					}
				}
		);
	}
	/**
	  * This method positions all swing objects in JFrame
	  * 
	  * @param void
	  * 
	  */ 
	public void createUI(){
		
		this.positionElements();
		
		this.implementEventListeners();
		this.jlabel.setText("Enter a number between 3 and 100 to generate random points");
		
		jframe.add(this.jlabel);
		jframe.add(this.jtextField);
		jframe.add(this.generateButton);
        jframe.add(this.hullButton);
		jframe.add(this.triangulateButton);
        jframe.add(this.jpanel);
        
        
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setResizable(false);
        jframe.setSize(580,630);
        jframe.setLayout(null);
       
        jframe.setVisible(true);
	}	
}

