//package f2.spw;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args){
		JFrame frame = new JFrame("Space WarII");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 720);//add size
		
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setVisible(true);
		
		
	}
}
