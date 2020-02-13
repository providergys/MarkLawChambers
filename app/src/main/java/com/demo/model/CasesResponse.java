package com.demo.model;

import java.io.Serializable;
import java.util.List;

public class CasesResponse {


    /**
     * status : ok
     * RespCode : 1003
     * success : true
     * Message : Data Fetched Successfully
     * posts_data : [{"id":1321,"date":"2020-02-07 07:46:53","title":"Case - 2 Park File Ref: HHQ/18/2345/A - 18-08/KL","content":"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#8217;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make","username":"developer1","progress":"In progress","cases":[{"file":"https://mark-lawchambers.com/wp-content/uploads/2020/02/sample.pdf","date":"7th January, 2020","case_studies":"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged."}]},{"id":1310,"date":"2020-02-07 06:38:15","title":"Case - 1 Park File Ref: HHQ/18/2345/A - 18-08/KL","content":"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#8217;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make","username":"geet","progress":"Completed","cases":[{"file":"https://mark-lawchambers.com/wp-content/uploads/2020/02/sample.pdf","date":"4th February, 2020","case_studies":"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged."},{"file":"https://mark-lawchambers.com/wp-content/uploads/2020/02/sample.pdf","date":"5th February, 2020","case_studies":"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged."}]}]
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
         * id : 1321
         * date : 2020-02-07 07:46:53
         * title : Case - 2 Park File Ref: HHQ/18/2345/A - 18-08/KL
         * content : Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#8217;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make
         * username : developer1
         * progress : In progress
         * cases : [{"file":"https://mark-lawchambers.com/wp-content/uploads/2020/02/sample.pdf","date":"7th January, 2020","case_studies":"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged."}]
         */

        private int id;
        private String date;
        private String title;
        private String content;
        private String username;
        private String progress;
        private List<CasesBean> cases;

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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getProgress() {
            return progress;
        }

        public void setProgress(String progress) {
            this.progress = progress;
        }

        public Serializable getCases() {
            return (Serializable) cases;
        }

        public void setCases(List<CasesBean> cases) {
            this.cases = cases;
        }

        public  class CasesBean implements Serializable {
            /**
             * file : https://mark-lawchambers.com/wp-content/uploads/2020/02/sample.pdf
             * date : 7th January, 2020
             * case_studies : Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.
             */

            private String file;
            private String date;
            private String case_studies;

            public String getFile() {
                return file;
            }

            public void setFile(String file) {
                this.file = file;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getCase_studies() {
                return case_studies;
            }

            public void setCase_studies(String case_studies) {
                this.case_studies = case_studies;
            }
        }
    }
}
