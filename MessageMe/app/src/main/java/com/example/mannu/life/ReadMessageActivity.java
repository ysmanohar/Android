package com.example.mannu.life;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ReadMessageActivity extends AppCompatActivity {
    public  static String sender;
    public  static String message;
    public  static TextView textsender;
    public  static TextView textmessage;
    public  static ImageButton delete;
    public  static ImageButton mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_message);
        textsender= (TextView) findViewById(R.id.sender);
        textmessage= (TextView) findViewById(R.id.message);
        delete= (ImageButton) findViewById(R.id.delete);
        mail= (ImageButton) findViewById(R.id.mail);


        if(getIntent().getExtras()!=null){
            sender=getIntent().getExtras().getString("SENDER");
            message=getIntent().getExtras().getString("MESSAGE");
        }

        textsender.setText(sender);
        textmessage.setText(message);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id==R.id.mail){
            Intent compose=new Intent(ReadMessageActivity.this,ComposemessageActivity.class);
            compose.putExtra("SENDER",sender);
            startActivity(compose);
        }
        if(id==R.id.delete){

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.displaymenu,menu);
        return true;
    }


}
