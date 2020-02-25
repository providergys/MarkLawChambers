package com.demo.model;

public class PodCastList {
    String mTitle,mDate;
    int mImage;


    public PodCastList(String mTitle, String mDate, int mImage) {
        this.mTitle = mTitle;
        this.mDate = mDate;
        this.mImage = mImage;
    }


    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public int getmImage() {
        return mImage;
    }

    public void setmImage(int mImage) {
        this.mImage = mImage;
    }
}
