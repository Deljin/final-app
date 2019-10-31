package com.example.attendence;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class register extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText roll,name,branch,atten,subjj1,subjj2,subjj3,email,phone;
    Button btnAddData;
    Button btnViewAll,btnviewUpdate,btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        myDB = new DatabaseHelper(this);

        roll = (EditText)findViewById(R.id.rolldb);
        name = (EditText)findViewById(R.id.namedb);
        branch = (EditText)findViewById(R.id.branchdb);
        atten = (EditText)findViewById(R.id.attendb);
        subjj1 = (EditText)findViewById(R.id.sub1);
        subjj2 = (EditText)findViewById(R.id.sub2);
        subjj3 = (EditText)findViewById(R.id.sub3);
        email = (EditText)findViewById(R.id.emaildb);
        phone = (EditText)findViewById(R.id.phonedb);
        btnAddData = (Button)findViewById(R.id.login);
        btnViewAll = (Button)findViewById(R.id.view);
        btnviewUpdate = (Button)findViewById(R.id.update);
        btnDelete = (Button)findViewById(R.id.delete);
        AddData();
    }

    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDB.insertData(roll.getText().toString(),
                                name.getText().toString(),
                                branch.getText().toString(),atten.getText().toString(),subjj1.getText().toString(),subjj2.getText().toString(),subjj3.getText().toString(),email.getText().toString(),phone.getText().toString() );
                        if(isInserted == true)
                            Toast.makeText(register.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(register.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }



}
