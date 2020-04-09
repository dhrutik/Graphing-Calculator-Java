package graph;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GraphingCalculator extends JPanel implements ActionListener{
	private JTextField  equation;  // Input boxes for the numbers.
	JLabel jlabel;
	private JButton graph, firstDer, secondDer,reset; 
	GraphComponent graphArea;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame window = new JFrame("Graphing Calculator");
		GraphingCalculator content = new GraphingCalculator();
		window.setContentPane(content);
		window.setPreferredSize(new Dimension(1500, 1600));
		window.pack();  // Sizes window to preferred size of contents.
		window.setLocation(100,100);
		window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		window.setVisible(true);
	}
	public GraphingCalculator() {
		
		setLayout(new BorderLayout());
		JPanel panel1 = new JPanel(new GridBagLayout());
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		//JPanel panel4 = new JPanel();
		//panel4.setBackground(Color.blue);
		this.add(panel1, BorderLayout.NORTH);
		this.add(panel2, BorderLayout.CENTER);
		this.add(panel3, BorderLayout.SOUTH);
		//this.add(panel4, BorderLayout.EAST);

		panel1.setPreferredSize(new Dimension(1000,200));
		panel2.setPreferredSize(new Dimension(1000,400));
		panel3.setPreferredSize(new Dimension(1000,200));
		//panel4.setPreferredSize(new Dimension(200,400));

		
		JLabel label = new JLabel(" f(x) = ");
		label.setFont(new Font("TimesRoman", Font.ITALIC, 40));
		equation = new JTextField("",20);
		equation.setFont(new Font("Arial", Font.PLAIN, 40));
		Font btnFont = new Font("courier", Font.PLAIN, 30);

		graph = new JButton("Get f(x)");
		graph.addActionListener(this);
		
		firstDer = new JButton("Get f'(x)");
		firstDer.addActionListener(this);
		
		secondDer = new JButton("Get f''(x)");
		secondDer.addActionListener(this);
		
		reset = new JButton("Reset");
		reset.addActionListener(this);
		
		graph.setFont(btnFont);
		firstDer.setFont(btnFont);
		secondDer.setFont(btnFont);
		reset.setFont(btnFont);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,5,5,5);
		
        gbc.gridx=0;
        gbc.gridy=0;
		panel1.add(label,gbc);
		
		gbc.gridx=1;
        gbc.gridy=0;
		panel1.add(equation,gbc);

		gbc.gridx=0;
        gbc.gridy=1;
		panel1.add(graph,gbc);
		gbc.gridx=1;
        gbc.gridy=1;
		panel1.add(firstDer,gbc);
		gbc.gridx=2;
        gbc.gridy=1;
		panel1.add(secondDer,gbc);
		gbc.gridx=2;
        gbc.gridy=0;
		panel1.add(reset,gbc);
		
		graphArea =new GraphComponent();
		panel2.add(graphArea);
		  jlabel = new JLabel("");
		 
		 jlabel.setFont(new Font("Verdana",1,20));
		 panel3.add(jlabel);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent evt) {
		String op = evt.getActionCommand();
		graphArea.equation= equation.getText();
		if (op.equals("Get f(x)")) {

			if (graphArea.d0==1)
				graphArea.d0=0;
			else
				graphArea.d0=1;
			//graphArea.repaint();
			//jlabel.setText(graphArea.extrema);
		}
		if (op.equals("Get f'(x)")) {

			if (graphArea.d1==1)
				graphArea.d1=0;
			else
			graphArea.d1=1;
			
		}
		if (op.equals("Get f''(x)")) {

			if (graphArea.d2==1)
				graphArea.d2=0;
			else
			graphArea.d2=1;
			
		}
		if (op.equals("Reset")) {

			graphArea.d0=0;
				graphArea.d1=0;
			
			graphArea.d2=0;
			
		}
		
		graphArea.repaint();
	}
}
