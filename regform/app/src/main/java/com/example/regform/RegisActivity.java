package com.example.regform;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisActivity extends AppCompatActivity {

    CDB cdb;
    EditText nm,unm,pass,cpass;
    Intent obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nm = findViewById(R.id.etName);
        unm = findViewById(R.id.etUname);
        pass = findViewById(R.id.etPwrd);
        cpass = findViewById(R.id.etCpwrd);
        cdb = new CDB(this);
    }

    public void onRegis(View view) {
        if(unm.getText().toString()=="admin")
            throw new SQLiteException();
        try {
            if(pass.getText().toString().equals(cpass.getText().toString()))
            {
                cdb.addUser(nm.getText().toString(), unm.getText().toString(), pass.getText().toString());
                Toast.makeText(this, "Registered", Toast.LENGTH_SHORT).show();
                obj = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(obj);
            }
            else
                Toast.makeText(this,"Password not Matching",Toast.LENGTH_SHORT).show();
        }catch(SQLiteException sqle)
        {
            Toast.makeText(this,"Cannot Be Blank/User Already Exists",Toast.LENGTH_SHORT).show();
        }
    }

    public void onBack(View view) {
        obj = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(obj);
    }
}