package com.example.mannu.life;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
   public static EditText username;
    public static EditText password;
    public static Button Login;
    public static Button newuser;
    static public Firebase firebaseRoot;
    static public String name;
    static public String pass;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Firebase.setAndroidContext(this);
        firebaseRoot = new Firebase("https://life-2536e.firebaseio.com/");
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                Log.d("Changed","Changed");
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent=new Intent(MainActivity.this,InboxActivity.class);
                    startActivity(intent);

                    Log.d("Status", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    Log.d("Status", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };


        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        Login = (Button) findViewById(R.id.Login);
        newuser = (Button) findViewById(R.id.newuser);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = username.getText().toString();
                pass = password.getText().toString();
                Log.d("Credentials",name+pass);
                if (name != null && pass != null) {
                    mAuth.signInWithEmailAndPassword(name,pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Login Sucessfull",
                                        Toast.LENGTH_SHORT).show();
                            }
                            if (!task.isSuccessful()) {
                                Log.w("signInWithEmail", task.getException());
                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "Please enter credentials", Toast.LENGTH_LONG).show();
                }
            }


        });


        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(MainActivity.this,"Logged Off",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Disable Authentication State Listener when view is destroyed
        mAuth.removeAuthStateListener(mAuthListener);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
}
