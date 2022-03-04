package com.example.gitajob.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.gitajob.R;


//por hacer
public class FullUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_user);



    }


    private class taskGetPlayerAchivement extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
    }


    private class taskGetFriendList extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
    }

    private class taskGetRecentlyPlayedGames extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
    }



}