package estimators;

import java.lang.Math;
import general.Tag;
import java.math.BigInteger;

public class Vahedi extends Estimator {
	
	BigInteger fac[];
	public int facSize;
	
	public Vahedi(int numberTags, int frameSize) {
		super(numberTags, frameSize);
		this.fac = new BigInteger[40];
		this.facSize = 40;
	}
	
	
	public void simulate () {
		this.metrics.setEstimatorTime(System.currentTimeMillis());
		
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
		this.metrics.setEstimatorTime(System.currentTimeMillis()-this.metrics.getEstimatorTime());
	}
	
	private int calculateNextFrameSize(int E, int C, int S){
		int L = E + S + C;
		int n =  S + 2*C;
		double next = 0;
		double previous = -1;
		

		
		while (previous < next) {
			double p1 = Math.pow((1 - (E/L)), n);
			
			double x = this.fac[n]/facMult(new int[]{S,n-S});
			double y = Math.pow((L-E-S), (n-S)) / Math.pow((L-E), n);
			double p2 = x*y*this.fac[S];
			double p3 = 0;

			for(int k = 0; k < C; k++) {
				for(int v = 0; v < C - k; v++) {
					double a = Math.pow(-1, k+v);
		
					double b = this.fac[C]/facMult(new int[]{k,C-k});
	
					double c = this.fac[C-k]/facMult(new int[]{v,(C-k-v)});
					double d = this.fac[n-S]/this.fac[n-S-k];
					double e = Math.pow((C-k-v), (n-S-k))/Math.pow(C, (n-S));
					p3 = p3 + a * b * c * d * e;
				}
			}

			previous = next;
			next = (this.fac[L]/this.fac[E]*this.fac[S]*this.fac[C])*p1*p2*p3;
			n = n + 1;
		}
		
		return n - 2;
	}
	
	public void fac()  { 
	     
	    for (int i=0; i<this.facSize; i++) { 
	       BigInteger factorial = new BigInteger("1");
	       for(int j=1; j<=i+1; j++) { 
	          factorial = factorial.multiply(BigInteger.valueOf(j)); 
	       } 
	      this.fac[i] = factorial; 
	    };
	}
	

	public static void main(String[] args) {
		
		
	}

}
