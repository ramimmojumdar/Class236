package com.example.class236;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText edName;
    AppCompatButton btn_save, btn_new_activity;
    SharedPreferences sharedPreferences;
    TextView tvOpenCount;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edName = findViewById(R.id.edName);
        btn_save = findViewById(R.id.btn_save);
        btn_new_activity = findViewById(R.id.btn_new_activity);
        tvOpenCount = findViewById(R.id.tvOpenCount);

        sharedPreferences = getSharedPreferences(getString(R.string.app_name) , MODE_PRIVATE);
        editor = sharedPreferences.edit();

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = edName.getText().toString();
                editor.putString("name", ""+name);
                editor.apply();


            }
        });


        btn_new_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });






        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        String currentDate = sdf.format(new Date());

        String savedDate = sharedPreferences.getString("date", "");
        int openCount = sharedPreferences.getInt("count", 0);

// Check if the current date is the same as the saved date
        if (currentDate.equals(savedDate)) {
            // Same day, increment the count
            openCount++;
        } else {
            // New day, reset the count to 1
            openCount = 1;
            editor.putString("date", currentDate);
        }

// Save the new count
        editor.putInt("count", openCount);
        editor.apply();

        tvOpenCount.setText("App Open Count: " + openCount +"\n" + currentDate);



    }
}