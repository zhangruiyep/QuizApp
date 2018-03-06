package com.example.android.quizapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private int getQ1score() {
        int retVal = 0;
        RadioGroup q1_rg = (RadioGroup) findViewById(R.id.q1_radio_group);
        RadioButton chosen = (RadioButton) findViewById(q1_rg.getCheckedRadioButtonId());
        if (chosen.getText().toString().equals(getResources().getString(R.string.ManUtd))) {
            retVal = 30;
        }

        return retVal;
    }

    private int getQ2score() {
        int retVal = 0;
        CheckBox q2_mu = (CheckBox) findViewById(R.id.q2_mu_checkbox);
        CheckBox q2_asn = (CheckBox) findViewById(R.id.q2_asn_checkbox);
        CheckBox q2_liv = (CheckBox) findViewById(R.id.q2_liv_checkbox);
        CheckBox q2_leeds = (CheckBox) findViewById(R.id.q2_leeds_checkbox);

        if (q2_mu.isChecked()
                && !(q2_asn.isChecked())
                && q2_liv.isChecked()
                && !(q2_leeds.isChecked())) {
            retVal = 30;
        }

        return retVal;
    }

    private int getQ3score() {
        int retVal = 0;
        EditText q3 = (EditText) findViewById(R.id.q3_answer_edit_text);

        Log.i("MainActivity", "q3=" + q3.getText().toString() + "len=" + q3.getText().toString().length());
        Log.i("MainActivity", "mu=" + getResources().getString(R.string.ManUtd) + "len=" + getResources().getString(R.string.ManUtd).length());

        if (q3.getText().toString().equals(getResources().getString(R.string.ManUtd))) {
            Log.i("MainActivity", "matched");
            retVal = 40;
        } else {
            Log.i("MainActivity", "not matched");
            retVal = 0;
        }

        return retVal;
    }

    public void onCreateDialog(String name, int score) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Quiz App");
        builder.setMessage("Hi " + name + getResources().getString(R.string.your_score_is) + Integer.toString(score) + ".");
        builder.setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // You don't have to do anything here if you just
                // want it dismissed when clicked
            }
        });

        // Create the AlertDialog object and return it
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void commit(View view) {
        EditText name_et = (EditText) findViewById(R.id.name_edit_text);
        int total = 0;

        total += getQ1score();
        Log.i("MainActivity", "total=" + total);
        total += getQ2score();
        Log.i("MainActivity", "total=" + total);
        total += getQ3score();
        Log.i("MainActivity", "total=" + total);

        //Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_LONG).show();
        onCreateDialog(name_et.getText().toString(), total);
    }
}
