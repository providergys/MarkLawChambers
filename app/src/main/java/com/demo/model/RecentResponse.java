package com.demo.model;

import java.util.List;

public class RecentResponse {

    /**
     * status : ok
     * RespCode : 1003
     * success : true
     * Message : Data Fetched Successfully
     * posts_data : [{"id":907,"date":"2019-03-24 09:43:44","title":"Singapore : Mobile Apps & Contract of Development, 24.03.2019","content":"Mobile Apps &amp; Contract of Development On 24th March 2019, our Managing Partner was invited to share his views and legal insight on the Contract of Development for Mobile Apps. ","image":"https://mark-lawchambers.com/wp-content/uploads/2019/02/WhatsApp-Image-2019-03-28-at-12.15.03-AM.jpeg"},{"id":926,"date":"2019-03-28 12:05:21","title":"Legal Career Workshop at MMU \u2013 30.11.2018","content":"Legal Career Workshop in MMU On November 30th 2018, Our Managing Partner was invited to Multimedia University (Alma Mater) to speak on a topic \u201cHow to set up your legal ","image":"https://mark-lawchambers.com/wp-content/uploads/2019/02/WhatsApp-Image-2019-03-28-at-12.19.51-AM.jpeg"},{"id":1134,"date":"2019-04-05 08:04:46","title":"Taiwan :- Mobile Apps & Contract Development, 05.04.2019","content":"Mobile Apps &amp; Contract Development On 5th of April 2019, Our Managing Partner was invited to share his views and legal insight in pertaining to Contract Development for Mobile Apps. ","image":"https://mark-lawchambers.com/wp-content/uploads/2019/06/taiwan-processed.png"},{"id":929,"date":"2019-04-15 10:13:59","title":"Malaysia :- Mobile Apps & Intellectual Rights, 23.11.2018","content":"Mobile Apps &amp; Intellectual Rights On 23.11.2018, our Managing Partner was invited to speak at Eastin Hotel, Malaysia on Legal Consideration &amp; Intellectual Rights in Mobile Apps Development. The 3 ","image":"https://mark-lawchambers.com/wp-content/uploads/2019/02/WhatsApp-Image-2019-03-28-at-12.14.18-AM.jpeg"},{"id":1142,"date":"2019-05-15 09:40:17","title":"FM Interview : Cityplus FM \u2013 15.05.2019","content":"Mobile Apps &amp; Intellectual Rights On May 15 2019, Our Managing Partner, Aaron Mark was invited for an interview with Cityplus FM on the topic of Labour Laws. The interview ","image":"https://mark-lawchambers.com/wp-content/uploads/2019/06/WhatsApp-Image-2019-06-19-at-2.23.17-AM.jpeg"},{"id":1177,"date":"2019-05-19 16:15:07","title":"Hanoi, Vietnam :- Life in Business and Startup, 19.05.2019","content":"Life in Business and Startup Our 19.05.2019, our partner Mr. Aaron Mark Pius and Mr. Ow Ji Jim was invited to Hanoi, Vietnam to share their experience as start-up and ","image":"https://mark-lawchambers.com/wp-content/uploads/2019/06/IMG20180518213341.jpg"},{"id":1161,"date":"2019-05-29 10:49:39","title":"FM Interview : Melody FM","content":"Animal Rights On May 29 2019, Our Managing Partner, Aaron Mark Pius was invited for an interview with Melody FM on the topic of Animal Rights. The interview session was ","image":"https://mark-lawchambers.com/wp-content/uploads/2019/06/WhatsApp-Image-2019-06-19-at-2.24.07-AM.jpeg"},{"id":1165,"date":"2019-06-24 10:55:07","title":"Mark Law Chambers Football Team \u2013 RIMBAYU FC","content":"Mark Law Chambers Football Team \u2013 RIMBAYU FC Mark Law Chambers is a proud sponsor to an amateur football club, Rimbayu FC. Rimbayu FC will be playing against several other ","image":"https://mark-lawchambers.com/wp-content/uploads/2019/06/WhatsApp-Image-2019-06-19-at-2.28.26-AM1.jpeg"},{"id":1233,"date":"2019-08-17 15:04:01","title":"Asia CEO Forum 2019","content":"From the most left, Yoga (Cityplus FM MC), Zhen Wei (The Alley CEO), Chui Ling (Celebrity Enterpreneur), YB Lim Boon Chye (Deputy Minister of Health), Cliva Tan (VIC Holdings CEO) ","image":"https://mark-lawchambers.com/wp-content/uploads/2019/08/ASIA-CEO-FORUM-.jpg"}]
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
         * id : 907
         * date : 2019-03-24 09:43:44
         * title : Singapore : Mobile Apps & Contract of Development, 24.03.2019
         * content : Mobile Apps &amp; Contract of Development On 24th March 2019, our Managing Partner was invited to share his views and legal insight on the Contract of Development for Mobile Apps.
         * image : https://mark-lawchambers.com/wp-content/uploads/2019/02/WhatsApp-Image-2019-03-28-at-12.15.03-AM.jpeg
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
