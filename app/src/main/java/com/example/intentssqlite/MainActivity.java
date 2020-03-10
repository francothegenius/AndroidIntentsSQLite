package com.example.intentssqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    DBHelper db;
    EditText name, lastName, dob, role, semester, nickname, password, confPassword;
    Button registerButton, loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);

        name = (EditText)findViewById(R.id.nameRField);
        lastName = (EditText)findViewById(R.id.lastNameRField);
        dob = (EditText)findViewById(R.id.dobRField);
        role = (EditText)findViewById(R.id.roleRField);
        semester = (EditText)findViewById(R.id.semesterRField);
        nickname = (EditText)findViewById(R.id.nicknameRField);
        password = (EditText)findViewById(R.id.passwordRField);
        confPassword = (EditText)findViewById(R.id.passwordRField2);

        registerButton = (Button) findViewById(R.id.registerButton);
        loginButton = (Button) findViewById(R.id.loginLButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String entryName = name.getText().toString();
                String entryLastName = lastName.getText().toString();
                String entryDOB = dob.getText().toString();
                String entryRole = role.getText().toString();
                String entrySemester = semester.getText().toString();
                String entryNickname = nickname.getText().toString();
                String entryPassword = password.getText().toString();
                String entryConfPassword = confPassword.getText().toString();

                if(entryName.equals("") || entryLastName.equals("") || entryRole.equals("")
                || entrySemester.equals("") || entryNickname.equals("") || entryPassword.equals("")
                || entryConfPassword.equals("")){
                    Toast.makeText(getApplicationContext(), "Some fields are empty!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (entryPassword.equals(entryConfPassword)){
                        Boolean checkNickname = db.checkNickname(entryNickname);
                        if (checkNickname == true){
                            User user = new User(entryName, entryLastName, entryDOB, entryRole, Integer.parseInt(entrySemester), entryNickname,
                                    entryPassword);
                            Boolean insert = db.insertUser(user);
                            if(insert == true) {
                                Toast.makeText(getApplicationContext(), "User registered!!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Nickname already exists!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Password do not match", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });


    }
}
