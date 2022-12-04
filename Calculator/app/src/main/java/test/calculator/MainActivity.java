package test.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void sum (View v)
    {
        EditText t1,t2;
        Button add;
        add=(Button) findViewById(R.id.add);
        t1=(EditText) findViewById(R.id.no1);
        t2=(EditText) findViewById(R.id.no2);
        int a,b,s;
        a=Integer.parseInt(t1.getText().toString());
        b=Integer.parseInt(t2.getText().toString());
        s=a+b;
        Intent obj;
        obj=new Intent(this,second.class);
        obj.putExtra("value",s);
        startActivity(obj);
        add.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));


    }
    public void sub (View v)
    {
        EditText t1,t2;
        t1=(EditText) findViewById(R.id.no1);
        t2=(EditText) findViewById(R.id.no2);
        int a,b,s;
        a=Integer.parseInt(t1.getText().toString());
        b=Integer.parseInt(t2.getText().toString());
        s=a-b;
        Intent obj;
        obj=new Intent(this,second.class);
        obj.putExtra("value",s);
        startActivity(obj);



    }

    public void div (View v)
    {
        EditText t1,t2;
        t1=(EditText) findViewById(R.id.no1);
        t2=(EditText) findViewById(R.id.no2);
        int a,b,s;
        a=Integer.parseInt(t1.getText().toString());
        b=Integer.parseInt(t2.getText().toString());
        s=a/b;
        Intent obj;
        obj=new Intent(this,second.class);
        obj.putExtra("value",s);
        startActivity(obj);

    }
}