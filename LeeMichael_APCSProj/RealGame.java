import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.HashSet;
import javax.swing.*;
import java.util.ArrayList;
import java.text.DecimalFormat;


public class RealGame extends JPanel implements KeyListener, ActionListener {
	JFrame frame;//the window
	
	private int height, width;//height and width of the window

	private boolean first;//boolean used for when the game first starts up
	
	private HashSet<String> keys = new HashSet<String>();//the list that contains all the letters, so when pressed, it is used as a reference
	
	//for the boxes that are controlled
	private int padH = 20, padW = 20;
	private int rightPadX, rightPadY, leftPadX, leftPadY;
	private int inset = 10;
	
	private int padSpeed = 4;
	
	//heigh and width of the window
	static int windowWidth = 1000;
	static int windowHeight = 700;

	boolean gameRunning = false;
	
	int speed = 1;
	
	//x coordinates of the boxes falling down on the left half
	int x1 = getRandLeftX();
	int x2 = getRandLeftX();
	int x3 = getRandLeftX();
	int x4 = getRandLeftX();
	int x5 = getRandLeftX();
	
	//x coordinates of the boxes falling down on the right half
	int x6 = getRandRightX();
	int x7 = getRandRightX();
	int x8 = getRandRightX();
	int x9 = getRandRightX();
	int x10 = getRandRightX();
	
	//y coordinates of the boxes falling down on the left half
	int y1 = getRandY();
	int y2 = getRandY();
	int y3 = getRandY();
	int y4 = getRandY();
	int y5 = getRandY();

	//y coordinates of the boxes falling down on theright half
	int y6 = getRandY();
	int y7 = getRandY();
	int y8 = getRandY();
	int y9 = getRandY();
	int y10 = getRandY();
	
	//clock that appears on the top 
	public Timer clock;
	public int millisecs = 0;
	public int secs = 0;
	public int mins = 0;
	public JLabel display;//label that displays the clock
	private DecimalFormat dFormat = new DecimalFormat("00");
	
	//defines the role of each box: either collect or avoid
	private boolean isLeftCollect = true;//left box starts off collecting
	private boolean isRightCollect = false;//right box starts off avoiding

	Font normalFont = new Font("Cambria", Font.BOLD, 30);//description
	
	//chooses whether to show the switch sign on top or not
	 private boolean signToSwitch = false;
	
	//the amount of lives you have each time
	int lives = 10;
	
	//determines whether you lose a life or not
	private boolean isHurt = false;
	
	public static void main(String[] args) {
		
	}
	
	//loads the new window
	public void start(){
		new RealGame();
	}
		
	public RealGame() {
		//creates the new window
		frame = new JFrame();
		frame.setTitle("SPLIT");
		frame.setSize(1000, 700);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		first = true;//so that it knows that the game is just booted up
		gameRunning = true;//so it knows that the game hasn't stopped
		
		//the timer on top shows up
		display = new JLabel();
		add(display);
		
		//timer starts
		clock = new Timer(5, this);
		clock.start();	
	}
	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;//the class that is used to draw everything
		height = getHeight();
		width = getWidth();
		
		if (first) {
			//sets the starting position of the collecting boxes
			rightPadX = 750;
			rightPadY = 500;
			leftPadX = 250;
			leftPadY = 500;
			first = false;//the game has finished booting up
		}
		//right collecting box		
		g2d.setColor(Color.BLACK);
		Rectangle2D rightPad = new Rectangle(rightPadX, rightPadY, padW, padH);
		g2d.fill(rightPad);
		
		//left collecting box
		Rectangle2D leftPad = new Rectangle(leftPadX, leftPadY, padW, padH);
		g2d.fill(leftPad);
		
		//midline border
		g2d.setColor(Color.LIGHT_GRAY);
		Rectangle2D border = new Rectangle(500, 50, 10, 700);
		g2d.fill(border);
		
		//borders that separates the timer, switch sign, and the number of lives on top
		Rectangle2D clockBorder1= new Rectangle(400, 0, 10, 50);
		g2d.fill(clockBorder1);
		Rectangle2D clockBorder2= new Rectangle(590, 0, 10, 50);
		g2d.fill(clockBorder2);
		Rectangle2D clockBorder3= new Rectangle(250, 40, 500, 10);
		g2d.fill(clockBorder3);
		Rectangle2D clockBorder4= new Rectangle(250, 0, 500, 10);
		g2d.fill(clockBorder4);
		Rectangle2D clockBorder5= new Rectangle(250, 0, 10, 50);
		g2d.fill(clockBorder5);
		Rectangle2D clockBorder6= new Rectangle(750, 0, 10, 50);
		g2d.fill(clockBorder6);
		
