package com.hillarywando.sqlitedemo.dao;

import com.hillarywando.sqlitedemo.model.Border;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Hillary Wando
 */
public class BorderDAO {
    
    private final Connection conn;
    
    public BorderDAO(Connection conn) {
        this.conn = conn;
    }
    
    public void create() throws SQLException {
        String query = "create table if not exists Border (" +
                "id integer primary key AUTOINCREMENT," +
                "server_id varchar(100) not null," +
                "server_port varchar(100) not null," + 
                "database_name varchar(30) not null," + 
                "user_name varchar(255) not null," +
                "password varchar(100) not null," +
                "date_updated  timestamp NOT NULL default (datetime('now','localtime')));";
        System.out.println("create query: " + query);
        Statement stmt = conn.createStatement();
        stmt.execute(query);
    }
    
    public int add(Border b) throws SQLException {
        String query = "insert into Border "
                + "(server_id, server_port, database_name,"
                + "user_name, password)" 
                + "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, b.getServer_id());
        ps.setInt(2, b.getServer_port());
        ps.setString(3, b.getDatabase_name());
        ps.setString(4, b.getUser_name());
        ps.setString(5, b.getPassword());
        int n = ps.executeUpdate();
        return n;
    }
    
    public ArrayList<Border> get() throws SQLException {
        String query = "select * from Border";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        ArrayList<Border> ls = new ArrayList();
        while (rs.next()) {
            Border b = new Border();
//            Border b = Border.builder().build();
            b.setId(rs.getInt("id"));
            b.setServer_id(rs.getString("server_id"));
            b.setServer_port(rs.getInt("server_port"));
            b.setDatabase_name(rs.getString("database_name"));
            b.setUser_name(rs.getString("user_name"));
            b.setPassword(rs.getString("password"));
            b.setDate_updated(rs.getTimestamp("date_updated"));
            ls.add(b);
        }
        return ls;
    }
    
    public Border get(int id) throws SQLException {
        String query = "select * from Border where id = " + id;
        PreparedStatement ps = conn.prepareStatement(query);
        Border b = new Border();
//        Border b = Border.builder().build();
        ResultSet rs = ps.executeQuery();
        boolean check = false;
        while (rs.next()) {
            check = true;
            b.setId(rs.getInt("id"));
            b.setServer_id(rs.getString("server_id"));
            b.setServer_port(rs.getInt("server_port"));
            b.setDatabase_name(rs.getString("database_name"));
            b.setUser_name(rs.getString("user_name"));
            b.setPassword(rs.getString("password"));
            b.setDate_updated(rs.getTimestamp("date_updated"));
        }
        if (check == true) return b;
        else return null;
    }
    
    public boolean update(Border b) throws SQLException {
        String query = "update Border set server_id = ?, server_port = ?,"
              + " database_name = ?, user_name = ?, password = ? where id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, b.getServer_id());
        ps.setInt(2, b.getServer_port());
        ps.setString(3, b.getDatabase_name());
        ps.setString(4, b.getUser_name());
        ps.setString(5, b.getPassword());
        ps.setInt(6, b.getId());
        int rows = ps.executeUpdate();
        return rows >= 1;
    }
    
    public boolean delete(int id) throws SQLException {
        String query = "delete from Border where id = " + id;
        PreparedStatement ps = conn.prepareStatement(query);
        int rows = ps.executeUpdate();
        return rows >= 1;
    }
}
