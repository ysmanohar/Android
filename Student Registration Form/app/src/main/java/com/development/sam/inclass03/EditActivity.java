package com.development.sam.inclass03;

/** Assignment - 3
EditActivity.java
Samatha Downing
Sai Manohar Yerra **/

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    public String editField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        LinearLayout layoutEditName  = (LinearLayout) findViewById(R.id.layoutEditName);
        LinearLayout layoutEditEmail  = (LinearLayout) findViewById(R.id.layoutEditEmail);
        LinearLayout layoutEditLang  = (LinearLayout) findViewById(R.id.layoutEditLang);
        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            if(bundle.containsKey(DisplayActivity.EDIT_KEY)){
                editField = bundle.getString(DisplayActivity.EDIT_KEY);
                switch (editField){
                    case "name":
                        layoutEditName.setVisibility(View.VISIBLE);
                        layoutEditEmail.setVisibility(View.GONE);
                        layoutEditLang.setVisibility(View.GONE);
                        break;
                    case "email":
                        layoutEditName.setVisibility(View.GONE);
                        layoutEditEmail.setVisibility(View.VISIBLE);
                        layoutEditLang.setVisibility(View.GONE);
                        break;
                    case "fpl":
                        layoutEditName.setVisibility(View.GONE);
                        layoutEditEmail.setVisibility(View.GONE);
                        layoutEditLang.setVisibility(View.VISIBLE);
                        break;
                }
            }
        }
    }

    public void save(View v) {
        String result = "";
        switch (editField) {
            case "name":
                result = getName();
                break;
            case "email":
                result = getEmail();
                break;
            case "fpl":
                result = getFPL();
                break;
        }

        if (result == null || result.isEmpty()) {
            setResult(RESULT_CANCELED);
            return;
        }
       else{
        Intent i = new Intent();
        i.putExtra(DisplayActivity.VALUE_KEY, result);
        setResult(RESULT_OK, i);
       }
        finish();
    }

    public String getName(){
        String name;
        EditText nameText  = (EditText) findViewById(R.id.nameValue);
        name = nameText.getEditableText().toString();

        if(name == null || name.isEmpty()){
            Toast.makeText(EditActivity.this,"Please complete the field", Toast.LENGTH_SHORT).show();
        }
        return name;
    }

    public String getEmail(){
        String email;
        EditText emailText  = (EditText) findViewById(R.id.emailValue);
        email = emailText.getEditableText().toString();

        if(email == null || email.isEmpty()){
            Toast.makeText(EditActivity.this,"Please complete the field", Toast.LENGTH_SHORT).show();
        }
        return email;
    }

    public String getFPL(){
        String programmingLanguage;
        RadioGroup plGroup  = (RadioGroup) findViewById(R.id.radioGroup);
        int selectedID = plGroup.getCheckedRadioButtonId();

        if(selectedID <= 0) {
            Toast.makeText(EditActivity.this,"Please complete the field", Toast.LENGTH_SHORT).show();
            return null;
        }

        RadioButton selected = (RadioButton) findViewById(selectedID);
        programmingLanguage = selected.getText().toString();
        return programmingLanguage;
    }
}
