package applications.linegraph;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tokenizer.AbstractTreeBuilder;
import tokenizer.TokenizerException;

public class SecondActivity extends AppCompatActivity {

    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);

        final boolean result ;
            //result = func(editText4.getText().toString(), Double.parseDouble(editText1.getText().toString())) * func(editText4.getText().toString(), Double.parseDouble(editText2.getText().toString())) >= 0;


        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double var1 = 0.0;
                Double var2 = 0.0;

                if (editText1.getText().toString().equals("") || editText2.getText().toString().equals("") || editText3.getText().toString().equals("")) {
                    Toast.makeText(SecondActivity.this, "Veuillez inserer un numéro", Toast.LENGTH_SHORT).show();
                } else if (editText4.getText().toString().equals("")) {
                    Toast.makeText(SecondActivity.this, "Veuillez inserer une fonction", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        var1 = func(editText4.getText().toString(), Double.parseDouble(editText1.getText().toString()));
                        var2 = func(editText4.getText().toString(), Double.parseDouble(editText2.getText().toString()));
                    } catch (TokenizerException e) {
                        e.printStackTrace();
                    }
                    if (var1*var2>=0){
                        Toast.makeText(SecondActivity.this,"Vous avez mal estimé les bornes",Toast.LENGTH_SHORT).show();
                    } else {
                        openActivity3();
                    }
                }

            }
        });
    }

        public void openActivity3() {
        Double number1 = Double.parseDouble(editText1.getText().toString());
        Double number2 = Double.parseDouble(editText2.getText().toString());
        Double number3 = Double.parseDouble(editText3.getText().toString());
        String text = editText4.getText().toString();

        Intent intent = new Intent(SecondActivity.this, DichoActivity.class);
        intent.putExtra("numberprime1", number1);
        intent.putExtra("numberprime2", number2);
        intent.putExtra("numberprime3", number3);
        intent.putExtra("textprime", text);

        startActivity(intent);
        }






    public static Double func(String str, Double value) throws TokenizerException {
        AbstractTreeBuilder tree = new AbstractTreeBuilder(str);
        value = tree.getTree().getNumericResult(value);
        return value;

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SecondActivity.this,SupahActivity.class);
        startActivity(intent);
    }
}
