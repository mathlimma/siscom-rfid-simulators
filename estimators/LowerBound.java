package estimators;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import general.Metrics;


import general.Tag;

public class LowerBound {
	
	private int numberTags;
	private int frameSize;
	private int[] frame;
	private List<Tag> tags;
	private Metrics metrics;
	
	public Metrics getMetrics() {
		return this.metrics;
	}

	public void setMetrics(Metrics metrics) {
		this.metrics = metrics;
	}

	private int totalFrames;
	
	
	
	public int getTotalFrames() {
		return totalFrames;
	}

	public void setTotalFrames(int totalFrames) {
		this.totalFrames = totalFrames;
	}

	public double getEfficiecy() {
		return metrics.getEfficiency();
	}

	public void setEfficiecy(double efficiecy) {
		this.metrics.setEfficiency(efficiecy);
	}

	public double getNumberEmptySlots() {
		return this.metrics.getNumberEmptySlots();
	}

	public void setNumberEmptySlots(double numberEmptySlots) {
		this.metrics.setNumberEmptySlots(numberEmptySlots);
	}

	public double getNumberCollisionSlots() {
		return this.metrics.getNumberCollisionSlots();
	}

	public void setNumberCollisionSlots(double numberCollisionSlots) {
		this.metrics.setNumberCollisionSlots(numberCollisionSlots);
	}

	public double getNumberSucessSlots() {
		return this.metrics.getNumberSucessSlots();
	}

	public void setNumberSucessSlots(double numberSucessSlots) {
		this.metrics.setNumberSucessSlots(numberSucessSlots);
	}

	public int getNumberTotalSlots() {
		return this.metrics.getNumberTotalSlots();
	}

	public void setNumberTotalSlots(int numberTotalSlots) {
		this.metrics.setNumberTotalSlots(numberTotalSlots);
	}

	public int getNumberTags() {
		return numberTags;
	}

	public void setNumberTags(int numberTags) {
		this.numberTags = numberTags;
	}

	public int getFrameSize() {
		return frameSize;
	}

	public void setFrameSize(int frameSize) {
		this.frameSize = frameSize;
	}
	
	public void resetFrame(int frameSize) {
		this.frame = new int [frameSize];
	}
	
	public LowerBound(int numberTags, int frameSize) {
		this.numberTags = numberTags;
		this.frameSize = frameSize;
		this.frame =  new int[frameSize];
		
		this.metrics = new Metrics();
		
		this.tags = new ArrayList<Tag>(numberTags);
		//initializing tags
		 for (int i=0; i< this.numberTags;i++) {
			 this.tags.add(new Tag());
		 }
	}
	public void simulate () {
		
		while(!this.tags.isEmpty()){
			this.setNumberTotalSlots(this.frameSize+this.getNumberTotalSlots());
			
			for(Tag tg : this.tags) {
				this.frame[tg.getRandomNumber(this.frameSize)]++;
			}
			
			int frameCollisions=0;
			for(int i=0;i<frameSize;i++) {
				if(this.frame[i]==0) {
					this.setNumberEmptySlots(this.getNumberEmptySlots()+1);
				}else if(this.frame[i]==1) {
					this.setNumberSucessSlots(this.getNumberSucessSlots()+1);
					this.tags.remove(0);
				}else {
					this.setNumberCollisionSlots(this.getNumberCollisionSlots()+1);
					frameCollisions++;
				}
			}
			
			this.setFrameSize(frameCollisions * 2);
			this.resetFrame(this.frameSize);
			this.totalFrames++;
		}
		
		this.setEfficiecy(this.getNumberSucessSlots()/this.getNumberTotalSlots());
		this.setNumberCollisionSlots(this.getNumberCollisionSlots()/this.totalFrames);
		this.setNumberEmptySlots(this.getNumberEmptySlots()/this.totalFrames);
		this.setNumberSucessSlots(this.getNumberSucessSlots()/this.totalFrames);
	}

	public static void main(String[] args) {
		
		
	}

}
