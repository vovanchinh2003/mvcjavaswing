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
public class ViolationModel {
    private int violation_id;
    private String violation_type,description,penalty_method,student_id,user_id;
    private Date violation_date;

    public ViolationModel() {
    }

    public ViolationModel(int violation_id, String violation_type, String description, String penalty_method, String student_id, String user_id, Date violation_date) {
        this.violation_id = violation_id;
        this.violation_type = violation_type;
        this.description = description;
        this.penalty_method = penalty_method;
        this.student_id = student_id;
        this.user_id = user_id;
        this.violation_date = violation_date;
    }

    public int getViolation_id() {
        return violation_id;
    }

    public void setViolation_id(int violation_id) {
        this.violation_id = violation_id;
    }

    public String getViolation_type() {
        return violation_type;
    }

    public void setViolation_type(String violation_type) {
        this.violation_type = violation_type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPenalty_method() {
        return penalty_method;
    }

    public void setPenalty_method(String penalty_method) {
        this.penalty_method = penalty_method;
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

    public Date getViolation_date() {
        return violation_date;
    }

    public void setViolation_date(Date violation_date) {
        this.violation_date = violation_date;
    }

        
}
