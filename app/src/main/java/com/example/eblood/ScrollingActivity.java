package com.example.eblood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;


public class ScrollingActivity extends AppCompatActivity implements View.OnClickListener{
    CardView card_search;
    CardView card_information;
    CardView card_setting;
    CardView card_about;
    CardView card_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        cardViewInitializer();

    }
    public void cardViewInitializer(){
        card_search = (CardView) findViewById(R.id.card_search);
        card_search.setOnClickListener(this);
        card_information = (CardView) findViewById(R.id.card_information);
        card_information.setOnClickListener(this);
        card_setting = (CardView) findViewById(R.id.card_setting);
        card_setting.setOnClickListener(this);
        card_about = (CardView) findViewById(R.id.card_about);
        card_about.setOnClickListener(this);
        card_register = (CardView) findViewById(R.id.card_register);
        card_register.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.card_search){
            Intent intent = new Intent(ScrollingActivity.this, SearchActivity.class);
            startActivity(intent);
        }else if(v.getId()== R.id.card_information){
            Intent intent = new Intent(ScrollingActivity.this, InformationActivity.class);
            startActivity(intent);
        }else if(v.getId()== R.id.card_setting){

        }else if(v.getId()== R.id.card_register){
            Intent intent = new Intent(ScrollingActivity.this, Register.class);
            startActivity(intent);
        }else if(v.getId()== R.id.card_about){
            Intent intent = new Intent(ScrollingActivity.this, AboutActivity.class);
            startActivity(intent);
        }

    }
}

