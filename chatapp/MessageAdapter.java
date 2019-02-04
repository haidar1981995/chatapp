package com.example.user.chatapp;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.support.v7.widget.RecyclerView.*;

/**
 * Created by user on 11/03/2017.
*/

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>{
    private List<Mesage> mMessages;//list of messages to view it

    //constructor
    public MessageAdapter(List<Mesage> nMessages)
    {
        this.mMessages = nMessages;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.message,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewholder, int position) {
        Mesage message=mMessages.get(position);
        viewholder.setMessage(message.getmMessage());
        viewholder.setImage(message.getmImage());
    }


    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mMessages.get(position).getmType();
    }




    //inner class to detrmine type of message
    public class ViewHolder extends RecyclerView.ViewHolder{
            public ImageView mImageView;
            public TextView mMessageView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView=(ImageView)itemView.findViewById(R.id.imageview);
            mMessageView=(TextView)itemView.findViewById(R.id.messageview);
        }

        //set message on recyclerview
        public  void setMessage(String message){
            if(mMessageView==null) return;
            if(message==null) return;
            mMessageView.setText(message);
        }



        public void setImage(Bitmap map){
            if(mImageView==null) return;
            if(map==null) return;
            mImageView.setImageBitmap(map);


        }


    }
}

