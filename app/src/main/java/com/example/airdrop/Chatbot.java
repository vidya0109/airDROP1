package com.example.airdrop;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static com.example.airdrop.R.*;

public class Chatbot extends AppCompatActivity {
    String red="",green="",orange="";
    String start="",ans="",state="",name="";
    int x=0;
    boolean redzone=false,orangezone=false,greenzone=false;
    TextView textView,textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9;
    String q[] = {"Do you have fever?", "Are you experiencing dry cough?", "Do you have running nose?", "Do you have nasal congestion?", "Do you have tiredness?", "Do you have diarrhoea?", "Do you have sore throat?", "Do you have breathing difficulty?"};
    int countred=0,countorange=0,countgreen=0;
    int lock=0,lock2=0,lock3=0;
   boolean i1=false,i2=false,i3=false,i4=false,i5=false,i6=false,i7=false,i8=false;
    int textcount=0;
    String city="";
    LocationManager locationManager;
    LocationListener locationListener;
    DatabaseReference myref5,myref6,myref7;
    EditText editText,editText2;
    String redcity="",orangecity="",greencity="";
    LinearLayout linearLayout;
    Button button,button2,button3,button4,button5,button6,button7,button8,button9,button10,button11,button12;
   int count=0;
    boolean proceed=false;
    int c=20;
     FusedLocationProviderClient client;
    GradientDrawable gd;

    long names=0;
    int ques=0;
    long user_id=0;







public EditText createEdit(){

    editText = new EditText(this);
    final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    layoutParams.setMargins(0, 10, 0, 0);
    editText.setLayoutParams(layoutParams);
    editText.setTextColor(Color.rgb(108,117,125));
    linearLayout.addView(editText);
    return editText;





}

    public TextView createText1(String s) {

        textView1 = new TextView(this);
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 150);
        textView1.setTextSize(16);
        layoutParams.setMargins(0, 10, 0, 0);
        textView1.setLayoutParams(layoutParams);
        textView1.setBackgroundResource(drawable.tags_rounded_corners);
        textView1.setBackgroundColor(Color.rgb(30,30,48));
        textView1.setTextColor(Color.rgb(108,117,125));




        textView1.setText(s);

        linearLayout.addView(textView1);
        return textView1;
    }





public void chat(View view) {

    button11.setEnabled(false);
    createText1("Are you Ready? Reply in Yes or No!");
    button10.setEnabled(false);
    createEdit();
    createButton();
}



