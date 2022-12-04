package test.calculator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class third extends AppCompatActivity {
    EditText e1,e2,e3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third);
    }
    public void save(View v) throws IOException
    {
        e1=(EditText) findViewById(R.id.fName);
        e2=(EditText) findViewById(R.id.fData);
        String Fname=e1.getText().toString();
        String Data=e2.getText().toString();




        FileOutputStream fout=openFileOutput(Fname,0);
        OutputStreamWriter osw=new OutputStreamWriter(fout);
        osw.write(Data);
        osw.flush();
        osw.close();

    }
    public void load(View v) throws IOException
    {
        e3=(EditText) findViewById(R.id.read);
        FileInputStream fin;
        fin=openFileInput(e1.getText().toString());
        InputStreamReader isw=new InputStreamReader(fin);
        char[]b=new char[50];
        int n =isw.read(b,0,50);
        String str =new String(b,0,n);
        e3.setText(str);
        isw.close();


    }



}