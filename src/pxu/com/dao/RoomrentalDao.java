package pxu.com.dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import pxu.com.connect.connecting;
import pxu.com.model.RoomrentalModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author chinh
 */
public class RoomrentalDao {

    public boolean insert(RoomrentalModel t) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO room_rental (student_id,user_id,room_id,rental_date,status) VALUES (?,?,?,?,?)";
        try (Connection conn = connecting.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
            prstt.setString(1, t.getStudent_id());
            prstt.setString(2, t.getUser_id());
            prstt.setString(3, t.getRoom_id());
            prstt.setDate(4, t.getRental_date());
            prstt.setString(5, t.getStatus());
            return prstt.executeUpdate() > 0;
        }
    }
}
