
public class Enemy {
	private final long MAXTIME = 100;
	private int maxHealth, currentHealth, power, speedX, centerX, centerY;
	private long time;
	private boolean change;
	private Background bg = StartingClass.getBg1(); //The enemy has to move in the same direction as the background
	
	public void update(){
		time +=10;
		if(time >= MAXTIME){
			time =0;
			if(isChange()==true){
				change = false;
			}
		}
		centerX += speedX;
		speedX = bg.getSpeedX();
	}
	public void die(){
		
	}
	public void attack(){
		
	}
	public int getMaxHealth() {
		return maxHealth;
	}
	public int getCurrentHealth() {
		return currentHealth;
	}
	public int getPower() {
		return power;
	}
	public int getSpeedX() {
		return speedX;
	}
	public int getCenterX() {
		return centerX;
	}
	public int getCenterY() {
		return centerY;
	}
	public Background getBg() {
		return bg;
	}
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}
	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}
	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}
	public void setBg(Background bg) {
		this.bg = bg;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public boolean isChange() {
		return change;
	}
	public void setChange(boolean change) {
		this.change = change;
	}
}
