package test.calculator.loginpagewithadminloginonly;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
// a simple login page with no database connection!
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}