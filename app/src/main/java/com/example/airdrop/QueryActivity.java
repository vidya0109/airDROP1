package com.example.airdrop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class QueryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){

                    case R.id.home:
                        Intent intent = new Intent(getApplicationContext(),StatsActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;


                    case R.id.blogs:
                        Intent intent1 = new Intent(getApplicationContext(),BlogsActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                        return true;


                    case R.id.chatbot:
                        Intent intent2 = new Intent(getApplicationContext(),Chatbot.class);
                        startActivity(intent2);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.donate:
                        Intent intent3 = new Intent(getApplicationContext(),DonateActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(0,0);
                        return true;








                }

                return false;


            }
        });


    }
}
