
package com.demo.model;

import java.util.List;

public class GetchatResponse {


    /**
     * status : ok
     * RespCode : 7016
     * success : true
     * Message : Chats fetched successfully.
     * chat_message : [{"id":"1","sender_id":"1","receiver_id":"66","sender_name":"mlc_admin","receiver_name":"verma","messages":"","audio_file":"http://182.74.186.138/marklaw/wp-content/uploads/2020/02/audio_19-Feb-2020-5e4cea6337cff.mp3","isSender":false},{"id":"2","sender_id":"1","receiver_id":"66","sender_name":"mlc_admin","receiver_name":"verma","messages":"","audio_file":"http://182.74.186.138/marklaw/wp-content/uploads/2020/02/audio_19-Feb-2020-5e4cea6337cff.mp3","isSender":false},{"id":"3","sender_id":"1","receiver_id":"66","sender_name":"mlc_admin","receiver_name":"verma","messages":"","audio_file":"http://182.74.186.138/marklaw/wp-content/uploads/2020/02/audio_24-Feb-2020-5e5378009ca90.mp3","isSender":false},{"id":"4","sender_id":"66","receiver_id":"1","sender_name":"verma","receiver_name":"mlc_admin","messages":"dfgh","audio_file":"","isSender":true},{"id":"5","sender_id":"66","receiver_id":"1","sender_name":"verma","receiver_name":"mlc_admin","messages":"hello","audio_file":"","isSender":true},{"id":"9","sender_id":"1","receiver_id":"66","sender_name":"mlc_admin","receiver_name":"verma","messages":"how are you?","audio_file":"","isSender":false},{"id":"14","sender_id":"1","receiver_id":"66","sender_name":"mlc_admin","receiver_name":"verma","messages":"hello","audio_file":"","isSender":false},{"id":"18","sender_id":"66","receiver_id":"1","sender_name":"verma","receiver_name":"mlc_admin","messages":null,"audio_file":"http://182.74.186.138/marklaw/wp-content/uploads/2020/02/audio_24-Feb-2020-5e53c7a147f4f.mp3","isSender":true}]
     */

    private String status;
    private String RespCode;
    private String success;
    private String Message;
    private List<ChatMessageBean> chat_message;

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

    public List<ChatMessageBean> getChat_message() {
        return chat_message;
    }

    public void setChat_message(List<ChatMessageBean> chat_message) {
        this.chat_message = chat_message;
    }

    public static class ChatMessageBean {
        /**
         * id : 1
         * sender_id : 1
         * receiver_id : 66
         * sender_name : mlc_admin
         * receiver_name : verma
         * messages :
         * audio_file : http://182.74.186.138/marklaw/wp-content/uploads/2020/02/audio_19-Feb-2020-5e4cea6337cff.mp3
         * isSender : false
         */

        private String id;
        private String sender_id;
        private String receiver_id;
        private String sender_name;
        private String receiver_name;
        private String messages;
        private String audio_file;
        private boolean isSender;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSender_id() {
            return sender_id;
        }

        public void setSender_id(String sender_id) {
            this.sender_id = sender_id;
        }

        public String getReceiver_id() {
            return receiver_id;
        }

        public void setReceiver_id(String receiver_id) {
            this.receiver_id = receiver_id;
        }

        public String getSender_name() {
            return sender_name;
        }

        public void setSender_name(String sender_name) {
            this.sender_name = sender_name;
        }

        public String getReceiver_name() {
            return receiver_name;
        }

        public void setReceiver_name(String receiver_name) {
            this.receiver_name = receiver_name;
        }

        public String getMessages() {
            return messages;
        }

        public void setMessages(String messages) {
            this.messages = messages;
        }

        public String getAudio_file() {
            return audio_file;
        }

        public void setAudio_file(String audio_file) {
            this.audio_file = audio_file;
        }

        public boolean isIsSender() {
            return isSender;
        }

        public void setIsSender(boolean isSender) {
            this.isSender = isSender;
        }
    }
}
