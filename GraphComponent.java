package graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JComponent;
import javax.swing.JOptionPane;



public class GraphComponent extends JComponent{
	
	int unit = 50, invRes=10;
	String equation = "";

	boolean err=false;

	public GraphComponent() {
		super();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		super.setPreferredSize(new Dimension((int)(screenSize.getHeight()*0.6), (int)(screenSize.getHeight()*0.6)));
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setBackground(Color.white);
		//Get this component's dimensions
		int width = 1000;//super.getWidth();
		int height = 1000;//super.getHeight();
		//Clear the whole area
		g2d.clearRect(0, 0, width, height);
		//Paint the axes
		//Set line width to 2 pixels
		g2d.setStroke(new BasicStroke(2));
		//Set line color
		g2d.setColor(Color.blue);
		//Draw the y axis
		g2d.drawLine(width/2, 0, width/2, height);
		//Draw the x axis
		g2d.drawLine(0, height/2, width, height/2);
		//Draw the background grid
		//Set line stroke to dashed
		g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, new float[] {10, 2}, 0));
		//Vertical lines: from the center to the right
		for(int i=width/2+unit; i<width; i+=unit)
			g2d.drawLine(i, 0, i, height);
		//Vertical lines: from the center to the left
		for(int i=width/2-unit; i>0; i-=unit)
			g2d.drawLine(i, 0, i, height);
		//Horizontal lines: from the center to the bottom
		for(int i=height/2+unit; i<height; i+=unit)
			g2d.drawLine(0, i, width, i);
		//Horizontal lines: from the center to the top
		for(int i=height/2-unit; i>0; i-=unit)
			g2d.drawLine(0, i, width, i);
		//Write unit numbers on the axes
		for(int i=width/2, i2=0; i<width; i+=unit, i2++)
			g2d.drawString(i2+",0", i+5, height/2-5);
		for(int i=width/2-unit, i2=-1; i>0; i-=unit, i2--)
			g2d.drawString(i2+",0", i+5, height/2-5);
		for(int i=height/2+unit, i2=-1; i<height; i+=unit, i2--)
			g2d.drawString("0,"+i2, width/2+5, i-5);
		for(int i=height/2-unit, i2=1; i>0; i-=unit, i2++)
			g2d.drawString("0,"+i2, width/2+5, i-5);

		g2d.setStroke(new BasicStroke(2));
		g2d.setColor(Color.blue);
		if (!equation.equals(""))
		DrawEquation(equation, g2d, width, height);
	}

	private void DrawEquation(String s, Graphics2D g2d, int width, int height) {


		Point prev = null;

		float res = 1.0f/invRes;

		int mw = width/2;
		int mh = height/2;

		double mr = (double)mw/unit;
		err=false;

		for(double a=-mr; a<=mr; a+=res) {
			
			double b = stringToOperation(a, s);
			
			if(err)
				break;

			int a2 = (int)(a*unit + mw);
			int b2 = (int)(b*unit + mh);

			if(prev != null)
				g2d.drawLine(prev.x, prev.y, a2, height-b2);
			
			prev = new Point(a2, height-b2);
			
		}

	}

	
	private double stringToOperation(double a, String eq) {
		double b=0;
		//eq = eq.replace("x", a+"");
		
		//eq = eq.replace("y", a+"");
		
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		try {
			MathJS math = new MathJS();
			 b= Double.parseDouble(math.eval(a,eq));
			 System.out.println(b);
			
		} catch (ScriptException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			err = true;
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		return b;
	}
	private double stringToOperation1(double a, String eq) {
		double b=0;
		eq = eq.replace("x", a+"");
		
		eq = eq.replace("y", a+"");
		
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		try {
			
			b = ((Number) engine.eval(eq)).doubleValue();
			
		} catch (ScriptException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			err = true;
		}catch(Exception e) {
			
		}
		return b;
	}

}
