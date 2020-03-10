package com.example.intentssqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText nickname, password;
    Button loginLButton, registerLButton;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DBHelper(this);
        nickname = (EditText)findViewById(R.id.nicknameLField);
        password = (EditText)findViewById(R.id.passwordLField);
        loginLButton = (Button)findViewById(R.id.loginLButton);
        registerLButton = (Button)findViewById(R.id.registerLButton);

        loginLButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String entryNickname = nickname.getText().toString();
                String entryPassword = password.getText().toString();
                Boolean auth = db.auth(entryNickname, entryPassword);
                if(auth == true){
                    Toast.makeText(getApplicationContext(),"Login Succesfull!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, Welcome.class);
                    intent.putExtra("message", entryNickname);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(getApplicationContext(),"Wrong nickname or password!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerLButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }
}
