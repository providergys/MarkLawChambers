
package com.demo.model;

public class SendMessageResponse {


    /**
     * status : ok
     * RespCode : 7015
     * success : true
     * Message : Message sent successfully.
     * chat_message : {"sender_id":"44","receiver_id":"1","audio_file":"http://182.74.186.138/marklaw/wp-content/uploads/2020/02/audio_18-Feb-2020-5e4bc42fa560f.mp3","date":"2020-02-18 11:02:07"}
     */

    private String status;
    private String RespCode;
    private String success;
    private String Message;
    private ChatMessageBean chat_message;

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

    public ChatMessageBean getChat_message() {
        return chat_message;
    }

    public void setChat_message(ChatMessageBean chat_message) {
        this.chat_message = chat_message;
    }

    public static class ChatMessageBean {
        /**
         * sender_id : 44
         * receiver_id : 1
         * audio_file : http://182.74.186.138/marklaw/wp-content/uploads/2020/02/audio_18-Feb-2020-5e4bc42fa560f.mp3
         * date : 2020-02-18 11:02:07
         */

        private String sender_id;
        private String receiver_id;
        private String audio_file;
        private String date;

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

        public String getAudio_file() {
            return audio_file;
        }

        public void setAudio_file(String audio_file) {
            this.audio_file = audio_file;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}
