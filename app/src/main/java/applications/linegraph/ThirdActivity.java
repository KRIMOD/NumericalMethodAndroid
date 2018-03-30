package applications.linegraph;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        final EditText edittext = (EditText) findViewById(R.id.editText);
        String str = edittext.getText().toString();

        Button button = (Button) findViewById(R.id.button6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edittext.getText().toString().equals("")){
                    Toast.makeText(ThirdActivity.this,"Veuiller entrer une fonction",Toast.LENGTH_SHORT).show();
                } else {
                    String text = edittext.getText().toString();

                    Intent intent = new Intent(ThirdActivity.this, DerivActivity.class);
                    intent.putExtra("textos", text);
                    startActivityForResult(intent, 1);

                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ThirdActivity.this,SupahActivity.class);
        startActivity(intent);
    }
}
