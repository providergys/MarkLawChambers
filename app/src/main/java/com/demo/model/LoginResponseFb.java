package com.demo.model;

public class LoginResponseFb {


    /**
     * status : ok
     * RespCode : 1010
     * success : true
     * Message : Login Successfully
     * user_data : {"id":31,"useremail":"qa4dev@gmail.com","username":"nisha","social_logintype":"A"}
     */

    private String status;
    private String RespCode;
    private String success;
    private String Message;
    private UserDataBean user_data;

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

    public UserDataBean getUser_data() {
        return user_data;
    }

    public void setUser_data(UserDataBean user_data) {
        this.user_data = user_data;
    }

    public static class UserDataBean {
        /**
         * id : 31
         * useremail : qa4dev@gmail.com
         * username : nisha
         * social_logintype : A
         */

        private int id;
        private String useremail;
        private String username;
        private String social_logintype;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUseremail() {
            return useremail;
        }

        public void setUseremail(String useremail) {
            this.useremail = useremail;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getSocial_logintype() {
            return social_logintype;
        }

        public void setSocial_logintype(String social_logintype) {
            this.social_logintype = social_logintype;
        }
    }
}
