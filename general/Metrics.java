package general;

public class Metrics {
	
	private double numberEmptySlots;
	private double numberCollisionSlots;
	private double numberSucessSlots;
	private int numberTotalSlots;
	private double efficiency;
	private long estimatorTime;
	
	public Metrics (double numberEmptySlots, double numberCollisionSlots, double numberSucessSlots,
			int numberTotalSlots, double efficiency, long estimatorTime) {
		
		this.numberEmptySlots = numberEmptySlots;
		this.numberCollisionSlots = numberCollisionSlots;
		this.numberSucessSlots = numberSucessSlots;
		this.numberTotalSlots = numberTotalSlots;
		this.efficiency = efficiency;
		this.estimatorTime = estimatorTime;
	}
	
	public Metrics () {
		
		this.numberEmptySlots = 0.0;
		this.numberCollisionSlots = 0.0;
		this.numberSucessSlots = 0.0;
		this.numberTotalSlots = 0;
		this.efficiency = 0.0;
		this.estimatorTime = 0;
	}
	
	public Metrics sumMetrics(Metrics m2) {
		
		return new Metrics(this.numberEmptySlots+m2.numberEmptySlots,this.numberCollisionSlots+m2.numberCollisionSlots,
				this.numberSucessSlots+m2.numberSucessSlots, this.numberTotalSlots+m2.numberTotalSlots,this.efficiency+m2.efficiency, this.estimatorTime+m2.estimatorTime);
	}
	
	public Metrics divByNumberRepetitions(int numRepet) {
		return new Metrics(this.numberEmptySlots/numRepet,this.numberCollisionSlots/numRepet,
				this.numberSucessSlots/numRepet, this.numberTotalSlots/numRepet,this.efficiency/numRepet,this.estimatorTime/numRepet);
	}
	
	public double getNumberEmptySlots() {
		return numberEmptySlots;
	}

	public void setNumberEmptySlots(double numberEmptySlots) {
		this.numberEmptySlots = numberEmptySlots;
	}

	public double getNumberCollisionSlots() {
		return numberCollisionSlots;
	}

	public void setNumberCollisionSlots(double numberCollisionSlots) {
		this.numberCollisionSlots = numberCollisionSlots;
	}

	public double getNumberSucessSlots() {
		return numberSucessSlots;
	}

	public void setNumberSucessSlots(double numberSucessSlots) {
		this.numberSucessSlots = numberSucessSlots;
	}

	public int getNumberTotalSlots() {
		return numberTotalSlots;
	}

	public void setNumberTotalSlots(int numberTotalSlots) {
		this.numberTotalSlots = numberTotalSlots;
	}

	public double getEfficiency() {
		return efficiency;
	}

	public void setEfficiency(double efficiency) {
		this.efficiency = efficiency;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public long getEstimatorTime() {
		return estimatorTime;
	}

	public void setEstimatorTime(long estimatorTime) {
		this.estimatorTime = estimatorTime;
	}

}
