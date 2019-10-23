package estimators;

import java.lang.Math;
import general.Tag;

public class EomLee extends Estimator {
	
	public EomLee(int numberTags, int frameSize) {
		super(numberTags, frameSize);
	}
	
	
	public void simulate () {
		long time = System.currentTimeMillis();
		
		int numCollisionSlots=0;
		int numSucessSlots=0;
		int numEmptySlots=0;
		
		while(!this.tags.isEmpty()){
			numCollisionSlots=0;
			numSucessSlots=0;
			numEmptySlots=0;
			
			this.setNumberTotalSlots(this.frameSize+this.getNumberTotalSlots());
			
			for(Tag tg : this.tags) {
				this.frame[tg.getRandomNumber(this.frameSize)]++;
			}
			
			int frameCollisions=0;
			for(int i=0;i<frameSize;i++) {
				if(this.frame[i]==0) {
					numEmptySlots++;
				}else if(this.frame[i]==1) {
					numSucessSlots++;
					this.tags.remove(0);
				}else {
					numCollisionSlots++;
					frameCollisions++;
				}
			}
			
			this.setNumberEmptySlots(this.getNumberEmptySlots()+numEmptySlots);
			this.setNumberSucessSlots(this.getNumberSucessSlots()+numSucessSlots);
			this.setNumberCollisionSlots(this.getNumberCollisionSlots()+numCollisionSlots);
			
			this.frameSize = this.calculateNextFrameSize(this.frameSize,numCollisionSlots,numSucessSlots);
			this.resetFrame(this.frameSize);
			this.totalFrames++;
		}
		
		this.setEfficiecy(this.getNumberSucessSlots()/this.getNumberTotalSlots());
		time = System.currentTimeMillis()-time;
		this.metrics.setSimulatorTime(time+this.metrics.getSimulatorTime());
	}
	
	public int calculateNextFrameSize (int f, int c, int s) {
		long time = System.currentTimeMillis();
		
		double B, k1, num, den, frac;
		double k = 2.0;
		do {
			k1=k;
			B= f/((k1*c)+s);
			frac = Math.exp(-(1.0/B));
			num = 1.0 - frac;
			den = B*(1.0-(1.0+(1.0/B))*frac);
			k = num/den;
			
		} while(Math.abs(k1-k)>=0.001);
		time = System.currentTimeMillis()-time;
		this.metrics.setEstimatorTime(time+this.metrics.getEstimatorTime());
		return (int)Math.ceil(k*c);
	}
	

	public static void main(String[] args) {
		
		
	}

}
