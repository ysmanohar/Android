package com.example.mannu.afinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddpersonActivity extends AppCompatActivity {
    public static EditText name;
    public static EditText budget;
    public static DatabaseReference ref;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addperson);

        ref= FirebaseDatabase.getInstance().getReference();
        Firebase.setAndroidContext(this);

        name= (EditText) findViewById(R.id.personname);
        budget= (EditText) findViewById(R.id.budget);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addperson_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id==R.id.newperson){
         String newperson=name.getText().toString();
            Log.d("out",newperson);
            String estbudget= budget.getText().toString();
            if(check(newperson,estbudget)){
                Person person = new Person(newperson,estbudget,"0","0");
                ref.child("Person").push().setValue(person);
                Toast.makeText(AddpersonActivity.this,"Person added",Toast.LENGTH_LONG).show();
                finish();

            }

        }

        return super.onOptionsItemSelected(item);
    }

    private boolean check(String newperson, String estbudget) {
        if(newperson.length()==0){
            Log.d("out","string");
            Toast.makeText(AddpersonActivity.this,"Please enter name",Toast.LENGTH_LONG).show();
            return  false;
        }
        if(estbudget.length()==0){
            Log.d("out","budget");
            Toast.makeText(AddpersonActivity.this,"Please enter positive Amount",Toast.LENGTH_LONG).show();
            return  false;

        }
        return true;
    }
}
