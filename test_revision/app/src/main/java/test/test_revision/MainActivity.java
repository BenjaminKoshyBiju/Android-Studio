package test.test_revision;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
EditText e1,e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onFunc(View v)
    {
        e1=(EditText) findViewById(R.id.et1);
        e2=(EditText) findViewById(R.id.et2);
        int a=Integer.parseInt(e1.getText().toString());
        int b=Integer.parseInt((e2.getText().toString()));

        Intent obj;
        obj=new Intent(this,input.class);
        obj.putExtra("id1", a);
        obj.putExtra("id2", b);
        startActivity(obj);


    }
}