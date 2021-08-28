package practice;

import java.sql.*;

public class JDBC_Practice {
    public static void main(String[] args) {

        String jdbc_url = "jdbc:oracle:thin:@54.237.100.89:1521:xe" ;
        String username = "hr" ;
        String password = "hr" ;

        //Create Connection
        try {
            Connection conn = DriverManager.getConnection(jdbc_url,username,password);

            //Create statement from connection
            Statement stmnt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

            //get the resultset
            ResultSet rs = stmnt.executeQuery("SELECT * FROM REGIONS");

            // move to cursor from beforeFirst location to firstRow
            rs.next(); // now we are at first row
            System.out.println("rs.getString(2) = " + rs.getString(2));
            System.out.println("rs.getString(\"REGION_NAME\") = "
                                + rs.getString("REGION_NAME"));

            /* All row navigation methods available
            rs.previous();
            rs.absolute();
            rs.beforeFirst();
            rs.afterLast();
            rs.first();
            rs.last();
            rs.getMetaData();
            */

            // ResultSet object does not contain information abouıt column count and column names
            // if you want to get column related information you need ResultSetMetaData object

            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println("rsmd.getColumnCount() = " + rsmd.getColumnCount());
            System.out.println("rsmd.getColumnName(2) = " + rsmd.getColumnName(2));


            System.out.println("END OF THE LINE");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
