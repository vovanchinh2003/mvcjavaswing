package pxu.com.model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Blob;
import java.sql.Date;

/**
 *
 * @author chinh
 */
public class StudentModel {

    private String student_id, student_name, faculty, major, gmail, phone_number, room_id, gender, hometown, status;
    private int violation_count;
    private Date check_in_date, birth_date;
    private byte[] student_image;

    public StudentModel() {
    }

    public StudentModel(String student_id, String student_name, String faculty, String major, String gmail, String phone_number, String room_id, String gender, String hometown, String status, int violation_count, Date check_in_date, Date birth_date, byte[] student_image) {
        this.student_id = student_id;
        this.student_name = student_name;
        this.faculty = faculty;
        this.major = major;
        this.gmail = gmail;
        this.phone_number = phone_number;
        this.room_id = room_id;
        this.gender = gender;
        this.hometown = hometown;
        this.status = status;
        this.violation_count = violation_count;
        this.check_in_date = check_in_date;
        this.birth_date = birth_date;
        this.student_image = student_image;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getViolation_count() {
        return violation_count;
    }

    public void setViolation_count(int violation_count) {
        this.violation_count = violation_count;
    }

    public Date getCheck_in_date() {
        return check_in_date;
    }

    public void setCheck_in_date(Date check_in_date) {
        this.check_in_date = check_in_date;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public byte[] getStudent_image() {
        return student_image;
    }

    public void setStudent_image(byte[] student_image) {
        this.student_image = student_image;
    }

  

   
}
