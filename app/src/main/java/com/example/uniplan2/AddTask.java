package com.example.uniplan2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

public class AddTask extends AppCompatActivity implements View.OnClickListener {

    public Button btnDatePicker;
    public EditText txtDate;
    private int mYear, mMonth, mDay;
    private int dayInt, monthInt, yearInt;
    public String[] classSelect;
    public Spinner classSpinner;

    //Values to store in task table
    public String taskName;
    public String notes;
    public int id;
    private String taskAdded;
    private Toolbar mTopToolbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_add_task);

        mTopToolbar = findViewById(R.id.basic_toolbar);
        setSupportActionBar(mTopToolbar);

        final EditText taskNameEditText =  findViewById(R.id.taskNameEditText);
        final EditText taskDescriptionEditText =  findViewById(R.id.taskDescriptionEditText);

        classSpinner = findViewById(R.id.classSpinner);
        btnDatePicker=findViewById(R.id.dateButton);
        txtDate=findViewById(R.id.dueDateEdit);

        btnDatePicker.setOnClickListener(this);

    //~~~~~~~~~~~~~Once the done button is clicked~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        FloatingActionButton addTaskDoneButton = findViewById(R.id.addTaskDoneButton);
        addTaskDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskName = taskNameEditText.getText().toString();
                notes = taskDescriptionEditText.getText().toString();

                Intent i = new Intent(getBaseContext(), MainActivity.class );
                i.putExtra("taskName", taskName);
                i.putExtra("notes", notes);
                i.putExtra("day", dayInt);
                i.putExtra("month", monthInt);
                i.putExtra("year", yearInt);

                taskAdded = "t";
                i.putExtra("taskAdded", taskAdded);


                startActivity(i);


            }
        });
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    }

    @Override
    public void onClick(View v) {
        if(v==btnDatePicker){

            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    txtDate.setText(dayOfMonth + "/" + (month+1) + "/" + year);
                    dayInt = dayOfMonth;
                    monthInt = month+1;
                    yearInt = year;

                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }
}
