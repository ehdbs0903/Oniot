package org.techtown.oniot;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;

public class dataActivity extends AppCompatActivity {
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        TableRow tableRow = findViewById(R.id.TableRow1);
//        tableRow.setMovementMethod(new ScrollingMovementMethod());

        TextView textView1 = findViewById(R.id.textView21);
        TextView textView2 = findViewById(R.id.textView22);
        TextView textView3 = findViewById(R.id.textView23);
        TextView textView4 = findViewById(R.id.textView24);
        TextView textView5 = findViewById(R.id.textView25);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        textView1.setText(format.format(System.currentTimeMillis()));
        textView2.setText(String.format("%.3f",(double)(Math.random()+40)));
        textView3.setText(String.format("%.3f",(double)(Math.random()+20)));
        textView4.setText(String.format("%.3f",(double)(Math.random()+90)));
        textView5.setText(String.format("%.3f",(double)(Math.random()+30)));

        Toolbar toolbar = findViewById(R.id.toolbard);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Button gaugeButton = (Button) findViewById(R.id.gaugeButtond);
        gaugeButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        Button lineButton = (Button) findViewById(R.id.lineButtond);
        lineButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), LineActivity.class);
                startActivity(intent);
            }
        });

        new Thread() {
            public void run() {
                while(true) {
                    try {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                double sun_ran = Math.random()+40;
                                double temp_ran = Math.random()+20;
                                double hum_ran = Math.random()+90;
                                double cool_ran = Math.random()+30;
                                textView1.setText(textView1.getText() + "\n" + format.format(System.currentTimeMillis()));
                                textView2.setText(textView2.getText() + "\n" + String.format("%.3f",(double)(sun_ran)));
                                textView3.setText(textView3.getText() + "\n" + String.format("%.3f",(double)(temp_ran)));
                                textView4.setText(textView4.getText() + "\n" + String.format("%.3f",(double)(hum_ran)));
                                textView5.setText(textView5.getText() + "\n" + String.format("%.3f",(double)(cool_ran)));
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
}
