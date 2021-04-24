package com.satanbakespancakes.tocrackornottocrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.zip.CRC32;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView CRC32Input, comparableText;

    long hash, compare_hash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = (Button) findViewById(R.id.button2);
        CRC32Input = (TextView) findViewById(R.id.editTextTextPersonName);
        comparableText = (TextView) findViewById(R.id.textView2);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hash = Long.parseLong(CRC32Input.getText().toString().replace("0x", "").replace("L", ""), 16);
                compareFromFile();
            }
        });
    }
    Scanner sc;
    BufferedReader reader;
    private void compareFromFile(){

        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open("passwds.txt")));
            String line;
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        sc = new Scanner(reader);

        CRC32 crc32 = new CRC32();
        while (sc.hasNext()){
            String s = sc.next();
            comparableText.setText(s);
            crc32.reset();
            crc32.update(s.getBytes());

            if (hash == crc32.getValue()){
                return;
            }
        }

    }


}