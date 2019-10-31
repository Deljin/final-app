package com.example.attendence;


import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText roll,name,branch,atten,subjj1,subjj2,subjj3,email,phone;
    Button btnAddData;
    Button btnViewAll,btnviewUpdate,btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
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
        AddData();
        viewAll();
        UpdateData();
        DeleteData();
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
                            Toast.makeText(Activity2.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Activity2.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll() {
        btnViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDB.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            double percentage,s1,s2,s3=0.0;
                            buffer.append("ROLL NO:"+ res.getString(0)+"\n");
                            buffer.append("NAME:"+ res.getString(1)+"\n");
                            buffer.append("BRANCH :"+ res.getString(2)+"\n");
                            buffer.append("ATTENDANCE :"+ res.getString(3)+"\n");
                            buffer.append("SUBJECT1 :"+ res.getString(4)+"\n");
                            buffer.append("SUBJECT2 :"+ res.getString(5)+"\n");
                            buffer.append("SUBJECT3 :"+ res.getString(6)+"\n");
                            s1=res.getDouble(4);
                            s2=res.getDouble(5);
                            s3=res.getDouble(6);
                            percentage=(s1+s2+s3)/300;
                            s1=s2=s3=0;
                            buffer.append("PERCENTAGE :"+ Double.toString(percentage*100)+"%"+"\n\n");
                            percentage=0;
                        }

                        // Show all data
                        showMessage("REPORT CARD",buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
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
                            Toast.makeText(Activity2.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Activity2.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDB.deleteData(roll.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(Activity2.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Activity2.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}
