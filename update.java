package com.example.attendence;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class update extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText roll,name,branch,atten,subjj1,subjj2,subjj3;
    Button btnAddData;
    Button btnViewAll,btnviewUpdate,btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);
        myDB = new DatabaseHelper(this);

        roll = (EditText)findViewById(R.id.rolldb);
        name = (EditText)findViewById(R.id.namedb);
        branch = (EditText)findViewById(R.id.branchdb);
        atten = (EditText)findViewById(R.id.attendb);
        subjj1 = (EditText)findViewById(R.id.sub1);
        subjj2 = (EditText)findViewById(R.id.sub2);
        subjj3 = (EditText)findViewById(R.id.sub3);
        btnAddData = (Button)findViewById(R.id.login);
        btnViewAll = (Button)findViewById(R.id.view);
        btnviewUpdate = (Button)findViewById(R.id.update);
        btnDelete = (Button)findViewById(R.id.delete);

        UpdateData();
    }


    public void UpdateData() {
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDB.updateData(roll.getText().toString(),
                                name.getText().toString(),
                                branch.getText().toString(),atten.getText().toString(),subjj1.getText().toString(),subjj2.getText().toString(),subjj3.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(update.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(update.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}