		//the boxes that are falling down
		//the left boxes that are falling down
		g2d.setColor(Color.MAGENTA);
		Rectangle2D left1 = new Rectangle(x1, y1, 20, 20);
		g2d.fill(left1);
		Rectangle2D left2 = new Rectangle(x2, y2, 20, 20);
		g2d.fill(left2);
		Rectangle2D left3 = new Rectangle(x3, y3, 20, 20);
		g2d.fill(left3);
		Rectangle2D left4 = new Rectangle(x4, y4, 20, 20);
		g2d.fill(left4);
		Rectangle2D left5 = new Rectangle(x5, y5, 20, 20);
		g2d.fill(left5);
		
		//the right boxes that are falling down
		Rectangle2D right1 = new Rectangle(x6, y6, 20, 20);
		g2d.fill(right1);
		Rectangle2D right2 = new Rectangle(x7, y7, 20, 20);
		g2d.fill(right2);
		Rectangle2D right3 = new Rectangle(x8, y8, 20, 20);
		g2d.fill(right3);
		Rectangle2D right4 = new Rectangle(x9, y9, 20, 20);
		g2d.fill(right4);
		Rectangle2D right5 = new Rectangle(x10, y10, 20, 20);
		g2d.fill(right5);
		
		//causes the "fall" of the falling boxes
		y1+=speed;
		y2+=speed;
		y3+=speed;
		y4+=speed;
		y5+=speed;
		y6+=speed;
		y7+=speed;
		y8+=speed;
		y9+=speed;
		y10+=speed;
		
