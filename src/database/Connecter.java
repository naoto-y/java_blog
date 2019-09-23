package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connecter {
    // db接続共通メソッド
    public static Connection DBConnection() {
        Connection conn = null;
        String DB_HOST = null;
        String DB_DRIVER = null;

        String osname = System.getProperty("os.name");
        if(osname.indexOf("Windows")>=0) {
            DB_HOST = "jdbc:mariadb://192.168.179.7";
            DB_DRIVER = "org.mariadb.jdbc.Driver";
        } else if(osname.indexOf("Linux")>=0) {
            DB_HOST = "jdbc:mysql://localhost";
            DB_DRIVER = "com.mysql.jdbc.Driver";
        }

        try {
            Class.forName(DB_DRIVER);
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(
                DB_HOST + "/blog_sys",
                "tomcat",
                "2Bbbbbb\""
            );
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

}
