
public class Projectile {
	private int centerX, centerY, speedX;
	private boolean visible;

	public Projectile(int startX, int startY) {
		centerX = startX;
		centerY = startY;
		speedX = 7;
		visible = true;		
	}
	
	public void update(){
		centerX += speedX;
		if(centerX >= 800){
			visible = false;
		}
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public int getSpeedX() {
		return speedX;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
