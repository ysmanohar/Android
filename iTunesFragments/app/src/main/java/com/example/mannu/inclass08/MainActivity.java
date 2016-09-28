package com.example.mannu.inclass08;
/** Inclass -08
 * Sai Manohar Yerra
 * TJ Sneed
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements FirstscreenFrag.OnFragmentInteractionListener,SecondScreen.OnFragmentInteractionListener,Thirdfrag.OnFragmentInteractionListener {
    EditText username;
    EditText password;
    Button Login;
    public static String name, pass;
    public  static String TITLE;
    public static String PIC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.Username);
        password = (EditText) findViewById(R.id.Password);
        Login = (Button) findViewById(R.id.Login);

        getFragmentManager().beginTransaction().add(R.id.container, new FirstscreenFrag(MainActivity.this), "first").commit();}

    public void gotoNextFragment() {
        getFragmentManager().beginTransaction().replace(R.id.container, new SecondScreen(MainActivity.this), "second").
                addToBackStack(null).commit();
    }

    @Override
    public void GoThirdFrag() {
        getFragmentManager().beginTransaction().replace(R.id.container, new Thirdfrag(MainActivity.this), "third").
                addToBackStack(null).commit();
    }

    @Override
    public void onDataclicked(String URL, String title) {
            TITLE=title;
            PIC=URL;
    }

    @Override
    public void onFragmentInteraction(TextView title, ImageView pic) {
        title.setText(TITLE);
     //   pic= (ImageView)findViewById(R.id.imageView);
        Picasso.with(MainActivity.this).load(PIC).into(pic);
    }
}

