package test.calculator.sharedprefdemo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import test.calculator.sharedprefdemo2.HomeActivity;
import test.calculator.sharedprefdemo2.R;

public class MainActivity extends AppCompatActivity {
    EditText etUsr;
    SharedPreferences hp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onHome(View view) {
        etUsr = findViewById(R.id.etUser);
        hp = getSharedPreferences("HD", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = hp.edit();
        ed.putString("un",etUsr.getText().toString());
        ed.commit();
        Intent in = new Intent(this, HomeActivity.class);
        startActivity(in);
    }
}