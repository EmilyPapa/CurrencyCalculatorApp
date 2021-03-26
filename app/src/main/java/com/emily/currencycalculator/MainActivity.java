package com.emily.currencycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.JsonObject;

import RetrofitPackage.Builder;
import RetrofitPackage.RetrofitInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {

    EditText CurrencyAmount;
    EditText ConvertedAmount;
    Button ConvertBtn;
    Spinner FromSpinner;
    Spinner ToSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        CurrencyAmount = findViewById(R.id.amount);
        ConvertedAmount = findViewById(R.id.convertedAmount);
        FromSpinner = findViewById(R.id.from_spinner);
        ToSpinner = findViewById(R.id.to_spinner);

        //add the available currencies to spinners in a dropDown menu
        String[] CurrencyMenu = {"AUD","CAD","CHF","EUR","GBP","USD"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, CurrencyMenu);

        //implement the dropdownMenu with the currencies to spinners
        FromSpinner.setAdapter(adapter);
        ToSpinner.setAdapter(adapter);

        ConvertBtn = findViewById(R.id.convertbtn);
        ConvertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //accessing the builder by clicking the button
                RetrofitInterface retrofitInterface = Builder.getRetrofitInstance().create(RetrofitInterface.class);

                //Passing the Values from the API to the FromSpinner
                Call<JsonObject> call = retrofitInterface.getCurrency(FromSpinner.getSelectedItem().toString());
                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    //extracting the Data
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JsonObject res = response.body();

                        //get the rates of all the currencies from the API
                        JsonObject currencyrates = res.getAsJsonObject("conversion_rates");

                        //get the input text from user
                        double currencyconversion = Double.valueOf( CurrencyAmount.getText().toString());

                        //getting the rates from values of ToSpinner and makes the conversion
                        double converter = Double.valueOf(currencyrates.get(ToSpinner.getSelectedItem().toString()).toString());

                        //the final result and print it in the textfield
                        double convertedResult = currencyconversion * converter;
                        ConvertedAmount.setText(String.valueOf(convertedResult));

                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });

            }
        });





    }
}