package com.demo.model;

public class ContactUSRequest {


    /**
     * user_id : 66
     * user_name : sunakashi
     * user_email : sverma@gmail.com
     * user_phone : 12345678
     * user_message : test
     */

    private String user_id;
    private String user_name;
    private String user_email;
    private String user_phone;
    private String user_message;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_message() {
        return user_message;
    }

    public void setUser_message(String user_message) {
        this.user_message = user_message;
    }
}
