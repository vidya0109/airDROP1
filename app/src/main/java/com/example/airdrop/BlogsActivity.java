package com.example.airdrop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.internal.DialogRedirect;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BlogsActivity extends AppCompatActivity {
    DatabaseReference myref;
    EditText editText, editText2;
    TextView textView;
    LinearLayout linearLayout;
    String post = "", name = "",  time = "",oldname="",oldpost="";
    long post_id = 0;
    Post p1;
    Button button2;
    int lock=0;

    public TextView create(String post, String name) {

        textView = new TextView(this);
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        textView.setTextSize(22);
        layoutParams.setMargins(10, 10, 10, 10);
        textView.setLayoutParams(layoutParams);
        textView.setPadding(10, 10, 10, 10);
        textView.setBackgroundColor(Color.rgb(30, 30, 48));
        textView.setTextColor(Color.rgb(108,117,125));
        textView.setTypeface(null, Typeface.BOLD);
        textView.setText(post+"\n"+"\n"+"By:("+name+")");



        linearLayout.addView(textView);
        return textView;


    }







    public void post(View view) {


        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("POST YOUR BLOG");
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        editText = new EditText(this);
        editText.setHint("Enter your Name");
        layout.addView(editText);
        editText2 = new EditText(this);
        editText2.setHint("Post your Blog");
        layout.addView(editText2);
        dialog.setView(layout);
        dialog.setPositiveButton("Post", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                name=editText.getText().toString();
                if (name.equals(""))
                    name="Anonymous";
               post=editText2.getText().toString();
              if (post.equals(""))
                  post="We will fight this Virus together!!";

                p1 = new Post(name, post);
                myref.child(String.valueOf(post_id + 1)).setValue(p1);
                editText.getText().clear();
                editText2.getText().clear();


                create(post, name);
                dialog.cancel();




            }
        });
        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.show();










    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blogs);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.blogs);
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


        linearLayout = (LinearLayout) findViewById(R.id.linear);


        myref = FirebaseDatabase.getInstance().getReference("Posts");



        myref.addValueEventListener(new ValueEventListener() {


                                        @Override


                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                            if (dataSnapshot.exists())
                                                post_id = (dataSnapshot.getChildrenCount());

                                            if (lock==0) {

                                                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {

                                                    oldname = postsnapshot.child("name").getValue(String.class);


                                                    oldpost = postsnapshot.child("post").getValue(String.class);


                                                    create(oldpost, oldname);



                                                }

                                                lock=1;

                                            }


                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }


                                    }


        );









    }

}
