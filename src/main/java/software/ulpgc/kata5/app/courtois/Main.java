package software.ulpgc.kata5.app.courtois;

import software.ulpgc.kata5.app.Desktop;
import software.ulpgc.kata5.app.MovieDeserializer;
import software.ulpgc.kata5.app.RemoteStore;

public class Main {
    public static void main(String[] args) {
        Desktop.with(new RemoteStore(MovieDeserializer::fromTsv))
                .display()
                .setVisible(true);
    }
}
