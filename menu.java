package com.example.attendence;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class menu extends AppCompatActivity {
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

    }

    public void register(View view){
        Intent intent = new Intent(context, register.class);
        startActivity(intent);
    }
    public void view(View view){
        Intent intent = new Intent(context, view.class);
        startActivity(intent);
    }
    public void update(View view){
        Intent intent = new Intent(context, update.class);
        startActivity(intent);
    }
    public void delete(View view){
        Intent intent = new Intent(context, delete.class);
        startActivity(intent);
    }
    public void alert(View view){
        Intent intent = new Intent(context, sendEmail.class);
        startActivity(intent);
    }
}
