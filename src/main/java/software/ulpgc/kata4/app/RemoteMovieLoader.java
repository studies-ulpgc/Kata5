package software.ulpgc.kata4.app;

import software.ulpgc.kata4.io.MovieLoader;
import software.ulpgc.kata4.model.Movie;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;

public class RemoteMovieLoader implements MovieLoader {
    private static final String url =
            "https://datasets.imdbws.com/title.basics.tsv.gz";
    private final Function<String, Movie> deserialize;

    public RemoteMovieLoader(Function<String, Movie> deserialize) {
        this.deserialize = deserialize;
    }

    @Override
    public Stream<Movie> loadAll() {
        try {
            return loadFrom(new URL(url));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Stream<Movie> loadFrom(URL url) throws IOException {
        return loadFrom(url.openConnection());
    }

    private Stream<Movie> loadFrom(URLConnection urlConnection) throws IOException {
        return loadFrom(unzip(urlConnection.getInputStream()));
    }

    private Stream<Movie> loadFrom(InputStream inputStream) throws IOException {
        return loadFrom(toReader(inputStream));
    }

    private Stream<Movie> loadFrom(BufferedReader reader) throws IOException {
        return reader.lines().skip(1).map(deserialize);
    }

    private BufferedReader toReader(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    private InputStream unzip(InputStream inputStream) throws IOException {
        return new GZIPInputStream(new BufferedInputStream(inputStream));
    }
}
