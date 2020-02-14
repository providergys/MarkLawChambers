package com.demo.retroutility;

import java.util.List;

public class PracticeResponse {


    /**
     * status : ok
     * RespCode : 1003
     * success : true
     * Message : Data Fetched Successfully
     * posts_data : [{"id":1457,"date":"2020-02-12 04:36:12","title":"Media & Entertainment","content":"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has ","image":"http://182.74.186.138/marklaw/wp-content/uploads/2020/02/1.jpg"},{"id":1454,"date":"2020-02-12 04:36:02","title":"Conveyancing & Corporate","content":"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has ","image":"http://182.74.186.138/marklaw/wp-content/uploads/2019/08/2.jpg"},{"id":1451,"date":"2020-02-12 04:35:52","title":"Trademark Agent","content":"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has ","image":"http://182.74.186.138/marklaw/wp-content/uploads/2019/08/1.jpg"},{"id":1448,"date":"2020-02-12 04:35:42","title":"Civil Litigation","content":"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has ","image":"http://182.74.186.138/marklaw/wp-content/uploads/2020/02/2.jpg"},{"id":1445,"date":"2020-02-12 04:35:33","title":"Criminal Litigation","content":"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has ","image":"http://182.74.186.138/marklaw/wp-content/uploads/2019/08/5.jpg"}]
     */

    private String status;
    private String RespCode;
    private String success;
    private String Message;
    private List<PostsDataBean> posts_data;

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

    public List<PostsDataBean> getPosts_data() {
        return posts_data;
    }

    public void setPosts_data(List<PostsDataBean> posts_data) {
        this.posts_data = posts_data;
    }

    public static class PostsDataBean {
        /**
         * id : 1457
         * date : 2020-02-12 04:36:12
         * title : Media & Entertainment
         * content : Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has
         * image : http://182.74.186.138/marklaw/wp-content/uploads/2020/02/1.jpg
         */

        private int id;
        private String date;
        private String title;
        private String content;
        private String image;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
