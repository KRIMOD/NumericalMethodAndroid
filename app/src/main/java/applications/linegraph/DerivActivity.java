package applications.linegraph;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


import ast.Operation;
import tokenizer.AbstractTreeBuilder;
import tokenizer.TokenizerException;

public class DerivActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deriv);

        Intent intent = getIntent();
        String str = intent.getStringExtra("textos");
        str = str.toLowerCase();
        str = funcmanager(str);

        try {
            String z = dfunc(str);
            z = dfunc(str);
        } catch (Exception e) {
          openActivitytos();
      }

        try {
            str = dfunc(str);
        } catch (TokenizerException e) {
            e.printStackTrace();
        }
        TextView textView = (TextView) findViewById(R.id.textviewtos);
        textView.setText(str);



    }


    public static String dfunc(String str) throws TokenizerException {
        AbstractTreeBuilder tree = new AbstractTreeBuilder(str);
        Operation derivative = tree.getTree().getDerivative();
        String deriv = derivative.toString();
        deriv = deriv.replace("--","+");
        return deriv;
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

    public void openActivitytos() {
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
    }
}
