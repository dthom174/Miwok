package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private MediaPlayer mPlayer;

    private AudioManager am;
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener(){

        public void onAudioFocusChange(int focusChange){

            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                //pause
                mPlayer.pause();
                mPlayer.seekTo(0);

            } else if(focusChange == AudioManager.AUDIOFOCUS_GAIN){
                //resume
                mPlayer.start();
            }else if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                //stop
                releaseMediaPlayer();
            }

        }

    };

    private MediaPlayer.OnCompletionListener mMedia = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {

            releaseMediaPlayer();
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("red","wetetti", R.drawable.color_red, R.raw.color_red));
        words.add(new Word("green","chokokki", R.drawable.color_green, R.raw.color_green));
        words.add(new Word("brown","takaakki", R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word("gray","topoppi", R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word("black","kululli", R.drawable.color_black, R.raw.color_black));
        words.add(new Word("white","kelelli", R.drawable.color_white, R.raw.color_white));
        words.add(new Word("dusty yellow","ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new Word("mustard yellow","chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));


        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_colors);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                releaseMediaPlayer();

                int result = am.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);

                if(result == am.AUDIOFOCUS_REQUEST_GRANTED){

                    mPlayer = new MediaPlayer().create(ColorsActivity.this, words.get(i).getSong());
                    mPlayer.start();
                    mPlayer.setOnCompletionListener(mMedia);
                }

            }
        });

    }

    private void releaseMediaPlayer() {

        // If the media player is not null, then it may be currently playing a sound.
        if (mPlayer != null) {

            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mPlayer = null;

            am.abandonAudioFocus(mOnAudioFocusChangeListener);
        }

    }
}
