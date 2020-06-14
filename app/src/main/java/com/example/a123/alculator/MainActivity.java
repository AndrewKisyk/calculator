package com.example.a123.alculator;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {
    Button c, point, pecent,dot, back, sqrt, pow, pi, exp, sin, cos, tg, ln, first, two, thre, four , five , six, seven , eight, nine, zero, devide, plus, minus, multiple, equals, token;
    TextView text;
    TextView shadow;
    private String inStr = "0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.edtext);
        text.setText("0");
        shadow = (TextView) findViewById(R.id.ottext);


        BtnListener listener = new BtnListener();
        c = (Button) findViewById(R.id.btn_delete);
        c.setOnClickListener(listener);
        dot = (Button) findViewById(R.id.btn_dot);
        dot.setOnClickListener(listener);
        back = (Button) findViewById(R.id.btn_back);
        back.setOnClickListener(listener);
        sqrt = (Button) findViewById(R.id.btn_sqrt);
        sqrt.setOnClickListener(listener);
        pow = (Button) findViewById(R.id.btn_squeare);
        pow.setOnClickListener(listener);
        pecent = (Button) findViewById(R.id.btn_percentage);
        pecent.setOnClickListener(listener);
        pi = (Button) findViewById(R.id.btn_pi);
        pi.setOnClickListener(listener);
        exp = (Button) findViewById(R.id.btn_e);
        exp.setOnClickListener(listener);
        sin = (Button) findViewById(R.id.btn_sin);
        sin.setOnClickListener(listener);
        cos = (Button) findViewById(R.id.btn_cos);
        cos.setOnClickListener(listener);
        tg = (Button) findViewById(R.id.btn_tan);
        tg.setOnClickListener(listener);
        ln = (Button) findViewById(R.id.btn_ln);
        ln.setOnClickListener(listener);
        first = (Button) findViewById(R.id.btn_one);
        first.setOnClickListener(listener);
        two = (Button) findViewById(R.id.btn_two);
        two.setOnClickListener(listener);
        thre = (Button) findViewById(R.id.btn_three);
        thre.setOnClickListener(listener);
        four = (Button) findViewById(R.id.btn_four);
        four.setOnClickListener(listener);
        five = (Button) findViewById(R.id.btn_five);
        five.setOnClickListener(listener);
        six = (Button) findViewById(R.id.btn_six);
        six.setOnClickListener(listener);
        seven = (Button) findViewById(R.id.btn_seven);
        seven.setOnClickListener(listener);
        eight = (Button) findViewById(R.id.btn_eight);
        eight.setOnClickListener(listener);
        nine = (Button) findViewById(R.id.btn_nine);
        nine.setOnClickListener(listener);
        zero = (Button)  findViewById(R.id.btn_zero);
        zero.setOnClickListener(listener);
        devide = (Button)  findViewById(R.id.btn_divide);
        devide.setOnClickListener(listener);
        plus = (Button)  findViewById(R.id.btn_plus);
        plus.setOnClickListener(listener);
        minus = (Button)  findViewById(R.id.btn_minus);
        minus.setOnClickListener(listener);
        multiple = (Button) findViewById(R.id.btn_multiple);
        multiple.setOnClickListener(listener);
        equals = (Button) findViewById(R.id.btn_equal);
        equals.setOnClickListener(listener);
        token = (Button) findViewById(R.id.btn_small_bracket);
        token.setOnClickListener(listener);
    }
    private class BtnListener implements OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                // Number buttons: '0' to '9'
                case R.id.btn_zero:
                case R.id.btn_one:
                case R.id.btn_two:
                case R.id.btn_three:
                case R.id.btn_four:
                case R.id.btn_five:
                case R.id.btn_six:
                case R.id.btn_seven:
                case R.id.btn_eight:
                case R.id.btn_nine:
                case R.id.btn_plus:
                case R.id.btn_minus:
                case R.id.btn_multiple:
                case R.id.btn_divide:
                case R.id.btn_sin:
                case R.id.btn_cos:
                case R.id.btn_tan:
                case R.id.btn_e:
                case R.id.btn_squeare:
                case R.id.btn_ln:
                case R.id.btn_sqrt:
                case R.id.btn_dot:
                case R.id.btn_pi:
                case R.id.btn_percentage:
                    output(view);
                    break;
                case R.id.btn_equal:
                    shadow.setText(inStr+'=');
                    Engine engine = new Engine(inStr);
                    text.setText(engine.compout(inStr));
                    inStr="";
                    break;
                case R.id.btn_back:
                    delate(view);
                    break;
                // Clear button
                case R.id.btn_delete:
                    inStr = "0";
                    text.setText("0");
                    shadow.setText("");
                    break;
                case R.id.btn_small_bracket:
                    token(view);
                    break;
            }
        }

        private void output(View view) {
            String inDigit = ((Button) view).getText().toString();
            if (inStr.equals("0")||inStr.equals("")) {
                text.setText(inDigit);
                inStr ="";
                inStr =inDigit;
            } else {
                text.setText(text.getText()+inDigit);
                inStr += inDigit; // accumulate input digit
            }
        }

        private void delate(View view){
            try {
                inStr = inStr.substring(0, inStr.length() - 1);
                text.setText(inStr);
            }catch (Exception e){}
        }

        private void token(View view) {
            if (inStr == "0") {
                text.setText("(");
                inStr = "";
                inStr = "(";
            }
            int openingtokens = 0;
            int endingtokens = 0;
            try {
                for (int i = 0; i <= inStr.length(); i++) {
                    if (inStr.charAt(i) == '(')
                        openingtokens++;
                    if (inStr.charAt(i) == ')')
                        endingtokens++;
                }
            } catch (StringIndexOutOfBoundsException ee) {
            }
            try{
            if (openingtokens == endingtokens && (isNumber(inStr.charAt(inStr.length() - 1))||inStr.charAt(inStr.length() - 1)==')')) {
                text.setText(text.getText() + "x(");
                inStr += "x(";
            } else if (isNumber(inStr.charAt(inStr.length() - 1))) {
                text.setText(text.getText() + ")");
                inStr += ")";

            } else if (!isNumber(inStr.charAt(inStr.length() - 1))&&inStr!="(") {
                text.setText(text.getText() + "(");
                inStr += "(";
            }
        }catch (StringIndexOutOfBoundsException e){}
        }
        private  boolean isNumber(char c) {
            boolean trust = false;
            switch (c) {
                case '0':
                    trust = true;
                    break;
                case '1':
                    trust = true;
                    break;
                case '2':
                    trust = true;
                    break;
                case '3':
                    trust = true;
                    break;
                case '4':
                    trust = true;
                    break;
                case '5':
                    trust = true;
                    break;
                case '6':
                    trust = true;
                    break;
                case '7':
                    trust = true;
                    break;
                case '8':
                    trust = true;
                    break;
                case '9':
                    trust = true;
                    break;
                case '.':
                    trust = true;
                    break;
            }
            return trust;
        }
    }
}