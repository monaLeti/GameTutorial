package game.framework;

import java.awt.Image;
import java.util.ArrayList;

public class Animation {
	
	private ArrayList frames;
	private int currentFrame;
	private long animTime; //long takes more memory but it saves more acurately
	private long totalDuration;
	

	public Animation() {
		frames = new ArrayList();
		totalDuration = 0;
		
		synchronized (this){
			animTime = 0;
			currentFrame = 0;
		}
		
	}
	
	public synchronized void addFrame (Image image, long duration){
		totalDuration += duration;
		frames.add(new AnimFrame(image, duration));
	} 
	
	public synchronized void update(long elapsedTime){
		if(frames.size()>1){
			animTime += elapsedTime;
			if(animTime >= totalDuration){
				animTime = animTime % totalDuration;
				currentFrame = 0;
			}
			
			while (animTime > getFrame(currentFrame).endTime){
				currentFrame++;
			}
		}
	}
	
	public synchronized Image getImage(){
		if(frames.size()==0){
			return null;
		}else {
			return getFrame(currentFrame).image;
		}
	}

	private AnimFrame getFrame(int i){
		return (AnimFrame)frames.get(i);
	}
	private class AnimFrame{
		
		Image image;
		long endTime;
		
		public AnimFrame(Image image, long endTime){
			this.image = image;
			this.endTime = endTime;
		}
	}
}
