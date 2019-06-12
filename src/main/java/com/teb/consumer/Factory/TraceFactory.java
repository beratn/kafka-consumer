package com.teb.consumer.Factory;

import org.charts.dataviewer.api.trace.LineTrace;
import org.charts.dataviewer.api.trace.TraceConfiguration;
import org.charts.dataviewer.utils.TraceColour;
import org.charts.dataviewer.utils.TraceVisibility;

public class TraceFactory {

    public static LineTrace<?> createLineTrace() {
        LineTrace<Object> lineTrace = new LineTrace<>();
        lineTrace.setxArray(new String[]{"09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00"});
        lineTrace.setyArray(new Double[]{0.0, 20.0, 30.0, 10.0, 60.0, 20.0, 60.0, 70.0, 80.0, 90.0, 100.0});
        lineTrace.setTraceName("Istanbul");
        TraceConfiguration lineConfig = new TraceConfiguration();
        lineConfig.setTraceColour(TraceColour.BLUE);
        lineConfig.setTraceName("MyLineTrace1");
        lineConfig.setTraceVisibility(TraceVisibility.TRUE);

        lineTrace.setConfiguration(lineConfig);
        return lineTrace;
    }

    public static LineTrace<?> createLineTraceWithConfig() {
        LineTrace<Object> lineTrace = new LineTrace<>();
        lineTrace.setxArray(new String[]{"09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00"});
        lineTrace.setyArray(new Double[]{0.0, 10.0, 20.0, 30.0, 40.0, 50.0, 60.0, 70.0, 80.0, 90.0, 100.0});

        TraceConfiguration lineConfig = new TraceConfiguration();
        lineConfig.setTraceColour(TraceColour.RED);
        lineConfig.setTraceName("MyLineTrace1");
        lineConfig.setTraceVisibility(TraceVisibility.TRUE);

        lineTrace.setConfiguration(lineConfig);
        return lineTrace;
    }


}
