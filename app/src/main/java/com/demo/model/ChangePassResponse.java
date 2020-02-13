package com.demo.model;

public class ChangePassResponse {

    /**
     * status : ok
     * RespCode : 1018
     * success : true
     * Message : Password updated successfully.
     */

    private String status;
    private String RespCode;
    private String success;
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

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }
}
