package com.example.bose_activity_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText text_un, text_pw, text_pw2;
    Button bt_reg, bt_back;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_un = (EditText)findViewById(R.id.txt_un);
        text_pw = (EditText)findViewById(R.id.txt_pw);
        text_pw2 = (EditText)findViewById(R.id.txt_pw2);
        bt_reg = (Button)findViewById(R.id.btn_reg);
        bt_back = (Button)findViewById(R.id.btn_back);
        DB = new DBHelper(this);

        bt_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = text_un.getText().toString();
                String pass = text_pw.getText().toString();
                String repassword = text_pw2.getText().toString();

                if(user.equals("")||pass.equals("")||repassword.equals(""))
                    Toast.makeText(MainActivity.this,"Not all fields are entered", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repassword)){
                        Boolean checkuser = DB.checkusername(user);

                        if(checkuser==false){
                            Boolean insert = DB.insertData(user,pass);

                            if(insert==true){
                                Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Login_Page.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this,"Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this,"Username Already Exists", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this,"Password does not match", Toast.LENGTH_SHORT).show();
                        }
                }
            }
        });

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Login_Page.class);
                startActivity(intent);
            }
        });
    }
}