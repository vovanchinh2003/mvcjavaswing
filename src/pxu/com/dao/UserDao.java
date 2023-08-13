/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pxu.com.dao;

import java.sql.Blob;
import pxu.com.connect.connecting;
import pxu.com.model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.sql.rowset.serial.SerialBlob;

/**
 *
 * @author chinh
 */
public class UserDao {

    public UserModel checkLogin(String username, String password) throws SQLException, ClassNotFoundException {
        String sql = "select user_id, full_name,username,password,position from user"
                + " where username=? and password=?";
        try (
                Connection conn = connecting.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
            prstt.setString(1, username);
            prstt.setString(2, password);
            try (ResultSet rs = prstt.executeQuery();) {
                if (rs.next()) {
                    UserModel nd = new UserModel();
                    nd.setUsername(username);
                    nd.setUser_id(rs.getString("user_id"));
                    nd.setPosition(rs.getString("position"));
                    nd.setFull_name(rs.getString("full_name"));
                    return nd;
                }
            }
        }
        return null;
    }

    public static ArrayList<UserModel> getall() throws SQLException {
        ArrayList<UserModel> list = new ArrayList<>();

        Connection conn = connecting.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from user";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                UserModel user = new UserModel();
                user.setUser_id(rs.getString(1));
                user.setFull_name(rs.getString(2));
                user.setPhone_number(rs.getString(3));
                user.setAddress(rs.getString(4));
                user.setUsername(rs.getString(5));
                user.setPosition(rs.getString(6));
                user.setPassword(rs.getString(7));
                list.add(user);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public boolean insertin(UserModel user) throws SQLException {
        String sql = "INSERT INTO user (user_id, full_name, phone_number, address, position,username, password, user_image) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
        try (Connection conn = connecting.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
            prstt.setString(1, user.getUser_id());
            prstt.setString(2, user.getFull_name());
            prstt.setString(3, user.getPhone_number());
            prstt.setString(4, user.getAddress());
            prstt.setString(5, user.getPosition());
            prstt.setString(6, user.getUsername());
            prstt.setString(7, user.getPassword());
            prstt.setString(8, user.getUser_image());
            return prstt.executeUpdate() > 0;
        }
    }

    public UserModel FindManv(String user) throws SQLException, ClassNotFoundException {
        String sql = "select * from user where user_id=?";
        try (
                Connection conn = connecting.getConnection(); PreparedStatement prst = conn.prepareStatement(sql);) {
            prst.setString(1, user);
            try (ResultSet rs = prst.executeQuery();) {
                if (rs.next()) {
                    UserModel users = new UserModel();
                    users.setUser_id(rs.getString("user_id"));
                    users.setFull_name(rs.getString("full_name"));
                    users.setPhone_number(rs.getString("phone_number"));
                    users.setAddress(rs.getString("address"));
                    users.setPosition(rs.getString("position"));
                    users.setUsername(rs.getString("username"));
                    users.setPassword(rs.getString("password"));
                    return users;
                }
            }
            return null;
        }
    }

    public boolean update(UserModel user) throws SQLException {
        String sql = "UPDATE user SET full_name = ?, phone_number = ?, address = ?, position = ?, username = ?, password = ?, user_image = ? WHERE user_id = ?";
        try (Connection conn = connecting.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql)) {
            prstt.setString(1, user.getFull_name());
            prstt.setString(2, user.getPhone_number());
            prstt.setString(3, user.getAddress());
            prstt.setString(4, user.getPosition());
            prstt.setString(5, user.getUsername());
            prstt.setString(6, user.getPassword());
            prstt.setString(7, user.getUser_image());
            prstt.setString(8, user.getUser_id());

            return prstt.executeUpdate() > 0;
        }
    }

    public boolean delete(UserModel user_id) throws SQLException {
        String sql = "DELETE FROM user WHERE user_id = ?";
        try (Connection conn = connecting.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql)) {
            prstt.setString(1, user_id.getUser_id());

            return prstt.executeUpdate() > 0;
        }
    }

}
