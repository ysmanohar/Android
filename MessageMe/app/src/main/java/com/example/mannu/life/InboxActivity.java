package com.example.mannu.life;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class InboxActivity extends AppCompatActivity {
    ImageButton compose;
    ListView listview;
    DatabaseReference ref;
    private FirebaseAuth mAuth;
    public static ArrayList<Messages> messagelist=new ArrayList<Messages>();
    public static String click;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        ref= FirebaseDatabase.getInstance().getReference();
        Firebase.setAndroidContext(this);
        mAuth = FirebaseAuth.getInstance();


        listview = (ListView) findViewById(R.id.listView);
//        Log.d("Triggered",mAuth.getCurrentUser().getUid());
        ref.child("Message").child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(com.google.firebase.database.DataSnapshot datasnapshot1:dataSnapshot.getChildren()){
                    Log.d("Getkey",datasnapshot1.getKey());
                    String isread= (String) datasnapshot1.child("isread").getValue();
                    String message=datasnapshot1.child("message").getValue().toString();
                    String sender=datasnapshot1.child("sender").getValue().toString();
                    String time=datasnapshot1.child("time").getValue().toString();
                    Log.d("Extracted",message+" "+sender+" "+" "+time+" "+isread);
                    Messages newmessage = new Messages(sender,message,isread,time);
                    messagelist.add(newmessage);


                    Log.d("list", String.valueOf(messagelist));
                    Appadapter adapter = new Appadapter(InboxActivity.this,R.layout.adapterlist,messagelist);
                    listview.setAdapter(adapter);
                    adapter.setNotifyOnChange(true);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                Log.d("ID", String.valueOf(ref.child("Message").child(mAuth.getCurrentUser().getUid())));
                ref.child("Message").child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.d("Heymaam","Lopaliki");
                        for (com.google.firebase.database.DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                            String temp= (String) dataSnapshot1.child("time").getValue();
                            Log.d("TIme",temp);
                            if(temp.equals(messagelist.get(position).getTime())){
                                click=dataSnapshot1.getKey();
                                Log.d("Clickeee",click);
                            }
                        }
                        ref.child("Message").child(mAuth.getCurrentUser().getUid()).child(click).child("isread").setValue("read");

                        Intent intent=new Intent(InboxActivity.this,ReadMessageActivity.class);
                        intent.putExtra("SENDER",messagelist.get(position).getSender());
                        intent.putExtra("MESSAGE",messagelist.get(position).getMessage());
                        startActivity(intent);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });




            }
        });




    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id==R.id.email){
            Intent intent=new Intent(InboxActivity.this,ComposemessageActivity.class);
            startActivity(intent);
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(InboxActivity.this,"Logged Off",Toast.LENGTH_LONG).show();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.inboxmenu,menu);
        return true;
    }



}
