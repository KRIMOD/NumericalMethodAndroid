package applications.linegraph;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import tokenizer.AbstractTreeBuilder;
import tokenizer.TokenizerException;

public class DichoActivity extends AppCompatActivity {

    String fresult1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dicho);

        Intent intent = getIntent();
        Double a = intent.getDoubleExtra("numberprime1",0);
        Double b = intent.getDoubleExtra("numberprime2",0);
        Double t = intent.getDoubleExtra("numberprime3",0);
        String str = intent.getStringExtra("textprime");
        str = str.toLowerCase();
        str = funcmanager(str);

        try {
            Double z = func(str,a);
            z = func(str,b);
        } catch (Exception e) {
            openActivityprime();
        }

        try {
            bisection(str,a,b,t);
        } catch (TokenizerException e) {
            e.printStackTrace();
        }


    }

    public void openActivityprime() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    public void bisection(String str, double a, double b, double e) throws TokenizerException {
        double c = a;
        while ((b - a) >= e) {
            //disséqué [a,b]
            c = (a + b) / 2;

            // vérifié si c est une solution à l'équation
            if (func(str, c) == 0.0)
                break;

                // choisir de quel côté, suivre l'algorithme
            else if (func(str, c) * func(str, a) < 0)
                b = c;
            else
                a = c;
        }
        TextView textView = (TextView) findViewById(R.id.potato);
        TextView textViewprime = (TextView) findViewById(R.id.potatoprime);
        fresult1 = ("L'une des solutions de l'equation  :");
        textView.setText(fresult1);
        textViewprime.setText(""+c);
    }

    public static Double func(String str, Double value) throws TokenizerException {
        AbstractTreeBuilder tree = new AbstractTreeBuilder(str);
        value = tree.getTree().getNumericResult(value);
        return value;

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
