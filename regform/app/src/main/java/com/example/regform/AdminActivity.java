package com.example.regform;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AdminActivity extends AppCompatActivity {

    CDB cdb;
    TextView th,tl;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        cdb = new CDB(this);
        th = findViewById(R.id.tvHead) ;
        tl = findViewById(R.id.tvList);
        str="";
        List<CUser> rec = cdb.getAllvalues();
        for(CUser cr : rec)
        {
            str += cr.id + "\t\t\t\t\t\t\t\t" + cr.name + "\t\t\t\t\t\t\t\t" + cr.uname + "\t\t\t\t\t\t\t\t" + cr.pass + "\n";
        }
        tl.setText(str);
    }

    public void onLogout(View view) {
        Intent obj = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(obj);
    }
}