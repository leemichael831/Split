import java.awt.*;
import java.awt.event.*;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import java.util.*;

import javax.swing.*;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

class helpScreen extends JPanel{
	JFrame window;
	JPanel titleNamePanel, descriptionPanel, startButtonPanel;
	JLabel titleNameLabel, descriptionLabel;
	Font titleFont = new Font("Cambria", Font.PLAIN, 45);
	Font normalFont = new Font("Cambria", Font.PLAIN, 15);
	JButton startButton;
	TitleScreenHandler tsHandler = new TitleScreenHandler();
	boolean second = false;

	
	public static void main(String[] args) {
		new helpScreen();//creates screen
		
	}
	
	public helpScreen(){
		
		
		window = new JFrame();
		window.setSize(1000,1000);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.BLACK);
		window.setLayout(null);
		window.setVisible(true);
		
		
		//paragraph of instructions
		descriptionPanel = new JPanel();
		descriptionPanel.setBounds(215,250,850, 800);
		descriptionPanel.setBackground(Color.BLACK);
		descriptionLabel = new JLabel("<html>Hey guys! Welcome to SPLIT. Below are some instructions.<br>Instructions: The screen is split in half, Use TFGH to control the left box and <br> arrow keys to control the right box. To start off, the left box needs to gather all the falling objects, while the <br>right one needs to avoid all the falling objects. However, midgame, their purposes might change when<br> you see the word Switch. WHEN THE SWITCH SIGN FINISHES ITS THIRD TIME BLINKING, THAT IS WHEN YOU <br>HAVE TO SWITCH, OR ELSE YOU WILL SUFFER A MISERABLE DEATH <br>As you progress, there will be more switch occurrences. <br>The objects falling down<br> will also fall faster. A life will be lost each time a falling box falls to the bottom<br> without being collected by either side, and if the box that is meant to avoid <br>the box touches it, it's an automatic game overThis is a survival game. <br>Let's see what your time is. Have fun!</html>");
		descriptionLabel.setForeground(Color.WHITE);
		descriptionLabel.setFont(normalFont);
		
		
		//starts the game
		startButtonPanel = new JPanel();
		startButtonPanel.setBounds(425,500, 150,150);
		startButtonPanel.setBackground(Color.BLACK);
		startButton = new JButton("START");
		startButton.setBackground(Color.BLUE);
		startButton.setForeground(Color.BLACK);
		startButton.setFont(normalFont);
		startButton.addActionListener(tsHandler);
		
		//title "instructions"
		titleNamePanel = new JPanel();
		titleNamePanel.setBounds(200,0,600,50);
		titleNamePanel.setBackground(Color.BLACK);
		titleNameLabel = new JLabel("INSTRUCTIONS");
		titleNameLabel.setForeground(Color.WHITE);
		titleNameLabel.setFont(titleFont);
		
		
		titleNamePanel.add(titleNameLabel);
		descriptionPanel.add(descriptionLabel);
		startButtonPanel.add(startButton);
		
		//adds all panels to window so it shows up
		window.add(titleNamePanel);
		window.add(descriptionPanel);
		window.add(startButtonPanel);
		
		
	
	}
	public void createGameScreen(){
		//creates the real game screen to play
		window.dispose();
		RealGame good = new RealGame();
		JFrame frame = new JFrame();
		frame.setTitle("SPLIT");
		frame.setContentPane(good);
		good.start();
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
	