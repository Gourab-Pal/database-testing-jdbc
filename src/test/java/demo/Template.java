package demo;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class Template {
    String dbName = "automationtesting";
    static Connection con;
    static Statement st;
    String dbUrl = "jdbc:mysql://localhost:3306/" + dbName + "?useSSL=true";
    String driver = "com.mysql.cj.jdbc.Driver";
    String userName = "root";
    String password = "admin";
    static int numberOfRows;

    public static ResultSet fetchResultUsingSelectQuery(String sqlQuery) throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sqlQuery);

        // // Get metadata from the result set
        // ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
        // int columnCount = rsmd.getColumnCount();
        // // Print column headers dynamically
        // for (int i = 1; i <= columnCount; i++) {
        //     System.out.printf("%-20s", rsmd.getColumnName(i));
        // }
        // System.out.println();
        // System.out.println("-------------------------------------------------------------------");

        // // Print rows dynamically
        // while (rs.next()) {
        //     for (int i = 1; i <= columnCount; i++) {
        //         System.out.printf("%-20s", rs.getString(i));
        //     }
        //     System.out.println();
        // }
        return rs;
    }

    @BeforeTest(alwaysRun = true)
    public void connectDatabase() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, SQLException {
        Class.forName(driver).getDeclaredConstructor().newInstance();
        con = DriverManager.getConnection(dbUrl, userName, password);
        if (con != null) {
            System.out.println("Connection successful!");
        } else {
            System.out.println("Error connecting to the database!");
            return;
        }
    }

    @Test(description = "Verify rows are present in the table")
    public static void testcase01() throws SQLException {
        System.out.println("Executing testcase01");
        ResultSet resultSet = fetchResultUsingSelectQuery("SELECT COUNT(*) AS total_records FROM users");

        if (resultSet.next()) { // Move cursor to first row
            numberOfRows = resultSet.getInt("total_records");
        } else {
            throw new SQLException("No records found!");
        }
        Assert.assertTrue(numberOfRows > 0);
        System.out.println("End of testcase01");
    }

    @Test(description = "add one row and check if the number of row count increases")
    public static void testcase02() throws SQLException {
        int beforeRowCount = numberOfRows;
        int afterRowCount = 0;
        PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users(userid, username, city, salary) VALUES (1458, 'new user2', 'kolkata', 2589)");
        ps.executeUpdate();
        ResultSet resultSet = fetchResultUsingSelectQuery("SELECT COUNT(*) AS total_records FROM users");
        if (resultSet.next()) { // Move cursor to first row
            afterRowCount = resultSet.getInt("total_records");
        } else {
            throw new SQLException("No records found!");
        }
        Assert.assertTrue((afterRowCount - beforeRowCount)==1);
        numberOfRows = afterRowCount;
        System.out.println(beforeRowCount + "  " + afterRowCount);
    }

    @Test(description = "how many people have salary more than 1000?")
    public static void testcase03() throws SQLException {
        ResultSet resultSet = fetchResultUsingSelectQuery("SELECT COUNT(*) AS numberPeople FROM users WHERE salary>1000");
        if(resultSet.next()) {
            System.out.println("Number of poeple having salary more than 1000: " + resultSet.getInt("numberPeople"));
        } else {
            throw new SQLException("No records found!");
        }
        
    }

    @AfterTest
    public static void tearDown() throws SQLException {
        if (con != null) {
            con.close();
            System.out.println("Connection closed successfully!");
        } else {
            System.out.println("Something went wrong while closing the connection!");
            return;
        }
    }
}
