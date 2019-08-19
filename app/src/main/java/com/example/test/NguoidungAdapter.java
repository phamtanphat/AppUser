package com.example.test;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

public class NguoidungAdapter extends ArrayAdapter<Nguoidung> {

    public NguoidungAdapter(Context context, int resource, List<Nguoidung> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        convertView = layoutInflater.inflate(R.layout.item_nguoidung, null);

        TextView txtUsername = convertView.findViewById(R.id.textusername);
        TextView txtPassword = convertView.findViewById(R.id.textpassword);
        TextView txtBirthdate = convertView.findViewById(R.id.textbirthdate);
        TextView txtGender = convertView.findViewById(R.id.textgender);
        TextView txtHobbies = convertView.findViewById(R.id.texthobbies);

        Nguoidung nguoidung = getItem(position);

        txtUsername.setText("User name : " +nguoidung.getUsername());
        txtPassword.setText("Password : " +nguoidung.getPassword());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY");
        txtBirthdate.setText("Birthdate : " + simpleDateFormat.format(nguoidung.getBirthday()));
        if (nguoidung.getGender() == Unit.FEMALE) {
            txtGender.setText("Gender : Female");
        } else if (nguoidung.getGender() == Unit.MALE) {
            txtGender.setText("Gender : Female");
        }
        String hobbies = "";

        if (nguoidung.getHobbies().contains(String.valueOf(Unit.FUTBAL))) {
            hobbies += " Futbal ";
        }
        if (nguoidung.getHobbies().contains(String.valueOf(Unit.TENNIS))) {
            hobbies += " Tennis ";
        }
        if (nguoidung.getHobbies().contains(String.valueOf(Unit.OTHERS))) {
            hobbies += " Others ";
        }

        txtHobbies.setText("Hobbies : " + hobbies);
        return convertView;
    }
}
