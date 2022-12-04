package test.test_revision;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class result extends AppCompatActivity {
int res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.res);
            Intent ob1=getIntent();
         res=ob1.getIntExtra("result",0);
        EditText e1;
        e1= (EditText) findViewById(R.id.et1);
        e1.setText(""+res);
    }
    public void onFunc(View v)
    {
        finish();


    }
}