package com.example.rsa_qrcode;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class KeyMgtActivity extends AppCompatActivity {

    SharedPreferences sp;
    SharedPreferences.Editor ed;
    Intent obj;
    EditText tp,tq;
    TextView key;

    public static int gcd(int e, int z)
    {
        if (e == 0)
            return z;
        else
            return gcd(z % e, e);
    }

    static boolean isPrime(int n)
    {
        if (n <= 1)
            return false;

        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keymgt);

        sp = getSharedPreferences("SD", Context.MODE_PRIVATE);
        ed = sp.edit();

        tp = findViewById(R.id.etP);
        tq = findViewById(R.id.etQ);
        key = findViewById(R.id.tvPkey);

        key.setText("Public Key : n=" + String.valueOf(sp.getInt("n",33)) + " e="  + String.valueOf(sp.getInt("e",3)));
    }

    public void onSave(View view) {

        int p, q, n, z, x, d = 0, e, i;

        p = Integer.parseInt(tp.getText().toString());
        q = Integer.parseInt(tq.getText().toString());

        if(isPrime(p) && isPrime(q))
        {
            n = p * q;
            z = (p - 1) * (q - 1);

            for (e = 2; e < z; e++)
                if (gcd(e, z) == 1)
                    break;

            for (i = 0; i <= 9; i++) {
                x = 1 + (i * z);
                if (x % e == 0) {
                    d = x / e;
                    break;
                }
            }
            ed.putInt("n", n);
            ed.putInt("e", e);
            ed.putInt("d", d);
            ed.commit();
            key.setText("Public Key : n=" + String.valueOf(n) + " e="  + String.valueOf(e));
            tp.setText("");
            tq.setText("");
        }
        else
            Toast.makeText(this, "!Invalid Input! Prime Numbers Required...", Toast.LENGTH_LONG).show();
    }

    public void onBack(View view) {
        obj = new Intent(this, MainActivity.class);
        startActivity(obj);
    }
}