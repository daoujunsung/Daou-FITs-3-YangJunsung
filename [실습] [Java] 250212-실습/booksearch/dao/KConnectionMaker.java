package booksearch.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class KConnectionMaker implements ConnectionMaker {

    public KConnectionMaker() {

    }

    @Override
    public Connection makeNewConnection() {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String id = "C##DEV";
            String pw = "4587";

            conn = DriverManager.getConnection(url, id, pw);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}
