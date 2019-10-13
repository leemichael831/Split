import java.awt.*;
import java.awt.event.*;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import java.util.*;

import javax.swing.*;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

class Main {
	JFrame window;//the window that pops up
	JPanel titleNamePanel, startButtonPanel, helpButtonPanel;//the different containers displayed on the window
	JLabel titleNameLabel;//the SPLIT title
	Font titleFont = new Font("Cambria", Font.PLAIN, 90);//BIG FONT
	Font normalFont = new Font("Cambria", Font.PLAIN, 30);//small font
	JButton startButton, helpButton;//buttons
	TitleScreenHandler tsHandler = new TitleScreenHandler();//the new actionevent that is called when start button pressed
	helpScreenHandler hpHandler = new helpScreenHandler();//the new actionevent that is called when help button pressed
	boolean second = false;

	
	public static void main(String[] args) {
		new Main();//creates the main screen
		
	}
	
	public Main(){
		window = new JFrame();
		window.setSize(1000,1000);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.BLACK);
		window.setLayout(null);
		window.setVisible(true);

		//title
		titleNamePanel = new JPanel();
		titleNamePanel.setBounds(300,100,375,100);
		titleNamePanel.setBackground(Color.BLACK);
		titleNameLabel = new JLabel("SPLIT");
		titleNameLabel.setForeground(Color.WHITE);
		titleNameLabel.setFont(titleFont);
		
		//startbutton
		startButtonPanel = new JPanel();
		startButtonPanel.setBounds(425,500, 150,100);
		startButtonPanel.setBackground(Color.BLACK);
		startButton = new JButton("START");
		startButton.setBackground(Color.BLUE);
		startButton.setForeground(Color.BLACK);
		startButton.setFont(normalFont);
		startButton.addActionListener(tsHandler);
		
		//helpButton
		helpButtonPanel = new JPanel();
		helpButtonPanel.setBounds(600,500, 100,100);
		helpButtonPanel.setBackground(Color.BLACK);
		helpButton = new JButton("?");
		helpButton.setBackground(Color.BLUE);
		helpButton.setForeground(Color.BLACK);
		helpButton.setFont(normalFont);
		helpButton.addActionListener(hpHandler);
		
		//adding the labels and buttons to each panel so it actually displays
		titleNamePanel.add(titleNameLabel);
		helpButtonPanel.add(helpButton);
		startButtonPanel.add(startButton);
		
		//adding each panel to the window, so it shows on the window
		window.add(titleNamePanel);
		window.add(helpButtonPanel);
		window.add(startButtonPanel);
		
		
	
	}
	public void createGameScreen(){
	
		window.dispose();//destroys the window
		RealGame good = new RealGame();
		JFrame frame = new JFrame();//the new screen
		frame.setTitle("SPLIT");
		frame.setContentPane(good);//the new screen becomes RealGame
		good.start();
		frame.setSize(1000, 700);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
				
	}
	public void createHelpScreen(){
		
			window.dispose();//destroys the screen
			window.setVisible(false);
	
			
			helpScreen hScreen = new helpScreen();
			window.setContentPane(hScreen);//the new screen becomes the help page


		 	
			
					
		}
		//new class that calls to a new screen when start button clicked
	public class TitleScreenHandler implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			createGameScreen();
		}
	}
		// new class that calls to a new screen help button clicked
	public class helpScreenHandler implements ActionListener{
			
			public void actionPerformed(ActionEvent e){
				createHelpScreen();
			}
		}
}
	