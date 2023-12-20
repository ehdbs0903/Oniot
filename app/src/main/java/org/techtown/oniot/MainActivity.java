package org.techtown.oniot;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import java.util.Locale;

import pl.pawelkleczkowski.customgauge.CustomGauge;



public class MainActivity extends AppCompatActivity {
    public static Context context_main;
    private CustomGauge sun_gauge;
    private CustomGauge hum_gauge;
    private CustomGauge temp_gauge;
    private CustomGauge cool_gauge;
    private TextView sun_textView;
    private TextView hum_textView;
    private TextView temp_textView;
    private TextView cool_textView;
    private Toast toast;
    private long backKeyPressedTime = 0;

    SQLiteDatabase database;
    String tableName;

    public int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context_main = this;

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ImageButton imgbutton = findViewById(R.id.onButton);
        sun_gauge = findViewById(R.id.sunshine_gauge);
        temp_gauge = findViewById(R.id.temperature_gauge);
        hum_gauge = findViewById(R.id.humidity_gauge);
        cool_gauge = findViewById(R.id.coolant_gauge);
        sun_textView = findViewById(R.id.sunshine_textView);
        temp_textView = findViewById(R.id.temperature_textView);
        hum_textView = findViewById(R.id.humidity_textView);
        cool_textView = findViewById(R.id.coolant_textView);
        sun_textView.setText(String.valueOf(sun_gauge.getValue()));
        temp_textView.setText(String.valueOf(temp_gauge.getValue()));
        hum_textView.setText(String.valueOf(hum_gauge.getValue()));
        cool_textView.setText(String.valueOf(cool_gauge.getValue()));

        imgbutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 1;
                sun_gauge.setEndValue(200);
                sun_gauge.setValue(0);
                temp_gauge.setEndValue(200);
                temp_gauge.setValue(0);
                hum_gauge.setEndValue(200);
                hum_gauge.setValue(0);
                cool_gauge.setEndValue(200);
                cool_gauge.setValue(0);
                sun_textView.setText(String.format(Locale.getDefault(), "%1d", sun_gauge.getValue()));
                temp_textView.setText(String.format(Locale.getDefault(), "%1d", temp_gauge.getValue()));
                hum_textView.setText(String.format(Locale.getDefault(), "%1d", hum_gauge.getValue()));
                cool_textView.setText(String.format(Locale.getDefault(), "%1d", cool_gauge.getValue()));
                new Thread() {
                    public void run() {
                        while(true) {
                            try {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        double sun_ran = Math.random();
                                        double temp_ran = Math.random();
                                        double hum_ran = Math.random();
                                        double cool_ran = Math.random();
                                        sun_gauge.setValue(40);
                                        sun_textView.setText(String.format(Locale.getDefault(), "%.1f", sun_gauge.getValue() + sun_ran));
                                        temp_gauge.setValue(20);
                                        temp_textView.setText(String.format(Locale.getDefault(), "%.1f", temp_gauge.getValue() + temp_ran));
                                        hum_gauge.setValue(90);
                                        hum_textView.setText(String.format(Locale.getDefault(), "%.1f", hum_gauge.getValue() + hum_ran));
                                        cool_gauge.setValue(30);
                                        cool_textView.setText(String.format(Locale.getDefault(), "%.1f", cool_gauge.getValue() + cool_ran));
                                    }
                                });
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();
            }
        });

        Button lineButton = (Button) findViewById(R.id.lineButton);
        lineButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), LineActivity.class);
                startActivity(intent);
            }
        });

        Button dataButton = (Button) findViewById(R.id.dataButton);
        dataButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), dataActivity.class);
                startActivity(intent);
            }
        });
    }

    protected  void onRestart(){
        super.onRestart();
        if(flag==1) {
            new Thread() {
                public void run() {
                    while(true) {
                        try {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    double sun_ran = Math.random();
                                    double temp_ran = Math.random();
                                    double hum_ran = Math.random();
                                    double cool_ran = Math.random();
                                    sun_gauge.setValue(40);
                                    sun_textView.setText(String.format(Locale.getDefault(), "%.1f", sun_gauge.getValue() + sun_ran));
                                    temp_gauge.setValue(20);
                                    temp_textView.setText(String.format(Locale.getDefault(), "%.1f", temp_gauge.getValue() + temp_ran));
                                    hum_gauge.setValue(90);
                                    hum_textView.setText(String.format(Locale.getDefault(), "%.1f", hum_gauge.getValue() + hum_ran));
                                    cool_gauge.setValue(30);
                                    cool_textView.setText(String.format(Locale.getDefault(), "%.1f", cool_gauge.getValue() + cool_ran));
                                }
                            });
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }
    }

    protected void onPause() {
        super.onPause();
        new Thread() {
            public void run() {
                while(true) {
                    try {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                double sun_ran = Math.random();
                                double temp_ran = Math.random();
                                double hum_ran = Math.random();
                                double cool_ran = Math.random();
                                sun_gauge.setValue(40);
                                sun_textView.setText(String.format(Locale.getDefault(), "%.1f", sun_gauge.getValue() + sun_ran));
                                temp_gauge.setValue(20);
                                temp_textView.setText(String.format(Locale.getDefault(), "%.1f", temp_gauge.getValue() + temp_ran));
                                hum_gauge.setValue(90);
                                hum_textView.setText(String.format(Locale.getDefault(), "%.1f", hum_gauge.getValue() + hum_ran));
                                cool_gauge.setValue(30);
                                cool_textView.setText(String.format(Locale.getDefault(), "%.1f", cool_gauge.getValue() + cool_ran));
                            }
                        });
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu1:
                break;
            case R.id.menu2:
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return true;
    }

    public void onBackPressed(){
        if(System.currentTimeMillis()>backKeyPressedTime+2500) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "뒤로 가기 버튼을 한 번 더 누르시면 종료됩니다.", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }
        else{
            ActivityCompat.finishAffinity(this);
        }
    }
}