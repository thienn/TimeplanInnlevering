package no.westerdals;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.*;

public class Client2 {
    Connection con;
    public static void main(String[] args) {
        Client2 pro = new Client2();
        try {
            pro.getConnection();
            pro.readTable();
     //       pro.addBatch();
       //         pro.readTable();
       //     pro.createTable();
        //    pro.updateTable();
          //      pro.deleteTable();
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

    void addBatch() {
        try {
            /*
            Statement stmt = con.createStatement();
            stmt.addBatch("INSERT INTO USERS2 VALUES('USER1', 25)");
            stmt.addBatch("INSERT INTO USERS2 VALUES('USER2', 30)");
            stmt.addBatch("INSERT INTO USERS2 VALUES('USER3', 40)");
            stmt.addBatch("INSERT INTO USERS2 VALUES('USER5', 60)");
            */
            PreparedStatement stmt = con.prepareStatement("INSERT INTO USERS2 VALUES(?,?)");
            stmt.setString(1, "USERS6");
            stmt.setInt(2, 40);
            stmt.addBatch();;

            stmt.clearParameters();
            stmt.setString(1, "USERS7");
            stmt.setInt(2, 50);
            stmt.addBatch();

            stmt.clearParameters();
            int [] res = stmt.executeBatch();

            for (int re : res) {
                System.out.println(re);
            }
            /*
            int [] ar = stmt.executeBatch();
            for(int i : ar) {
                System.out.println(i);
            } */
         //   stmt.executeBatch();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
    void deleteTable() {
        try{
            PreparedStatement stmt = con.prepareStatement("DELETE FROM USERS2 WHERE name = ?");
            stmt.setString(1, "Per");
            stmt.executeUpdate();
            System.out.println("SQL DELETED");
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

    void readTable() {
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM USERS2");
            while(rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                System.out.println("name: " + name + " age: " + age);
            }
            System.out.println("Table read");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
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
/*        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM USERS2");
        while(rs.next()) {
            String name = rs.getString("name");
            int age = rs.getInt("age");
            System.out.println("name: " + name + " age: " + age);
        } */
        return con;

    }


}
