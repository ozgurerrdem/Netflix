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

public class LoginActivity extends AppCompatActivity {
    private Button login_btn;
    private Button register_btn;
    private EditText username;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.text_username);
        password = findViewById(R.id.textPassword);

        login_btn = findViewById(R.id.loginButton);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseUtil db = new DatabaseUtil();
                User user = new User(String.valueOf(username.getText()),String.valueOf(password.getText()));
                db.readUserData(new CallWraperUser() {
                    @Override
                    public void readUserDataCallback(List<User> users) {
                        boolean control = true;
                        for (User userdata : users){
                            if((userdata.getMail().equals(user.getUsername()) || userdata.getUsername().equals(user.getUsername())) && userdata.getPassword().equals(user.getPassword())){
                                Toast.makeText(getApplicationContext(),"Başarılı",Toast.LENGTH_LONG).show();
                                control = false;
                                MockAccount.mail = userdata.getMail();
                                MockAccount.username = userdata.getUsername();
                                MockAccount.name = userdata.getName();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            }
                        }
                        if(control){
                            Toast.makeText(getApplicationContext(),"Hatalı giriş",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        register_btn = findViewById(R.id.kayitButton);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
}