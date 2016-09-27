package com.alrubaye.mytracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity {
EditText EDTNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EDTNumber=(EditText)findViewById(R.id.EDTNumber);
    }

    public void BuNext(View view) {

        GlobalInfo.PhoneNumber=GlobalInfo.FormatPhoneNumber(EDTNumber.getText().toString());
        GlobalInfo.UpdatesInfo(GlobalInfo.PhoneNumber);
finish();
        Intent intent=new Intent(this, MyTrackers.class);
        startActivity(intent);
    }
}
