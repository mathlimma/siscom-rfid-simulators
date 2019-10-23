package estimators;

import java.lang.Math;
import general.Tag;

public class ILCM extends Estimator {
	
	public ILCM(int numberTags, int frameSize) {
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
			
			for(int i=0;i<frameSize;i++) {
				if(this.frame[i]==0) {
					numEmptySlots++;
				}else if(this.frame[i]==1) {
					numSucessSlots++;
					this.tags.remove(0);
				}else {
					numCollisionSlots++;
				}
			}
			
			this.setNumberEmptySlots(this.getNumberEmptySlots()+numEmptySlots);
			this.setNumberSucessSlots(this.getNumberSucessSlots()+numSucessSlots);
			this.setNumberCollisionSlots(this.getNumberCollisionSlots()+numCollisionSlots);
			
			this.frameSize = this.calculateNextFrameSize(numEmptySlots,numCollisionSlots,numSucessSlots);
			this.resetFrame(this.frameSize);
			this.totalFrames++;
		}
		
		this.setEfficiecy(this.getNumberSucessSlots()/this.getNumberTotalSlots());
		time = System.currentTimeMillis()-time;
		this.metrics.setSimulatorTime(time+this.metrics.getSimulatorTime());
	}
	
	public int calculateNextFrameSize (double E, double C, double S) {
		long time = System.currentTimeMillis();
		
		double L = E+S+C;
		double l = (1.2592+(1.513*L))*Math.tan(1.234*(Math.pow(L, -0.9907))*C);
		double k = C/((4.344*L-16.28)+((L/(-2.282-0.273*L))*C))+0.2407*Math.log(L+42.56);
		
		if(k<0) {
			k=0;
		}
		double res=k*S+l;
		if(C==0){
			res=S;
		}
		
		time = System.currentTimeMillis()-time;
		this.metrics.setEstimatorTime(time+this.metrics.getEstimatorTime());
		return (int)Math.ceil(res);
		
	}
	

	public static void main(String[] args) {
		
		
	}

}