public Button createButton() {

    button = new Button(this);
    final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    layoutParams.setMargins(0, 5, 0, 0);
    button.setLayoutParams(layoutParams);
    button.setBackgroundColor(Color.rgb(30,30,48));
    button.setTextColor(Color.rgb(108,117,125));
    button.setText("Enter");
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            start = editText.getText().toString();
            editText.setCursorVisible(false);
            editText.setFocusable(false);
            editText.setEnabled(false);

            if (start.equalsIgnoreCase("yes")) {
                createText1("Okay!! Lets proceed");

                button.setEnabled(false);
                createText1(q[0]);
                createEdit();
                createButton1();


            }
            else if (start.equalsIgnoreCase("no")){

                button.setEnabled(false);
                createText1("Okay!! Bye Have a nice day");

            }
            else {
                button.setEnabled(false);
                createText1("Try again !");
                createEdit();
                createButton();

            }


        }



    });
    linearLayout.addView(button);
    return button;
}





    public Button createButton1(){

        button2  = new Button(this);
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 5, 0, 0);
        button2.setLayoutParams(layoutParams);
        button2.setBackgroundColor(Color.rgb(30,30,48));
        button2.setTextColor(Color.rgb(108,117,125));
        button2.setText("Enter");
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ans = editText.getText().toString();
                editText.setCursorVisible(false);
                editText.setFocusable(false);
                editText.setEnabled(false);
                if(ans.equalsIgnoreCase("yes"))
                {

                    c=c+10;
                    myref6.child(name).child(String.valueOf(ques)).setValue(ans);
                    ques++;
                    button2.setEnabled(false);
                    createText1(q[1]);
                    createEdit();
                    createButton2();

                }
                else if(ans.equalsIgnoreCase("no"))
                {
                    c=c-5;
                    myref6.child(name).child(String.valueOf(ques)).setValue(ans);
                    ques++;
                    button2.setEnabled(false);
                    createText1(q[1]);
                    createEdit();
                    createButton2();

                }
                else
                {      button2.setEnabled(false);
                    createText1("Please respond in yes or no");
                    createText1(q[0]);
                    createEdit();
                    createButton1();
                }

            }
        });
        linearLayout.addView(button2);
        return button2;
}

    public Button createButton2(){

        button3  = new Button(this);
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 5, 0, 0);
        button3.setLayoutParams(layoutParams);
        button3.setBackgroundColor(Color.rgb(30,30,48));
        button3.setTextColor(Color.rgb(108,117,125));
        button3.setText("Enter");
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ans = editText.getText().toString();
                editText.setCursorVisible(false);
                editText.setFocusable(false);
                editText.setEnabled(false);

                if(ans.equalsIgnoreCase("yes"))
                {
                    c=c+10;
                    myref6.child(name).child(String.valueOf(ques)).setValue(ans);
                    ques++;
                    button3.setEnabled(false);
                    createText1(q[2]);
                    createEdit();
                    createButton3();

                }
                else if(ans.equalsIgnoreCase("no"))
                {
                    c=c-5;
                    myref6.child(name).child(String.valueOf(ques)).setValue(ans);
                    ques++;
                    button3.setEnabled(false);
                    createText1(q[2]);
                    createEdit();
                    createButton3();

                }
                else
                {
                    button3.setEnabled(false);

                    createText1("Please respond in yes or no");
                    createText1(q[1]);
                    createEdit();
                    createButton2();


                }

            }
        });
        linearLayout.addView(button3);
        return button3;
    }
    public Button createButton3(){

        button4  = new Button(this);
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 5, 0, 0);
        button4.setLayoutParams(layoutParams);
        button4.setBackgroundColor(Color.rgb(30,30,48));
        button4.setTextColor(Color.rgb(108,117,125));
        button4.setText("Enter");
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ans = editText.getText().toString();
                editText.setCursorVisible(false);
                editText.setFocusable(false);
                editText.setEnabled(false);

                if(ans.equalsIgnoreCase("yes"))
                {
                    c=c+10;
                    myref6.child(name).child(String.valueOf(ques)).setValue(ans);
                    ques++;
                    button4.setEnabled(false);
                    createText1(q[3]);
                    createEdit();
                    createButton4();


                }
                else if(ans.equalsIgnoreCase("no"))
                {
                    c=c-5;
                    myref6.child(name).child(String.valueOf(ques)).setValue(ans);
                    ques++;
                    button4.setEnabled(false);
                    createText1(q[3]);
                    createEdit();
                    createButton4();

                }
                else
                {     button4.setEnabled(false);
                    createText1("Please respond in yes or no");
                    createText1(q[2]);
                    createEdit();
                    createButton3();

                }
            }
        });
        linearLayout.addView(button4);
        return button4;
    }
    public Button createButton4(){

        button5  = new Button(this);
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 5, 0, 0);
        button5.setLayoutParams(layoutParams);
        button5.setBackgroundColor(Color.rgb(30,30,48));
        button5.setTextColor(Color.rgb(108,117,125));
        button5.setText("Enter");
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ans = editText.getText().toString();
                editText.setCursorVisible(false);
                editText.setFocusable(false);
                editText.setEnabled(false);

                if(ans.equalsIgnoreCase("yes"))
                {
                    c=c+10;
                    myref6.child(name).child(String.valueOf(ques)).setValue(ans);
                    ques++;
                    button5.setEnabled(false);
                    createText1(q[4]);
                    createEdit();
                    createButton5();


                }
                else if(ans.equalsIgnoreCase("no"))
                {
                    c=c-5;
                    myref6.child(name).child(String.valueOf(ques)).setValue(ans);
                    ques++;
                    button5.setEnabled(false);
                    createText1(q[4]);
                    createEdit();
                    createButton5();

                }
                else
                {
                    button5.setEnabled(false);
                    createText1("Please respond in yes or no");
                    createText1(q[3]);
                    createEdit();
                    createButton4();

                }
            }
        });
        linearLayout.addView(button5);
        return button5;
    }
    public Button createButton5(){

        button6  = new Button(this);
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 5, 0, 0);
        button6.setLayoutParams(layoutParams);
        button6.setBackgroundColor(Color.rgb(30,30,48));
        button6.setTextColor(Color.rgb(108,117,125));
        button6.setText("Enter");
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ans = editText.getText().toString();
                editText.setCursorVisible(false);
                editText.setFocusable(false);
                editText.setEnabled(false);

                if(ans.equalsIgnoreCase("yes"))
                {

                    c=c+10;
                    myref6.child(name).child(String.valueOf(ques)).setValue(ans);
                    ques++;
                    button6.setEnabled(false);
                    createText1(q[5]);
                    createEdit();
                    createButton6();


                }
                else if(ans.equalsIgnoreCase("no"))
                {
                    c=c-5;
                    myref6.child(name).child(String.valueOf(ques)).setValue(ans);
                    ques++;
                    button6.setEnabled(false);
                    createText1(q[5]);
                    createEdit();
                    createButton6();

                }
                else
                {
                    button6.setEnabled(false);
                    createText1("Please respond in yes or no");
                    createText1(q[4]);
                    createEdit();
                    createButton5();

                }
            }
        });
        linearLayout.addView(button6);
        return button6;
    }
    public Button createButton6(){

        button7  = new Button(this);
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 5, 0, 0);
        button7.setLayoutParams(layoutParams);
        button7.setBackgroundColor(Color.rgb(30,30,48));
        button7.setTextColor(Color.rgb(108,117,125));
        button7.setText("Enter");
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ans = editText.getText().toString();
                editText.setCursorVisible(false);
                editText.setFocusable(false);
                editText.setEnabled(false);

                if(ans.equalsIgnoreCase("yes"))
                {
                    c=c+10;
                    myref6.child(name).child(String.valueOf(ques)).setValue(ans);
                    ques++;
                    button7.setEnabled(false);
                    createText1(q[6]);
                    createEdit();
                    createButton7();


                }
                else if(ans.equalsIgnoreCase("no"))
                {
                    c=c-5;
                    myref6.child(name).child(String.valueOf(ques)).setValue(ans);
                    ques++;
                    button7.setEnabled(false);
                    createText1(q[6]);
                    createEdit();
                    createButton7();

                }
                else
                {      button7.setEnabled(false);
                    createText1("Please respond in yes or no");
                    createText1(q[5]);
                    createEdit();
                    createButton6();

                }
            }
        });
        linearLayout.addView(button7);
        return button7;
    }
    public Button createButton7(){

        button8  = new Button(this);
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 5, 0, 0);
        button8.setLayoutParams(layoutParams);
        button8.setBackgroundColor(Color.rgb(30,30,48));
        button8.setTextColor(Color.rgb(108,117,125));
        button8.setText("Enter");
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ans = editText.getText().toString();
                editText.setCursorVisible(false);
                editText.setFocusable(false);
                editText.setEnabled(false);

                if(ans.equalsIgnoreCase("yes"))
                {
                    c=c+10;
                    myref6.child(name).child(String.valueOf(ques)).setValue(ans);
                    ques++;
                    button8.setEnabled(false);
                    createText1(q[7]);
                    createEdit();
                    createButton8();


                }
                else if(ans.equalsIgnoreCase("no"))
                {
                    c=c-5;
                    myref6.child(name).child(String.valueOf(ques)).setValue(ans);
                    ques++;
                    button8.setEnabled(false);
                    createText1(q[7]);
                    createEdit();
                    createButton8();

                }
                else
                {   button8.setEnabled(false);
                    createText1("Please respond in yes or no");
                    createText1(q[6]);
                    createEdit();
                    createButton7();

                }
            }
        });
        linearLayout.addView(button8);
        return button8;
    }
    public Button createButton8(){

        button9 = new Button(this);
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 5, 0, 0);
        button9.setLayoutParams(layoutParams);
        button9.setBackgroundColor(Color.rgb(30,30,48));
        button9.setTextColor(Color.rgb(108,117,125));
        button9.setText("Enter");
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ans = editText.getText().toString();
                editText.setCursorVisible(false);
                editText.setFocusable(false);
                editText.setEnabled(false);

                if(ans.equalsIgnoreCase("yes"))
                {
                    c=c+10;
                    myref6.child(name).child(String.valueOf(ques)).setValue(ans);
                    ques++;
                    button9.setEnabled(false);
                    calculate();


                }
                else if(ans.equalsIgnoreCase("no"))
                {
                    c=c-5;

                    ques++;
                    button9.setEnabled(false);
                    calculate();


                }
                else
                {   button9.setEnabled(false);
                    createText1("Please respond in yes or no");
                    createText1(q[7]);
                    createEdit();
                    createButton8();

                }
            }
        });
        linearLayout.addView(button9);
        return button9;
    }

    public void calculate(){




        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("RESULTS");


    if (redzone==true) {

        c = c + 20;
    }

        if(c<0)
        {
            dialog.setMessage("Congratulations you don't have corona virus"+c);
            dialog.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();

                }
            });
            dialog.show();

        }
        else if(c>=70)
        {
            dialog.setMessage("you have 80 percent possibility of bieng affected by covid-19");
            dialog.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();

                }
            });
            dialog.show();
        }
        else if(c==100)
        {
            dialog.setMessage("you have "+c+" percent possibility of being affected by covid-19"+"\n"+"I would recommend you to concern a doctor");
            dialog.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();

                }
            });
            dialog.show();
        }
        else
        {
            dialog.setMessage("you have "+c+" percent possibility of being affected by covid-19");
            dialog.setPositiveButton("OK",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();

                }
            });
            dialog.show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_chatbot);

        BottomNavigationView bottomNavigationView = findViewById(id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(id.chatbot);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){

                    case id.home:
                        Intent intent = new Intent(getApplicationContext(),StatsActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;


                    case id.blogs:
                        Intent intent1 = new Intent(getApplicationContext(),BlogsActivity.class);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                        return true;


                    case id.chatbot:
                        Intent intent2 = new Intent(getApplicationContext(),Chatbot.class);
                        startActivity(intent2);
                        overridePendingTransition(0,0);



                        return true;

                    case id.donate:
                        Intent intent3 = new Intent(getApplicationContext(),DonateActivity.class);
                        startActivity(intent3);
                        overridePendingTransition(0,0);
                        return true;





                }


                return false;

            }
        });




        linearLayout=(LinearLayout)findViewById(id.linear4);
        button10=(Button)findViewById(id.button2);
        button11=(Button)findViewById(id.button3);
         gd = new GradientDrawable();

        client= LocationServices.getFusedLocationProviderClient(this);
        editText2=(EditText)findViewById(id.editText);
        myref6=FirebaseDatabase.getInstance().getReference("ChatBot");
        myref6.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    names = dataSnapshot.getChildrenCount();


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });











    }
    public void name(View view){

        name=editText2.getText().toString();
        if (name.equals("")){

            createText1("Please Enter your name");
            editText2.setCursorVisible(true);
            editText2.setFocusable(true);



        }
        else {

            editText2.setCursorVisible(false);
            editText2.setFocusable(false);
            editText2.setEnabled(false);
            myref6.child(String.valueOf(names+1)).setValue(name);
            createText1("Hii! "+name);





        }


    }

    public void query(View view){


       Intent intent = new Intent(this,QueryActivity.class);
       startActivity(intent);
        overridePendingTransition(0,0);


    }
}
