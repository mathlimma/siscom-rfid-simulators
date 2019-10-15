package estimators;

import java.lang.Math;
import general.Tag;

public class EomLee extends Estimator {
	
	private double B;
	private double Y;
	private double _Y;  // Yk-1
	
	public EomLee(int numberTags, int frameSize) {
		super(numberTags, frameSize);
		this.B= Double.POSITIVE_INFINITY;
		this.Y= 2;
		this._Y= 2;
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
			
			this.frameSize = this.calculateNextFrameSize();
			this.resetFrame(this.frameSize);
			this.totalFrames++;
		}
		
		this.setEfficiecy(this.getNumberSucessSlots()/this.getNumberTotalSlots());
		this.setNumberCollisionSlots(this.getNumberCollisionSlots());
		this.setNumberEmptySlots(this.getNumberEmptySlots());
		this.setNumberSucessSlots(this.getNumberSucessSlots());
	}
	
	public int calculateNextFrameSize () {
		
		if(this.Y<=0.001)
			return (int) Math.ceil(this.Y*this.getNumberCollisionSlots());
		
		this.B=this.calculateB();
		this.Y=this.calculateY();
		
		return (int)Math.ceil(this.Y*this.getNumberCollisionSlots());
	}
	
	public double calculateB() {
		
		return this.frameSize/(_Y*this.getNumberCollisionSlots()+this.getNumberSucessSlots());
	}
	
	public double calculateY() {
		
		this._Y=this.Y;
		
		if(this.totalFrames==0)
			return this.Y;
		
		double nom = 1.0 - Math.exp(-(1.0/this.B));
		double dem = this.B*(1.0-(1.0+(1/this.B))*Math.exp(-(1.0/this.B)));
		double result = nom/dem;
		return result;
	}

	public static void main(String[] args) {
		
		
	}

}
