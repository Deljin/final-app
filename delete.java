package com.example.attendence;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class delete extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText roll,name,branch,atten,subjj1,subjj2,subjj3;
    Button btnAddData;
    Button btnViewAll,btnviewUpdate,btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete);
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
        DeleteData();
    }



    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDB.deleteData(roll.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(delete.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(delete.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}
