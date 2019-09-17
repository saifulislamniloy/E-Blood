package com.example.eblood;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Custom_adapter extends ArrayAdapter<DataToRegister> {

    private Activity context;
    private List<DataToRegister> dataToRegisterList;

    public Custom_adapter(Activity context, List<DataToRegister> dataToRegistersList) {
        super(context, R.layout.sample_layout, dataToRegistersList);
        this.context = context;
        this.dataToRegisterList = dataToRegistersList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample_layout, null, true);
        DataToRegister dataToRegister = dataToRegisterList.get(position);

        TextView t1 = view.findViewById(R.id.nameID);
        TextView t2 = view.findViewById(R.id.bgID);
        TextView t3 = view.findViewById(R.id.districtID);
        TextView t4 = view.findViewById(R.id.divisionId);
        TextView t5 = view.findViewById(R.id.registrationFormPhoneNumber);


        t1.setText("Blood Group : " +dataToRegister.getSearchKey());
        t2.setText("Name : " +dataToRegister.getName());
        t3.setText("Phone No : " +dataToRegister.getPhoneNumber());


        return view;
    }
}