		//is called from actionPerformed when the switch challenge is activated	
		//shows the user that it is time to switch roles for the boxes	
		if(signToSwitch){
			g2d.setColor(Color.RED);
			g2d.setFont(new Font("Cambria", Font.BOLD, 35));
			g2d.drawString("SWITCH",445, 35);	
		}
		
	}
	
	//generates a random x coordinate for the left falling boxes
	public int getRandLeftX(){
		return (int)(Math.random()*480);
	}
	
	//generates a random x coordinate for the left falling boxes
	public int getRandRightX(){
		return (int)(Math.random()*480+510);
	}
	
	//generates a random y coordinate for the falling boxes
	public int getRandY(){
		return (int)(Math.random()*-1000-1);
	}

	//defines all the if statements/scenarios and what the program's reaction would be
	@Override
	public void actionPerformed(ActionEvent e) {
		//contains all the actions that the program will perform if a key is pressed
		//each key will move the box in its own direction
		if (keys.contains("LEFT")) {
			if(rightPadX>windowWidth/2+10)
			rightPadX = rightPadX-padSpeed;
			
		}
		else if (keys.contains("RIGHT")) {
			if(rightPadX<windowWidth-18)
			rightPadX = rightPadX+padSpeed;
		}
		if (keys.contains("UP")) {
			if(rightPadY>0)
			rightPadY = rightPadY-padSpeed;
		}
		else if (keys.contains("DOWN")) {
			if(rightPadY<windowHeight-42)
			rightPadY = rightPadY+padSpeed;
		}
		if (keys.contains("F")) {
			if(leftPadX>0)
			leftPadX = leftPadX-padSpeed;
		}
		else if (keys.contains("H")) {
			if(leftPadX<480)
			leftPadX = leftPadX+padSpeed;
		}
		if (keys.contains("T")) {
			if(leftPadY>0)
			leftPadY = leftPadY-padSpeed;
		}
		else if (keys.contains("G")) {
			if(leftPadY<windowHeight-42)
			leftPadY = leftPadY+padSpeed;
		}
				
		//controls the clock
		if (e.getSource() == clock)
		{
			millisecs++;//increases the millseconds
		}
 
		if (millisecs == 100)
		{
			secs++;//increases the seconds
			millisecs = 0;//millisecs reset
		}
 
		if (secs == 60)
		{
			mins++;
			secs= 0;
			millisecs = 0;
		}
		if (mins == 60)//if over an hour, reset whole clock
		{
			mins = 0;
			secs = 0;
			millisecs = 0;
		}
 
		//display the timer and the clock on the top left
		display.setText(
				dFormat.format(mins) + ":" + 
				dFormat.format(secs) + ":" + 
				dFormat.format(millisecs) + "                               Lives:"+ dFormat.format(lives));
		
		display.setFont(normalFont);

		//checks collisions with each falling block
		if(y1>leftPadY-25 && y1 <leftPadY+20 && x1>leftPadX-20 && x1<leftPadX+20){
			if(isLeftCollect == true )//if left box is collecting, and a block touches it,
			y1 = getRandY();//it will go back to the top, negative y coordinate so it's unseen
			else 
			gameOver();//if left box is colleceting and a block touches it, it will change to game over screen
			
		}
		if(y2>leftPadY-25 && y2 <leftPadY+20 && x2>leftPadX-20 && x2<leftPadX+20){
			if(isLeftCollect == true )
			y2 = getRandY();
			else 
			gameOver();

		}
		if(y3>leftPadY-25 && y3 <leftPadY+20 && x3>leftPadX-20 && x3<leftPadX+20){
			if(isLeftCollect == true )
			y3 = getRandY();
			else 
			gameOver();

		}
		if(y4>leftPadY-25 && y4 <leftPadY+20 && x4>leftPadX-20 && x4<leftPadX+20){
			if(isLeftCollect == true )
			y4 = getRandY();
			else 
			gameOver();

		}
		if(y5>leftPadY-25 && y5 <leftPadY+20 && x5>leftPadX-20&& x5<leftPadX+20){
			if(isLeftCollect == true )
			y5 = getRandY();
			else 
			gameOver();

		}
		
		if(y6>rightPadY-25 && y6 <rightPadY+20 && x6>rightPadX-20 && x6<rightPadX+20){
			if(isRightCollect == true )//if right box is colleceting and a block touches it, the block will go back to the top, negative y coordinate
			y6 = getRandY();
			else 
			gameOver();//if right box is colleceting and a block touches it, it will change to game over screen

		}
		if(y7>rightPadY-25 && y7 < rightPadY+20  && x7>rightPadX-20 && x7<rightPadX+20){
			if(isRightCollect == true )
			y7 = getRandY();
			else 
			gameOver();

		}
		if(y8>rightPadY-25 && y8 < rightPadY+20&& x8>rightPadX-20 && x8<rightPadX+20){
			if(isRightCollect == true )
			y8 = getRandY();
			else 
			gameOver();

		}
		if(y9>rightPadY-25 && y9 < rightPadY+20  && x9>rightPadX-20 && x9<rightPadX+20){
			if(isRightCollect == true )
			y9 = getRandY();
			else 
			gameOver();

		}
		if(y10>rightPadY-25&& y10 < rightPadY+20  && x10>rightPadX-20 && x10<rightPadX+20){
			if(isRightCollect == true )
			y10 = getRandY();
			else 
			gameOver();

		}
		
		
		//different difficulties that will happen at a set time
		if(secs ==7 && mins ==0 && millisecs>30 && millisecs<70){//blink 1
			signToSwitch = true;
			
		}
		if(secs ==8 && mins ==0){
			signToSwitch = false;
			
		}
		if(secs ==8 && mins ==0 && millisecs>30 && millisecs<70){//blink 2
			signToSwitch = true;
			
		}
		if(secs ==9 && mins ==0){
			signToSwitch = false;
			
		}
		if(secs ==9 && mins ==0 && millisecs>30 && millisecs<70){//blink 3
			signToSwitch = true;
			
		}
		if(secs ==10 && mins ==0){//switch
			isLeftCollect = false;
			isRightCollect = true;
			signToSwitch = false;
			
		}
		
		if (secs == 20 && mins ==0){//speed up
			speed = 2;
		}
		
		if(secs ==27 && mins ==0 && millisecs>30 && millisecs<70){//blink 1
			signToSwitch = true;
			
		}
		if(secs ==28 && mins ==0){
			signToSwitch = false;
			
		}
		if(secs ==28 && mins ==0 && millisecs>30 && millisecs<70){//blink 2
			signToSwitch = true;
		}
		if(secs ==29 && mins ==0){
			signToSwitch = false;
			
		}
		if(secs ==29 && mins ==0 && millisecs>30 && millisecs<70){//blink 3
			signToSwitch = true;
			
		}
		if(secs ==30 && mins ==0){//switch
			isLeftCollect = true;
			isRightCollect = false;
			signToSwitch = false;
			
		}
		if(secs == 40 && mins ==0){//speed up
			speed = 3;
		}
		
		if(secs ==47 && mins ==0 && millisecs>30 && millisecs<70){//blink 1
			signToSwitch = true;
			
		}
		if(secs ==48 && mins ==0){
			signToSwitch = false;
			
		}
		if(secs ==48 && mins ==0 && millisecs>30 && millisecs<70){//blink 2
			signToSwitch = true;
			
		}
		if(secs ==49 && mins ==0){
			signToSwitch = false;
			
		}
		if(secs ==49 && mins ==0 && millisecs>30 && millisecs<70){//blink 3
			signToSwitch = true;
			
		}
		if(secs ==50 && mins ==0){//switch
			isLeftCollect = false;
			isRightCollect = true;
			signToSwitch = false;
			
		}
		if(secs ==50 && mins ==0){//speed up
			speed = 4;
		}
		if(mins ==1){
			speed =5;//speed up
		}

		//if lives reach 0, change to game over screen
		if(lives<=0){
			gameOver();
		}
		
		//reset the y coordinates of the falling blocks if they touch the bottom
		if(y1>700){
			if(isLeftCollect ==true){
				lives--;
				isHurt=true;
			}
			x1 = getRandLeftX();
			y1 = -5;
		}
		if(y2>700){
			if(isLeftCollect ==true){
				lives--;
				isHurt=true;
			}
			x2 = getRandLeftX();
			y2 = -5;
		}
		if(y3>700){
			if(isLeftCollect ==true){
				lives--;
				isHurt=true;
			}
			x3 = getRandLeftX();
			y3 = -5;
		}
		if(y4>700){
			if(isLeftCollect ==true){
				lives--;
				isHurt=true;
			}
			x4 = getRandLeftX();
			y4 = -5;
		}
		if(y5>700){
			if(isLeftCollect ==true){
				lives--;
				isHurt=true;
			}
			x5 = getRandLeftX();
			y5 = -5;
		}
		if(y6>700){
			if(isRightCollect ==true){
				lives--;
				isHurt=true;
			}
			x6 = getRandRightX();
			y6 = -5;
		}
		if(y7>700){
			if(isRightCollect ==true){
				lives--;
				isHurt=true;
			}
			x7 = getRandRightX();
			y7 = -5;
		}
		if(y8>700){
			if(isRightCollect ==true){
				lives--;
				isHurt=true;
			}
			x8 = getRandRightX();
			y8 = -5;
		}
		if(y9>700){
			if(isRightCollect ==true){
				lives--;
				isHurt=true;
			}
			x9 = getRandRightX();
			y9 = -5;
		}
		if(y10>700){
			if(isRightCollect ==true){
				lives--;
				isHurt=true;
			}
			x10= getRandRightX();
			y10 = -5;
		}
		
		repaint();//updates the whole window by calling paint and sees if any changes need to be made
	
	
	}
	
	//calls a new window game over which changes screen to game over
	public void gameOver(){
		frame.dispose();
		gameOverScreen gameOver = new gameOverScreen(mins, secs, millisecs);
		JFrame gameOverWindow = new JFrame();
		gameOverWindow.setTitle("SPLIT");
		gameOverWindow.setContentPane(gameOverWindow);
		gameOverWindow.setSize(1000, 700);
		gameOverWindow.setResizable(false);
		gameOverWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameOver.start();
		gameOverWindow.setVisible(true);
		gameRunning = false;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		switch (code) {
		case KeyEvent.VK_LEFT:
			keys.add("LEFT");
			break;
		case KeyEvent.VK_RIGHT:
			keys.add("RIGHT");
			break;
		case KeyEvent.VK_UP:
			keys.add("UP");
			break;
		case KeyEvent.VK_DOWN:
			keys.add("DOWN");
			break;
		case KeyEvent.VK_F:
			keys.add("F");
			break;
		case KeyEvent.VK_H:
			keys.add("H");
			break;
		case KeyEvent.VK_T:
			keys.add("T");
			break;
		case KeyEvent.VK_G:
			keys.add("G");
			break;
		}			
	
				
	}
	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		switch (code) {
		case KeyEvent.VK_LEFT:
			keys.remove("LEFT");
			break;
		case KeyEvent.VK_RIGHT:
			keys.remove("RIGHT");
			break;
		case KeyEvent.VK_UP:
			keys.remove("UP");
			break;
		case KeyEvent.VK_DOWN:
			keys.remove("DOWN");
			break;
		case KeyEvent.VK_F:
			keys.remove("F");
			break;
		case KeyEvent.VK_H:
			keys.remove("H");
			break;
		case KeyEvent.VK_T:
			keys.remove("T");
			break;
		case KeyEvent.VK_G:
			keys.remove("G");
			break;	
		
		}
	}
}
