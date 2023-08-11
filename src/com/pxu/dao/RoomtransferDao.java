package com.pxu.dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.phuxuan.quanlyktx.connectJDBC.Databaseee;
import com.pxu.model.RoomtransferModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author chinh
 */
public class RoomtransferDao {

    public boolean insert(RoomtransferModel c) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO room_transfer (student_id,user_id,previous_room_id,room_id,transfer_date,reason) VALUES (?,?,?,?,?,?)";
        try (Connection conn = Databaseee.getConnection(); PreparedStatement prstt = conn.prepareStatement(sql);) {
            prstt.setString(1, c.getStudent_id());
            prstt.setString(2, c.getUser_id());
            prstt.setString(3, c.getPrevious_room_id());
            prstt.setString(4, c.getRoom_id());
            prstt.setDate(5, c.getTransfer_date());
            prstt.setString(6, c.getReason());
            return prstt.executeUpdate() > 0;
        }
    }
}
