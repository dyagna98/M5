package com.example.vlisn.m4;

import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * used to display the rat data
 * Created by Divya on 10/17/2017.
 */

public class displayRatData extends AppCompatActivity {

    int args = RatData.args;
    ArrayAdapter<String> adapter;


    /**
     * displays information about each key upon clicking on it
     *
     * @param savedInstanceState bundle object that is passed on creation
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_rat_data);
        final ListView ratDates = (ListView) findViewById(R.id.ratDisplay);
        final ArrayList<String> ratDatalist = new ArrayList<String>();
        DatabaseReference mFirebaseInstance1 = FirebaseDatabase.getInstance().getReference().child("rats");
        mFirebaseInstance1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               for (int i = 0; i <= args; i++) {
                    HashMap ratkeysdate = (HashMap) dataSnapshot.getValue();
//                    System.out.println("args " + args);
//                    System.out.println("i " + i);
//                    System.out.println("ratdate " + ratkeysdate);
//                    if (i == args) {
//                        System.out.println("ratdate " + ratkeysdate);
//                        String x = ratkeysdate.toString() + "%rats";
                        ratDatalist.add(ratkeysdate.toString());
                    }
               // }

                adapter = new ArrayAdapter<String>(displayRatData.this,
                        R.layout.activity_listview, ratDatalist);
                ratDates.setAdapter(adapter);
                ;

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException(); // don't ignore errors
            }
        });
    }
}



