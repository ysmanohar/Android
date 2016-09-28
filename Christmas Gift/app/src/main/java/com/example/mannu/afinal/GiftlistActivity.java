package com.example.mannu.afinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class GiftlistActivity extends AppCompatActivity {
    public  static String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giftlist);

        if(getIntent().getExtras()!=null){
            name= getIntent().getExtras().getString("NAME");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addgift,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id==R.id.addgift){
            Intent intent=new Intent(GiftlistActivity.this,AddgiftActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
