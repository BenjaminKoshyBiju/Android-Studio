package com.example.rsa_qrcode;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Locale;


public class EncryptActivity extends AppCompatActivity {

    public final static int WIDTH = 400;
    public final static int HEIGHT = 400;

    SharedPreferences sp;
    SharedPreferences.Editor ed;
    Intent obj;
    ImageView imageView;
    EditText ptext,tn,te;
    Bitmap bitmap;

    int n,e,cint;
    String pstr,cstr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt);

        sp = getSharedPreferences("SD", Context.MODE_PRIVATE);
        ed = sp.edit();
        n = sp.getInt("n",33);
        e = sp.getInt("e",3);

        imageView = findViewById(R.id.idIVQrcode);
        ptext = findViewById(R.id.etPlainText);
        tn = findViewById(R.id.etNval);
        te = findViewById(R.id.etEval);
    }

    public void onEncrypt(View view) {

        pstr = ptext.getText().toString();
        pstr = pstr.replaceAll("\\s", "");
        pstr = pstr.toLowerCase();

        cint = Integer.parseInt(pstr);

        cstr = String.valueOf((Math.pow(cint, e)) % n);
        try {
            bitmap = encodeAsBitmap(cstr);
            imageView.setImageBitmap(bitmap);
        } catch (WriterException ex) {
            ex.printStackTrace();
        }
    }

    Bitmap encodeAsBitmap(@NonNull String str) throws WriterException {
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix bitMatrix = writer.encode(str, BarcodeFormat.QR_CODE, WIDTH, HEIGHT);

        int w = bitMatrix.getWidth();
        int h = bitMatrix.getHeight();
        int[] pixels = new int[w * h];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                pixels[y * w + x] = bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, w, 0, 0, w, h);
        return bitmap;
    }

    public void onBack(View view) {
        obj = new Intent(this, MainActivity.class);
        startActivity(obj);
    }
}