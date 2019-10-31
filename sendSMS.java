package com.example.attendence;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static com.example.attendence.R.layout.email;

public class sendSMS extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    Button sendBtn;
    EditText txtphoneNo;
    EditText txtMessage;
    String phoneNo;
    String message;
    DatabaseHelper myDB;
    String s=null,ss = null,sq=null,finalss="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(email);
        myDB = new DatabaseHelper(this);
        sendBtn = (Button) findViewById(R.id.SMS);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                viewAll();
                sendSMSMessage();
                //sendSMS();
            }
        });
    }

    public void viewAll() {

        Cursor res = myDB.getAllData();
        if(res.getCount() != 0) {
            // show message
            while (res.moveToNext()) {
                double percentage,s1,s2,s3=0.0;
                s = "ROLL NO:"+ res.getString(0)+"\n"+
                        "NAME:"+ res.getString(1)+"\n"+
                        "BRANCH :"+ res.getString(2)+"\n"+
                        "ATTENDANCE :"+ res.getString(3)+"\n"+
                        "SUBJECT1 :"+ res.getString(4)+"\n"+
                        "SUBJECT2 :"+ res.getString(5)+"\n"+
                        "SUBJECT3 :"+ res.getString(6)+"\n"+
                        "EMAIL ID :"+ res.getString(7)+"\n";
                sq =   res.getString(8);
                s1=res.getDouble(4);
                s2=res.getDouble(5);
                s3=res.getDouble(6);
                percentage=(s1+s2+s3)/300;
                s1=s2=s3=0;
                ss= "PERCENTAGE :"+ Double.toString(percentage*100)+"%";
                percentage=0;
            };
            finalss=s+"\n"+ss;
            System.out.println(finalss);
        }



    }

    protected void sendSMSMessage() {
        phoneNo = sq;
        message = finalss;

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }
//protected void sendSMS() {
//    Log.i("Send SMS", "");
//    Intent smsIntent = new Intent(Intent.ACTION_VIEW);
//
//    smsIntent.setData(Uri.parse("smsto:"));
//    smsIntent.setType("vnd.android-dir/mms-sms");
//    smsIntent.putExtra("address"  ,sq);
//    smsIntent.putExtra("sms_body"  ,finalss);
//
//    try {
//        startActivity(smsIntent);
//        finish();
//        Log.i("Finished sending SMS...", "");
//    } catch (android.content.ActivityNotFoundException ex) {
//        Toast.makeText(sendSMS.this,
//                "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
//    }
//}
//
//    @SuppressLint("ResourceType")
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(email, menu);
//        return true;
//    }

}