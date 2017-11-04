package no.westerdals;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.*;
import java.util.Scanner;

public class DB2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        /* Table 1
        System.out.print("Name ");
        String name = scanner.nextLine();

        try {
            MysqlDataSource ds = new MysqlDataSource();
            ds.setDatabaseName("myDB");
            ds.setServerName("localhost");
            ds.setUser("root");
            ds.setPassword("gamer1234");
            Connection con = ds.getConnection();

            // 2. Create a statement
            String sql = "insert into users "
                    + " (name)" + " values (?)";
            PreparedStatement pStmt = con.prepareStatement(sql);

            // set param values
            pStmt.setString(1, name);
            // 3. Execute SQL query
            pStmt.executeUpdate();
            pStmt.close();
            System.out.println("Insert Done");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        */
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Age: ");
        int age = scanner.nextInt();

        try {
            MysqlDataSource ds = new MysqlDataSource();
            ds.setDatabaseName("myDB");
            ds.setServerName("localhost");
            ds.setUser("root");
            ds.setPassword("gamer1234");
            Connection con = ds.getConnection();

            // 2. Create a statement
            String sql = "insert into USERS2 "
                    + "(name, age)" + " values (?,?)";
            PreparedStatement pStmt = con.prepareStatement(sql);

            // set param values
            pStmt.setString(1, name);
            pStmt.setInt(2, age);
            // 3. Execute SQL query
            pStmt.executeUpdate();
            pStmt.close();
            System.out.println("Insert Done");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    /*
    public Connection getConnection() throws SQLException {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setDatabaseName("myDB");
        ds.setServerName("localhost");
        ds.setUser("root");
        ds.setPassword("gamer1234");
        Connection con = ds.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);
        return con;

    }

    */
}
