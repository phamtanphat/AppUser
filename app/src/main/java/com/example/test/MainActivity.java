package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword, edtRetype, edtBirthdate;
    RadioButton radioMale, radioFemale;
    CheckBox ckTennis, ckFutbal, ckOthers;
    Button btnReset, btnSignup, btnSelect;
    static SQLite sqLite;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY");
    DatePickerDialog datePickerDialog = null;
    String chuoi = "";
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        init();
        event();
    }

    private void event() {
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int date = calendar.get(Calendar.DATE);
                datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                        calendar.set(y, m, d);
                        edtBirthdate.setText(simpleDateFormat.format(calendar.getTimeInMillis()));
                    }
                }, year, month, date);
                datePickerDialog.show();
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Unit.HOBBIES = "";
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                String retype = edtRetype.getText().toString().trim();
                String birthday = edtBirthdate.getText().toString().trim();
                if (username.length() > 0 && password.length() > 0) {
                    if (password.equals(retype)) {
                        if (birthday.length() > 0) {
                            if (radioMale.isChecked() || radioFemale.isChecked()) {
                                Unit.GENDER = radioMale.isChecked() ? Unit.MALE : Unit.GENDER;
                                Unit.GENDER = radioFemale.isChecked() ? Unit.FEMALE : Unit.GENDER;
                                if (ckFutbal.isChecked() || ckTennis.isChecked() || ckOthers.isChecked()) {
                                    Unit.HOBBIES += ckTennis.isChecked() ? Unit.TENNIS : "";
                                    Unit.HOBBIES += ckFutbal.isChecked() ? Unit.FUTBAL : "";
                                    Unit.HOBBIES += ckOthers.isChecked() ? Unit.OTHERS : "";


                                    String insertdata = "INSERT INTO Nguoidung VALUES(null , '" + username + "','" + password + "' , " + calendar.getTimeInMillis() + " , " + Unit.GENDER + " , '" + Unit.HOBBIES + "')";
                                    sqLite.onQuery(insertdata);


                                } else {
                                    Toast.makeText(MainActivity.this, "Ban chua chon ve so thich", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "Ban hay chon gioi tinh", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Ban hay chon ngay sinh", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Ban nhap sai xac nhan", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Moi ban nhap du thong tin", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menudanhsach:
                Intent intent = new Intent(MainActivity.this, DanhsachActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        calendar = Calendar.getInstance();
        sqLite = new SQLite(this, "Quanlynguoidung.sql", null, 1);
        String createTable = "CREATE TABLE IF NOT EXISTS Nguoidung(Id INTEGER PRIMARY KEY AUTOINCREMENT , Username VARCHAR ,Password INTEGER ,Birthdate LONG , Gender INTEGER ,Hobbies VARCHAR)";
        sqLite.onQuery(createTable);
    }


    private void anhxa() {
        edtBirthdate = findViewById(R.id.edittextBirthday);
        edtPassword = findViewById(R.id.edittextPassword);
        edtRetype = findViewById(R.id.edittextRetype);
        edtUsername = findViewById(R.id.edittextUsername);
        ckFutbal = findViewById(R.id.checkboxFutbal);
        ckOthers = findViewById(R.id.checkboxOthers);
        ckTennis = findViewById(R.id.checkboxTennis);
        btnReset = findViewById(R.id.buttonReset);
        btnSignup = findViewById(R.id.buttonSignup);
        btnSelect = findViewById(R.id.buttonSelect);
        radioFemale = findViewById(R.id.radiobuttonFemale);
        radioMale = findViewById(R.id.radiobuttonMale);
    }
}
