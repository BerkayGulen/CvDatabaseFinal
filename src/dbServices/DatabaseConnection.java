package dbServices;

import java.io.File;
import java.sql.*;

public class DatabaseConnection {
    private static Connection conn = null;

    private DatabaseConnection() {
    }

    public static Connection getInstance() {

        if (conn == null) {
            try {
                String fileName = "res/cvOwner.db";
                File file = new File(fileName);
                boolean firstRun = !file.exists();

                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection("jdbc:sqlite:" + fileName);
                if (firstRun) {
                    Statement statement = conn.createStatement();
                    statement.executeUpdate("create table cvOwner("
                            + "id INTEGER NOT NULL unique,"
                            + "name varchar(60),"
                            + "surname varchar(60),"
                            + "department varchar(60),"
                            + "cvFilePath varchar(60),"
                            + "primary key (id));");
                    System.out.println("db is successfully created.");
                } else {
                    System.out.println("already have db ");
                }
            } catch (ClassNotFoundException | SQLException e) {
                System.err.println(e);
            }
        }
        return conn;
    }


}
