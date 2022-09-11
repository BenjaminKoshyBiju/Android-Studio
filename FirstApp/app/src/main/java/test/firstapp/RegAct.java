package test.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class RegAct extends AppCompatActivity {

    EditText e1, e2, e3, e4;
    CDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg);
        e1 =  findViewById(R.id.ename);
        e2 =  findViewById(R.id.euname);
        e3 =  findViewById(R.id.epass);
       // e4 =  findViewById(R.id.e4);

        db=new CDB(this);


    }
    public void onSave(View v)
    {
      /*  String ds,dn, dl;
        ds=e1.getText().toString();
        dn=e2.getText().toString();
        dl=e3.getText().toString();*/
        db.addDept( e2.getText().toString(), e3.getText().toString());
        Toast.makeText(this, "registered", Toast.LENGTH_SHORT).show();
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
        TextView t = (TextView) findViewById(R.id.tv1);
        t.setText(str);


    }

    public void onBack(View v)
    {
        Intent obj;
        obj=new Intent(this,MainActivity.class);
        startActivity(obj);

    }
}