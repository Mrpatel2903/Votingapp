package com.example.yash.votingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class home_screen extends AppCompatActivity {

    private Button userBtn, adminBtn, reportBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);

        adminBtn = (Button) findViewById(R.id.admin_btn);
        userBtn = (Button) findViewById(R.id.user_btn);
        reportBtn = (Button) findViewById(R.id.report_btn);

        adminBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                 Intent i = new Intent(home_screen.this, AddAdminScreen.class);
                startActivity(i);
			}
        });

        userBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(home_screen.this, login_registration.class);
                startActivity(i);
            }
        });

        reportBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(home_screen.this, ReportScreen.class);
                startActivity(i);
            }
        });
    }
}
