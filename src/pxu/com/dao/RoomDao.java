package pxu.com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pxu.com.connect.connecting;

import pxu.com.model.RoomModel;

public class RoomDao {

    private Connection conn;

    public RoomDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/ql_ktxx", "root", "chinh2003");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<RoomModel> getAllRoom() throws SQLException {
        List<RoomModel> romList = new ArrayList<>();
        PreparedStatement pst = conn.prepareStatement("select * from room");
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            RoomModel roomModel = new RoomModel();
            roomModel.setRoom_id(rs.getString("room_id"));
            roomModel.setRoom_type(rs.getString("room_type"));
            roomModel.setBed_count(rs.getInt("bed_count"));
            roomModel.setOccupancy_count(rs.getInt("occupancy_count"));
            roomModel.setRoom_price(rs.getFloat("room_price"));

            romList.add(roomModel);
        }
        rs.close();
        pst.close();
        return romList;
    }

    public void update(int occupancy_count, String room_id) throws SQLException, ClassNotFoundException {
        String jdbcURL = "jdbc:mysql://localhost:3306/ql_ktxx";
        String dbUsername = "root";
        String dbPassword = "chinh2003";
        String sql = "update room SET occupancy_count= ? where room_id=?";
        try (Connection conn = DriverManager.getConnection(jdbcURL, dbUsername, dbPassword); PreparedStatement prstt = conn.prepareStatement(sql);) {
            prstt.setInt(1, occupancy_count);
            prstt.setString(2, room_id);
            prstt.executeUpdate();
        }
    }

    public boolean insert(RoomModel m) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO ROOM (room_id,room_type,bed_count,occupancy_count,room_price) VALUES (?,?,?,?,?)";
        try (Connection connn = connecting.getConnection(); PreparedStatement prstt = connn.prepareStatement(sql);) {
            prstt.setString(1, m.getRoom_id());
            prstt.setString(2, m.getRoom_type());
            prstt.setInt(3, m.getBed_count());
            prstt.setInt(4, m.getOccupancy_count());
            prstt.setFloat(5, m.getRoom_price());
            return prstt.executeUpdate() > 0;
        }
    }
}
