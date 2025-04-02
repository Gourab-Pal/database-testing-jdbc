package demo;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class App {

    static Connection con;

    public static void connectDatabase(String dbName)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
            NoSuchMethodException, SecurityException, ClassNotFoundException, SQLException {
        String dbUrl = "jdbc:mysql://localhost:3306/" + dbName + "?useSSL=true";
        String driver = "com.mysql.cj.jdbc.Driver";
        String userName = "root";
        String password = "admin";

        Class.forName(driver).getDeclaredConstructor().newInstance();
        con = DriverManager.getConnection(dbUrl, userName, password);
    }

    public static void fetchResultUsingSelectQuery(String sqlQuery) throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM users");

        // Get metadata from the result set
        ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        // Print column headers dynamically
        for (int i = 1; i <= columnCount; i++) {
            System.out.printf("%-20s", rsmd.getColumnName(i));
        }
        System.out.println();
        System.out.println("-------------------------------------------------------------------");

        // Print rows dynamically
        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("%-20s", rs.getString(i));
            }
            System.out.println();
        }
    }

    public static void main(String[] args)
            throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {

        // establish the connection to database
        connectDatabase("automationtesting");

        // execute insert query
        PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users (username, userid, city, salary) VALUES('gourab', 18, 'kolkata', 1200)");
        //ps.executeUpdate(); // executeUpdate() is for INSERT, UPDATE and DELETE
        // fetchResultUsingSelectQuery("SELECT * FROM users");

        //execute update query
        ps = con.prepareStatement("UPDATE users SET userid=180 WHERE username='gourab'");
        ps.executeUpdate();
        fetchResultUsingSelectQuery("SELECT * FROM users");

        // close the database connection
        con.close();
    }

}
