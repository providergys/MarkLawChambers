package com.demo.model;

public class ForgotPassResponse {

    /**
     * status : ok
     * RespCode : 7013
     * Message : Mail Sent Successfully
     */

    private String status;
    private String RespCode;
    private String Message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRespCode() {
        return RespCode;
    }

    public void setRespCode(String RespCode) {
        this.RespCode = RespCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }
}
