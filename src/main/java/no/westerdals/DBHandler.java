package no.westerdals;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBHandler {
    MysqlDataSource ds = new MysqlDataSource();
    Connection con;
    /*
    public static void main(String[] args) {

    }*/

    public Connection getConnection() {

        try {
            con = ds.getConnection();
            ds.setDatabaseName("myDB");
            ds.setServerName("localhost");
            ds.setUser("root");
            ds.setPassword("gamer1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
