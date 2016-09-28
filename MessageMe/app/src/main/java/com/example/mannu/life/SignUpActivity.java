package com.example.mannu.life;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    EditText firstname,lastname,username,password,re_passowrd;
    Button Signup;
    String uname,pname,lname,fname;

    public static String uid;
    public static Firebase mFireBaseRef;
    private FirebaseAuth mAuth;
    DatabaseReference database;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Firebase.setAndroidContext(this);
        mFireBaseRef = new Firebase("https://life-2536e.firebaseio.com/");
        mAuth = FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance().getReference();

        username= (EditText) findViewById(R.id.signupusername);
        password= (EditText) findViewById(R.id.signuppassword);
        Signup= (Button) findViewById(R.id.Signup);
        re_passowrd= (EditText) findViewById(R.id.confirmpass);
        firstname= (EditText) findViewById(R.id.firstname);
        lastname= (EditText) findViewById(R.id.lastname);


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    uid=user.getUid();
                    Log.d("Status", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("Status", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };



        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname=username.getText().toString();
                pname=password.getText().toString();
                fname=firstname.getText().toString();
                lname=lastname.getText().toString();
                String repass=re_passowrd.getText().toString();
                if(repass.equals(pname)){
                Log.d("params",uname+pname);
                    mAuth.createUserWithEmailAndPassword(uname,pname).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SignUpActivity.this,"Account Created",Toast.LENGTH_LONG).show();
                                UserDetails user =new UserDetails(fname,lname,uname,pname);
                                Log.d("Input",fname+" "+lname+" "+uname+" "+pname+" "+mAuth.getCurrentUser().getUid());
                                mFireBaseRef.child("Users").child(mAuth.getCurrentUser().getUid()).setValue(user);

                            }
                            if(!task.isSuccessful()){
                                Toast.makeText(SignUpActivity.this,"Username already exists",Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }else{
                    Toast.makeText(SignUpActivity.this,"Password not matching",Toast.LENGTH_LONG).show();
                }


            }
        });







    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
