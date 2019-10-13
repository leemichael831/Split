import java.awt.*;
import java.awt.event.*;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import java.util.*;

import javax.swing.*;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

class gameOverScreen extends JPanel {
	JFrame window;
	JPanel titleNamePanel, restartButtonPanel;
	JLabel titleNameLabel;
	Font titleFont = new Font("Cambria", Font.PLAIN, 120);
	Font normalFont = new Font("Cambria", Font.PLAIN, 30);
	JButton restartButton;
	TitleScreenHandler tsHandler = new TitleScreenHandler();
	boolean second = false;

	
	public static void main(String[] args) {	
	}
	
	public void start(){
		//new gameOverScreen();
	}
	
	public gameOverScreen(int min, int secs, int millisecs){
		//shows the time survived
		String Smin = "" +min;
		String Ssecs = "" +secs;
		String Smillisecs = "" +millisecs;
		if(min<10){
			Smin = "0"+min;
		}
		if(secs<10){
			Ssecs = "0"+secs;
		}
		if(millisecs<10){
			Smillisecs = "00"+millisecs;
		}
		String time = "TIME SURVIVED: "+Smin+":"+Ssecs+":"+Smillisecs;
		
		//creates the window
		window = new JFrame();
		window.setSize(1000,1000);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.BLACK);
		window.setLayout(null);
		window.setVisible(true);
		
		//title
		titleNamePanel = new JPanel();
		titleNamePanel.setBounds(200,100,650,150);
		titleNamePanel.setBackground(Color.BLACK);
		titleNameLabel = new JLabel("GAME OVER");
		titleNameLabel.setForeground(Color.RED);
		titleNameLabel.setFont(titleFont);
		
		//puts the time survived on the Panel
		JPanel timePanel = new JPanel();
		timePanel.setBounds(250,300,500,150);
		timePanel.setBackground(Color.BLACK);
		JLabel stime = new JLabel(time);
		stime.setForeground(Color.WHITE);
		
		//restart button
		restartButtonPanel = new JPanel();
		restartButtonPanel.setBounds(450,500, 200,200);
		restartButtonPanel.setBackground(Color.BLACK);
		restartButton = new JButton("restart");
		restartButton.setBackground(Color.BLUE);
		restartButton.setForeground(Color.BLACK);
		restartButton.setFont(normalFont);
		restartButton.addActionListener(tsHandler);
		
		//puts all buttons and labels on corresponding panels
		titleNamePanel.add(titleNameLabel);
		timePanel.add(stime);
		restartButtonPanel.add(restartButton);
		
		//puts all panels on window
		window.add(titleNamePanel);
		window.add(timePanel);
		window.add(restartButtonPanel);
	}
	public void createGameScreen(){
		//creates new window when restart button pressed
		window.dispose();
		RealGame g = new RealGame();
		JFrame frame = new JFrame();
		frame.setTitle("Pong");
		frame.setContentPane(g);
		frame.setSize(1000, 700);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
				
	}
	public class TitleScreenHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			createGameScreen();
		}
	}
}