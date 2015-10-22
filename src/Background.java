
public class Background {
	private int bX;
	private int bY;
	private int speedX;
	
	public Background(int x, int y){
		bX = x;
		bY = y;
		speedX = 0;
	}
	
	public void update (){
		bX +=speedX;
		
		if(bX <= -2160){
			bX += 4320;
		}
	}

	public int getbX() {
		return bX;
	}

	public int getbY() {
		return bY;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setbX(int bX) {
		this.bX = bX;
	}

	public void setbY(int bY) {
		this.bY = bY;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}
}
