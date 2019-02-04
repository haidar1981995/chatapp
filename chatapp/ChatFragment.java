package com.example.user.chatapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChatFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChatFragment#newInstance} factory method to
 * create an instance of this fragment.
*/
public class ChatFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    static public int mutex=0;//used to prevent user to send to himself
    private EditText  mInputMessageView;
    private RecyclerView mMessageView;
    private OnFragmentInteractionListener mListener;
    private List<Mesage> mMessages=new ArrayList<Mesage>();//list of messages
    private RecyclerView.Adapter mAdapter;
    public static String encryptedKey="";
    public static long na=-1;
    public static long ea=-1;
    public static String []keyRsa;
    public static com.github.nkzawa.socketio.client.Socket socket;//packger for I/O operation

    //connect to server to port 5000
   {
        try{  socket= IO.socket("http://192.168.173.1:5000");}
        catch(URISyntaxException e){throw new RuntimeException(e);}


    }




    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChatFragment.*/

    // TODO: Rename and change types and number of parameters
   public static ChatFragment newInstance(String param1, String param2) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        encryptedKey="";
        RecieveKey.numberOfMess=0;
        socket.connect();//strat connection
        socket.on("message",handlerIncomingMessages);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mAdapter=new MessageAdapter(mMessages);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMessageView=(RecyclerView)view.findViewById(R.id.messages);
        mMessageView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMessageView.setAdapter(mAdapter);
        ImageButton imageButton=(ImageButton)view.findViewById(R.id.send_button);
        mInputMessageView=(EditText)view.findViewById(R.id.message_input);
      //when we click to send button
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mutex=1;//thats mean the sender make mutex=1 to can not send to himself
                sendMessage();//function to send message
                //Toast.makeText(getContext(),RecieveKey.numberOfMess+"",Toast.LENGTH_SHORT).show();
            }
        });
    }

   //function for send message to another side
    public void sendMessage(){
        if(encryptedKey.length()==0) { //prevet send any thing before create session
            Toast.makeText(getContext(),"you should first create session",Toast.LENGTH_SHORT).show();
            mutex=0;
          }
         else
           {
               RecieveKey.numberOfMess=5;
              String message=mInputMessageView.getText().toString().trim();
               mInputMessageView.setText("");
               addMessage(message);//add message to recyler view in sender side
               String EncMessage=JavaApplication18.AES_Encryption(message,encryptedKey);//encrypt message using AES
               String EncMessageAscii="";//this variable will conatain the encrypted message in ASCII
               String tempi="";
               //this process to convert hexadicimal string to ASCII string
               for(int i=0;i<EncMessage.length();i+=2)
               {
                   tempi+=EncMessage.charAt(i)+""+EncMessage.charAt(i+1);
                   int ord=Mathimatical.st2Hex(tempi);
                   EncMessageAscii+=(char)ord;
                   tempi="";
               }
               JSONObject sendText=new JSONObject();//JSON object to send message
                try {
                      RecieveKey.numberOfMess=5;
                    //Toast.makeText(getContext(),EncMessageAscii,Toast.LENGTH_SHORT).show();
                      sendText.put("text", EncMessageAscii);
                      socket.emit("message", sendText);//this statement send message to server
                     }
                catch(JSONException e){
                    }}

        }

   /* public void sendImage(String path){
    JSONObject sendData=new JSONObject();
        try{
            sendData.put("image",encodeImage(path));
            Bitmap btm=decodeImage(sendData.getString("image"));
            //mutex=1;
            addImage(btm);
            socket.emit("message",sendData);
          }catch (JSONException e){}

    }*/

    // this function enable adding message to recyler view in sender side
    public void addMessage(String message){
        mMessages.add(new Mesage.Builder(Mesage.TYPE_MESSAGE).message("you : "+message).build());
        mAdapter=new MessageAdapter(mMessages);
        mAdapter.notifyItemInserted(0);
        scrollToButton();

    }



     /*public void addImage(Bitmap bmp){
         mMessages.add(new Mesage.Builder(Mesage.TYPE_MESSAGE).message("you : ").build());
         mAdapter=new MessageAdapter(mMessages);
         mMessages.add(new Mesage.Builder(Mesage.TYPE_MESSAGE).image(bmp).build());
         mAdapter=new MessageAdapter(mMessages);
         mAdapter.notifyItemInserted(0);
         scrollToButton();
     }*/


    public void scrollToButton(){
  mMessageView.scrollToPosition(mAdapter.getItemCount()-1);

    }

   /* public String encodeImage(String path){
        File imagefile=new File(path);
        FileInputStream fis=null;
        try{
            fis=new FileInputStream(imagefile);

        }catch (FileNotFoundException e){ e.printStackTrace();}

        Bitmap bm= BitmapFactory.decodeStream(fis);
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte []b=baos.toByteArray();
        String encImage= Base64.encodeToString(b,Base64.DEFAULT);

        return  encImage;

    }*/



   /* public Bitmap decodeImage(String data){
        byte []b=Base64.decode(data,Base64.DEFAULT);
        Bitmap bm=BitmapFactory.decodeByteArray(b,0,b.length);
         return  bm;
    }
*/

