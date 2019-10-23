package graphic;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import general.Metrics;
import graphic.DrawGraph;

public class Graphic extends JFrame {
	
	public int inicialNumberTags;
	public int incrementTagsBy;
	public int maxNumberTags;
	public int choosenEstimators;
	
	public List<Metrics> lbMetrics;
	public List<Metrics> elMetrics;
	public List<Metrics> ilcmMetrics;

	public List<Metrics> vaMetrics;


    public Graphic(int choosenEstimators,int incTagsBy, int iniNumberTags, int maxNumTags) {
        
        this.incrementTagsBy = incTagsBy;
		this.inicialNumberTags = iniNumberTags;
		this.maxNumberTags = maxNumTags;
		this.choosenEstimators = choosenEstimators;
        
        if(choosenEstimators==1) {
			this.lbMetrics = new ArrayList<Metrics>();
		}else if(choosenEstimators==2) {
			this.elMetrics = new ArrayList<Metrics>();
		}else {
			this.lbMetrics = new ArrayList<Metrics>();
			this.elMetrics = new ArrayList<Metrics>();
			this.ilcmMetrics = new ArrayList<Metrics>();

			this.vaMetrics = new ArrayList<Metrics>();

		}
		
    }

    private XYDataset createDatasetEmpty() {
    	XYSeries series1 = new XYSeries("LowerBound");
    	XYSeries series2 = new XYSeries("Eomlee");
    	XYSeries series3 = new XYSeries("ILCM");

    	XYSeries series4 = new XYSeries("Vahedi");
r
    	
    	int numberTags = this.inicialNumberTags;
    	
    	for(int i=0;numberTags<=this.maxNumberTags;i++) {
    		
    			if(this.choosenEstimators==1) {
    				series1.add(numberTags, this.lbMetrics.get(i).getNumberEmptySlots());
    			}else if(this.choosenEstimators==2) {
    				series2.add(numberTags, this.elMetrics.get(i).getNumberEmptySlots());
    			}else {
    				series1.add(numberTags, this.lbMetrics.get(i).getNumberEmptySlots());
    	    		series2.add(numberTags, this.elMetrics.get(i).getNumberEmptySlots());
    	    		series3.add(numberTags, this.ilcmMetrics.get(i).getNumberEmptySlots());
            
    	    		series4.add(numberTags, this.vaMetrics.get(i).getNumberEmptySlots());

    			}
			
			numberTags+=this.incrementTagsBy;
		}        

        XYSeriesCollection dataset = new XYSeriesCollection();
        
        if(this.choosenEstimators!=2)
        	dataset.addSeries(series1);
        
        if(this.choosenEstimators!=1)
        	dataset.addSeries(series2);
        

        if(this.choosenEstimators!=1 && this.choosenEstimators!=2) {
        	dataset.addSeries(series3);
        	dataset.addSeries(series4);
        }
        	

        if(this.choosenEstimators!=1 && this.choosenEstimators!=2)
        	dataset.addSeries(series3);

        
        return dataset;
    }
    
    private XYDataset createDatasetCollision() {
    	XYSeries series1 = new XYSeries("LowerBound");
    	XYSeries series2 = new XYSeries("Eomlee");
    	XYSeries series3 = new XYSeries("ILCM");

    	XYSeries series4 = new XYSeries("Vahedi");

    	
    	int numberTags = this.inicialNumberTags;
    	
    	for(int i=0;numberTags<=this.maxNumberTags;i++) {
    		
    		if(this.choosenEstimators==1) {
				series1.add(numberTags, this.lbMetrics.get(i).getNumberCollisionSlots());
			}else if(this.choosenEstimators==2) {
				series2.add(numberTags, this.elMetrics.get(i).getNumberCollisionSlots());
			}else {
				series1.add(numberTags, this.lbMetrics.get(i).getNumberCollisionSlots());
	    		series2.add(numberTags, this.elMetrics.get(i).getNumberCollisionSlots());
	    		series3.add(numberTags, this.ilcmMetrics.get(i).getNumberCollisionSlots());

	    		series4.add(numberTags, this.vaMetrics.get(i).getNumberCollisionSlots());

			}
			
			numberTags+=this.incrementTagsBy;
		}        

        XYSeriesCollection dataset = new XYSeriesCollection();
        
        if(this.choosenEstimators!=2)
        	dataset.addSeries(series1);
        
        if(this.choosenEstimators!=1)
        	dataset.addSeries(series2);
        

        if(this.choosenEstimators!=1 && this.choosenEstimators!=2) {
        	dataset.addSeries(series3);
        	dataset.addSeries(series4);
        }

        if(this.choosenEstimators!=1 && this.choosenEstimators!=2)
        	dataset.addSeries(series3);

        return dataset;
    }
    
