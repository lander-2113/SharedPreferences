package com.rohitsahu.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.rohitsahu.sharedpreferences", Context.MODE_PRIVATE);

        ArrayList<String> friends = new ArrayList<>();

        friends.add("Lola");
        friends.add("pola");
        friends.add("jonah");

        try {
            sharedPreferences.edit().putString("friends", ObjectSerialization.serialize(friends)).apply();
            Log.i("Serialized", ObjectSerialization.serialize(friends));
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<String> newFriends = new ArrayList<>();

        try{
            newFriends = (ArrayList<String>)ObjectSerialization.deserialize(sharedPreferences.getString("friends", ObjectSerialization.serialize(new ArrayList<String>())));
        }catch(Exception e){
            e.printStackTrace();
        }

        Log.i("new friends", newFriends.toString());


        //sharedPreferences.edit().putString("a", "a");

    }
}