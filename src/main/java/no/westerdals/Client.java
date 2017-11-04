package no.westerdals;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client {
    Connection con;
    public static void main(String[] args) {
        Client program = new Client();

        try {
            program.getConnection();
            System.out.println("Database Connection Success");

            // Drops table if it exist - for testing purposes
            program.dropTable();

            // Create table
            program.createTable();

            // Runs through method for reading from CSV File
            try {
                program.readFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Setup of DB and populating successful");
            // Ask for user input - Read part
            program.userInput();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Database Connection Failed");
        }
    }


    public Connection getConnection() throws SQLException {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setDatabaseName("myDB");
        ds.setServerName("localhost");
        ds.setUser("root");
        ds.setPassword("gamer1234");
        con = ds.getConnection();
        return con;

    }



    void userInput() {
        int values;
        Scanner input = new Scanner(System.in);
        System.out.println("If you want all of the subjects out type 1, if you want a specific one type 2: ");
        values = input.nextInt();
        if(values == 1) {
            readTable();
        } else if (values == 2) {
            System.out.println("Which subject do you want - (Use subjectid)? ");
            String emnekode = input.next();
            try {
                //      Statement stmt = con.createStatement();
                //     ResultSet rs = stmt.executeQuery("SELECT + emnekode + FROM EMNER");
                //   ResultSet rs = stmt.executeQuery("select * from EMNER WHERE subjectid = 'PGR200';");
                //     ResultSet rs = stmt.executeQuery("select * from EMNER Where subjectid = " + emnekode);
            /*   String sql = "SELECT * FROM EMNER WHERE subjectid = 'PGR200'";
                ResultSet rs = stmt.executeQuery(sql);
            */
            /*
                PreparedStatement stmt = con.prepareStatement();
                String sql = "SELECT * FROM EMNER where subjectid = ?";

            //    PreparedStatement stmt = con.prepareStatement("SELECT * FROM EMNER where subjectid = ?");
                stmt = con.prepareStatement(sql);
                */
            /*
                String sql = "select * from EMNER where subjectid = '" + emnekode +"'";
                ResultSet rs = stmt.executeQuery(sql);

                */

                // Next make sure this goes into a loop, if invalid, report back to user, then ask for another value
                String subjectid = emnekode;
                PreparedStatement prepStmt = con.prepareStatement("select * from EMNER where subjectid = ?");
                prepStmt.setString(1, subjectid);
                ResultSet rs = prepStmt.executeQuery();

                while (rs.next()) {
                    String name = rs.getString("name");
                    subjectid = rs.getString("subjectid");
                    String lecturer = rs.getString("lecturer");
                    String starttime = rs.getString("starttime");
                    String endtime = rs.getString("endtime");

                    System.out.println("Emnenavn: " + name + " Emnekode: " + subjectid + " Foreleser: " + lecturer + " Startdato: " + starttime + " Sluttdato: " + endtime);
                }
                System.out.println("Table read - Finished");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

        /* Create */

    void createTable() {
        try {
            String q = "CREATE TABLE EMNER(" + "name varchar(100),"
                    + "subjectid varchar(30), "
                    + "lecturer varchar(50),"
                    + "starttime varchar(30),"
                    + "endtime varchar(30)"
                    + ");";
            Statement stmt = con.createStatement();
            stmt.execute(q);
            System.out.println("Successfully created table");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/*
    void addBatch() {
        try {

            Statement stmt = con.createStatement();
            stmt.addBatch("INSERT INTO USERS2 VALUES('USER1', 25)");
            stmt.addBatch("INSERT INTO USERS2 VALUES('USER2', 30)");
            stmt.addBatch("INSERT INTO USERS2 VALUES('USER3', 40)");
            stmt.addBatch("INSERT INTO USERS2 VALUES('USER5', 60)");

            PreparedStatement stmt = con.prepareStatement("INSERT INTO EMNER VALUES(?,?,?,?,?)");
            stmt.setString(1, "Avansert Javaprogrammering 2");
            stmt.setString(2, "PGR200");
            stmt.setString(3, "Per Lauvas");
            stmt.setString(4, "21. August");
            stmt.setString(5, "19. Novemeber");
            stmt.addBatch();
            stmt.clearParameters();

            stmt.setString(1, "Mobile Okosystemer");
            stmt.setString(2, "IS3200");
            stmt.setString(3, "Alexander Dreyer");
            stmt.setString(4, "23. August");
            stmt.setString(5, "14. Novemeber");
            stmt.addBatch();
            stmt.clearParameters();

            stmt.setString(1, "Grensesnittdesign");
            stmt.setString(2, "DS3800");
            stmt.setString(3, "Andreas Beining");
            stmt.setString(4, "24. August");
            stmt.setString(5, "24. Novemeber");
            stmt.addBatch();
            stmt.clearParameters();
            int [] res = stmt.executeBatch();

            for (int re : res) {
                System.out.println(re);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    } */

    void dropTable() {
        try {
            Statement stmt = con.createStatement();
            String sql = "DROP TABLE EMNER";
            stmt.executeUpdate(sql);
            stmt.close();
            System.out.println("Dropped Table");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void readTable() {
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM EMNER");
            while(rs.next()) {
                String name = rs.getString("name");
                String subjectid = rs.getString("subjectid");
                String lecturer = rs.getString("lecturer");
                String starttime = rs.getString("starttime");
                String endtime = rs.getString("endtime");

                System.out.println("Emnenavn: " + name + " Emnekode: " + subjectid + " Foreleser: " + lecturer + " Startdato: " + starttime + " Sluttdato: " + endtime );
            }
            System.out.println("Table read - Finished");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Read from CSV File and input to DB
    void readFile() throws IOException {
        try {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO EMNER VALUES(?,?,?,?,?)");


            //Open a file input stream for CSV
            BufferedReader reader = new BufferedReader(new FileReader("Emner.csv"));

            String line = null; //line read from csv
            Scanner scanner = null; //scanned line

            reader.readLine(); //omits the first line

            // Reading file line by line and uploads the information to DB
            try {
                while ((line = reader.readLine()) != null) {
                    scanner = new Scanner(line);
                    scanner.useDelimiter(";");
                    while (scanner.hasNext()) {
                        try {
                            stmt.setString(1, scanner.next());
                            stmt.setString(2, scanner.next());
                            stmt.setString(3, scanner.next());
                            stmt.setString(4, scanner.next());
                            stmt.setString(5, scanner.next());
                            stmt.executeUpdate();
                        } catch (NoSuchElementException e) {

                        }
                    }

                    System.out.println("Data imported");

                }
                stmt.close();
                reader.close(); //closing CSV reader

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {

            }

        } catch (SQLException e) {

        }

    }
}
