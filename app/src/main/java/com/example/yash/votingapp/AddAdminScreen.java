package com.example.yash.votingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AddAdminScreen extends AppCompatActivity {

    private EditText firsNameEdit,LastNameEdit,partyNameEdit,symbolEdit;
    private Button saveBtn,cancleBtn;
    private LoginDataBaseAdapter loginDataBaseAdapter;
    ArrayList<String> tempList=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_admin_screen);

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();
        firsNameEdit=(EditText) findViewById(R.id.firstname_edt);
        LastNameEdit=(EditText) findViewById(R.id.lastname_edt);
        partyNameEdit=(EditText) findViewById(R.id.partyname_edt);
        symbolEdit=(EditText) findViewById(R.id.symbol_edt);

        saveBtn=(Button) findViewById(R.id.save_btn);
        cancleBtn=(Button) findViewById(R.id.cancel_btn);

        saveBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(firsNameEdit.getText().toString())&&!TextUtils.isEmpty(LastNameEdit.getText().toString())&&!TextUtils.isEmpty(symbolEdit.getText().toString())&&!TextUtils.isEmpty(partyNameEdit.getText().toString())) {

                    tempList.add(partyNameEdit.getText().toString());
                    Set<String> set = new HashSet<String>(tempList);

                    if(set.size() < tempList.size()){
					    /* There are duplicates */
                        Toast.makeText(AddAdminScreen.this, "Party Alerady exist!", Toast.LENGTH_LONG).show();
                    }else {
                        // TODO Auto-generated method stub
                        loginDataBaseAdapter.insertAdminEntry(firsNameEdit.getText().toString(), LastNameEdit.getText().toString(), partyNameEdit.getText().toString(), symbolEdit.getText().toString());
                        Toast.makeText(AddAdminScreen.this, "Data save successfully !", Toast.LENGTH_LONG).show();
                        firsNameEdit.setText("");
                        LastNameEdit.setText("");
                        partyNameEdit.setText("");
                        symbolEdit.setText("");
                    }


                }else {
                    Toast.makeText(AddAdminScreen.this, "Enter Details!", Toast.LENGTH_LONG).show();
                }

            }
        });

        cancleBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });

    }
}
