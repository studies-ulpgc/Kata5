package software.ulpgc.kata3;

import software.ulpgc.kata3.io.MovieDeserializer;
import software.ulpgc.kata3.io.RemoteMovieLoader;
import software.ulpgc.kata3.model.Histogram;
import software.ulpgc.kata3.model.Movie;
import software.ulpgc.kata3.tasks.HistogramBuilder;

import java.util.List;

public class Main {
    private static final String url =
            "https://datasets.imdbws.com/title.basics.tsv.gz";

    public static void main(String[] args) {
        List<Movie> movies = new RemoteMovieLoader(url, MovieDeserializer::fromTsv).loadAll();
        display(new HistogramBuilder(Movie::duration).build(movies));
        display(new HistogramBuilder(m -> m.duration()/60).build(movies));
    }

    private static void display(Histogram histogram) {
        for (int key : histogram)
            System.out.println(key + " " + histogram.count(key));
    }
}
