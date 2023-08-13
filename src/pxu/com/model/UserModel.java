package pxu.com.model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author chinh
 */
public class UserModel {

    private String user_id, full_name, phone_number, address, position, password, username, user_image;

    public UserModel() {
    }

    public UserModel(String user_id, String full_name, String phone_number, String address, String position, String password, String username, String user_image) {
        this.user_id = user_id;
        this.full_name = full_name;
        this.phone_number = phone_number;
        this.address = address;
        this.position = position;
        this.password = password;
        this.username = username;
        this.user_image = user_image;

    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_image() {
        return user_image;
    }

    @Override
    public String toString() {
        return "UserModel{" + "user_id=" + user_id + ", full_name=" + full_name + ", phone_number=" + phone_number + ", address=" + address + ", position=" + position + ", password=" + password + ", username=" + username + ", user_image=" + user_image + '}';
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

}
