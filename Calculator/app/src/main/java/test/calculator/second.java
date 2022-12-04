package test.calculator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class second extends AppCompatActivity {
    EditText e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        EditText e1;
        e1=(EditText) findViewById(R.id.ans);
        Intent obj=getIntent();

         int t=obj.getIntExtra("value",0);
        e1.setText(""+t);
    }
    public void clk(View v)
    {
        e2=(EditText) findViewById(R.id.url);
        String g="https://www.google.com/search?q=";
        String urll= g+e2.getText().toString();
        Intent obj;
        obj=new Intent(Intent.ACTION_VIEW);
        obj.setData(Uri.parse(urll));
        startActivity(obj);
    }
    public void next(View v)
    {
        Intent ob;
        ob=new Intent(this,third.class);
        startActivity(ob);
    }

}