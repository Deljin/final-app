package com.example.attendence;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    EditText username,password;
    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void submitOrder(View view) {
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);

        if(Objects.equals(username.getText().toString(), "admin")&&Objects.equals(password.getText().toString(),"admin"))
        {
            username=null;
            password=null;
            Toast.makeText(MainActivity.this,"You have Authenticated Successfully",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, menu.class);
            startActivity(intent);
        }else
        {
            Toast.makeText(MainActivity.this,"Authentication Failed",Toast.LENGTH_LONG).show();
        }

    }


}
