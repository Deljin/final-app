package com.example.attendence;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class sendEmail extends AppCompatActivity {
    DatabaseHelper myDB;
    String s=null,ss = null,finalss="",string=null,def=null;
    EditText rollno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email);
        myDB = new DatabaseHelper(this);
        rollno = (EditText) findViewById(R.id.rollno);
        Button startBtn = (Button) findViewById(R.id.sendEmail);
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                viewAll();
                SendEmail();
            }
        });
    }

    public void viewAll() {


        Cursor res = myDB.getAllData();
        //System.out.println(res.getString(0));
        if(res.getCount() != 0) {
            // show message
            while (res.moveToNext()) {
                double percentage,s1,s2,s3=0.0;
                if(res.getString(0).equals(rollno.getText().toString())){
                    if(res.getDouble(3)> 75){
                        def = "NO";
                    }
                    else{
                        def = "YES";
                    }
                    s = "ROLL NO:"+ res.getString(0)+"\n"+
                            "NAME :"+ res.getString(1)+"\n"+
                            "BRANCH :"+ res.getString(2)+"\n"+
                            "ATTENDANCE :"+ res.getString(3)+"\n"+
                            "DEFAULTER :"+def+"\n"+
                            "SUBJECT1 :"+ res.getString(4)+"\n"+
                            "SUBJECT2 :"+ res.getString(5)+"\n"+
                            "SUBJECT3 :"+ res.getString(6)+"\n"+
                            "EMAIL ID :"+ res.getString(7)+"\n"+
                            "PHONE NO :"+ res.getString(8);
                    s1=res.getDouble(4);
                    s2=res.getDouble(5);
                    s3=res.getDouble(6);
                    percentage=(s1+s2+s3)/300;
                    s1=s2=s3=0;
                    string = res.getString(7);
                    ss= "PERCENTAGE :"+ Double.toString(percentage*100)+"%";
                    percentage=0;
                }

            };
           finalss=s+"\n"+ss;

        }



    }

    @SuppressLint("LongLogTag")
    protected void SendEmail() {
        Log.i("Send email", "hii");
        String[] TO = {string};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);


        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "REPORT CARD");
        emailIntent.putExtra(Intent.EXTRA_TEXT, finalss);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(sendEmail.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}