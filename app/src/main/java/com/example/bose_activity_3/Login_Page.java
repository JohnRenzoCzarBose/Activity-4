package com.example.bose_activity_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login_Page extends AppCompatActivity {

    EditText text_un2, text_pw3;
    Button  bt_login, bt_regis;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__page);

        text_un2 = (EditText)findViewById(R.id.txt_un2);
        text_pw3 = (EditText)findViewById(R.id.txt_pw3);
        bt_login = (Button)findViewById(R.id.btn_login);
        bt_regis = (Button)findViewById(R.id.btn_register);
        DB = new DBHelper(this);


        bt_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = text_un2.getText().toString();
                String pass = text_pw3.getText().toString();
                if(user.equals("")||pass.equals(""))
                    Toast.makeText(Login_Page.this, "Not all fields are entered", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user,pass);
                    if(checkuserpass==true){
                        Toast.makeText(Login_Page.this, "Login Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),Home.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(Login_Page.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}