package com.example.tipcalculator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.Rating;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;

public class MainActivity extends AppCompatActivity {
    private double cost = 0;
    private double percent = 0;
    private double tip = 0;
    private double total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnRatingBar();
    }

    public void addListenerOnRatingBar(){

        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        final EditText editText = (EditText) findViewById(R.id.editTextCost);
        final TextView percentText = (TextView) findViewById(R.id.textViewPercent);
        final TextView tipText = (TextView) findViewById(R.id.textViewTip);
        final TextView totalText = (TextView) findViewById(R.id.textViewTotal);

        final DecimalFormat df = new DecimalFormat("$####0.00");
        final DecimalFormat percentDf = new DecimalFormat("##0.##%");

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float value, boolean b) {
                percent = value * 0.04;

                if(!editText.getText().toString().equals("") && !editText.getText().toString().equals(".")){
                    cost = Double.parseDouble(editText.getText().toString());
                    tip = cost * percent;
                    total = cost + tip;
                    percentText.setText((percentDf.format(percent)));
                    tipText.setText(df.format(tip));
                    totalText.setText(df.format(total));
                }
                else{
                    Context context  = getApplicationContext();
                    CharSequence string = "You should really type in a cost, not anything strange.(Type in a cost.)";
                    Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
                    percentText.setText("Mhm");
                    tipText.setText("I can definitely calculate your tips");
                    totalText.setText("with the info you gave me");
                }
            }
        });

    }
}
