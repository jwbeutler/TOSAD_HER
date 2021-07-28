package brgenerator.persistency;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class pgTargetDao {
        private static final String DB_URL ="jdbc:postgresql://localhost:5432/TosadTARGET";
       private static final String DB_USER = "postgres";
        private static final String DB_PASS = "6december";
        private static Connection conn;

        public Connection getConnection() {
            if(conn == null){
                try {
                    conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                    return conn;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else{
                return conn;
            }
            return null;
        }
    }
