package com.hillarywando.sqlitedemo;

import com.hillarywando.sqlitedemo.dao.BorderDAO;
import com.hillarywando.sqlitedemo.model.Border;
import com.hillarywando.sqlitedemo.util.DatabaseConnection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hillary Wando
 */
public class SqliteDemo {

    public static void main(String[] args) {
        DatabaseConnection.dbConnect();
        Date now = new Date();
        Timestamp timestamp = new Timestamp(now.getTime());
//        Border border = new Border(0, "127.0.0.1", 3307, 
//                "idms_local", "bioshock", 
//                "kathmandu", timestamp);
        Border border = Border.builder()
                .id(0)
                .server_id("127.0.0.1")
                .server_port(3307)
                .database_name("idms_local")
                .user_name("bioshock")
                .password("kathmandu")
                .date_updated(timestamp)
                .build();
        System.out.println("border: " + border);
        BorderDAO borderDao = new BorderDAO(DatabaseConnection.conn);
        try {
            borderDao.create();
            int rows = borderDao.add(border);
            System.out.println("insert rows: " + rows);
            ArrayList<Border> borders = borderDao.get();
            System.out.println("borders: " + borders);
            Border num2 = borderDao.get(borders.get(0).getId());
            System.out.println("borders number 2: " + num2);
            num2.setUser_name("bioprint");
            num2.setPassword("secret");
            borderDao.update(num2);
//            borderDao.delete(num2.getId());
        } catch (SQLException ex) {
            Logger.getLogger(SqliteDemo.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        System.out.println("Hello World!");
    }
}
