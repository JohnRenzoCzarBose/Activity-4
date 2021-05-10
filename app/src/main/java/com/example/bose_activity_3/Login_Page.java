package com.example.bose_activity_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login_Page extends AppCompatActivity {

    EditText text_username, text_password;
    Button button_login,button_register;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        text_username = (EditText) findViewById(R.id.txt_un2);
        text_password = (EditText) findViewById(R.id.txt_pw3);
        button_login = (Button) findViewById(R.id.btn_login);
        button_register = (Button) findViewById(R.id.btn_register);
        DB = new DBHelper(this);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = text_username.getText().toString();
                String pass = text_password.getText().toString();
                if(user.equals("")||pass.equals(""))
                    Toast.makeText(Login_Page.this, "Not All Fields Are Entered", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user,pass);
                    if(checkuserpass==true){
                        Toast.makeText(Login_Page.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),Home.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login_Page.this, "INVALID CREDENTIALS", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });


    }
}