package com.example.android.miwok;


import android.app.Activity;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class WordAdapter extends ArrayAdapter<Word> {

    private int color;

    public WordAdapter(Activity context, ArrayList<Word> word, int color){

        super(context,0,word);
        this.color = color;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItem = convertView;

        if(listItem == null){

            listItem = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        Word current = getItem(position);

        TextView miwokTextView = (TextView) listItem.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(current.getmMiwokTranslation());

        //changing the background
        View u = (View) miwokTextView.getParent();
        //find the color that the resource ID maps to
        int colors = ContextCompat.getColor(getContext(), color);
        u.setBackgroundColor(colors);


        TextView defaultTextView = (TextView) listItem.findViewById(R.id.default_text_view);
        defaultTextView.setText(current.getmDefaultTranslation());


        ImageView miwokImageView = (ImageView) listItem.findViewById(R.id.icon);

        if(current.getImageID() != 0){


            miwokImageView.setImageResource(current.getImageID());
        }
        else{

            miwokImageView.setVisibility(View.GONE);
        }


        return listItem;
    }
}
