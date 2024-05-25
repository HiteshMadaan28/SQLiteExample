package com.example.sqliteexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView t1,t2,t3;
    EditText e1,e2,e3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        t1 = findViewById(R.id.tvname);
        t2 = findViewById(R.id.tvlocation);
        t3 = findViewById(R.id.tvdesignation);

        e1 = findViewById(R.id.etname);
        e2 = findViewById(R.id.etlocation);
        e3 = findViewById(R.id.etdesignation);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = e1.getText().toString()+"\n";
                String location = e2.getText().toString();
                String designation = e3.getText().toString();

                DbHandler db = new DbHandler(MainActivity.this);
                db.insertUserDetails(name,location,designation);

                Intent intent= new Intent(MainActivity.this,details.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"Data Inserted", Toast.LENGTH_SHORT).show();

            }
        });

    }
}