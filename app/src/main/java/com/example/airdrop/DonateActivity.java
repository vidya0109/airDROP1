package com.example.airdrop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DonateActivity extends AppCompatActivity {


    public void linked (View view) {

            goToUrl ( "http://www.pmcares.gov.in/en/web/contribution/donate_india");
    }
        public void linkedf (View view) {

            goToUrl ( "http://www.pmcares.gov.in/en/web/contribution/donate_foreign");
    }
        public void goToUrl (String url) {
            Uri uriUrl = Uri.parse(url);
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);
    }







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.donate);
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

    public void query(View view){


        Intent intent = new Intent(this,QueryActivity.class);
        startActivity(intent);
        overridePendingTransition(0,0);


    }

}
