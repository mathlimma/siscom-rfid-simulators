package control;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import estimators.EomLee;
import estimators.Estimator;
import estimators.ILCM;
import estimators.LowerBound;
import general.Metrics;
import graphic.Graphic;

public class Controller {
	
	private List<Estimator> est; 
	
	private int inicialNumberTags;
	private int incrementTagsBy;
	private int maxNumberTags;
	private int repetitionsEachNumberTags;
	private int inicialFrameSize;
	
	private Metrics metrics;
	
	private Graphic graphic;
	
	public Controller (int iniNumTags, int incTagsBy,
		int maxNumTags, int numRepet, int iniFrameSize, int choosenEstimators) {
		
		this.inicialNumberTags = iniNumTags;
		this.incrementTagsBy = incTagsBy;
		this.maxNumberTags = maxNumTags;
		this.repetitionsEachNumberTags = numRepet;
		this.inicialFrameSize = iniFrameSize;
		
		this.metrics = new Metrics();
		
		this.est = new ArrayList<Estimator>();
		if(choosenEstimators==1) {
			this.est.add(new LowerBound(this.inicialNumberTags,this.inicialFrameSize));
		}else if(choosenEstimators==2) {
			this.est.add(new EomLee(this.inicialNumberTags,this.inicialFrameSize));
		}else {
			this.est.add(new LowerBound(this.inicialNumberTags,this.inicialFrameSize));
			this.est.add(new EomLee(this.inicialNumberTags,this.inicialFrameSize));
			this.est.add(new ILCM(this.inicialNumberTags, this.inicialFrameSize));
		}
			
		this.graphic = new Graphic(choosenEstimators,this.incrementTagsBy,this.inicialNumberTags,this.maxNumberTags);
		
	}
	
	public Estimator resetEst(Estimator est, int iniNumTags,int iniFrameSize) {
		
		if(est instanceof LowerBound) {
			return new LowerBound(iniNumTags,iniFrameSize);
		}else if(est instanceof EomLee) {
			return new EomLee(iniNumTags,iniFrameSize);
		}
		//return est;
		return new ILCM(iniNumTags, iniFrameSize);
		
	}
	
	private void runEstimator(Estimator est) {
		est.getMetrics().setEstimatorTime(System.currentTimeMillis());
		
		int numberTags = this.inicialNumberTags;
		
		while(numberTags<=this.maxNumberTags) {
			
			est = resetEst(est, numberTags, this.inicialFrameSize); 
			
			for(int i=0;i<this.repetitionsEachNumberTags;i++) {
				est.simulate();
				
				this.metrics=this.metrics.sumMetrics(est.getMetrics());
				est = resetEst(est, numberTags, this.inicialFrameSize);
			}
			
			numberTags+=this.incrementTagsBy;
			this.metrics = this.metrics.divByNumberRepetitions(this.repetitionsEachNumberTags);
			this.metrics.setEstimatorTime(System.currentTimeMillis()-this.metrics.getEstimatorTime());

			if (est instanceof LowerBound) {
				this.graphic.lbMetrics.add(this.metrics);
			}else {
				this.graphic.elMetrics.add(this.metrics);
			}
			
		}
		
	}
	
	public void runEstimators() throws IOException {
		
		for(int i=0;i<this.est.size();i++) { // add threads here later
			runEstimator(this.est.get(i));
		}
		
		//this.graphic.plotGraphic();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
