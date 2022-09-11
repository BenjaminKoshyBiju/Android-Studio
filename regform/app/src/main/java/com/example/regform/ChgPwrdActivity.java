package com.example.regform;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChgPwrdActivity extends AppCompatActivity {

    CDB cdb;
    SharedPreferences sp;
    String un,pw;
    EditText opw,npw,cpw;
    Intent obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chgpwrd);
        cdb = new CDB(this);
        sp = getSharedPreferences("SD", Context.MODE_PRIVATE);
        un = sp.getString("un","");
        pw = sp.getString("pw","");
        opw = findViewById(R.id.etOld);
        npw = findViewById(R.id.etNew);
        cpw = findViewById(R.id.etCnew);
    }

    public void onUpdate(View view) {
        String o,n,c;
        o = opw.getText().toString();
        n = npw.getText().toString();
        c = cpw.getText().toString();
        if(o.equals(pw) && n.equals(c))
        {
            cdb.update(un,n);
            Toast.makeText(this,"Password Changed!",Toast.LENGTH_SHORT).show();
            obj = new Intent(this,ProfileActivity.class);
            startActivity(obj);
        }
        else
            Toast.makeText(this,"Invalid!",Toast.LENGTH_SHORT).show();
    }

    public void onBack(View view) {
        obj = new Intent(this,ProfileActivity.class);
        startActivity(obj);
    }
}