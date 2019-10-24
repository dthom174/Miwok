package com.example.android.miwok;

import android.content.Context;

public class Word {

    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageID;
    private int mSong;

    public Word(String mDefaultTranslation, String mMiwokTranslation, int song){

        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        mSong = song;
    }

    public Word(String mDefaultTranslation, String mMiwokTranslation, int resource, int song){

        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        mImageID = resource;
        mSong = song;
    }

    public String getmDefaultTranslation(){

        return mDefaultTranslation;
    }

    public String getmMiwokTranslation(){

        return mMiwokTranslation;
    }

    public int getImageID(){

        return mImageID;
    }

    public int getSong(){

        return mSong;
    }
}
