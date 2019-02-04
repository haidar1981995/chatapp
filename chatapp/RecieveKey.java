package com.example.user.chatapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;

import org.json.JSONException;
import org.json.JSONObject;

public class RecieveKey extends Activity {
    public static int numberOfMess =0;//variable to determine sending priority
    EditText pval,qval;
    private static  Long d;
    static Long n;
    Button but;
    static long e=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recieve_key);
        pval=(EditText)findViewById(R.id.p);
        qval=(EditText)findViewById(R.id.q);
        but=(Button)findViewById(R.id.sendbutton);
        Animation an= AnimationUtils.loadAnimation(getApplicationContext(),R.animator.one);

        //to allow send p and q only one time
        if(RecieveKey.numberOfMess>4)
            but.setEnabled(false);
        else
            but.startAnimation(an);
    }




    //compute private key to save it
    private long computeD(long e,Long p1,Long q1){
        long c1=p1,c2=q1;
        long d1;
        long phi=(c1-1)*(c2-1);

        d1=2;

        while((d1*e)%phi!=1)
        {
            d1++;
        }

        return d1;

    }

   // this function send public key{e,n} to another side
    public void sendpAndQ(View v) {
        if (pval.getText().toString().length() == 0)
            Toast.makeText(getBaseContext(), "please,Enter value of P", Toast.LENGTH_SHORT).show();
        else
              if (qval.getText().toString().length() == 0)
                    Toast.makeText(getBaseContext(), "please,Enter value of q", Toast.LENGTH_SHORT).show();
              else
              {
                  Long p = java.lang.Long.parseLong(pval.getText().toString());
                  Long q = java.lang.Long.parseLong(qval.getText().toString());
                  if (!DetermineKeys.isPrime(p))
                       Toast.makeText(getBaseContext(), "first number nust be primary and Large", Toast.LENGTH_SHORT).show();
                  else
                       if (!DetermineKeys.isPrime(q))
                              Toast.makeText(getBaseContext(), "Second number nust be primary and Large", Toast.LENGTH_SHORT).show();

                       else {
                              n = p * q;
                              Long phi = (p - 1) * (q - 1);
                              while (DetermineKeys.gcd(e, phi) != 1 || e % 2 == 0)
                                    e++;
                              d = computeD(e, p, q);
                              sendEandN();
                              but.setEnabled(false);

                       }
              }
        }
    public   void sendEandN() {
        String eval = java.lang.Long.toString(e);
        String nval =  java.lang.Long.toString(n);
        String readyToSend = eval + " " + nval;
        String readyToSend1 = "e= " + eval + " , " + "n =" + nval;
        JSONObject sendText = new JSONObject();
        try {
            sendText.put("text", readyToSend);
            ChatFragment.socket.emit("message", sendText);
            for (int l = 0; l < 900000; l++) ;
            pval.setText("");
            qval.setText("");
            Toast.makeText(getBaseContext(), "" + readyToSend1, Toast.LENGTH_SHORT).show();
            //numberOfMess=2;
            //  Intent mainIntent=new Intent(this,Main.class);
            // startActivity(mainIntent);
        } catch (JSONException ex) {

        }

    }
    public static String rsaDecrypt(String[] res ,long e,long n){
        String s="";
        long m;
        long f;
        char c;


        for(int i=0;i<res.length;i++)
        {
            m = RSAgen.exp_mod(java.lang.Long.parseLong(res[i]), d, n);



            s=s+(char)m;


        }

        return s;
    }

       // ChatFragment.socket.connect();
    //     ChatFragment.socket.on("message",handlerIncomingMessages);
    }

/*
public Emitter.Listener handlerIncomingMessages=new Emitter.Listener() {
    @Override
    public void call(final Object... args) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                JSONObject data = (JSONObject) args[0];
/*
                if (numberOfMess == 0) {
                    String message;
                    try {
                        message = data.getString("text").toString();
                        Toast.makeText(getBaseContext(), "the recieved key is  : " + message, Toast.LENGTH_SHORT).show();
                        numberOfMess++;
                        ChatFragment.encryptedKey=message;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else
                    if(numberOfMess==1){
                        String e;
                        try {
                            e = data.getString("text").toString();
                            Toast.makeText(getBaseContext(), "public key=   " + e, Toast.LENGTH_SHORT).show();
                            numberOfMess++;
                            ChatFragment.ea=java.lang.Long.parseLong(e);
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                    }
                    else
                    if(numberOfMess==2){
                        String n;
                        try {
                            n = data.getString("text").toString();
                            Toast.makeText(getBaseContext(), "n=  : " + n, Toast.LENGTH_SHORT).show();
                            numberOfMess++;
                            ChatFragment.na=java.lang.Long.parseLong(n);
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                    }
*/

            //}




/*
        });

    }
};*/
