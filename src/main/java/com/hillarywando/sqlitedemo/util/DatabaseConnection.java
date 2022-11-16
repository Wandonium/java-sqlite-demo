package com.hillarywando.sqlitedemo.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Hillary Wando
 */
public class DatabaseConnection {
    
    public static Connection conn;
    
    public static void createDB() {
        String url = "jdbc:sqlite:bioprint.db";

        try (Connection con = DriverManager.getConnection(url)) {
            if (con != null) {
                DatabaseMetaData meta = con.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("The database has been created.");
                conn = con;
            } else {
                System.out.println("Fatal Error! Failed to create the bioprint Sqlite DB!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void dbConnect() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:bioprint.db");
         } catch ( ClassNotFoundException | SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         }
         System.out.println("BioPrint Sqlite DB opened successfully!");
    }
    
}
