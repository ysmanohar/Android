package com.example.mannu.homework04;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mannu on 6/21/2016.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private ArrayList<AppData> mDataset;
    private Bitmap bitmap;
    Context context;
    RecyclerView mRecyclerView;
    int layout;

    public interface OnItemClickListener {
        void onItemClick(View v, AppData item);
    }
    private final OnItemClickListener listener;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView,textView;
        public ImageButton imageButton;
        public ImageView imageView;
        View view;
        public ViewHolder(View v) {
            super(v);
            view=v;
            mTextView = (TextView) v.findViewById(R.id.textViewTitle);
            textView = (TextView) v.findViewById(R.id.textViewDate);
            imageView = (ImageView) v.findViewById(R.id.imageView);
            imageButton = ( ImageButton) v.findViewById(R.id.imageButton);
        }

        public void bind(final AppData item, final OnItemClickListener listener) {
            mTextView.setText(item.getTitle());
            textView.setText(item.getDate());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("demo1",item.getTitle()+"view");
                    v.setTag("view");
                    listener.onItemClick(v,item);
                }
            });

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {

                    v.setTag("button");
                    Log.d("demo1",item.getTitle()+"button");
                    listener.onItemClick(v,item);
                }
            });
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MainAdapter(Context context, ArrayList<AppData> myDataset, int layout, RecyclerView mRecyclerView, OnItemClickListener listener) {
        this.listener = listener;
        this.context = context;
        mDataset = myDataset;
        this.mRecyclerView = mRecyclerView;
        this.layout = layout;

    }
    // Create new views (invoked by the layout manager)
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {

        View v = null ;
        if(layout==1){
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.playscreen, parent, false);
        }
        else if(layout ==2) {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.gridview, parent, false);
        }
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder( v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("demo","dctfvygbh");


            }
        });
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context)
                .load(mDataset.get(position).getImage_URL())
                .into(holder.imageView);
        holder.bind(mDataset.get(position), listener);

    }

    public void setBitmap(Bitmap bitmap){
        this.bitmap = bitmap;
    }
    public Bitmap getData(){
        return bitmap;
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
