package com.anwesome.ui.beautifulswitchesdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.anwesome.ui.beautifulswitches.BeautifulSwitch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BeautifulSwitch beautifulSwitch = new BeautifulSwitch(this);
        beautifulSwitch.addSwitch(200,200);
        beautifulSwitch.addSwitch(new BeautifulSwitch.SwitchListener() {
            @Override
            public void onNo() {
                Toast.makeText(MainActivity.this, "No", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onYes() {
                Toast.makeText(MainActivity.this,"Yes",Toast.LENGTH_SHORT).show();
            }
        },500,500);
    }
}
