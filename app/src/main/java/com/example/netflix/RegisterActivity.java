package com.example.netflix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.netflix.Database.CallWraperUser;
import com.example.netflix.Database.DatabaseUtil;
import com.example.netflix.Database.Models.User;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    private Button register;
    private EditText name;
    private EditText username;
    private EditText password;
    private EditText mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        name = findViewById(R.id.textName);
        username = findViewById(R.id.textUsername);
        password = findViewById(R.id.textPassword);
        mail = findViewById(R.id.textMail);
        register = findViewById(R.id.registerButton);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseUtil db = new DatabaseUtil();

                User user = new User(String.valueOf(name.getText()),
                        String.valueOf(username.getText()),
                        String.valueOf(password.getText()),
                        String.valueOf(mail.getText()));
                db.readUserData(new CallWraperUser() {
                    @Override
                    public void readUserDataCallback(List<User> users) {
                        boolean control = true;
                        for (User userdata : users){
                            if(userdata.getMail().equals(user.getMail()) || userdata.getUsername().equals(user.getUsername())){
                                Toast.makeText(getApplicationContext(),"Bu kullanıcı adı veya mail ile kayıtlı hesap bulunmakta",Toast.LENGTH_LONG).show();
                                control = false;
                            }
                        }
                        if(control){
                            db.addUser(user);
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            finish();
                        }
                    }
                });
            }
        });
    }
}