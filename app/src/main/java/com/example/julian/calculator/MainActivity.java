/*
 * Android Studio Calculator application.
 */
package com.example.julian.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // String representations of the two numbers calculated.
    private String firstNum;
    private String secNum;

    // Operator being used.
    private String operator;
    private boolean opSet = false;

    // EditText object where values will be displayed.
    private EditText resultDisplay;
    private EditText calcDisplay;

    // All Buttons being declared in this activity.
    Button zero;
    Button one;
    Button two;
    Button three;
    Button four;
    Button five;
    Button six;
    Button seven;
    Button eight;
    Button nine;

    Button plusBtn;
    Button minusBtn;
    Button timesBtn;
    Button divideBtn;

    Button enterBtn;
    Button clearBtn;
    Button deleteBtn;
    Button dotBtn;


    /**
     * Extends parent's onCreate method.
     * Performs Calculator operations on this activity.
     *
     * @param savedInstanceState Parent's Bundle object.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultDisplay = (EditText) findViewById(R.id.res);
        resultDisplay.setFocusable(false);
        resultDisplay.setEnabled(false);
        resultDisplay.setClickable(true);

        calcDisplay = (EditText) findViewById(R.id.currCalc);
        calcDisplay.setFocusable(false);
        calcDisplay.setClickable(true);

        zero = (Button) findViewById(R.id.btn0);
        displayVal(zero);
        one = (Button) findViewById(R.id.btn1);
        displayVal(one);
        two = (Button) findViewById(R.id.btn2);
        displayVal(two);
        three = (Button) findViewById(R.id.btn3);
        displayVal(three);
        four = (Button) findViewById(R.id.btn4);
        displayVal(four);
        five = (Button) findViewById(R.id.btn5);
        displayVal(five);
        six = (Button) findViewById(R.id.btn6);
        displayVal(six);
        seven = (Button) findViewById(R.id.btn7);
        displayVal(seven);
        eight = (Button) findViewById(R.id.btn8);
        displayVal(eight);
        nine = (Button) findViewById(R.id.btn9);
        displayVal(nine);

        plusBtn = (Button) findViewById(R.id.btnAdd);
        displayVal(plusBtn);
        minusBtn = (Button) findViewById(R.id.btnSub);
        displayVal(minusBtn);
        timesBtn = (Button) findViewById(R.id.btnMul);
        displayVal(timesBtn);
        divideBtn = (Button) findViewById(R.id.btnDiv);
        displayVal(divideBtn);

        enterBtn = (Button) findViewById(R.id.btnEnter);
        displayVal(enterBtn);

        clearBtn = (Button) findViewById(R.id.btnClr);
        displayVal(clearBtn);

        dotBtn = (Button) findViewById(R.id.btnDot);
        displayVal(dotBtn);

        deleteBtn = (Button) findViewById(R.id.btnDelete);
        displayVal(deleteBtn);
    }

    /**
     * Sets action listener of Button btn.
     *
     * @param btn Button object where the action
     *              listener is being set.
     */
    private void displayVal(final Button btn){

        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String btnTxt = btn.getText().toString();
                if (btnTxt.equals("Enter")) {
                    if (opSet) {
                        setSecNum();
                        String currText = calcDisplay.getText().toString();
                        currText += " ";
                        currText += secNum;
                        calcDisplay.setText(currText);

                        // Calculation
                        Double result = calculate(firstNum, secNum, operator); // Used object type.

                        if (result % 1 == 0) { // If there is only 0 after decimal point.
                            int resultInt = result.intValue();
                            resultDisplay.setText(String.format("%s", resultInt));
                        } else {
                            resultDisplay.setText(String.format("%s", result));
                        }

                        firstNum = resultDisplay.getText().toString();

                        opSet = false;
                    }
                } else if (btnTxt.equals("CLEAR")) {
                    clearCalc();
                } else if (btnTxt.equals("DEL")){
                    String currNum = resultDisplay.getText().toString();
                    currNum = removeLastChar(currNum);
                    resultDisplay.setText(currNum);
                }else if (isOperator(btnTxt)) {
                    if (!opSet) {

                        opSet = true;
                        operator = btnTxt;
                        setFirstNum();
                        calcDisplay.setText(String.format("%s %s", firstNum, operator));
                        resultDisplay.setText("0");
                    }

                }else { // Includes "."
                    insertNum(btn);
                }

            }
        });
    }

    /**
     * Displays the values of the buttons pressed in resultDisplay,
     * the EditText object.
     *
     * @param btn Value of this Button object being displayed.
     */
    private void insertNum(final Button btn){

        String curr = resultDisplay.getText().toString();

        // Don't want 0 to be the first number.
        if (curr.equals("0")){
            curr = btn.getText().toString();
        } else {
            curr += btn.getText().toString();
        }
        resultDisplay.setText(curr);

    }

    /**
     * Sets the value of firstNum to the value
     * in the EditTextBox resultDisplay.
     */
    private void setFirstNum(){

        firstNum = resultDisplay.getText().toString();

    }

    /**
     * Sets the value of secNum to the value
     * in the EditTextBox resultDisplay.
     */
    private void setSecNum(){

        secNum = resultDisplay.getText().toString();
    }

    /*
     * Returns whether the string op is a valid operator
     * for this application.
     */
    private boolean isOperator(String op){
        return op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/");
    }

    /*
     * Resets the Calculator to default settings.
     */
    private void clearCalc(){
        resultDisplay.setText("0");
        calcDisplay.setText("");
        opSet = false;
        firstNum = null;
        secNum = null;
    }

    /**
     * Returns the the result of the calculation
     * of num1 +,-,*,/ num2, where num1 and num2 are
     * string representations of doubles or ints.
     *
     * @param num1 string representation of the first number
     * @param num2 string representation of the second number
     * @param operator operator to use to perform arithmetic between
     *                num1 and num2.
     * @return The result of the performed arithmetic of num1 and num2.
     */
    private double calculate(String num1, String num2, String operator){

        double val1 = Double.parseDouble(num1);
        double val2 = Double.parseDouble(num2);

        double endVal = 0.0;


        switch (operator){
            case "+":
                endVal = val1 + val2;
                break;
            case "-":
                endVal = val1 - val2;
                break;
            case "*":
                endVal = val1 * val2;
                break;
            case "/":
                endVal = val1 / val2;
                break;
        }

        return endVal;
    }

    /**
     * Returns the string representation of num
     * excluding the last digit. If the length
     * of num is 1, then num is replaced by "0".
     *
     * @param num string representation of a number.
     * @return num without the last character.
     */
    private String removeLastChar(String num){
        if(num.length() > 1){
            num = num.substring(0, num.length() - 1);
        } else {
            num = "0";
        }

        return num;
    }

}
