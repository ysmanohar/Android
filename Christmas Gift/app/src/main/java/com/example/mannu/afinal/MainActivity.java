package com.example.mannu.afinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static public Firebase firebaseRoot;
    private FirebaseAuth.AuthStateListener mAuthListener;
    ListView listview;
    DatabaseReference ref;
    private FirebaseAuth mAuth;
    public static ArrayList<Person> personlist=new ArrayList<Person>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);
        firebaseRoot = new Firebase("https://project-d9dac.firebaseio.com/");
        mAuth = FirebaseAuth.getInstance();

        listview = (ListView) findViewById(R.id.listView);

        firebaseRoot.child("Person").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datashot:dataSnapshot.getChildren()){
                    String spendamount=datashot.child("amountspent").getValue().toString();
                    String budget=datashot.child("budget").getValue().toString();
                    String giftsbought=datashot.child("giftsbought").getValue().toString();
                    String name=datashot.child("name").getValue().toString();
                    Person person=new Person(name,budget,spendamount,giftsbought);
                    personlist.add(person);
                }
                Log.d("LIST", String.valueOf(personlist));
                Giftadapter adapter = new Giftadapter(MainActivity.this,R.layout.adapterlist,personlist);
                listview.setAdapter(adapter);
                adapter.setNotifyOnChange(true);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent sideintent = new Intent(MainActivity.this,GiftlistActivity.class);
                sideintent.putExtra("NAME",personlist.get(position).getName());
                Log.d("name",personlist.get(position).getName());
                startActivity(sideintent);
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id==R.id.newperson){
            Intent intent=new Intent(MainActivity.this,AddpersonActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
