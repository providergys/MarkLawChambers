
package com.demo.model;

public class LoginRequest {


    /**
     * username : develxxoper
     * email : qa4dexv@gmail.com
     * password : 123456
     * mobile_number : 1234567891
     */

    private String username;
    private String email;
    private String password;
    private String mobile_number;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }
}
