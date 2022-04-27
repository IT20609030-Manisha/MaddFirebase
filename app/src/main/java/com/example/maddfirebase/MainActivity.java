package com.example.maddfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    //Button btnAdd;
    EditText txtID, txtName, txtAdd, txtConNo;
    Button btnSave, btnShow, btnUpdate, btnDelete;
    DatabaseReference dbRef;
    Student std;

    //method to clear all user inputs
    private void clearControls(){
        txtID.setText("");
        txtName.setText("");
        txtAdd.setText("");
        txtConNo.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtID = findViewById(R.id.txtID);
        txtName = findViewById(R.id.txtName);
        txtAdd = findViewById(R.id.txtAdd);
        txtConNo = findViewById(R.id.txtConNo);

        btnSave = findViewById(R.id.btnSave);
        btnShow = findViewById(R.id.btnShow);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        std = new Student();

        //btnAdd = findViewById(R.id.btnAdd);

        btnSave.setOnClickListener(view -> {
            dbRef = FirebaseDatabase.getInstance().getReference().child("Student");

            try {
                if (TextUtils.isEmpty(txtID.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please enter an ID", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(txtName.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please enter a Name", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(txtAdd.getText().toString()))
                    Toast.makeText(getApplicationContext(), "Please enter an Address", Toast.LENGTH_SHORT).show();
                else {
                    std.setID(txtID.getText().toString().trim());
                    std.setName(txtName.getText().toString().trim());
                    std.setAddress(txtAdd.getText().toString().trim());
                    std.setConNo(Integer.parseInt(txtConNo.getText().toString().trim()));

                    //insert into the database
                    dbRef.push().setValue(std);
                    //dbRef.child("std1").setValue(std);

                    //feedback to the user via toast
                    Toast.makeText(getApplicationContext(), "Data saved successfully", Toast.LENGTH_SHORT).show();
                    clearControls();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
            }
        });

        /*
        //Nangi
        btnShow.setOnClickListener(view -> {


        });

        //Nethmee
        btnUpdate.setOnClickListener(view -> {

        });

         */
    }
}