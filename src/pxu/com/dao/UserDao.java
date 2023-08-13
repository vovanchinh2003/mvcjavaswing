/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pxu.com.dao;

import pxu.com.connect.connecting;
import pxu.com.model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

//    public static ArrayList<UserModel> getall() throws SQLException {
//        ArrayList<UserModel> list = new ArrayList<>();
//
//        Connection conn = connecting.getConnection();
//        try {
//            Statement stmt = conn.createStatement();
//            String sql = "select * from NGUOI_DUNG ";
//            ResultSet rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                UserModel n = new UserModel();
//                n.setMand(rs.getString(1));
//                n.setHotennd(rs.getString(2));
//                n.setDienthoai(rs.getString(3));
//                n.setDiachi(rs.getString(4));
//                n.setTendangnhap(rs.getString(5));
//                n.setChucvu(rs.getString(6));
//                n.setMatkhau(rs.getString(7));
//                list.add(n);
//            }
//            rs.close();
//            stmt.close();
//            conn.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return list;
//    }
//
//    public boolean insertin(UserModel m) throws SQLException {
//        String sql = "INSERT INTO NGUOI_DUNG (mand,hotennd,dienthoai,diachi,tendangnhap,chucvu,matkhau) VALUES (?, ?, ?, ?, ?, ?, ?)";
//        try (Connection conn = connecting.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
//            prstt.setString(1, m.getMand());
//            prstt.setString(2, m.getHotennd());
//            prstt.setString(3, m.getDienthoai());
//            prstt.setString(4, m.getDiachi());
//            prstt.setString(5, m.getChucvu());
//            prstt.setString(6, m.getTendangnhap());
//            prstt.setString(7, m.getMatkhau());
//            return prstt.executeUpdate() > 0;
//        }
//    }
//
//    public boolean update(UserModel m) throws SQLException {
//        String sql = "update NGUOI_DUNG set hotennd=?,dienthoai=?,diachi=?,tendangnhap=?,chucvu=?,matkhau=?"
//                + " where mand=?";
//        try (Connection conn = connecting.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
//            prstt.setString(7, m.getMand());
//            prstt.setString(1, m.getHotennd());
//            prstt.setString(2, m.getDienthoai());
//            prstt.setString(3, m.getDiachi());
//            prstt.setString(4, m.getTendangnhap());
//            prstt.setString(5, m.getChucvu());
//            prstt.setString(6, m.getMatkhau());
//            return prstt.executeUpdate() > 0;
//        }
//    }
//
//    public boolean delete(UserModel m) throws SQLException {
//        String sql = "delete from NGUOI_DUNG"
//                + " where mand=?";
//        try (Connection conn = connecting.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
//            prstt.setString(1, m.getMand());
//            return prstt.executeUpdate() > 0;
//        }
//    }
//
//    public UserModel FindManv(String mand) throws SQLException, ClassNotFoundException {
//        String sql = "select * from NGUOI_DUNG where mand=?";
//        try (
//                Connection conn = connecting.getConnection(); PreparedStatement prst = conn.prepareStatement(sql);) {
//            prst.setString(1, mand);
//            try (ResultSet rs = prst.executeQuery();) {
//                if (rs.next()) {
//                    UserModel m = new UserModel();
//                    m.setMand(rs.getString("mand"));
//                    m.setHotennd(rs.getString("hotennd"));
//                    m.setDienthoai(rs.getString("dienthoai"));
//                    m.setDiachi(rs.getString("diachi"));
//                    m.setChucvu(rs.getString("chucvu"));
//                    m.setMatkhau(rs.getString("matkhau"));
//                    return m;
//                }
//            }
//            return null;
//        }
//    }
}
