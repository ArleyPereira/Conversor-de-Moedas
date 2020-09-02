package com.example.conversormoedas.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.conversormoedas.R;
import com.example.conversormoedas.api.MoedaService;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ConversorFragment extends Fragment {

    private EditText editReal;
    private EditText editDolar;
    private EditText editEuro;
    private ProgressBar progressBar;
    private TextView textInfo;

    private Retrofit retrofit;

    private double real = 0;
    private double dolar = 0;
    private double euro = 0;

    private NumberFormat nf;

    public ConversorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_conversor, container, false);

        // Inicia componentes de tela
        nf = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
        iniciaComponentes(view);

        // Inicia serviço Retrofit
        iniciaRetrofit();

        // Recupera as moedas
        buscarMoedas();

        // Ouvinte EditsText
        configEditsText();

        return view;

    }

    // Ouvinte EditsText
    private void configEditsText(){

        TextWatcher textWatcher;

        editReal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String realString = editReal.getText().toString();
                if(!realString.isEmpty()){
                    real = Double.parseDouble(realString);

                    editReal.addTextChangedListener(textWatcher);

                    editDolar.setText(nf.format(real / dolar));
                    //editEuro.setText(nf.format(real / euro));
                }else {
                    editDolar.setText("");
                    //editEuro.setText("");
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        editDolar.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                String dolarString = editDolar.getText().toString();
//                if(!dolarString.isEmpty()){
//                    double d = Double.parseDouble(dolarString);
//
//                    editReal.setText(nf.format(d * dolar));
//                    editEuro.setText(nf.format(d / euro));
//                }else {
//                    editReal.setText("");
//                    editEuro.setText("");
//                }
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });


    }

    // Realiza a chamada da busca
    public void buscarMoedas() {

        MoedaService moedaService = retrofit.create(MoedaService.class);
        Call<Object> call = moedaService.getMoedas("only_results", "e9645ae7");

        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                String result = new Gson().toJson(response.body());
                try {

                    textInfo.setText("");
                    progressBar.setVisibility(View.GONE);

                    JSONObject jsonObject = new JSONObject(result);

                    dolar = Double.parseDouble(jsonObject.getJSONObject("currencies").getJSONObject("USD").getString("buy"));
                    euro = Double.parseDouble(jsonObject.getJSONObject("currencies").getJSONObject("EUR").getString("buy"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });

    }

    // Inicia serviço Retrofit
    private void iniciaRetrofit() {
        retrofit = new Retrofit
                .Builder()
                .baseUrl("https://api.hgbrasil.com/finance/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    // Inicia componentes de tela
    private void iniciaComponentes(View view){
        editReal = view.findViewById(R.id.editReal);
        editDolar = view.findViewById(R.id.editDolar);
        editEuro = view.findViewById(R.id.editEuro);
        progressBar = view.findViewById(R.id.progressBar);
        textInfo = view.findViewById(R.id.textInfo);
    }

}