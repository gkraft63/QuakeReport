package com.example.android.quakereport;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class QuakeAppSettings extends AppCompatActivity {
    int quakeNum;
    String quakeMinMag= "4.0";
    String yearStart = "2020-01-01";
    String yearEnd = "2020-03-20";
    Button startDate,endDate;
    TextView startDateTxt;
    DatePickerDialog datePicker;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quake_settings);

        SharedPreferences sp = getSharedPreferences("AppStorage", Activity.MODE_PRIVATE);
        quakeNum = sp.getInt("Quake_Num", 50);
        quakeMinMag = sp.getString("Quake_min_mag","4.0");
        yearStart = sp.getString("Year_Start","2020-01-01");
        yearEnd = sp.getString("Year_End","2020-03-20");

        EditText quakeNumEditText = findViewById(R.id.num_quakes);
        quakeNumEditText.setText(String.valueOf(quakeNum));

        EditText quakeMinMagEditText = findViewById(R.id.quakes_min_mag);
        quakeMinMagEditText.setText(String.valueOf(quakeMinMag));

        Button yearStartBtnText = findViewById(R.id.btnDate);
        yearStartBtnText.setText(String.valueOf(yearStart));

        Button yearEndBtnText = findViewById(R.id.btnEndDate);
        yearEndBtnText.setText(String.valueOf(yearEnd));

        startDate = findViewById(R.id.btnDate);
        endDate = findViewById(R.id.btnEndDate);
        startDateTxt = findViewById(R.id.tvSelectedDate);

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePicker = new DatePickerDialog(QuakeAppSettings.this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker datePicker, int year, int month, int day) {
                Button yearStartBtnText = findViewById(R.id.btnDate);
                yearStartBtnText.setText(year+"-"+(month+1) + "-" + day);
                startDateTxt.setText((month+1) + "/" + day + "/" + year);
                    }
                }, year, month, dayOfMonth);
                datePicker.show();
            }
        });

        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePicker = new DatePickerDialog(QuakeAppSettings.this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker datePicker, int year, int month, int day) {
                Button yearEndBtnText = findViewById(R.id.btnEndDate);
                 yearEndBtnText.setText(year+"-"+(month+1) + "-" + day);
                startDateTxt.setText((month+1) + "/" + day + "/" + year);
                    }
                }, year, month, dayOfMonth);
                datePicker.show();
            }
        });
    }

    public void saveSettings(View v) {
        EditText quakeNumEditText = findViewById(R.id.num_quakes);
        EditText quakeMinMagEditText = findViewById(R.id.quakes_min_mag);
        Button yearStartBtnText = findViewById(R.id.btnDate);
        Button yearEndBtnText = findViewById(R.id.btnEndDate);

        CharSequence v1 = quakeNumEditText.getText();
        try {
            quakeNum = Integer.parseInt(v1.toString());
        } catch (Exception e) {
            quakeNumEditText.setText("10");
            return;
        }
        CharSequence v2 = quakeMinMagEditText.getText();
        try {
            quakeMinMag = v2.toString();
        } catch (Exception e) {
            quakeMinMagEditText.setText("4.0");
            return;
        }


    CharSequence v3 = yearStartBtnText.getText();
        try {
        yearStart = v3.toString();
    } catch (Exception e) {
        yearStartBtnText.setText("2020-01-01");
        return;
    }


    CharSequence v4 = yearEndBtnText.getText();
        try {
                yearEnd = v4.toString();
                } catch (Exception e) {
                yearEndBtnText.setText("2020-03-20");
                return;
                }

        SharedPreferences settings = getSharedPreferences("AppStorage", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("Quake_Num", quakeNum);
        editor.putString("Quake_min_mag",quakeMinMag);
        editor.putString("Year_Start",yearStart);
        editor.putString("Year_End",yearEnd);


        editor.commit();
        Toast.makeText(QuakeAppSettings.this, "Settings Saved!", Toast.LENGTH_LONG).show();
        onBackPressed();
       }

    double getDouble(final SharedPreferences prefs, final String key, final double defaultValue) {
        if ( !prefs.contains(key))
            return defaultValue;

        return Double.longBitsToDouble(prefs.getLong(key, 0));
    }

}