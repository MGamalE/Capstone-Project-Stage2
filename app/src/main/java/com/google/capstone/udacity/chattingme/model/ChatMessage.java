package com.google.capstone.udacity.chattingme.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mohammad on 17/02/2018.
 */

public class ChatMessage implements Parcelable {

    private String text;
    private String name;
    private String photoUrl;

    public ChatMessage() {

    }

    public ChatMessage(String text, String name, String photoUrl) {
        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
    }

    protected ChatMessage(Parcel in) {
        text = in.readString();
        name = in.readString();
        photoUrl = in.readString();
    }

    public static final Creator<ChatMessage> CREATOR = new Creator<ChatMessage>() {
        @Override
        public ChatMessage createFromParcel(Parcel in) {
            return new ChatMessage(in);
        }

        @Override
        public ChatMessage[] newArray(int size) {
            return new ChatMessage[size];
        }
    };

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(text);
        parcel.writeString(name);
        parcel.writeString(photoUrl);
    }
}
