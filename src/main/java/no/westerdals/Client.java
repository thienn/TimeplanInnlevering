package no.westerdals;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.*;

public class Client {
    Connection con;
    public static void main(String[] args) {
        Client pro = new Client();
        try {
            pro.getConnection();
       //     pro.createTable();
        //    pro.updateTable();
            System.out.println("Query done");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*
        try {
            pro.getConnection();
            System.out.println("Database Connection Success");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Database Connection Failed");
        }
        */

    }

    /* Create

    void createTable() {
        try {
            String q = "CREATE TABLE DB1(" + "name varchar(100),"
                    + "age int, "
                    + "salary float"
                    + ");";
            Statement stmt = con.createStatement();
            stmt.execute(q);
            System.out.println("Successfully created table");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    */

    /*
    void updateTable() {
        try {
            PreparedStatement stmt = con.prepareStatement("UPDATE USERS2 SET AGE = ? WHERE NAME = ?");
            stmt.setInt(1, 20);
            stmt.setString(2, "Per");
            stmt.executeUpdate();
            System.out.println("Update Done!");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    */
    /*
    public Connection getConnection() throws SQLException {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setDatabaseName("myDB");
        ds.setServerName("localhost");
        ds.setUser("root");
        ds.setPassword("gamer1234");
        Connection con = ds.getConnection();
        return con;

    }
    */
    public Connection getConnection() throws SQLException {
        /* Table 1
        MysqlDataSource ds = new MysqlDataSource();
        ds.setDatabaseName("myDB");
        ds.setServerName("localhost");
        ds.setUser("root");
        ds.setPassword("gamer1234");
        Connection con = ds.getConnection();
        Statement stmt = con.createStatement();
              ResultSet rs = stmt.executeQuery("SELECT * FROM USERS");
        while(rs.next()) {
            String name = rs.getString("name");
            System.out.println(name);
        }
        return con;
        */
        MysqlDataSource ds = new MysqlDataSource();
        ds.setDatabaseName("myDB");
        ds.setServerName("localhost");
        ds.setUser("root");
        ds.setPassword("gamer1234");
        con = ds.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM USERS2");
        while(rs.next()) {
            String name = rs.getString("name");
            int age = rs.getInt("age");
            System.out.println("name: " + name + " age: " + age);
        }
        return con;

    }


}
