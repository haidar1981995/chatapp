package com.example.user.chatapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.app.Fragment;
import com.example.user.chatapp.ChatFragment;



public class Main extends AppCompatActivity {

    String imgdecodableString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("onCreateOptionsMenu", "create menu");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.socket_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

/*            case R.id.action_attach:
                Log.d("onOptionsItemSelected", "action Attach");
                openGallery();
                return true;
*/
            case R.id.senderRloe:
                Log.d("onOptionsItemSelected", "sender");
                Intent intents=new Intent(this,DetermineKeys.class);
                startActivity(intents);return  true;
            case R.id.recieverRole:
                Log.d("onOptionsItemSelected", "reciever");
                Intent intentr=new Intent(this,RecieveKey.class);
                startActivity(intentr);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery, 1);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //get fragment to main activity
            ChatFragment  chatFragment=(ChatFragment)getSupportFragmentManager().findFragmentById(R.id.chatF);
           ChatFragment.mutex=1;
            // chatFragment.sendImage(imgdecodableString);
         }
        }



