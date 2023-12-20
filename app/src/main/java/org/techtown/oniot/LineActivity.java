package org.techtown.oniot;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class LineActivity extends AppCompatActivity {

    private LineChart chart;
    public static Context context_line;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);

        context_line = this;

        Toolbar toolbar = findViewById(R.id.toolbarl);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Button gaugeButton = (Button) findViewById(R.id.gaugeButton);
        gaugeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        Button dataButton = (Button) findViewById(R.id.dataButtonl);
        dataButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), dataActivity.class);
                startActivity(intent);
            }
        });


        chart = findViewById(R.id.lineChart);

        ArrayList<Entry> sun = new ArrayList<>();
        ArrayList<Entry> temp = new ArrayList<>();
        ArrayList<Entry> humi = new ArrayList<>();
        ArrayList<Entry> cool = new ArrayList<>();


        float val1 = (float) (Math.random());
        float val2 = (float) (Math.random());
        float val3 = (float) (Math.random());
        float val4 = (float) (Math.random());
        sun.add(new Entry(0, val1+40));
        temp.add(new Entry(0, val2+20));
        humi.add(new Entry(0, val3+90));
        cool.add(new Entry(0, val4+30));


        LineData data = new LineData();

        LineDataSet sunshine = new LineDataSet(sun, "일조량");
        LineDataSet temperature = new LineDataSet(temp, "온도");
        LineDataSet humidity = new LineDataSet(humi, "습도");
        LineDataSet coolant = new LineDataSet(cool, "냉각수");

        sunshine.setColor(Color.YELLOW);
        sunshine.setCircleColor(Color.YELLOW);
        temperature.setColor(Color.RED);
        temperature.setCircleColor(Color.RED);
        humidity.setColor(Color.BLUE);
        humidity.setCircleColor(Color.BLUE);
        coolant.setColor(Color.CYAN);
        coolant.setCircleColor(Color.CYAN);

        data.addDataSet(sunshine);
        data.addDataSet(temperature);
        data.addDataSet(humidity);
        data.addDataSet(coolant);

        chart.setVisibleXRangeMaximum(20);
        chart.setData(data);

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
                                addEntry(sun_ran+40,0);
                                addEntry(temp_ran+20,1);
                                addEntry(hum_ran+90,2);
                                addEntry(cool_ran+30,3);
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
//    ImageButton imageButton = findViewById(R.id.onButton);

    private void addEntry(double n, int idx){
        LineData ndata = chart.getData();

        ILineDataSet set = ndata.getDataSetByIndex(idx);

        ndata.addEntry(new Entry(set.getEntryCount(), (float)n),idx);
        ndata.notifyDataChanged();

        chart.notifyDataSetChanged();
        chart.setVisibleXRangeMaximum(1000);
        chart.moveViewToX(ndata.getEntryCount());
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
