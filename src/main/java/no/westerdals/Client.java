package no.westerdals;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class Client {
    Connection con;
    public static void main(String[] args) {
        Client pro = new Client();
        try {
            pro.getConnection();

                pro.dropTable();
                 pro.createTable();
            System.out.println("Table created");
                 pro.addBatch();
            System.out.println("Info added");
                 pro.readTable();
            System.out.println("Table read");
      //           pro.dropTable();
            System.out.println("Table dropped");
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

    void addBatch() {
        try {
            /*
            Statement stmt = con.createStatement();
            stmt.addBatch("INSERT INTO USERS2 VALUES('USER1', 25)");
            stmt.addBatch("INSERT INTO USERS2 VALUES('USER2', 30)");
            stmt.addBatch("INSERT INTO USERS2 VALUES('USER3', 40)");
            stmt.addBatch("INSERT INTO USERS2 VALUES('USER5', 60)");
            */
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
            System.out.println("Table read");
        } catch (SQLException e) {
            e.printStackTrace();
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

    void readFile() throws IOException {
     /*   String insertTableSQL = "INSERT INTO EMNER"
                + "(name, subjectid, lecturer, starttime, endtime) VALUES "
                + "(?,?,?,?,?)";


        preparedStatement = con.prepareStatement(insertTableSQL);
    */

        try {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO EMNER VALUES(?,?,?,?,?)");
            //RETRIEVING INFORMATION FROM CSV FILE

//opening a file input stream
            BufferedReader reader = new BufferedReader(new FileReader("Emner.csv"));

            String line = null; //line read from csv
            Scanner scanner = null; //scanned line

            // SimpleDateFormat date = new SimpleDateFormat("mm/DD/yyyy");

            reader.readLine(); //omits the first line

//READING FILE LINE BY LINE AND UPLOADING INFORMATION TO DATABASE
            try {
                while ((line = reader.readLine()) != null) {
                    scanner = new Scanner(line);
                    scanner.useDelimiter(",");
                    while (scanner.hasNext()) {
                        stmt.setString(1, scanner.next());
                        stmt.setString(2, scanner.next());
                        stmt.setString(3, scanner.next());
                        stmt.setString(4, scanner.next());
                        stmt.setString(5, scanner.next());
               /* try {
                    java.util.Date d;
                    d = date.parse(scanner.next());
                    preparedStatement.setDate(6, new java.sql.Date(d.getTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                preparedStatement.setString(7, scanner.next());
            } */
                        stmt.executeUpdate();
                    }

                    stmt.close();
                    System.out.println("Data imported");

                    reader.close(); //closing CSV reader

                } catch(SQLException e){
                    e.printStackTrace();
                }finally{//CLOSING CONNECTION
                    try {
                        if (statement != null)
                            myConnection.close();
                    } catch (SQLException se) {
                    }// do nothing
                    try {
                        if (myConnection != null)
                            myConnection.close();
                    } catch (SQLException se) {
                        se.printStackTrace();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
    }
}
