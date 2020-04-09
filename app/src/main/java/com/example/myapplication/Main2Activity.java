package com.example.myapplication;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.widget.EditText;
        import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    TextView rows;
    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        rows = (TextView) findViewById(R.id.rows);
        input = (EditText) findViewById(R.id.input);

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
}
