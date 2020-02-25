
package com.demo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendMessageRequest {

    @SerializedName("sender_id")
    @Expose
    private String senderId;
    @SerializedName("receiver_id")
    @Expose
    private String receiverId;



    @SerializedName("chatmessage")
    @Expose
    private String chatmessage;



    @SerializedName("audio_file")
    @Expose
    private String audio;

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getChatmessage() {
        return chatmessage;
    }

    public void setChatmessage(String chatmessage) {
        this.chatmessage = chatmessage;
    }

}
