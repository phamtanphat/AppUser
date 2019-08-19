package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class DanhsachActivity extends AppCompatActivity {

    ListView lvNguoidung;
    NguoidungAdapter nguoidungAdapter ;
    ArrayList<Nguoidung> nguoidungs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsach);
        lvNguoidung = findViewById(R.id.listviewNguoidung);
        nguoidungs = new ArrayList<>();
        nguoidungAdapter = new NguoidungAdapter(this,android.R.layout.simple_list_item_1,nguoidungs);
        lvNguoidung.setAdapter(nguoidungAdapter);
        getData();
    }
    private void getData() {
        Cursor cursor = MainActivity.sqLite.ongetData("SELECT * FROM Nguoidung");
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String username = cursor.getString(1);
            String password = cursor.getString(2);
            long birthdate = cursor.getLong(3);
            int gender = cursor.getInt(4);
            String hobbies = cursor.getString(5);
            nguoidungs.add(new Nguoidung(id ,username,password,birthdate,gender,hobbies));
            nguoidungAdapter.notifyDataSetChanged();

        }

    }
}
