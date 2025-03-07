package org.gustavolyra.arduinospring;

import com.formdev.flatlaf.FlatDarkLaf;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

@SpringBootApplication
public class ArduinoSpringApplication implements CommandLineRunner {
    public static TimeSeries temperatureSeries = new TimeSeries("Temperature");
    public static TimeSeries humiditySeries = new TimeSeries("Humidity");
    public static TimeSeries lightSeries = new TimeSeries("Light");
    public static TimeSeriesCollection dataset = new TimeSeriesCollection();
    static JFrame frame = new JFrame("Trabalho Arduino");


    public static void main(String[] args) {
        SpringApplication.run(ArduinoSpringApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        dataset.addSeries(temperatureSeries);
        dataset.addSeries(humiditySeries);
        dataset.addSeries(lightSeries);
        UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
        FlatDarkLaf.setup();
        JDialog.setDefaultLookAndFeelDecorated(true);
        JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.updateComponentTreeUI(frame);

        JFreeChart chart = ChartFactory.createTimeSeriesChart("Arduino Sensors Data", "Time", "Value", dataset, true, true, false);

        chart.setBackgroundPaint(Color.BLACK);
        chart.getTitle().setPaint(Color.WHITE);
        XYPlot plot = (XYPlot) chart.getPlot();


        plot.getRenderer().setSeriesPaint(0, Color.RED);
        plot.getRenderer().setSeriesPaint(1, Color.BLUE);
        plot.getRenderer().setSeriesPaint(2, Color.YELLOW);

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("HH:mm:ss"));
        plot.setBackgroundPaint(Color.BLACK);
        plot.setDomainGridlinePaint(Color.DARK_GRAY);
        plot.setRangeGridlinePaint(Color.DARK_GRAY);

        axis.setTickLabelPaint(Color.WHITE);
        axis.setLabelPaint(Color.WHITE);

        ValueAxis rangeAxis = plot.getRangeAxis();
        rangeAxis.setTickLabelPaint(Color.WHITE);
        rangeAxis.setLabelPaint(Color.WHITE);

        if (chart.getLegend() != null) {
            chart.getLegend().setBackgroundPaint(Color.BLACK);
            chart.getLegend().setItemPaint(Color.WHITE);
        }

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}