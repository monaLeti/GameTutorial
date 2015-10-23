import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;
import game.framework.Animation;


public class StartingClass extends Applet implements Runnable, KeyListener{
	
	private Robot robot;
	
	private ArrayList <Image>imagesHeliboy;

	private int i;
	
	private Heliboy hb, hb2;
	private Image image, currentSprite, character, character2, character3, background, down, jumped, heliboy, heliboy2, heliboy3, heliboy4, heliboy5;
	private Graphics second;
	private URL base;
	private static Background bg1, bg2;
	private Animation anim, hanim;
	
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
		
		character = getImage(base, "data/character1.png");
		character2 = getImage(base, "data/character2.png");
		character3 = getImage(base, "data/character3.png");
		
		down = getImage(base, "data/down1.png");
		jumped = getImage(base, "data/jumped1.png");
		
		heliboy = getImage(base, "data/heliboy.png");
		heliboy2 = getImage(base, "data/heliboy2.png");
		heliboy3 = getImage(base, "data/heliboy3.png");
		heliboy4 = getImage(base, "data/heliboy4.png");
		heliboy5 = getImage(base, "data/heliboy5.png");
		
		background = getImage(base, "data/background.png");
		
		
		currentSprite = character;
	}
	
	@Override
	public void start(){
		i=0;
		imagesHeliboy=new ArrayList<Image>();
		imagesHeliboy.add(heliboy);
		imagesHeliboy.add(heliboy2);
		imagesHeliboy.add(heliboy3);
		imagesHeliboy.add(heliboy4);
		imagesHeliboy.add(heliboy5);
		imagesHeliboy.add(heliboy4);
		imagesHeliboy.add(heliboy3);
		imagesHeliboy.add(heliboy2);
		
		
		
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
		
	}
	
	@Override
	public void destroy(){
		
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
			ArrayList projectiles = robot.getProjectiles();
			for(int i =0; i<projectiles.size(); i++){
				Projectile p = (Projectile) projectiles.get(i);
				if(p.isVisible()==true){
					p.update();
				}else{
					projectiles.remove(i);
				}
			}
			
			hb.update();
			hb2.update();
			bg1.update();
			bg2.update();
//			animate();
			repaint();
			try{
				Thread.sleep(17);
			}catch(InterruptedException e){
				e.printStackTrace();
				
			}
		}
		
	}
//	public void animate(){
//		anim.update(10);
//		hanim.update(50);
//	}
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
		
		ArrayList projectiles = robot.getProjectiles();
		for(int i =0; i<projectiles.size(); i++){
			Projectile p = (Projectile) projectiles.get(i);
			g.setColor(Color.YELLOW);
			g.fillRect(p.getCenterX(), p.getCenterY(), 10, 5);
		}
		g.drawImage(currentSprite, robot.getCenterX()-61, robot.getCenterY()-63, this);
		
		g.drawImage(imagesHeliboy.get(i), hb.getCenterX(), hb.getCenterY(), this);
		if(hb.isChange()==false){
			i++;
			if(i==imagesHeliboy.size()){
				i=0;
			}
			g.drawImage(imagesHeliboy.get(i), hb.getCenterX(), hb.getCenterY(), this);
			hb.setChange(true);
		}
		g.drawImage(imagesHeliboy.get(i), hb2.getCenterX(), hb2.getCenterY(), this);
		if(hb2.isChange()==false){
			i++;
			if(i==imagesHeliboy.size()){
				i=0;
			}
			g.drawImage(imagesHeliboy.get(i), hb2.getCenterX(), hb2.getCenterY(), this);
			hb2.setChange(true);
		}
		
		
		
		
		
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
		
		case KeyEvent.VK_CONTROL:
			if(robot.isDucked()== false && robot.isJumped() == false){
				robot.shoot();
			}
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
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public static Background getBg1(){
		return bg1;
	}
	public static Background getBg2(){
		return bg2;
	}
}
