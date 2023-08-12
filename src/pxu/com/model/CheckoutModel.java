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
public class CheckoutModel {

    private int checkout_id;
    private String student_id, user_id, room_id, reason;
    private Date checkout_date;

    public CheckoutModel() {
    }

    public CheckoutModel(int checkout_id, String student_id, String user_id, String room_id, String reason, Date checkout_date) {
        this.checkout_id = checkout_id;
        this.student_id = student_id;
        this.user_id = user_id;
        this.room_id = room_id;
        this.reason = reason;
        this.checkout_date = checkout_date;
    }

    public int getCheckout_id() {
        return checkout_id;
    }

    public void setCheckout_id(int checkout_id) {
        this.checkout_id = checkout_id;
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

    public Date getCheckout_date() {
        return checkout_date;
    }

    public void setCheckout_date(Date checkout_date) {
        this.checkout_date = checkout_date;
    }

}
