package pxu.com.model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Date;

/**
 *
 * @author chinh
 */
public class RoomtransferModel {

    private int transfer_id;
    private String student_id, user_id, previous_room_id, room_id, reason;
    private Date transfer_date;

    public RoomtransferModel() {
    }

    public RoomtransferModel(int transfer_id, String student_id, String user_id, String previous_room_id, String room_id, String reason, Date transfer_date) {
        this.transfer_id = transfer_id;
        this.student_id = student_id;
        this.user_id = user_id;
        this.previous_room_id = previous_room_id;
        this.room_id = room_id;
        this.reason = reason;
        this.transfer_date = transfer_date;
    }

    public int getTransfer_id() {
        return transfer_id;
    }

    public void setTransfer_id(int transfer_id) {
        this.transfer_id = transfer_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPrevious_room_id() {
        return previous_room_id;
    }

    public void setPrevious_room_id(String previous_room_id) {
        this.previous_room_id = previous_room_id;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getTransfer_date() {
        return transfer_date;
    }

    public void setTransfer_date(Date transfer_date) {
        this.transfer_date = transfer_date;
    }

}
