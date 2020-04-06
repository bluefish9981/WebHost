package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.content.Intent;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private Button newView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newView = (Button) findViewById(R.id.newView);
        newView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open2ndActivity();
            }
        });
    }

    public void open2ndActivity() {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}
