package com.example.sqliteexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class details extends AppCompatActivity {
    Intent intent;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);

        DbHandler db=new DbHandler(details.this);

        ArrayList<HashMap<String,String>> userlist=db.getUser();
        ListView lv = (ListView)findViewById(R.id.user_list);
        ListAdapter adapter =new SimpleAdapter(details.this,userlist,R.layout.list_row,new String[]{"name","designation","location"},new int[]{R.id.name,R.id.designation,R.id.location});
        lv.setAdapter(adapter);

        back=findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(details.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }
}