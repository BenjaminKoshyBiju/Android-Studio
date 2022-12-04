package com.example.rsa_qrcode;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class MainActivity extends AppCompatActivity {

    SharedPreferences sp;
    Intent obj;
    TextView tmsg;
    String mstr;
    int mint,n,e,d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences("SD", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        tmsg = findViewById(R.id.tvMessage);
    }

    public void onEncryptI(View view) {
        obj = new Intent(this, EncryptActivity.class);
        startActivity(obj);
    }

    public void onDecryptI(View view) {
        n = sp.getInt("n",33);
        e = sp.getInt("e",3);
        d = sp.getInt("d",7);
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setPrompt("Scan a barcode or QR Code");
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_SHORT).show();
            } else {
                mstr = intentResult.getContents();
                mint = Integer.parseInt(mstr);
                double temp = (Math.pow(mint,d))%n;
                tmsg.setText(String.valueOf(temp));
                tmsg.setText(intentResult.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void onKeymgt(View view) {
        obj = new Intent(this, KeyMgtActivity.class);
        startActivity(obj);
    }
}