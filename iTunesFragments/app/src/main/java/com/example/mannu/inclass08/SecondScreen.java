package com.example.mannu.inclass08;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SecondScreen.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class SecondScreen extends Fragment implements GetAppDataAsyncTask.AppData{
    ListView listview;
    public  static String URL;
    public static String title;

    public static OnFragmentInteractionListener mListener;

   public static MainActivity activity;

    public SecondScreen(MainActivity activity) {
        this.activity=activity;
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
        new GetAppDataAsyncTask(SecondScreen.this).execute("https://itunes.apple.com/us/rss/topgrossingapplications/limit=100/xml");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second_screen, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event

 /*   @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }  */

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    @Override
    public void setupData(final ArrayList<App> applist) {
        Log.d("Title", String.valueOf(applist.get(0).getAppTitle()));
        listview = (ListView) getActivity().findViewById(R.id.listView);
        Appadapter adapter=new Appadapter(activity,R.layout.istitems,applist);
        listview.setAdapter(adapter);
        adapter.setNotifyOnChange(true);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mListener.GoThirdFrag();
                URL=applist.get(position).getLargeImage();
                title=applist.get(position).getAppTitle();
                mListener.onDataclicked(URL,title);
            }
        });

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        public  void GoThirdFrag();
        public void onDataclicked(String URL,String title);
    }
}
