package com.example.user.chatapp;

import android.graphics.Bitmap;

/**
 * Created by user on 11/03/2017.*/

public class Mesage {
    public static final int TYPE_MESSAGE=0;
    public static final int TYPE_LOG=1;
    public static final int TYPE_ACTION=2;


    private int mType;
    private String mMessage;
    private Bitmap mImage;


    public Mesage() {
    }

    public int getmType() {
        return mType;
    }

    public String getmMessage() {
        return mMessage;
    }

    public Bitmap getmImage() {
        return mImage;
    }


    public static class Builder{

        private int mType;
        private String mMessage;
        private Bitmap mImage;


        public Builder(int type) {
            this.mType = type;
        }

        public Builder message(String messagem) {
            this.mMessage = messagem;
            return this;
        }

        public Builder image(Bitmap imagem) {
         this.mImage = imagem;
            return this;
        }


        public Mesage build(){

            Mesage m=new Mesage();
            m.mType=mType;
            m.mMessage=mMessage;
            m.mImage=mImage;
            return m;

        }


    }
}

