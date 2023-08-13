/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pxu.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import pxu.com.connect.connecting;
import pxu.com.model.CrarentalModel;

/**
 *
 * @author chinh
 */
public class CrarentalDao {

    public static ArrayList<CrarentalModel> getall() throws SQLException {
        ArrayList<CrarentalModel> list = new ArrayList<>();

        Connection conn = connecting.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from CAR_RENTAL ";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                CrarentalModel x = new CrarentalModel();
                x.setCar_id(rs.getInt(1));
                x.setStudent_id(rs.getString(2));
                x.setUser_id(rs.getString(3));
                x.setCar_type(rs.getString(4));
                x.setLicense_plate(rs.getString(5));
                x.setSubmission_date(rs.getDate(6));
                list.add(x);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public boolean insertin(CrarentalModel x) throws SQLException {
        String sql = "INSERT INTO CAR_RENTAL (student_id,user_id,car_type,license_plate,submission_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connecting.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
//            prstt.setInt(1, m.getMakl());
            prstt.setString(1, x.getStudent_id());
            prstt.setString(2, x.getUser_id());
            prstt.setString(3, x.getCar_type());
            prstt.setString(4, x.getLicense_plate());
            prstt.setDate(5, x.getSubmission_date());
            return prstt.executeUpdate() > 0;
        }
    }

    public boolean update(CrarentalModel x) throws SQLException {
        String sql = "update CAR_RENTAL set student_id=?,user_id=?,car_type=?,license_plate=?,submission_date=?"
                + " where car_id=?";
        try (Connection conn = connecting.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
            prstt.setString(1, x.getStudent_id());
            prstt.setString(2, x.getUser_id());
            prstt.setString(3, x.getCar_type());
            prstt.setString(4, x.getLicense_plate());
            prstt.setDate(5, x.getSubmission_date());
            prstt.setInt(6, x.getCar_id());
            return prstt.executeUpdate() > 0;
        }
    }

    public boolean delete(CrarentalModel x) throws SQLException {
        String sql = "delete from CAR_RENTAL "
                + " where car_id=?";
        try (Connection conn = connecting.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
            prstt.setInt(1, x.getCar_id());
            return prstt.executeUpdate() > 0;
        }
    }

    public CrarentalModel FindManv(int maxe) throws SQLException, ClassNotFoundException {
        String sql = "select * from CAR_RENTAL where car_id=?";
        try (
                Connection conn = connecting.getConnection(); PreparedStatement prst = conn.prepareStatement(sql);) {
            prst.setInt(1, maxe);
            try (ResultSet rs = prst.executeQuery();) {
                if (rs.next()) {
                    CrarentalModel x = new CrarentalModel();
                    x.setCar_id(rs.getInt("car_id"));
                    x.setStudent_id(rs.getString("student_id"));
                    x.setUser_id(rs.getString("user_id"));
                    x.setCar_type(rs.getString("car_type"));
                    x.setLicense_plate(rs.getString("license_plate"));
                    x.setSubmission_date(rs.getDate("submission_date"));
                    return x;
                }
            }
            return null;
        }
    }

}
