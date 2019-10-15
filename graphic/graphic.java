package graphic;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.List;
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

import estimators.EomLee;
import estimators.Estimator;
import estimators.LowerBound;
import general.Metrics;

public class graphic extends JFrame {
	
	public int inicialNumberTags;
	public int incrementTagsBy;
	public int maxNumberTags;
	public int choosenEstimators;
	
	public List<Metrics> lbMetrics;
	public List<Metrics> elMetrics;

    public graphic(int choosenEstimators,int incTagsBy, int iniNumberTags, int maxNumTags) {
        
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
		}
		
    }
    
 
    private void initUI(XYDataset dataset, String eixoY) throws IOException {

        JFreeChart chart = createChart (dataset, eixoY);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Line chart");
        setLocationRelativeTo(null);
        
        JFreeChart chart2 = createChart (dataset, eixoY);
        ChartPanel chartPanel2 = new ChartPanel(chart2);
        chartPanel2.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel2.setBackground(Color.white);
        add(chartPanel2);

        pack();
        setTitle("Line chart");
        setLocationRelativeTo(null);
    }

    private XYDataset createDatasetEmpty() {
    	XYSeries series1 = new XYSeries("LowerBound");
    	XYSeries series2 = new XYSeries("Eomlee");
    	
    	int numberTags = this.inicialNumberTags;
    	
    	for(int i=0;numberTags<=this.maxNumberTags;i++) {
    		
    			if(this.choosenEstimators==1) {
    				series1.add(numberTags, this.lbMetrics.get(i).getNumberEmptySlots());
    			}else if(this.choosenEstimators==2) {
    				series2.add(numberTags, this.elMetrics.get(i).getNumberEmptySlots());
    			}else {
    				series1.add(numberTags, this.lbMetrics.get(i).getNumberEmptySlots());
    	    		series2.add(numberTags, this.elMetrics.get(i).getNumberEmptySlots());
    			}
			
			numberTags+=this.incrementTagsBy;
		}        

        XYSeriesCollection dataset = new XYSeriesCollection();
        
        if(this.choosenEstimators!=2)
        	dataset.addSeries(series1);
        
        if(this.choosenEstimators!=1)
        	dataset.addSeries(series2);
        return dataset;
    }
    
    private XYDataset createDatasetCollision() {
    	XYSeries series1 = new XYSeries("LowerBound");
    	XYSeries series2 = new XYSeries("Eomlee");
    	
    	int numberTags = this.inicialNumberTags;
    	
    	for(int i=0;numberTags<=this.maxNumberTags;i++) {
    		
    		if(this.choosenEstimators==1) {
				series1.add(numberTags, this.lbMetrics.get(i).getNumberCollisionSlots());
			}else if(this.choosenEstimators==2) {
				series2.add(numberTags, this.elMetrics.get(i).getNumberCollisionSlots());
			}else {
				series1.add(numberTags, this.lbMetrics.get(i).getNumberCollisionSlots());
	    		series2.add(numberTags, this.elMetrics.get(i).getNumberCollisionSlots());
			}
			
			numberTags+=this.incrementTagsBy;
		}        

        XYSeriesCollection dataset = new XYSeriesCollection();
        
        if(this.choosenEstimators!=2)
        	dataset.addSeries(series1);
        
        if(this.choosenEstimators!=1)
        	dataset.addSeries(series2);
        

        return dataset;
    }
    
    private XYDataset createDatasetEfficiency() {
    	XYSeries series1 = new XYSeries("LowerBound");
    	XYSeries series2 = new XYSeries("Eomlee");
    	
    	int numberTags = this.inicialNumberTags;
    	
    	for(int i=0;numberTags<=this.maxNumberTags;i++) {
    		
    		if(this.choosenEstimators==1) {
				series1.add(numberTags, this.lbMetrics.get(i).getEfficiency());
			}else if(this.choosenEstimators==2) {
				series2.add(numberTags, this.elMetrics.get(i).getEfficiency());
			}else {
				series1.add(numberTags, this.lbMetrics.get(i).getEfficiency());
	    		series2.add(numberTags, this.elMetrics.get(i).getEfficiency());
			}
			
			numberTags+=this.incrementTagsBy;
		}        

        XYSeriesCollection dataset = new XYSeriesCollection();
        
        if(this.choosenEstimators!=2)
        	dataset.addSeries(series1);
        
        if(this.choosenEstimators!=1)
        	dataset.addSeries(series2);

        return dataset;
    }
    
    private XYDataset createDatasetTotalSlots() {
    	XYSeries series1 = new XYSeries("LowerBound");
    	XYSeries series2 = new XYSeries("Eomlee");
    	
    	int numberTags = this.inicialNumberTags;
    	
    	for(int i=0;numberTags<=this.maxNumberTags;i++) {
    		
    		if(this.choosenEstimators==1) {
				series1.add(numberTags, this.lbMetrics.get(i).getNumberTotalSlots());
			}else if(this.choosenEstimators==2) {
				series2.add(numberTags, this.elMetrics.get(i).getNumberTotalSlots());
			}else {
				series1.add(numberTags, this.lbMetrics.get(i).getNumberTotalSlots());
	    		series2.add(numberTags, this.elMetrics.get(i).getNumberTotalSlots());
			}
			
			numberTags+=this.incrementTagsBy;
		}        

        XYSeriesCollection dataset = new XYSeriesCollection();
        
        if(this.choosenEstimators!=2)
        	dataset.addSeries(series1);
        
        if(this.choosenEstimators!=1)
        	dataset.addSeries(series2);

        return dataset;
    }

    public JFreeChart createChart(final XYDataset dataset, String eixoY) throws IOException {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "SisCom", 
                "Numero de Etiquetas",
                eixoY,
                dataset, 
                PlotOrientation.VERTICAL,
                true, 
                true, 
                false
        );

        XYPlot plot = chart.getXYPlot();
        
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));        

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);
        
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("SisCom - Simuladores DFSA",
                        new Font("Serif", Font.BOLD, 18)
                )
        );
        String str = eixoY+".png";
        ChartUtilities.saveChartAsPNG(new File(str), chart, 450, 400);
        return chart;
    }
    
    public void plotGraphic() throws IOException {
    	initUI(createDatasetTotalSlots(),"Numero de Slots");
    	initUI(createDatasetEfficiency(),"Eficiencia");
    	initUI(createDatasetEmpty(),"Numero de Slots Vazios");
    	initUI(createDatasetCollision(),"Numero de Slots em Colisoees");
       /* SwingUtilities.invokeLater(() -> {
            this.setVisible(true);
        });*/
    }
    
}