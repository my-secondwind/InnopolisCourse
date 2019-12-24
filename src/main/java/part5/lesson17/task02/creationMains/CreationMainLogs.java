package part5.lesson17.task02.creationMains;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static part4.lesson16.task01.connectionManager.ConnectionManagerJdbcImpl.POSTGRES_PASSWORD;
import static part4.lesson16.task01.connectionManager.ConnectionManagerJdbcImpl.POSTGRES_USER;

/**
 * CreationMain
 * <p>
 * Create table logs in DB logs.
 *
 * @author Ekaterina Belolipetskaya
 */
public class CreationMainLogs {
    public static final String POSTGRES_URL_LOGS = "jdbc:postgresql://localhost:5432/logs";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        try (Connection connection = DriverManager.getConnection(POSTGRES_URL_LOGS, POSTGRES_USER, POSTGRES_PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS logs;\n" +
                    "CREATE TABLE logs\n" +
                    "(\n" +
                    "    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),\n" +
                    "    date date,\n" +
                    "    log_level character varying(255) COLLATE pg_catalog.\"default\",\n" +
                    "    message character varying(255) COLLATE pg_catalog.\"default\",\n" +
                    "    exception character varying(255) COLLATE pg_catalog.\"default\",\n" +
                    "    CONSTRAINT logs_pkey PRIMARY KEY (id)\n" +
                    ");\n" +
                    "\n" +
                    "ALTER TABLE logs\n" +
                    "    OWNER to postgres;");
        }
    }
}