package test.calculator.sharedprefdemo2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import test.calculator.sharedprefdemo2.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onShow(View view) {
        Intent in = new Intent(this, test.calculator.sharedprefdemo2.ShowActivity.class);
        startActivity(in);
    }
}