package test.test_revision;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class input extends AppCompatActivity {
    EditText e1,e2;
    int c=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inp);
        e1=(EditText) findViewById(R.id.et1);
        e2=(EditText) findViewById(R.id.et2);
       Intent obj=getIntent();
        int a= obj.getIntExtra("id1",0);
        int b= obj.getIntExtra("id2",0);
        e1.setText(""+a);
        e2.setText(""+b);

        c=a+b;

    }
    public void onFunc(View v)
    {




        Intent ob1;
        ob1=new Intent(this,result.class);
        ob1.putExtra("result",c);
        startActivity(ob1);


    }
}