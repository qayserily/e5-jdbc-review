package practice;

import utilities.DBUtils;

public class VyTrackDB_Practice {
    public static void main(String[] args) {
        String jdbc_url ="jdbc:mysql://3.87.155.124:3306/bitnami_orocrm";
        String username="qa_user";
        String password="qa_user";

        DBUtils.createConnection(jdbc_url,username,password);

        System.out.println("DBUtils.getQueryResultMap(\"SELECT * FROM orocrm_contact\") = "
                            + DBUtils.getQueryResultMap("SELECT * FROM orocrm_contact"));


    }
}
