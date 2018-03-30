package applications.linegraph;


import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;


import tokenizer.AbstractTreeBuilder;
import tokenizer.TokenizerException;


import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {

    LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        Intent intent = getIntent();
        Double a = intent.getDoubleExtra("number1",0);
        Double b = intent.getDoubleExtra("number2",0);
        String str = intent.getStringExtra("text");
        str = str.toLowerCase();
        String save = str;
        str = funcmanager(str);

        try {
            Double z = func(str,a);
            z = func(str,b);
        } catch (Exception e) {
            openActivity1();
        }


        lineChart = (LineChart) findViewById(R.id.lineChart);

        ArrayList<String> xAXES = new ArrayList<>();
        ArrayList<Entry> yAXESsin = new ArrayList<>();
        double x = a;
        int numDataPoints = (int) (Math.abs(a-b)/0.01);
        for(int i=0;i<numDataPoints;i++){
            float sinFunction = 0;
            try {
                sinFunction = Float.parseFloat(String.valueOf(func(str,x)));
            } catch (TokenizerException e) {
                e.printStackTrace();
            }
            x = x + 0.01;
            yAXESsin.add(new Entry(sinFunction,i));

            xAXES.add(i, String.valueOf(x));
        }
        String[] xaxes = new String[xAXES.size()];
        for(int i=0; i<xAXES.size();i++){
            xaxes[i] = xAXES.get(i).toString();
        }

        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();


        LineDataSet lineDataSet2 = new LineDataSet(yAXESsin,"f(x) = "+save);
        lineDataSet2.setDrawCircles(false);
        lineDataSet2.setColor(Color.BLUE);

        lineDataSets.add(lineDataSet2);

        lineChart.setData(new LineData(xaxes,lineDataSets));

        lineChart.setVisibleXRangeMaximum(300f);


    }

    public static Double func(String str, Double value) throws TokenizerException {
        AbstractTreeBuilder tree = new AbstractTreeBuilder(str);
        value = tree.getTree().getNumericResult(value);
        return value;

    }

    public void openActivity1(){
        Intent intent = new Intent(this, FirstActivity.class);
        startActivity(intent);
    }

    public static String funcmanager(String val){
        char c;
        for (int i = 0; i < val.length(); i++) {
            c = val.charAt(i);
            if (c == '-' && i == 0) {
                val = "0" + val;
            }
            if (c == '-' && i != 0) {
                if (val.charAt(i - 1) == '(') {
                    val = val.substring(0, i) + "0" + val.substring(i, val.length());
                }
            }
        }

        return val;
    }
}
