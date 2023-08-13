package pxu.com.dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import pxu.com.connect.connecting;
import pxu.com.model.ViolationModel;

/**
 *
 * @author chinh
 */
public class ViolationDao {

    public static ArrayList<ViolationModel> getall() throws SQLException {
        ArrayList<ViolationModel> list = new ArrayList<>();

        Connection conn = connecting.getConnection();
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from VIOLATION ";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ViolationModel k = new ViolationModel();
                k.setViolation_id(rs.getInt(1));
                k.setViolation_type(rs.getString(2));
                k.setDescription(rs.getString(3));
                k.setPenalty_method(rs.getString(4));
                k.setStudent_id(rs.getString(5));
                k.setUser_id(rs.getString(6));
                k.setViolation_date(rs.getDate(7));
                list.add(k);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public boolean insertin(ViolationModel m) throws SQLException {
        String sql = "INSERT INTO VIOLATION (violation_type,description,penalty_method,student_id,user_id,violation_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = connecting.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
//            prstt.setInt(1, m.getMakl());
            prstt.setString(1, m.getViolation_type());
            prstt.setString(2, m.getDescription());
            prstt.setString(3, m.getPenalty_method());
            prstt.setString(4, m.getStudent_id());
            prstt.setString(5, m.getUser_id());
            prstt.setDate(6, m.getViolation_date());
            return prstt.executeUpdate() > 0;
        }
    }

    public boolean update(ViolationModel m) throws SQLException {
        String sql = "update VIOLATION set violation_type=?,description=?,penalty_method=?,student_id=?,user_id=?,violation_date=? "
                + " where violation_id=?";
        try (Connection conn = connecting.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
            prstt.setString(1, m.getViolation_type());
            prstt.setString(2, m.getDescription());
            prstt.setString(3, m.getPenalty_method());
            prstt.setString(4, m.getStudent_id());
            prstt.setString(5, m.getUser_id());
            prstt.setDate(6, m.getViolation_date());
            prstt.setInt(7, m.getViolation_id());
            return prstt.executeUpdate() > 0;
        }
    }

    public boolean delete(ViolationModel m) throws SQLException {
        String sql = "delete from VIOLATION"
                + " where violation_id=?";
        try (Connection conn = connecting.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
            prstt.setInt(1, m.getViolation_id());
            return prstt.executeUpdate() > 0;
        }
    }

    public ViolationModel FindManv(int violation_id) throws SQLException, ClassNotFoundException {
        String sql = "select * from VIOLATION where violation_id=?";
        try (
                Connection conn = connecting.getConnection(); PreparedStatement prst = conn.prepareStatement(sql);) {
            prst.setInt(1, violation_id);
            try (ResultSet rs = prst.executeQuery();) {
                if (rs.next()) {
                    ViolationModel k = new ViolationModel();
                    k.setViolation_id(rs.getInt("violation_id"));
                    k.setViolation_type(rs.getString("violation_type"));
                    k.setDescription(rs.getString("description"));
                    k.setStudent_id(rs.getString("student_id"));
                    k.setUser_id(rs.getString("user_id"));
                    k.setViolation_date(rs.getDate("violation_date"));
                    return k;
                }
            }
            return null;
        }
    }
}
