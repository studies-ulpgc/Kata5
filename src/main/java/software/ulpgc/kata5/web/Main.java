package software.ulpgc.kata5.web;

import io.javalin.Javalin;
import software.ulpgc.kata5.app.DatabaseStore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        start(app(using(database())));
    }

    private static void start(Javalin app) {
        app.start(8080);
    }

    private static Javalin app(WebServiceAdapter adapter) {
        return Javalin.create()
                .get("/histogram", adapter::histogram);
    }

    private static WebServiceAdapter using(Connection connection) {
        return new WebServiceAdapter(new DatabaseStore(connection));
    }

    private static Connection database() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:movies.db");
    }
}
