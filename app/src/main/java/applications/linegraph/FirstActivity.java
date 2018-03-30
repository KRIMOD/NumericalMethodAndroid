package applications.linegraph;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class FirstActivity extends AppCompatActivity {

    private EditText editText1;
    private EditText editText2;
    private EditText editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);


        editText1 = (EditText) findViewById(R.id.edittext1);
        editText2 = (EditText) findViewById(R.id.edittext2);
        editText3 = (EditText) findViewById(R.id.edittext3);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText1.getText().toString().equals("") || editText2.getText().toString().equals("")) {
                    Toast.makeText(FirstActivity.this, "Veuillez inserer un numéro", Toast.LENGTH_SHORT).show();
                } else if (editText3.getText().toString().equals("")) {
                    Toast.makeText(FirstActivity.this, "Veuillez inserer une fonction", Toast.LENGTH_SHORT).show();
                } else {
                    openActivity2();
                }
            }
        });


    }


    public void openActivity2() {


        Double number1 = Double.parseDouble(editText1.getText().toString());
        Double number2 = Double.parseDouble(editText2.getText().toString());
        String text = editText3.getText().toString();

        Intent intent = new Intent(FirstActivity.this, GraphActivity.class);
        intent.putExtra("number1", number1);
        intent.putExtra("number2", number2);
        intent.putExtra("text", text);

        startActivityForResult(intent, 1);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FirstActivity.this,SupahActivity.class);
        startActivity(intent);
    }
}


