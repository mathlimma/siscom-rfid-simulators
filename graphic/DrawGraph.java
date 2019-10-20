package graphic;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class DrawGraph extends JFrame {
	private XYDataset dataset;
	private String eixoY;

    public DrawGraph(XYDataset dataset, String eixoY) throws IOException {
    	this.dataset=dataset;
    	this.eixoY=eixoY;
    	initUI();
    }

    public void initUI() throws IOException {

        JFreeChart chart = createChart (this.dataset, this.eixoY);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle(this.eixoY);
        setLocationRelativeTo(null);
    }


    private JFreeChart createChart(final XYDataset dataset, String eixoY) throws IOException {

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
                        new Font("Serif", Font.BOLD, 18))
        );
        String str = eixoY+".png";
        ChartUtilities.saveChartAsPNG(new File(str), chart, 450, 400);
        return chart;
    }
    
}