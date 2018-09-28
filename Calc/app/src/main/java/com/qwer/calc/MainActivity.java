package com.qwer.calc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView result;
    EditText n1, n2;
    Button bt1, bt2, bt3, bt4;

    float res;
    int num1, num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView)findViewById(R.id.result);
        n1 = (EditText)findViewById(R.id.number1);
        n2 = (EditText)findViewById(R.id.number2);
        bt1 = (Button)findViewById(R.id.add);
        bt2 = (Button)findViewById(R.id.substract);
        bt3 = (Button)findViewById(R.id.multiply);
        bt4 = (Button)findViewById(R.id.divide);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = Integer.parseInt(n1.getText().toString());
                num2 = Integer.parseInt(n2.getText().toString());
                res = num1 + num2;
                result.setText(String.valueOf(res));
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = Integer.parseInt(n1.getText().toString());
                num2 = Integer.parseInt(n2.getText().toString());
                res = num1 - num2;
                result.setText(String.valueOf(res));
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = Integer.parseInt(n1.getText().toString());
                num2 = Integer.parseInt(n2.getText().toString());
                res = num1 * num2;
                result.setText(String.valueOf(res));
            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num1 = Integer.parseInt(n1.getText().toString());
                num2 = Integer.parseInt(n2.getText().toString());
                res = num1 / num2;
                result.setText(String.valueOf(res));
            }
        });


    }
}
