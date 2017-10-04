package com.example.yash.votingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OtpSubmitScreen extends AppCompatActivity {

    private Button submit_btn;
    private TextView txt_otp;
    private EditText otp_edt;
    private String stringOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_submit_screen);

        txt_otp = (TextView) findViewById(R.id.txt_otp);
        otp_edt = (EditText) findViewById(R.id.otp_edt);
        submit_btn = (Button) findViewById(R.id.submit_btn);
        stringOtp = generatePIN();

        txt_otp.setText(generatePIN());
        submit_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(otp_edt.getText().toString())&& txt_otp.getText().toString().equals(otp_edt.getText().toString())) {
                    Toast.makeText(OtpSubmitScreen.this, "Login Successfully", Toast.LENGTH_LONG).show();
                    Intent ii=new Intent(OtpSubmitScreen.this,AfterLoginScreen.class);
                    startActivity(ii);
                }else {
                    Toast.makeText(OtpSubmitScreen.this, "Enter Correct OTP!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public String generatePIN()
    {
        //generate a 4 digit integer 1000 <10000
        int randomPIN = (int)(Math.random()*9000)+1000;
        //Store integer in a string
        return String.valueOf(randomPIN);

    }
}
