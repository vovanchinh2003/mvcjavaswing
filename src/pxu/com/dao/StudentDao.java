package pxu.com.dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import pxu.com.connect.connecting;
import pxu.com.model.StudentModel;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.sql.rowset.serial.SerialBlob;

/**
 *
 * @author chinh
 */
public class StudentDao {

//    public static ArrayList<StudentModel> getAll() throws SQLException, ClassNotFoundException {
//        ArrayList<StudentModel> lst = new ArrayList<>();
//        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
//        Connection conn = connecting.getConnection();
//        try {
//            Statement stmt = conn.createStatement();
//            String sql = "select * from SINH_VIEN ";
//            ResultSet rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                StudentModel s = new StudentModel();
//                s.setMasv(rs.getString(1));
//                s.setHotenSV(rs.getString(2));
//                s.setKhoa(rs.getString(3));
//                s.setNganh(rs.getString(4));
//                s.setNgaysinh(rs.getDate(5));
//                s.setCmnd(rs.getString(6));
//                s.setDienthoai(rs.getString(7));
//                s.setGioitinh(rs.getString(8));
//                s.setQuequan(rs.getString(9));
//                s.setMaphong(rs.getString(10));
//                s.setSolanvipham(rs.getInt(11));
//                s.setNgayvao(rs.getDate(12));
//                s.setTrangthai(rs.getString(13));
//                lst.add(s);
//            }
//            rs.close();
//            stmt.close();
//            conn.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return lst;
//    }
    public boolean insert(StudentModel s) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO student (student_id,student_name,faculty,major,birth_date,gmail,phone_number,"
                + " gender,hometown,room_id,violation_count,"
                + " check_in_date,status,student_image) VALUES (?,?, ?, ?, ?, ?,?,?,?,?,?,?,?,?)";
        try (Connection conn = connecting.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
            prstt.setString(1, s.getStudent_id());
            prstt.setString(2, s.getStudent_name());
            prstt.setString(3, s.getFaculty());
            prstt.setString(4, s.getMajor());
            prstt.setDate(5, s.getBirth_date());
            prstt.setString(6, s.getGmail());
            prstt.setString(7, s.getPhone_number());
            prstt.setString(8, s.getGender());
            prstt.setString(9, s.getHometown());
            prstt.setString(10, s.getRoom_id());
            prstt.setInt(11, s.getViolation_count());
            prstt.setDate(12, s.getCheck_in_date());
            prstt.setString(13, s.getStatus());
            if (s.getStudent_image() != null) {
                Blob hinh = new SerialBlob(s.getStudent_image());
                prstt.setBlob(14, hinh);
            } else {
                Blob hinh = null;
                prstt.setBlob(14, hinh);
            }
            return prstt.executeUpdate() > 0;
        }
    }

    public boolean update(StudentModel s) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE student set room_id=?"
                + " where student_id=?";
        try (Connection conn = connecting.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
            prstt.setString(1, s.getRoom_id());
            prstt.setString(2, s.getStudent_id());
            return prstt.executeUpdate() > 0;
        }
    }

    public boolean updatetrangthai(StudentModel s) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE student set status=?"
                + " where student_id=?";
        try (Connection conn = connecting.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
            prstt.setString(1, s.getStatus());
            prstt.setString(2, s.getStudent_id());
            return prstt.executeUpdate() > 0;
        }
    }
//
//    public boolean delete(Phongmodel ph) throws SQLException, ClassNotFoundException {
//        String sql = "delete from phong where maphong=?";
//        Connection conn = connecting.getConnection();
//        PreparedStatement prstt = conn.prepareStatement(sql);
//        {
//            prstt.setString(1, ph.getMaphong());
//            return prstt.executeUpdate() > 0;
//        }
//    }
//

    public StudentModel FindManv(String maSV) throws SQLException, ClassNotFoundException {
        String sql = "select * from student where student_id=?";
        try (Connection conn = connecting.getConnection(); PreparedStatement prst = conn.prepareStatement(sql);) {
            prst.setString(1, maSV);
            try (ResultSet rs = prst.executeQuery();) {
                if (rs.next()) {
                    StudentModel s = new StudentModel();
                    s.setStudent_id(rs.getString("student_id"));
                    return s;
                }
            }
            return null;
        }
    }
}
