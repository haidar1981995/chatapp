package com.example.user.chatapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.nkzawa.socketio.client.IO;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.*;
import java.net.URISyntaxException;

public class DetermineKeys extends Activity {
    static AutoCompleteTextView keyAes;
    String []propKeys;
    Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.determine_keys);
        keyAes=(AutoCompleteTextView) findViewById(R.id.AESKEYValue);
        confirmButton=(Button)findViewById(R.id.confirm_but);
        //apply autoComplete to Key
         propKeys=getResources().getStringArray(R.array.proposalKeys);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,propKeys);
        keyAes.setAdapter(adapter);
        Animation an= AnimationUtils.loadAnimation(getApplicationContext(),R.animator.one);

        //enable sending encryption key only once
        if(RecieveKey.numberOfMess>4)
            confirmButton.setEnabled(false);
        else
            confirmButton.startAnimation(an);
    }


    public void confirmTrans(View v) {
        try {
            if (keyAes.getText().toString().trim().length() <32)
                Toast.makeText(getBaseContext(), "please, Enter Encryption Key with 16 byte", Toast.LENGTH_SHORT).show();
            else
             if(ChatFragment.ea==-1 || ChatFragment.na==-1)
                 Toast.makeText(getBaseContext(), "you should first recieve public key {e,n} from another side", Toast.LENGTH_SHORT).show();

            else
             {
                String message = keyAes.getText().toString().trim();
                ChatFragment.encryptedKey = message;
                String gg = RSAgen.rsaEncrypt(message, ChatFragment.ea, ChatFragment.na);
                Toast.makeText(getBaseContext(), "create Session Succefully , go to chat", Toast.LENGTH_SHORT).show();
                RecieveKey.numberOfMess = 5;
                confirmButton.setEnabled(false);
                sendEncryptionKey(gg);
               }
        }
            catch(Exception hj){
                Toast.makeText(getBaseContext(), "ERROR SENDING ENCRYPTION KEY", Toast.LENGTH_SHORT).show();

            }


    }


            //send encryptionkey to another side
        public static  void sendEncryptionKey(String str){


            JSONObject sendText=new JSONObject();
            try {
                sendText.put("text", str);
                ChatFragment.socket.emit("message", sendText);
                RecieveKey.numberOfMess++;

                keyAes.setText("");

            }

            catch(JSONException ex){

            }

        }

//cpmoute greatest common divesor for 2 numbers
public static long gcd(long a,long b){
    long x=a,y=b;
    while(x!=y) {
        if (x > y) x = x - y;
        else
            y = y - x;
    }
    return  x;
}


    //check if the number if prime or not prime
    public static boolean isPrime(Long a){
        for(int i=2;i<Math.sqrt(a);i++)
            if(a%i==0)
                return false;
        return  true;

    }


}
