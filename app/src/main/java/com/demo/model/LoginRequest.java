
package com.demo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    @SerializedName("imonumber")
    @Expose
    private String imonumber;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("user_id")
    @Expose
    private String userId;
    public String getImonumber() {
        return imonumber;
    }

    public void setImonumber(String imonumber) {
        this.imonumber = imonumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
