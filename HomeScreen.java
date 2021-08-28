package com.example.fitnessisolate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity {
    TextView tv;
    String st,ss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Button btnMain=findViewById(R.id.btnMainActivity);

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(HomeScreen.this,MainActivity.class);
                startActivity(intent);

            }});
            Button btnReg=findViewById(R.id.btnMainRegister);

        btnReg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(HomeScreen.this,Registration.class);
                    startActivity(intent);

                }
        });
        Button btnGoLogin=findViewById(R.id.btnGoLogin);

        btnGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(HomeScreen.this,Login.class);
                startActivity(intent);

            }

        });
        tv=(TextView)findViewById(R.id.textVisitor);
        st=getIntent().getExtras().getString("Value");
        ss=getIntent().getExtras().getString("Values");
        tv.setText("WELCOME USER:"+ st + " " + ss);
    }


}
