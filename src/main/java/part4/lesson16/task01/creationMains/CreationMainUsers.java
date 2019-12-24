package part4.lesson16.task01.creationMains;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static part4.lesson16.task01.connectionManager.ConnectionManagerJdbcImpl.*;

/**
 * @author Ekaterina Belolipetskaya
 */
public class CreationMainUsers {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");

        try (Connection connection = DriverManager.getConnection(POSTGRES_URL_USERS, POSTGRES_USER, POSTGRES_PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS user_role;\n" +
                    "DROP TABLE IF EXISTS role;\n" +
                    "DROP TABLE IF EXISTS users;\n" +
                    "\n" +
                    "CREATE TABLE role\n" +
                    "(\n" +
                    "    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),\n" +
                    "    name character varying(255) COLLATE pg_catalog.\"default\" NOT NULL,\n" +
                    "    description character varying(255) COLLATE pg_catalog.\"default\",\n" +
                    "    CONSTRAINT role_pkey PRIMARY KEY (id)\n" +
                    ");\n" +
                    "\n" +
                    "ALTER TABLE role\n" +
                    "    OWNER to postgres;\n" +
                    "\t\n" +
                    "CREATE TABLE users\n" +
                    "(\n" +
                    "    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),\n" +
                    "    name character varying(255) COLLATE pg_catalog.\"default\" NOT NULL,\n" +
                    "    birthday date,\n" +
                    "    login_id integer NOT NULL,\n" +
                    "    city character varying(255) COLLATE pg_catalog.\"default\",\n" +
                    "    email character varying(255) COLLATE pg_catalog.\"default\",\n" +
                    "    description character varying(255) COLLATE pg_catalog.\"default\",\n" +
                    "    CONSTRAINT users_pkey PRIMARY KEY (id)\n" +
                    ");\n" +
                    "\n" +
                    "ALTER TABLE users\n" +
                    "    OWNER to postgres;\n" +
                    "\n" +
                    "CREATE TABLE user_role\n" +
                    "(\n" +
                    "    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),\n" +
                    "    user_id integer NOT NULL,\n" +
                    "    role_id integer NOT NULL,\n" +
                    "    CONSTRAINT user_role_pkey PRIMARY KEY (id),\n" +
                    "    CONSTRAINT role_id FOREIGN KEY (role_id)\n" +
                    "        REFERENCES role (id) MATCH SIMPLE\n" +
                    "        ON UPDATE NO ACTION\n" +
                    "        ON DELETE NO ACTION,\n" +
                    "    CONSTRAINT user_id FOREIGN KEY (user_id)\n" +
                    "        REFERENCES users (id) MATCH SIMPLE\n" +
                    "        ON UPDATE NO ACTION\n" +
                    "        ON DELETE NO ACTION\n" +
                    ");\n" +
                    "\n" +
                    "ALTER TABLE user_role\n" +
                    "    OWNER to postgres;");
        }
    }
}
