package com.example.airdrop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StatsActivity extends AppCompatActivity {
    DatabaseReference myref,myref1,myref2;
   int count=0;
   int lock=0;
    String active="",deaths="",recovered="",states="";
    int totalactive=0,totaldeath=0,totalreco=0;
    LinearLayout linearLayout,linearLayout2,linearLayout3,linearLayout4;
    TextView textView,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10,textView11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
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


        Toast.makeText(this,"Last updated at 21:00 Hrs",Toast.LENGTH_LONG).show();


        linearLayout = (LinearLayout)findViewById(R.id.linear1);
        linearLayout.addView(createstates("Active"));

        linearLayout2 = (LinearLayout)findViewById(R.id.linear2);

        linearLayout2.addView(createstates("Rcvrd"));


        linearLayout3 = (LinearLayout)findViewById(R.id.linear3);
        linearLayout3.addView(createstates("Deaths"));

        linearLayout4=(LinearLayout)findViewById(R.id.linear0);
        linearLayout4.addView(createstates("States"));


        textView2=(TextView)findViewById(R.id.active);
        textView3=(TextView)findViewById(R.id.reco);
        textView4=(TextView)findViewById(R.id.dead);

        textView5=(TextView)findViewById(R.id.totalactive2);
        textView5.setText("Active");
        textView6=(TextView)findViewById(R.id.totalreco);
        textView6.setText("Recovered");
        textView7=(TextView)findViewById(R.id.totaldead);
        textView7.setText("Deaths");










        myref2=FirebaseDatabase.getInstance().getReference("states");
        myref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot post : dataSnapshot.getChildren()) {

                    while (count <= 35) {

                        states = post.child("states").child(String.valueOf(count)).getValue(String.class);

                        linearLayout4.addView(createstates(states));

                        count++;


                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







        myref= FirebaseDatabase.getInstance().getReference("stats");
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if (lock == 0) {


                    for (DataSnapshot post : dataSnapshot.getChildren()) {

                        active = post.child("active cases").getValue(String.class);

                        totalactive += Integer.parseInt(active);


                        linearLayout.addView(createactive(active));
                        ;

                    }
                    textView2.setText(String.valueOf(totalactive));


                    for (DataSnapshot post : dataSnapshot.getChildren()) {


                        recovered = post.child("recovered").getValue(String.class);

                        totalreco += Integer.parseInt(recovered);


                        linearLayout2.addView(createrecoverd(recovered));


                    }
                    textView3.setText(String.valueOf(totalreco));

                    for (DataSnapshot post : dataSnapshot.getChildren()) {


                        deaths = post.child("deaths").getValue(String.class);


                        totaldeath += Integer.parseInt(deaths);

                        linearLayout3.addView(createdeath(deaths));

                    }
                    textView4.setText(String.valueOf(totaldeath));

                    lock=1;


                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });









    }
    public TextView createstates(String actives) {

        textView = new TextView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,100);
        layoutParams.setMargins(0,10,0,10);
        textView.setLayoutParams(layoutParams);
        textView.setPadding(5,5,5,5);
        textView.setBackgroundColor(Color.rgb(28,28,43));
        textView.setText(actives);
        textView.setTextSize(12);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setTextColor(Color.rgb(97,105,114));


        return textView;

    }
    public TextView createactive(String actives) {

        textView = new TextView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 100);
        layoutParams.setMargins(0,10,0,10);
        textView.setLayoutParams(layoutParams);
        textView.setPadding(5,5,5,5);
        textView.setBackgroundColor(Color.rgb(28,28,43));
        textView.setText(actives);
        textView.setTextSize(15);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setTextColor(Color.rgb(97,105,114));


        return textView;

    }
    public TextView createrecoverd(String actives){


        textView = new TextView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,100);
        layoutParams.setMargins(0,10,0,10);
        textView.setPadding(5,5,5,5);
        textView.setBackgroundColor(Color.rgb(28,28,43));
        textView.setLayoutParams(layoutParams);

        textView.setText(actives);
        textView.setTextSize(15);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setTextColor(Color.rgb(97,105,114));

        return textView;


    }
    public TextView createdeath(String actives){

        textView = new TextView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,100);
        layoutParams.setMargins(0,10,0,10);
        textView.setPadding(5,5,5,5);
        textView.setBackgroundColor(Color.rgb(28,28,43));
        textView.setLayoutParams(layoutParams);

        textView.setText(actives);
        textView.setTextSize(15);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setTextColor(Color.rgb(97,105,114));


        return textView;



    }



}
