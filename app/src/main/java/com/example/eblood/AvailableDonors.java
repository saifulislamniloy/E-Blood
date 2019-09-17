package com.example.eblood;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AvailableDonors extends AppCompatActivity {

    private ListView listView;
    ProgressBar progressBar;
    DatabaseReference databaseReference;
    Query query;
    private List<DataToRegister> dataToRegisterList;
    private Custom_adapter custom_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_donors);

        Intent intent = getIntent();
        //String bg = intent.getStringExtra(SearchActivity.EXTRA_TEXT);
        //String district = intent.getStringExtra(SearchActivity.EXTRA_TEXT2);

        databaseReference = (DatabaseReference) FirebaseDatabase.getInstance().getReference();
        query = databaseReference.child("donor")/*.orderByChild("searchKey").equalTo(bg)*/;

        dataToRegisterList = new ArrayList<>();
        custom_adapter = new Custom_adapter(AvailableDonors.this, dataToRegisterList);

        listView = (ListView)findViewById(R.id.listViewId);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    protected void onStart() {

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressBar.setIndeterminate(true);
                dataToRegisterList.clear();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                        DataToRegister dataToRegister = dataSnapshot1.getValue(DataToRegister.class);
                        dataToRegisterList.add(dataToRegister);
                }
                progressBar.setIndeterminate(false);
                listView.setAdapter(custom_adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        super.onStart();
    }
}