//this object handles the recieved message
    public Emitter.Listener handlerIncomingMessages=new Emitter.Listener() {
        @Override
        public void call( final Object... args) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                JSONObject data=(JSONObject)args[0];//data is the recieved message

                if (RecieveKey.numberOfMess == 0)//this section is to recieve public Key {e,n}
                {
                    String recivedpublicKey;
                    try {
                        recivedpublicKey = data.getString("text").toString();
                        String []eAndn=recivedpublicKey.split("\\s");
                        eAndn[0]="e= "+eAndn[0];
                        eAndn[1]="n= "+eAndn[1];
                        String strngSho=eAndn[0]+" , "+eAndn[1];
                        Toast.makeText(getContext(),""+strngSho, Toast.LENGTH_SHORT).show();
                        RecieveKey.numberOfMess++;
                        String []arr=recivedpublicKey.split("\\s");
                        ChatFragment.ea=java.lang.Long.parseLong(arr[0]);//get e
                        ChatFragment.na=java.lang.Long.parseLong(arr[1]);//get n
                        RecieveKey.numberOfMess++;
                       // Toast.makeText(getContext(),""+RecieveKey.numberOfMess, Toast.LENGTH_SHORT).show();
                    } catch (JSONException eb) {
                        eb.printStackTrace();
                    }
                }
                else
                if(RecieveKey.numberOfMess==2)//this section is to recieve AES Encryption key
                {
                    String message;
                    try {
                        message = data.getString("text").toString().trim();

                        keyRsa=message.split("\\s");
                        encryptedKey=RecieveKey.rsaDecrypt(keyRsa,ea,na);
                        Toast.makeText(getContext(), "key =  : " + encryptedKey, Toast.LENGTH_SHORT).show();

                        RecieveKey.numberOfMess=5;

                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }
                }
                else
          if(RecieveKey.numberOfMess==5)//this section is to recieve messages between 2 sides
          {
                if(mutex==0)//if not the sender,then he can get messages
                {
                String message;
               // String imageText;

                try {
                    message=data.getString("text").toString();
                    String messageAscii="";
                    String tempi="";
                    //convert ASCII string to hexadicimal string
                    for(int i=0;i<message.length();i++){
                        messageAscii+=Mathimatical.AsciiToHex(message.charAt(i))+"";
                    }
                    String decMessage=AES_Decryption.AES_DecryptionS(messageAscii,encryptedKey);//do AES Decryption
                    //add message to recycler view
                    mMessages.add(new Mesage.Builder(Mesage.TYPE_MESSAGE).message("Mohammad  :  "+decMessage).build());
                    mAdapter=new MessageAdapter(mMessages);
                    mAdapter.notifyItemInserted(0);
                    scrollToButton();


                } catch (JSONException e) {
                    e.printStackTrace();
                }

              /*  try {
                    imageText=data.getString("image");
                   // addImage(decodeImage(imageText));
                    mMessages.add(new Mesage.Builder(Mesage.TYPE_MESSAGE).message("Ali : ").build());
                    mAdapter=new MessageAdapter(mMessages);
                    mMessages.add(new Mesage.Builder(Mesage.TYPE_MESSAGE).image(decodeImage(imageText)).build());
                    mAdapter=new MessageAdapter(mMessages);
                    mAdapter.notifyItemInserted(0);
                    scrollToButton();
                }
                catch (JSONException ev) {
                    ev.printStackTrace();
                }*/


            }
            else//if it is sender then dont recieve any thing
                    mutex=0;

        }}}
        );
        }
    };




    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        socket.disconnect();
    }
}

