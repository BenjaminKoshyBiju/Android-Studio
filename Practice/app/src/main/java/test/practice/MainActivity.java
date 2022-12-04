package test.practice;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.YELLOW;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int []ColorChoice={YELLOW,RED,Color.BLUE,GREEN,};
private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
    public void onNext(View v)
    {
        Intent obj;
        obj=new Intent(this,Second.class);
        startActivity(obj);
    }
    public void onBackground(View v) {
        TextView t2;
        button=(Button) findViewById(R.id.button2);
        Random generator = new Random();
        int Index = generator.nextInt(ColorChoice.length);
        t2 = (TextView) findViewById(R.id.tv2);
        t2.setBackgroundColor(ColorChoice[Index]);
        button.setBackgroundColor(ColorChoice[Index]);

    }
    public void onSum(View v)
    {
        EditText e1,e2;
        TextView t1;
        int a,b,c;
        e1=(EditText) findViewById(R.id.t1);
        e2=(EditText) findViewById(R.id.t2);
        t1=(TextView) findViewById(R.id.tRes);
        a=Integer.parseInt(e1.getText().toString());
        b=Integer.parseInt((e2.getText().toString()));

        c=a+b;
        t1.setText(""+c);
    }
    public void onMinus(View v)
    {
        EditText e1,e2;
        TextView t1;
        int a,b,c;
        e1=(EditText) findViewById(R.id.t1);
        e2=(EditText) findViewById(R.id.t2);
        t1=(TextView) findViewById(R.id.tRes);
        a=Integer.parseInt(e1.getText().toString());
        b=Integer.parseInt((e2.getText().toString()));

        c=a-b;
        t1.setText(""+c);
    }
    public void onMul(View v)
    {
        EditText e1,e2;
        TextView t1;
        int a,b,c;
        e1=(EditText) findViewById(R.id.t1);
        e2=(EditText) findViewById(R.id.t2);
        t1=(TextView) findViewById(R.id.tRes);
        a=Integer.parseInt(e1.getText().toString());
        b=Integer.parseInt((e2.getText().toString()));

        c=a*b;
        t1.setText(""+c);
    }
    public void onDiv(View v)
    {
        EditText e1,e2;
        TextView t1;
        int a,b;
        float c;
        e1=(EditText) findViewById(R.id.t1);
        e2=(EditText) findViewById(R.id.t2);
        t1=(TextView) findViewById(R.id.tRes);
        a=Integer.parseInt(e1.getText().toString());
        b=Integer.parseInt((e2.getText().toString()));

        c=a/b;
        t1.setText(""+c);
    }
}