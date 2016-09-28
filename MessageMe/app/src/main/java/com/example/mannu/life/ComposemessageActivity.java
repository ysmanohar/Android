package com.example.mannu.life;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ComposemessageActivity extends AppCompatActivity {
    ImageButton contactbutton;
    DatabaseReference ref;
    ArrayList<String> list=new ArrayList<String>();
    EditText contact;
    Button send;
    private FirebaseAuth mAuth;
    public static EditText mail;
    public static Firebase mFireBaseRef;
    String receiver;
    String sendername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composemessage);
        ref= FirebaseDatabase.getInstance().getReference();
        Firebase.setAndroidContext(this);
        mAuth = FirebaseAuth.getInstance();
        contactbutton= (ImageButton) findViewById(R.id.contactbutton);
        //ref=new Firebase("https://life-2536e.firebaseio.com/");
        contact= (EditText) findViewById(R.id.contact);
        send= (Button) findViewById(R.id.send);
        mail= (EditText) findViewById(R.id.message);

        ref.child("Users").addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                for (com.google.firebase.database.DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                        Log.d("Value", (String) dataSnapshot1.child("username").getValue());
                    list.add((String) dataSnapshot1.child("username").getValue());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        if(getIntent().getExtras()!=null){
            sendername=getIntent().getExtras().getString("SENDER");
            contact.setText(sendername);


            ref.child("Users").addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    Log.d("Lopalikki","Lopaliki");
                    for (com.google.firebase.database.DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                        Log.d("Value-1", (String) dataSnapshot1.child("username").getValue());
                        String temp= sendername;
                        Log.d("temp",temp);
                        String newtemp= (String) dataSnapshot1.child("username").getValue();
                        Log.d("newtemp",newtemp);
                        if(newtemp.equals(temp)){
                            Log.d("HI","HI");
                            receiver=dataSnapshot1.getKey();
                            Log.d("receiver",receiver);
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        contactbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ComposemessageActivity.this);
                builder.setTitle("Users");
                builder.setItems(list.toArray(new CharSequence[list.size()]), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("Selected",list.get(which));
                        contact.setText(list.get(which));

                        ref.child("Users").addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                            @Override
                            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                                Log.d("Lopalikki","Lopaliki");
                                for (com.google.firebase.database.DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                                    Log.d("Value-1", (String) dataSnapshot1.child("username").getValue());
                                    String temp= String.valueOf(contact.getText());
                                    Log.d("temp",temp);
                                    String newtemp= (String) dataSnapshot1.child("username").getValue();
                                    Log.d("newtemp",newtemp);
                                    if(newtemp.equals(temp)){
                                        Log.d("HI","HI");
                                        receiver=dataSnapshot1.getKey();
                                        Log.d("receiver",receiver);
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                });
                builder.show();


            }
        });


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                Log.d("Current time => ", String.valueOf(c.getTime()));



                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                String formattedDate = df.format(c.getTime());
                Messages message= new Messages(mAuth.getCurrentUser().getEmail().toString(),mail.getText().toString(),"notread",String.valueOf(c.getTime()));
                Log.d("UID",mAuth.getCurrentUser().getUid());
                ref.child("Message").child(receiver).push().setValue(message);
                Toast.makeText(ComposemessageActivity.this,"Message Sent",Toast.LENGTH_LONG).show();
                Intent login=new Intent(ComposemessageActivity.this,InboxActivity.class);
                startActivity(login);
            }
        });

    }
}
