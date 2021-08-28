package com.example.fitnessisolate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private String st,ss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnlogin=findViewById(R.id.btnLogin);
        EditText Name=findViewById(R.id.inputName2);
        EditText Surname=findViewById(R.id.inputSurname2);
        EditText IdNumber=findViewById(R.id.inputIdNumber2);
        EditText Password=findViewById(R.id.inputPassword2);
        TextView goRegister=findViewById(R.id.DontHaveAnAccount);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this,HomeScreen.class);
                Toast.makeText(Login.this, "Login successfull", Toast.LENGTH_LONG).show();
                st=Name.getText().toString();
                ss=Surname.getText().toString();
                intent.putExtra("Value",st);
                intent.putExtra("Values",ss);
                startActivity(intent);
                finish();

            }
        });
      goRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this,Registration.class);
                startActivity(intent);

            }
        });
    }
}