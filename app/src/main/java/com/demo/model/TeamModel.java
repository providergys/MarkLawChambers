package com.demo.model;

public class TeamModel {

    String mTitle,mDesc;
    int mProfileImage;

    public TeamModel(String mTitle, String mDesc, int mProfileImage) {
        this.mTitle = mTitle;
        this.mDesc = mDesc;
        this.mProfileImage = mProfileImage;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDesc() {
        return mDesc;
    }

    public void setmDesc(String mDesc) {
        this.mDesc = mDesc;
    }

    public int getmProfileImage() {
        return mProfileImage;
    }

    public void setmProfileImage(int mProfileImage) {
        this.mProfileImage = mProfileImage;
    }
}
