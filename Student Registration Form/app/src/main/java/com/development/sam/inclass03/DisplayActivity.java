package com.development.sam.inclass03;

/** Assignment - 3
DisplayActivity
Samatha Downing
Sai Manohar Yerra **/

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {
    public static int REQ_CODE = 8455460;
    public static String EDIT_KEY="FIELD";
    public static String VALUE_KEY="VALUE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        if(getIntent().getExtras() != null){
            Student student = getIntent().getExtras().getParcelable(MainActivity.STUDENT);
            TextView text1 = (TextView) findViewById(R.id.textView1);
            TextView text2 = (TextView) findViewById(R.id.textView2);
            TextView text3 = (TextView) findViewById(R.id.textView3);
            text1.setText(student.name.toString());
            text2.setText(student.email.toString());
            text3.setText(student.programmingLanguage.toString());
        }

        findViewById(R.id.imagebutton_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.development.sam.inclass03.intent.action.VIEW");
                intent.putExtra(EDIT_KEY, "name");
                startActivityForResult(intent, REQ_CODE);
            }
        });

        findViewById(R.id.imagebutton_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.development.sam.inclass03.intent.action.VIEW");
                intent.putExtra(EDIT_KEY, "email");
                startActivityForResult(intent, REQ_CODE);
            }
        });

        findViewById(R.id.imagebutton_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.development.sam.inclass03.intent.action.VIEW");
                intent.putExtra(EDIT_KEY, "fpl");
                startActivityForResult(intent, REQ_CODE);
            }
        });

//        @Override
//        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//            super.onActivityResult(requestCode, resultCode, data);
//        }

    }
}