
package com.demo.model;

public class LoginResponse {


    /**
     * status : ok
     * RespCode : 1004
     * success : true
     * Message : SignUp Successfully
     * user_data : {"id":14,"useremail":"qaxxzzz4sdzssexv@gmail.com","username":"devselxxxszsxoper","password":"123456","mobile_number":"1234567891","usertype":"Non Client"}
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
         * id : 14
         * useremail : qaxxzzz4sdzssexv@gmail.com
         * username : devselxxxszsxoper
         * password : 123456
         * mobile_number : 1234567891
         * usertype : Non Client
         */

        private int id;
        private String useremail;
        private String username;
        private String password;
        private String mobile_number;
        private String usertype;

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

        public String getUsertype() {
            return usertype;
        }

        public void setUsertype(String usertype) {
            this.usertype = usertype;
        }
    }
}
