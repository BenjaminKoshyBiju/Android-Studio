package com.example.regform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CDB cdb;
    EditText un,pw;
    CheckBox rem;
    SharedPreferences sp;
    Intent obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cdb = new CDB(this);
        un = findViewById(R.id.etUname);
        pw = findViewById(R.id.etPwrd);
        rem = findViewById(R.id.cbRem);
    }

    public void onRegis(View view) {
        obj = new Intent(getApplicationContext(),RegisActivity.class);
        startActivity(obj);
    }

    public void onLogin(View view) {
        String a[];
        try{
            String uns = un.getText().toString();
            String pws = pw.getText().toString();
            if(uns.equals("admin") && pws.equals("admin"))
            {
                obj = new Intent(this, AdminActivity.class);
                startActivity(obj);
            }
            else
            {
                a = cdb.getOneUser(uns);
                if (a[0] != "" && a[1].equals(uns) && a[2].equals(pws))
                {
                    sp = getSharedPreferences("SD", Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed = sp.edit();
                    ed.putString("un", uns);
                    ed.putString("pw", pws);
                    ed.commit();
                    obj = new Intent(this, ProfileActivity.class);
                    startActivity(obj);
                }
                else
                    Toast.makeText(this, "Invalid!", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception exception)
        {
            Log.d("SELECT123",exception.toString());
        }

    }

    public void onExit(View view) {
        finish();
    }
}