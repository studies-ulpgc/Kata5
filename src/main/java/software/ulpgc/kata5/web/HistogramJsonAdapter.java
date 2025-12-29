package software.ulpgc.kata5.web;

import software.ulpgc.kata5.architecture.viewmodel.Histogram;

import java.util.HashMap;
import java.util.Map;

public class HistogramJsonAdapter {
    public static Map<String, Object> from(Histogram histogram) {
        Map<String, Object> json = new HashMap<>();

        json.put("title", histogram.title());
        json.put("x", histogram.x());
        json.put("legend", histogram.legend());

        Map<Integer, Integer> values = new HashMap<>();
        for (int bin : histogram) {
            values.put(bin, histogram.count(bin));
        }

        json.put("data", values);
        return json;
    }
}
