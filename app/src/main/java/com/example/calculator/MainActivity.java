package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.*;


public class MainActivity extends AppCompatActivity {

    Button one;
    Button two;
    Button three;
    Button four;
    Button five;
    Button six;
    Button seven;
    Button eight;
    Button nine;
    Button plus;
    Button minus;
    Button asterisk;
    Button divide;
    Button clear;
    Button zero;
    Button equals;
    TextView output;
    String outputString = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        one = findViewById(R.id.id_button_1);
        two = findViewById(R.id.id_button_2);
        three = findViewById(R.id.id_button_3);
        four = findViewById(R.id.id_button_4);
        five = findViewById(R.id.id_button_5);
        six = findViewById(R.id.id_button_6);
        seven = findViewById(R.id.id_button_7);
        eight = findViewById(R.id.id_button_8);
        nine = findViewById(R.id.id_button_9);
        plus = findViewById(R.id.id_button_plus);
        minus = findViewById(R.id.id_button_minus);
        asterisk = findViewById(R.id.id_button_mult);
        divide = findViewById(R.id.id_button_div);
        clear = findViewById(R.id.id_button_clear);
        zero = findViewById(R.id.id_button_0);
        equals = findViewById(R.id.id_button_equals);

        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                int error = 0;
                String text = output.getText().toString();
                StringTokenizer t = new StringTokenizer(text, "+-*/", true);
                ArrayList<String> list = new ArrayList<String>();
                while (t.hasMoreTokens()) {
                    list.add(t.nextToken());
                }
                if (Double.parseDouble(list.get(list.size() - 1)) == 0 || list.get(list.size() - 1).equalsIgnoreCase("+")) {
                    error++;
                }
                for (int i = 0; i < list.size() - 1; i++) {
                    if (list.get(i).equals("/")) {
                        try {
                            double div = (Double.parseDouble(list.get(i - 1)) / Double.parseDouble(list.get(i + 1)));
                            if (Double.parseDouble(list.get(i + 1)) == 0 || list.get(list.size() - 1).equalsIgnoreCase("+")) {
                                error++;
                            }
                            if (div % 1.0 == 0) {
                                int temp = (int) div;
                                list.set(i - 1, String.valueOf(temp));
                            } else {
                                list.set(i - 1, String.valueOf(div));
                            }
                            list.remove(i);
                            list.remove(i);
                            i--;

                        } catch (Exception e) {
                            error++;
                        }
                    }
                    if (list.get(i).equals("*")) {
                        try {
                            double mult = (Double.parseDouble(list.get(i - 1)) * Double.parseDouble(list.get(i + 1)));
                            if (mult % 1.0 == 0) {
                                int temp = (int) mult;
                                list.set(i - 1, String.valueOf(temp));
                            } else {
                                list.set(i - 1, String.valueOf(mult));
                            }
                            list.remove(i);
                            list.remove(i);
                            i--;


                        } catch (Exception e) {
                            output.setText("Error");
                        }
                    }
                }
                for (int i = 0; i < list.size() - 1; i++) {
                    System.out.println(list);
                    if (list.get(i).equals("+")) {
                        output.setText(String.valueOf(list.get(0)));
                        try {
                            double add = (Double.parseDouble(list.get(i - 1)) + Double.parseDouble(list.get(i + 1)));
                            if (add % 1.0 == 0) {
                                int temp = (int) add;
                                list.set(i - 1, String.valueOf(temp));
                            } else {
                                list.set(i - 1, String.valueOf(add));
                            }
                            list.remove(i);
                            list.remove(i);
                            i--;
                        } catch (Exception e) {
                            error++;
                        }
                    }
                    System.out.println(list);
                    if (list.get(i).equals("-")) {
                        System.out.println(list);
                        try {
                            double min = (Double.parseDouble(list.get(i - 1)) - Double.parseDouble(list.get(i + 1)));
                            if (min % 1.0 == 0) {
                                int temp = (int) min;
                                list.set(i - 1, String.valueOf(temp));
                            } else {
                                list.set(i - 1, String.valueOf(min));
                            }

                            list.remove(i);
                            list.remove(i);
                            i--;

                        } catch (Exception e) {
                            error++;
                        }
                    }
                }
                if (error > 0) {
                    output.setText("Error");
                    outputString = "Error";
                } else {
                    output.setText(String.valueOf(list.get(0)));
                    outputString = String.valueOf(list.get(0));
                }
            }
            catch(Exception e){
                    output.setText("Error");
                    outputString = "Error";
                }
        }
        });

        output = findViewById(R.id.id_calc);
    }
    public void onClickGo1(View v){
        outputString += ((Button)v).getText();
        output.setText(outputString);
    }
    public void onClickGo2(View v){
        output.setText("");
        outputString = "";
    }

}