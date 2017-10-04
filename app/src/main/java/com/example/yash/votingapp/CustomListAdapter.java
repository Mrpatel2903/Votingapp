package com.example.yash.votingapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Yash on 06-09-2017.
 */

class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private List<String> itemname;
    private List<String> pname;
    private List<String> lastName;

    public CustomListAdapter(Activity context, List<String> itemname, List<String> pname, List<String> lastName) {
        super(context, R.layout.candidate_list, itemname);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.itemname = itemname;
        this.pname = pname;
        this.lastName = lastName;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.candidate_list, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.CandidateName_txtView);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.PartyLogo);
        TextView extratxt = (TextView) rowView.findViewById(R.id.party_txtView);

        txtTitle.setText(pname.get(position) + " " + lastName.get(position));
        extratxt.setText(itemname.get(position));

        if (itemname.get(position).equals("NCP")) {
            imageView.setImageResource(R.drawable.watch);
        } else if (itemname.get(position).equals("Congress")) {
            imageView.setImageResource(R.drawable.plam);
        } else if (itemname.get(position).equals("BJP")) {
            imageView.setImageResource(R.drawable.bjp);
        } else if (itemname.get(position).equals("Shivsena")) {
            imageView.setImageResource(R.drawable.shivsena);
        } else {
            imageView.setImageResource(R.drawable.independant);
        }
        return rowView;
    }
}
