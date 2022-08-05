package test.calculator.sharedprefdemo2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import test.calculator.sharedprefdemo2.R;

public class ShowActivity extends AppCompatActivity {

    TextView tvN;
    SharedPreferences hp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        tvN = findViewById(R.id.tvName);
        hp = getSharedPreferences("HD", Context.MODE_PRIVATE);
        tvN.setText(hp.getString("un",""));
    }
}
