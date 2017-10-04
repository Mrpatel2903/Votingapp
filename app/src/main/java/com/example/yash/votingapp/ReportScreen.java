package com.example.yash.votingapp;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ReportScreen extends AppCompatActivity {

    ListView list;

    List<String> politicianName,temList;
    LoginDataBaseAdapter loginDataBaseAdapter;

    private List<String> lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_screen);

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();
        temList=loginDataBaseAdapter.getAllPartyName();
        lastName=loginDataBaseAdapter.getAllLastName();
        politicianName=loginDataBaseAdapter.getAllFirstName();
        //Log.e("test", "Data:"+loginDataBaseAdapter.getAllPartyNameCount("NCP"));

        ReportListAdapter adapter = new ReportListAdapter(this, temList,politicianName,lastName);
        list = (ListView) findViewById(R.id.candidate_listView);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            private ImageView icon;
            private String fullName,partyName;

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /** To change selected state view */
                view.setSelected(true);
                String Slecteditem = temList.get(position);
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

                final Dialog dialog = new Dialog(ReportScreen.this);
                dialog.getWindow();
                //dialog.setTitle("Confirm your Vote");
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.vote_result_dialouge);

                final TextView candidate = (TextView) dialog.findViewById(R.id.CandidateName_txtView);
                final TextView polticianParty = (TextView) dialog.findViewById(R.id.party_txtView);
                final TextView result_text = (TextView) dialog.findViewById(R.id.result_text);
                icon = (ImageView) dialog.findViewById(R.id.PartyLogo);
                fullName=politicianName.get(position)+" "+lastName.get(position);
                partyName=temList.get(position);
                candidate.setText(politicianName.get(position)+" "+lastName.get(position));
                polticianParty.setText(temList.get(position));
                //Log.e("test", temList.get(position)+":Data:"+loginDataBaseAdapter.getAllPartyNameCount("NCP"));
                result_text.setText(Integer.toString(loginDataBaseAdapter.getAllPartyNameCount(temList.get(position))));
                if (temList.get(position).equals("NCP")) {
                    icon.setImageResource(R.drawable.watch);
                } else if (temList.get(position).equals("Congress")) {
                    icon.setImageResource(R.drawable.plam);
                } else if (temList.get(position).equals("BJP")) {
                    icon.setImageResource(R.drawable.bjp);
                } else if (temList.get(position).equals("Shivsena")) {
                    icon.setImageResource(R.drawable.shivsena);
                } else {
                    icon.setImageResource(R.drawable.independant);
                }
                dialog.show();
                Button cancelBtn = (Button) dialog.findViewById(R.id.cancel_btn);
                cancelBtn.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });
    }
}
