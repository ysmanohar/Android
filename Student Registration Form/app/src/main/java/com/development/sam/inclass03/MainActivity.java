package com.development.sam.inclass03;

/** Assignment - 3
MainActivity.java
Samatha Downing
Sai Manohar Yerra **/

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final static String STUDENT = "STUDENT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submit(View v){
        String name;
        String email;
        String programmingLanguage;

        EditText nameText  = (EditText) findViewById(R.id.nameValue);
        EditText emailText  = (EditText) findViewById(R.id.emailValue);
        name = nameText.getEditableText().toString();
        email = emailText.getEditableText().toString();


        RadioGroup plGroup  = (RadioGroup) findViewById(R.id.radioGroup);
        int selectedID = plGroup.getCheckedRadioButtonId();
        if(selectedID > 0) {
            RadioButton selected = (RadioButton) findViewById(selectedID);
            programmingLanguage = selected.getText().toString();
        }else {
            programmingLanguage = "";
        }

        if(name == null || name.isEmpty() || email == null || email.isEmpty() || programmingLanguage == null || programmingLanguage.isEmpty()){
            Toast.makeText(MainActivity.this,"Please complete all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Student student = new Student(name, email, programmingLanguage);
        Intent i = new Intent(MainActivity.this, DisplayActivity.class);
        i.putExtra(STUDENT, student);
        startActivity(i);
    }
}
