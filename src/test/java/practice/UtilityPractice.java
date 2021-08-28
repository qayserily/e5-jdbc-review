package practice;

import utilities.DBUtils;

public class UtilityPractice {
    public static void main(String[] args) {

        String dbUrl = "jdbc:oracle:thin:@52.87.154.190:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";

        DBUtils.createConnection(dbUrl,dbUsername,dbPassword);

        System.out.println("DBUtils.getQueryResultMap(\"SELECT * FROM REGIONS\") = "
                            + DBUtils.getQueryResultMap("SELECT * FROM REGIONS"));


        DBUtils.destroy();
    }
}
