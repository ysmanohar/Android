package com.example.mannu.inclass08;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FirstscreenFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FirstscreenFrag extends Fragment {

    public static OnFragmentInteractionListener mListener;
    MainActivity activity;

    public FirstscreenFrag(MainActivity activity) {
        this.activity=activity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_firstscreen, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().findViewById(R.id.Login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEst","hi");
                EditText username= (EditText) getActivity().findViewById(R.id.Username);
                String user=username.getText().toString();
               EditText password= (EditText) getActivity().findViewById(R.id.Password);
                String pass=password.getText().toString();
                Log.d("Test",user+pass);
                if(user.equals("admin") && pass.equals("test123")){
                    Log.d("Test","Entered");
                    mListener.gotoNextFragment();

                }else{
                    Toast.makeText(activity,"Please enter correct Credentials",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public interface OnFragmentInteractionListener {
        public  void gotoNextFragment();
    }
}
