package software.ulpgc.kata5.web;

import io.javalin.http.Context;
import software.ulpgc.kata5.architecture.io.Store;
import software.ulpgc.kata5.architecture.model.Movie;
import software.ulpgc.kata5.architecture.tasks.HistogramBuilder;
import software.ulpgc.kata5.architecture.viewmodel.Histogram;

public class WebServiceAdapter {
    private final Store store;

    public WebServiceAdapter(Store store) {
        this.store = store;
    }

    public void histogram(Context context) {
        context.json(HistogramJsonAdapter.from(getBuild(context)));
    }

    private Histogram getBuild(Context context) {
        return HistogramBuilder
                .with(store.moviesInRange(getParseInt(context, "from"), getParseInt(context, "to")))
                .title("Histogram for years " + getParseInt(context, "from") +
                        "-" + getParseInt(context, "from"))
                .x("year")
                .legend("count")
                .build(Movie::year);
    }

    private int getParseInt(Context context, String string) {
        return Integer.parseInt(context.queryParam(string));
    }
}
