import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;


public class StartingClass extends Applet implements Runnable, KeyListener{
	
	private Robot robot;
	private Heliboy hb, hb2;
	private Image image, currentSprite, character, background, down, jumped, heliboy;
	private Graphics second;
	private URL base;
	private static Background bg1, bg2;
	
	@Override
	public void init(){
		setSize(800,480);
		setBackground(Color.black);
		setFocusable(true);
		addKeyListener(this); //maybe you have to change the position
		
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Q-Bot Alpha");		
		
		try{
			base = getDocumentBase();
		}catch(Exception e){
			System.err.println("Error picture");
		}
		
		character = getImage(base, "data/character.png");
		down = getImage(base, "data/down.png");
		jumped = getImage(base, "data/jumped.png");
		heliboy = getImage(base, "data/walk.png");
		
		currentSprite = character;
		background = getImage(base, "data/background.png");
	}
	
	@Override
	public void start(){
		
		bg1 = new Background(0, 0);
		bg2 = new Background(2160, 0);
		hb = new Heliboy(340, 360);
		hb2 = new Heliboy(700,360);
		robot =  new Robot(); //Always below
		Thread thread = new Thread(this);
		thread.start();
		
		
	}
	
	@Override
	public void stop(){
		super.stop();
	}
	
	@Override
	public void destroy(){
		super.destroy();
	}

	@Override
	public void run() {
		while(true){
			robot.update();
			if(robot.isJumped()){
				currentSprite = jumped;
			}else if(robot.isJumped() == false && robot.isDucked()==false){
				currentSprite = character;
			}
			hb.update();
			hb2.update();
			bg1.update();
			bg2.update();
			repaint();
			try{
				Thread.sleep(17);
			}catch(InterruptedException e){
				e.printStackTrace();
				
			}
		}
		
	}
	@Override
	public void update(Graphics g){
		if(image == null){
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}
		
		second.setColor(getBackground());
		second.fillRect(0,0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);
		
		
		g.drawImage(image, 0, 0, this);
	}
	
	@Override
	public void paint(Graphics g){
		g.drawImage(background, bg1.getbX(), bg1.getbY(), this);
		g.drawImage(background, bg2.getbX(), bg2.getbY(), this);
		g.drawImage(heliboy, hb.getCenterX()-48, hb.getCenterY()-48,this);
		g.drawImage(heliboy, hb2.getCenterX()-48, hb2.getCenterY()-48,this);
		g.drawImage(currentSprite, robot.getCenterX()-61, robot.getCenterY()-63, this);
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		
		switch(e.getKeyCode()){
		
		case KeyEvent.VK_UP:
			System.out.println("Move up");
			break;
		
		case KeyEvent.VK_DOWN:
			currentSprite = down;
			if(robot.isJumped() == false){
				robot.setDucked(true);
				robot.setSpeedX(0);
			}
			break;
		
		case KeyEvent.VK_LEFT:
			currentSprite = character;
			robot.moveLeft();
			robot.setMovingLeft(true);
			break;
			
		case KeyEvent.VK_RIGHT:
			currentSprite =  jumped;
			robot.moveRight();
			robot.setMovingRight(true);
			break;
			
		case KeyEvent.VK_SPACE:
			robot.jump();
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
switch(e.getKeyCode()){
		
		case KeyEvent.VK_UP:
			System.out.println("Stop moving up");
			break;
		
		case KeyEvent.VK_DOWN:
			currentSprite = character;
			robot.setDucked(false);
			break;
		
		case KeyEvent.VK_LEFT:
			robot.stopLeft();
			break;
			
		case KeyEvent.VK_RIGHT:
			robot.stopRight();
			break;
			
		case KeyEvent.VK_SPACE:
			break;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public static Background getBg1(){
		return bg1;
	}
	public static Background getBg2(){
		return bg2;
	}
}