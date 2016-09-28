package com.example.mannu.inclass4;

/** Assignment- 4
 * MainActivity.java
 * Sai Manohar Yerra
 * TJ Sneed
 */

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ProgressDialog pd;
    String output;
    private int index;
    boolean values[]={false,false,false,false};
    private TextView passV;
    Handler handler;
    ProgressDialog progress;
    Message msg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress = new ProgressDialog(this);
        progress.setMessage("Generating Passwords");
        progress.setCancelable(false);


        ((Spinner) findViewById(R.id.pwLengthSpinner)).setSelection(0);

        passV = (TextView) findViewById(R.id.password_output);

        findViewById(R.id.generate_async).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check()) {
                    new DoWork().execute();
                }
            }
        });

        findViewById(R.id.generate_thread).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check()) {
                    new Thread(new DoWorkThread()).start();
                }
            }
        });

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {


                switch (msg.what){
                    case DoWorkThread.STATUS_START:
                        progress.show();
                        break;


                    case DoWorkThread.STATUS_STEP:
                        passV.setText(msg.getData().getString("res"));
                        break;

                    case DoWorkThread.STATUS_DONE:

                        progress.dismiss();
                        break;

                }


                return false;
            }
        });
    }

    private boolean check(){
        if ( ((Spinner) findViewById(R.id.pwLengthSpinner)).getSelectedItemPosition() != 0) {
            index = (((Spinner) findViewById(R.id.pwLengthSpinner)).getSelectedItemPosition() - 1);
            values[0] = ((CheckBox) findViewById(R.id.numbersBox)).isChecked();
            values[1] = ((CheckBox) findViewById(R.id.upperBox)).isChecked();
            values[2] = ((CheckBox) findViewById(R.id.lowerBox)).isChecked();
            values[3] = ((CheckBox) findViewById(R.id.specialCharBox)).isChecked();

            if (values[0] != false || values[1] != false || values[2] != false || values[3] != false){
                return true;

            }else {
                Toast.makeText(MainActivity.this, "Choose atleast 1 Checkbox", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(MainActivity.this, "Choose atleast 1  Dropdown Value", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    class DoWork extends AsyncTask<Integer, Integer, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(MainActivity.this);
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.setMessage("Generating Passwords ...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected void onPostExecute(String val) {
            super.onPostExecute(val);
            pd.dismiss();
            passV.setText(output);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }


        protected String doInBackground(Integer... params) {
            output = Util.getPassword(index,values[0],values[1],values[2],values[3]);
            return output;
        }
    }

    class DoWorkThread implements Runnable{
        static final int STATUS_START = 0x00;
        static final int STATUS_STEP = 0x01;
        static final int STATUS_DONE = 0x02;

        @Override
        public void run() {


            msg = new Message();
            Bundle bun = new Bundle();
            msg.what= STATUS_START;
            handler.sendMessage(msg);
            bun.putString("res", Util.getPassword(index,values[0],values[1],values[2],values[3]));
            Log.d("hi",Util.getPassword(index,values[0],values[1],values[2],values[3]));
            msg = new Message();
            msg.what= STATUS_STEP;
            msg.setData(bun);
            handler.sendMessage(msg);
            msg = new Message();
            msg.what= STATUS_DONE;
            handler.sendMessage(msg);
        }
    }
}



