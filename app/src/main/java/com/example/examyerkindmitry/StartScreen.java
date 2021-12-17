package com.example.examyerkindmitry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class StartScreen extends AppCompatActivity {

    private Object User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().hasExtra("User")) {
            User = getIntent().getParcelableExtra("User");
            System.out.println(User);
        }
    }
}