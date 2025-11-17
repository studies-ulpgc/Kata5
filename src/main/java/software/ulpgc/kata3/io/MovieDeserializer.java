package software.ulpgc.kata3.io;

import software.ulpgc.kata3.model.Movie;

public class MovieDeserializer {
    public static Movie fromTsv(String string){
        return fromTsv(string.split("\t"));
    }

    private static Movie fromTsv(String[] split) {
        return new Movie(split[2], toInt(split[5]), toInt(split[7]));
    }

    private static int toInt(String string) {
        if (string.equals("\\N")) return -1;
        return Integer.parseInt(string);
    }
}
