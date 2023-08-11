/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.phuxuan.quanlyktx.connectJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author chinh
 */
public class Databaseee {

    private static Connection connection;

//    public Databaseee() throws SQLException {
//        getConnection();
//    }
    public static Connection getConnection() throws SQLException {
        try {
            // kết nối với cơ sở dữ liệu
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ql_ktxx", "root", "chinh2003");
            Statement statement = connection.createStatement();
            // lấy dữ liệu từ cơ sở dữ liệu
            System.out.println("Kết nối thành công !!!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) throws SQLException {
        Databaseee.getConnection();
        new Databaseee();
    }
}
