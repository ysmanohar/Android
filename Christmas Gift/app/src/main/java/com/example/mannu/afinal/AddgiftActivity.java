package com.example.mannu.afinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddgiftActivity extends AppCompatActivity {
    public static DatabaseReference ref;
    private FirebaseAuth mAuth;
    public static ArrayList<Gift> newgiftlist=new ArrayList<Gift>();

    ListView giftlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addgift);
        ref= FirebaseDatabase.getInstance().getReference();
        Firebase.setAndroidContext(this);

        giftlist = (ListView) findViewById(R.id.giftlist);

        ref.child("Gifts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datashot:dataSnapshot.getChildren()){
                   String gift= datashot.child("gift").getValue().toString();
                    String imageurl= datashot.child("gift").getValue().toString();
                    String price= datashot.child("gift").getValue().toString();
                    Gift giftobject = new Gift(gift,imageurl,price);
                    newgiftlist.add(giftobject);

                }
                Log.d("GIFTLIST", String.valueOf(newgiftlist));
                NewGiftadapter adapter = new NewGiftadapter(AddgiftActivity.this,R.layout.activity_addgift,newgiftlist);
                giftlist.setAdapter(adapter);
                adapter.setNotifyOnChange(true);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
