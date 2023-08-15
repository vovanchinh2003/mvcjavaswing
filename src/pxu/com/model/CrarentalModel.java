/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pxu.com.model;

import java.sql.Date;

/**
 *
 * @author chinh
 */
public class CrarentalModel {

    private int car_id;
    private String student_id, user_id, car_type, license_plate;
    private Date submission_date;

    public CrarentalModel(int car_id, String student_id, String user_id, String car_type, String license_plate, Date submission_date) {
        this.car_id = car_id;
        this.student_id = student_id;
        this.user_id = user_id;
        this.car_type = car_type;
        this.license_plate = license_plate;
        this.submission_date = submission_date;
    }

    public CrarentalModel() {
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
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

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public Date getSubmission_date() {
        return submission_date;
    }

    public void setSubmission_date(Date submission_date) {
        this.submission_date = submission_date;
    }
    
    
    
}
