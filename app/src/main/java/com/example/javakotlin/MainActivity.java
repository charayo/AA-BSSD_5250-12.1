package com.example.javakotlin;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_CHOICE = "com.example.javakotlin.EXTRA_CHOICE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayout = findViewById(R.id.main_layout);

        RadioGroup radioGroup = new RadioGroup(this);

        RadioButton radioButton1 = new RadioButton(this);
        radioButton1.setId(View.generateViewId());
        radioButton1.setText("Blue");

        RadioButton radioButton2 = new RadioButton(this);
        radioButton2.setId(View.generateViewId());
        radioButton2.setText("Yellow");


        RadioButton radioButton3 = new RadioButton(this);
        radioButton3.setId(View.generateViewId());
        radioButton3.setText("Green");

        radioGroup.addView(radioButton1);
        radioGroup.addView(radioButton2);
        radioGroup.addView(radioButton3);

//        linearLayout.addView(radioGroup);
        linearLayout.addView(radioGroup, 1);

        Button submit = findViewById(R.id.submit_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checkedID = radioGroup.getCheckedRadioButtonId();
                RadioButton radio = findViewById(checkedID);
                String choice = radio.getText().toString();

                Intent intent = new Intent(getApplicationContext(), MoodActivity.class);
                intent.putExtra(MainActivity.EXTRA_CHOICE, choice);
                startActivity(intent);
            }
        });
    }
}