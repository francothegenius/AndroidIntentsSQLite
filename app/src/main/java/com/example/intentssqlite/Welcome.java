package com.example.intentssqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Welcome extends AppCompatActivity {

    TextView welcome;
    Intent intent;
    RecyclerView recycler;
    DataAdapter adapter;
    ArrayList<String> userInfo;
    User user;
    DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        intent = getIntent();
        welcome = (TextView)findViewById(R.id.welcomeTV);
        recycler = (RecyclerView)findViewById(R.id.recyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        db = new DBHelper(this);
        //GET USER
        user = db.getUserByNickname(intent.getStringExtra("message"));


       userInfo = new ArrayList<String>();


       welcome.setText("Welcome: "+intent.getStringExtra("message"));

       userInfo.add("Name: "+user.name);
       userInfo.add("Last Name: "+user.last_name);
       userInfo.add("Date of Birth: "+user.dob);
       userInfo.add("Role: "+user.role);
       userInfo.add("Semester: "+user.semester);
       userInfo.add("Nickname: "+user.nickname);



    }
}
