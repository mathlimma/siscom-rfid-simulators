package estimators;
import general.Tag;

public class LowerBound extends Estimator {
	
	
	public LowerBound(int numberTags, int frameSize) {
	super(numberTags, frameSize);
	}
	
	
	public void simulate () {
		long time = System.currentTimeMillis();
		
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
		time = System.currentTimeMillis()-time;
		this.metrics.setSimulatorTime(time+this.metrics.getSimulatorTime());
	}

	public static void main(String[] args) {
		
		
	}

}
