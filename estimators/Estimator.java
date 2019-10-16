package estimators;

import java.util.ArrayList;
import java.util.List;

import general.Metrics;
import general.Tag;

public abstract class Estimator {
	
	protected int numberTags;
	protected int frameSize;
	protected int[] frame;
	protected List<Tag> tags;
	protected Metrics metrics;
	protected int totalFrames;
	
	public Metrics getMetrics() {
		return this.metrics;
	}

	public void setMetrics(Metrics metrics) {
		this.metrics = metrics;
	}
	
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
		this.frameSize = frameSize;
		this.frame = new int [frameSize];
	}
	
	public Estimator (int numberTags, int frameSize) {
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

	public abstract void simulate();
	

}
