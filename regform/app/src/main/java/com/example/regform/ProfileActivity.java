package com.example.regform;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    TextView hello;
    SharedPreferences sp;
    SharedPreferences.Editor ed;
    Intent obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        hello = findViewById(R.id.tvHello);
        sp = getSharedPreferences("SD", Context.MODE_PRIVATE);
        ed = sp.edit();
        hello.setText("Hello " + sp.getString("un",""));
    }

    public void onChangePw(View view) {
        obj = new Intent(getApplicationContext(),ChgPwrdActivity.class);
        startActivity(obj);
    }

    public void onLogout(View view) {
        ed.clear();
        ed.commit();
        obj = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(obj);
    }
}