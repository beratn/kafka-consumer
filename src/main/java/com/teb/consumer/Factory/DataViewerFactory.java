package com.teb.consumer.Factory;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.charts.dataviewer.DataViewer;
import org.charts.dataviewer.api.config.DataViewerConfiguration;
import org.charts.dataviewer.api.data.PlotData;
import org.charts.dataviewer.api.trace.GenericTrace;
import org.charts.dataviewer.api.trace.LineTrace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataViewerFactory {

    private static final Logger log = LoggerFactory.getLogger(DataViewerFactory.class);

    private static ScheduledExecutorService executor = Executors.newScheduledThreadPool(4);

    private static int index = 0;

    private static DataViewer dataViewer;
    private static GenericTrace<?> lineTrace;

    private DataViewerFactory() {
        // Intentionally empty
    }

    public static synchronized DataViewer createDataViewerExample1() {

        dataViewer = new DataViewer("example1");
        lineTrace = TraceFactory.createLineTrace();
        DataViewerConfiguration config = new DataViewerConfiguration();
        config.setPlotTitle("Log Monitoring");
        config.setxAxisTitle("Time");
        config.setyAxisTitle("Log Counts");
        config.showLegend(true);
        config.setLegendInsidePlot(false);
        dataViewer.updateConfiguration(config);

        PlotData plotData = new PlotData();

        plotData.addTrace(lineTrace);

        dataViewer.updatePlot(plotData);

        return dataViewer;
    }

    // Update Example
    public static synchronized DataViewer createDataViewerUpdateExample() {

        String csvFile = "";
        String plotTitle = "";
        csvFile = DataViewerFactory.class.getResource("/files/test_file1.csv").getPath();
        plotTitle = "Test 1a data";

        List<Double[]> parsedDataDouble = new ArrayList<>();

        BufferedReader bufferReader = null;
        String line = "";
        try {
            bufferReader = new BufferedReader(new FileReader(csvFile));
            while ((line = bufferReader.readLine()) != null) {
                String[] field = line.split(",");
                Double[] dataRow = new Double[512];
                if (field.length == 513) {
                    for (int i = 1; i < field.length; i++) {
                        dataRow[i - 1] = Double.parseDouble(field[i]);
                    }
                    parsedDataDouble.add(dataRow);
                }
            }
        } catch (FileNotFoundException ex) {
            log.error("FileNotFoundException", ex);
        } catch (IOException ex) {
            log.error("IOException", ex);
        } finally {
            if (bufferReader != null) {
                try {
                    bufferReader.close();
                } catch (IOException ex) {
                    log.error("IOException", ex);
                }
            }
        }

//        List<Double[]> parsedDataDouble = new ArrayList<>();
//                        Double[] dataRow = new Double[512];
//                        dataRow[0] = 0.0;
//                        dataRow[1] = 1.1;
//        parsedDataDouble.add(1,data);
        Double[] exampleFrequency = new Double[512];
        for (int i = 0; i < 512; i++) {
            exampleFrequency[i] = (double) 11000 / (exampleFrequency.length * 2) * i;
        }

        DataViewer dataviewer = new DataViewer("updateExample");

        DataViewerConfiguration config = new DataViewerConfiguration();
        config.setPlotTitle("Kafka Log Monitoring");
        config.setxAxisTitle("Time");
        config.setyAxisTitle("Count");
        config.showLegend(true);
        config.setyRange(200, 0);
        dataviewer.updateConfiguration(config);

        PlotData plotData = new PlotData();
        LineTrace<Double> bigTrace = new LineTrace<>();
        bigTrace.setTraceName("Trace");
        bigTrace.setxArray(exampleFrequency);

        plotData.addTrace(bigTrace);

        dataviewer.updatePlot(plotData);

        executor.scheduleAtFixedRate(() -> DataViewerFactory.updateData(dataviewer, bigTrace, parsedDataDouble), 5000,
                150, TimeUnit.MILLISECONDS);

        return dataviewer;

    }

    private static void updateData(DataViewer dataviewer, LineTrace<Double> tuneTrace, List<Double[]> tuneData) {
        if (index == tuneData.size())
            index = 0;
        tuneTrace.setyArray(tuneData.get(index++));
        PlotData plot = new PlotData();
        plot.addTrace(tuneTrace);
        dataviewer.updatePlot(plot);
    }

    private static void updateLogData(DataViewer dataviewer, LineTrace<Double> tuneTrace, List<Double[]> tuneData) {
        if (index == tuneData.size())
            index = 0;
        tuneTrace.setyArray(tuneData.get(index++));
        PlotData plot = new PlotData();
        plot.addTrace(tuneTrace);
        dataviewer.updatePlot(plot);
    }

}
