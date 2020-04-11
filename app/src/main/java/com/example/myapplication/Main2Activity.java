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

import androidx.annotation.RequiresApi;
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

    private static final String FILE_NAME = "example.html";

    TextView rows;
    EditText input;
    Button save;
    String htmlSavePath = "/storage/emulated/0/HTML";
    OutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        rows = (TextView) findViewById(R.id.rows);
        input = (EditText) findViewById(R.id.input);
        save = (Button) findViewById(R.id.save);

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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void save(View v) {
        String htmlcode = input.getText().toString();
        FileOutputStream fos = null;
        if(isExternalStorageWriteable()){
            File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            File file = new File(dir, "example.html");

            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write(htmlcode);
            } catch (IOException e) {}
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_WPERMISSION_CODE);
        }
    }
}
