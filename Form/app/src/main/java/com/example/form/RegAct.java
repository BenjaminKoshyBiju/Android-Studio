package com.example.form;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class RegAct extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EditText e1,e2,e3,e4;
        TextView t=(TextView) findViewById(R.id.tv);
        CDB db;
        setContentView(R.layout.reg);
        e1=(EditText) findViewById(R.id.e1);
        e2=(EditText) findViewById(R.id.e2);
        e3=(EditText) findViewById(R.id.e3);
        e4=(EditText) findViewById(R.id.e4);
        db=new CDB(this);
    }
        public void onBack(View v)
        {
            Intent obj;
            obj=new Intent(this,MainActivity.class);
            startActivity(obj);

        }
    public void onSave(View v)
    {
        String ds,dn, dl;
        ds=e1.getText().toString();
        dn=e2.getText().toString();
        dl=e3.getText().toString();
        Toast.makeText(this, ds+dn+dl, Toast.LENGTH_SHORT).show();
        db.addDept(dn,dl);
        e1.setText("");
        e2.setText("");
        e3.setText("");
        e4.setText("");
        List<CDept> rec=db.getAllvalues();
        String str="";
        for (CDept cr:rec) {
            String log = "DId:" + cr.id + ",USERNAME:" + cr.dname + ",USERPASSWORD:" + cr.dpass;
            log = log + "\n";
            str = str + log;
        }
        t.setText(str);


    }
    }



