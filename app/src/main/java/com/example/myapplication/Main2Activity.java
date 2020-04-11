package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

public class Main2Activity extends AppCompatActivity {

    /*if (ContextCompat.checkSelfPermission(thisActivity, Manifest.permission.WRITE_CALENDAR)
            != PackageManager.PERMISSION_GRANTED) {
        // Permission is not granted
    }*/

    private int STORAGE_RPERMISSION_CODE = 1;
    private int STORAGE_WPERMISSION_CODE = 2;

    final String externalDir = Environment.getExternalStorageDirectory().getAbsolutePath();
    final String defaultSaveDir = externalDir + "/HTML";

    TextView rows;
    EditText input;
    Button save;
    OutputStream outputStream;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        rows = (TextView) findViewById(R.id.rows);
        input = (EditText) findViewById(R.id.input);
        save = (Button) findViewById(R.id.save);

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(defaultSaveDir);

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int lines = input.getLineCount();
                String lineText = "";
                for(int z = 1; z <= lines; z++)
                {
                    lineText = lineText + z + "\n";
                }
                rows.setText(lineText);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private boolean isExternalStorageWriteable()
    {
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
        {
            Log.i("State", "Yes, it is writeable!");
            return true;
        }else {
            return false;
        }
    }

    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            Log.i("State", "Yes, it is readable!");
            return true;
        } else {
        return false;}
    }

    public static boolean isExternalStorageMounted() {

        String dirState = Environment.getExternalStorageState();

        if(Environment.MEDIA_MOUNTED.equals(dirState))
        {
            return true;
        }else
        {
            return false;
        }
    }

    //Context context = getApplicationContext();

    public void save(View v) {
        String htmlcode = input.getText().toString();
        if(isExternalStorageWriteable()){
            File outputDirectory = new File(defaultSaveDir);
            if(outputDirectory.exists()){
                Toast.makeText(this, "Directory created", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "Directory not created", Toast.LENGTH_LONG).show();
                outputDirectory.mkdirs();
            }
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_WPERMISSION_CODE);
            save(null);
        }
    }
}
