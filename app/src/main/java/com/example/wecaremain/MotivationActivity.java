package com.example.wecaremain;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MotivationActivity extends AppCompatActivity {
    TextView tvQuote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motivation);
        tvQuote = findViewById(R.id.tvQuote);
        setButtons();
    }

    public void setButtons()
    {
        Button btnDream = findViewById(R.id.btnDream);
        btnDream.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setQuote("dream");
            }
        });
        Button btnInsp = findViewById(R.id.btnInspire);
        btnInsp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setQuote("inspiration");
            }
        });
        Button btngrow = findViewById(R.id.btnGrow);
        btngrow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setQuote("grow");
            }
        });
        Button btnLove = findViewById(R.id.btLove);
        btnLove.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setQuote("love");
            }
        });
        Button btnElse = findViewById(R.id.btnElse);
        btnElse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setQuote("else");
            }
        });


    }

    public void setQuote(String key)
    {
        Random rand = new Random(); //instance of random class
        int upperbound = Quotes.dict.get(key).size();
        tvQuote.setText(Quotes.dict.get(key).get(rand.nextInt(upperbound)));
    }
}