    private XYDataset createDatasetEfficiency() {
    	XYSeries series1 = new XYSeries("LowerBound");
    	XYSeries series2 = new XYSeries("Eomlee");
    	XYSeries series3 = new XYSeries("ILCM");

    	XYSeries series4 = new XYSeries("Vahedi");

    	
    	int numberTags = this.inicialNumberTags;
    	
    	for(int i=0;numberTags<=this.maxNumberTags;i++) {
    		
    		if(this.choosenEstimators==1) {
				series1.add(numberTags, this.lbMetrics.get(i).getEfficiency());
			}else if(this.choosenEstimators==2) {
				series2.add(numberTags, this.elMetrics.get(i).getEfficiency());
			}else {
				series1.add(numberTags, this.lbMetrics.get(i).getEfficiency());
	    		series2.add(numberTags, this.elMetrics.get(i).getEfficiency());
	    		series3.add(numberTags, this.ilcmMetrics.get(i).getEfficiency());

	    		series4.add(numberTags, this.vaMetrics.get(i).getEfficiency());


			}
			
			numberTags+=this.incrementTagsBy;
		}        

        XYSeriesCollection dataset = new XYSeriesCollection();
        
        if(this.choosenEstimators!=2)
        	dataset.addSeries(series1);
        
        if(this.choosenEstimators!=1)
        	dataset.addSeries(series2);
        

        if(this.choosenEstimators!=1 && this.choosenEstimators!=2) {
        	dataset.addSeries(series3);
        	dataset.addSeries(series4);
        }

        if(this.choosenEstimators!=1 && this.choosenEstimators!=2)
        	dataset.addSeries(series3);


        return dataset;
    }
    
    private XYDataset createDatasetEstimatorTime() {
    	XYSeries series1 = new XYSeries("LowerBound");
    	XYSeries series2 = new XYSeries("Eomlee");
    	XYSeries series3 = new XYSeries("ILCM");

    	XYSeries series4 = new XYSeries("Vahedi");
    	
    	int numberTags = this.inicialNumberTags;
    	
    	for(int i=0;numberTags<=this.maxNumberTags;i++) {
    		
    		if(this.choosenEstimators==1) {
    			
    			//System.out.println(this.lbMetrics.get(i).getEstimatorTime());
				series1.add(numberTags,this.lbMetrics.get(i).getEstimatorTime());
			}else if(this.choosenEstimators==2) {
				
				//System.out.println(this.elMetrics.get(i).getEstimatorTime());
				series2.add(numberTags,this.elMetrics.get(i).getEstimatorTime());
			}else {
				
				//System.out.println(this.lbMetrics.get(i).getEstimatorTime());
				//System.out.println(this.elMetrics.get(i).getEstimatorTime());
				series1.add(numberTags, this.lbMetrics.get(i).getEstimatorTime());
	    		series2.add(numberTags, this.elMetrics.get(i).getEstimatorTime());
	    		series3.add(numberTags, this.ilcmMetrics.get(i).getEstimatorTime());

	    		series4.add(numberTags, this.vaMetrics.get(i).getEstimatorTime());

			}
			
			numberTags+=this.incrementTagsBy;
		}        

        XYSeriesCollection dataset = new XYSeriesCollection();
        
        if(this.choosenEstimators!=2)
        	dataset.addSeries(series1);
        
        if(this.choosenEstimators!=1)
        	dataset.addSeries(series2);
        

        if(this.choosenEstimators!=1 && this.choosenEstimators!=2) {
        	dataset.addSeries(series3);
        	dataset.addSeries(series4);
        }

        if(this.choosenEstimators!=1 && this.choosenEstimators!=2)
        	dataset.addSeries(series3);

        return dataset;
    }
    
    private XYDataset createDatasetTotalSlots() {
    	XYSeries series1 = new XYSeries("LowerBound");
    	XYSeries series2 = new XYSeries("Eomlee");
    	XYSeries series3 = new XYSeries("ILCM");

    	XYSeries series4 = new XYSeries("Vahedi");
    	
    	int numberTags = this.inicialNumberTags;
    	
    	for(int i=0;numberTags<=this.maxNumberTags;i++) {
    		
    		if(this.choosenEstimators==1) {
				series1.add(numberTags, this.lbMetrics.get(i).getNumberTotalSlots());
			}else if(this.choosenEstimators==2) {
				series2.add(numberTags, this.elMetrics.get(i).getNumberTotalSlots());
			}else {
				series1.add(numberTags, this.lbMetrics.get(i).getNumberTotalSlots());
	    		series2.add(numberTags, this.elMetrics.get(i).getNumberTotalSlots());
	    		series3.add(numberTags, this.ilcmMetrics.get(i).getNumberTotalSlots());

	    		series4.add(numberTags, this.vaMetrics.get(i).getNumberTotalSlots());

			}
			
			numberTags+=this.incrementTagsBy;
		}        

        XYSeriesCollection dataset = new XYSeriesCollection();
        
        if(this.choosenEstimators!=2)
        	dataset.addSeries(series1);
        
        if(this.choosenEstimators!=1)
        	dataset.addSeries(series2);
        

        if(this.choosenEstimators!=1 && this.choosenEstimators!=2) {
        	dataset.addSeries(series3);
        	dataset.addSeries(series4);
        }

        if(this.choosenEstimators!=1 && this.choosenEstimators!=2)
        	dataset.addSeries(series3);

        return dataset;
    }
    
    
    public void plotGraphic() throws IOException {
    	DrawGraph plotTotalSlots=new DrawGraph(createDatasetTotalSlots(),"Numero de Slots");
    	plotTotalSlots.setVisible(true);
    	
    	DrawGraph plotEfficiency=new DrawGraph(createDatasetEfficiency(),"Eficiencia");
    	plotEfficiency.setVisible(true);
    	
    	DrawGraph plotSlotsVazio=new DrawGraph(createDatasetEmpty(),"Numero de Slots Vazios");
    	plotSlotsVazio.setVisible(true);
    	
    	DrawGraph plotColisao=new DrawGraph(createDatasetCollision(),"Numero de Slots em Colisoees");
    	plotColisao.setVisible(true);
    	
    	DrawGraph plotTime=new DrawGraph(createDatasetEstimatorTime(),"Tempo do simulador");
    	plotTime.setVisible(true);
       }
    
}