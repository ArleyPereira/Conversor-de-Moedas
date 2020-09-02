package com.example.conversormoedas.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.conversormoedas.R;
import com.example.conversormoedas.adapter.AdapterMoeda;
import com.example.conversormoedas.api.MoedaService;
import com.example.conversormoedas.api.RetrofitConfig;
import com.example.conversormoedas.model.Moeda;
import com.example.conversormoedas.model.MoedaList;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MoedasFragment extends Fragment {

    private AdapterMoeda adapterMoeda;
    private List<Moeda> moedaList = new ArrayList<>();
    private RecyclerView rvMoedas;
    private ProgressBar progressBar;
    private TextView textInfo;

    private Retrofit retrofit;

    public MoedasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_moedas, container, false);

        // Inicia componentes de tela
        iniciaComponentes(view);

        // Inicia serviço Retrofit
        retrofit = RetrofitConfig.getRetrofit();

        // Realiza a chamada da busca
        buscarMoedas();

        // Config RecyclerView
        configRecycler();

        return view;

    }

    // Config RecyclerView
    private void configRecycler(){
        rvMoedas.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMoedas.setHasFixedSize(true);
        adapterMoeda = new AdapterMoeda(moedaList);
        rvMoedas.setAdapter(adapterMoeda);
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

                    Gson gson = new Gson();
                    MoedaList moedas = gson.fromJson(jsonObject.getJSONObject("currencies").toString(), MoedaList.class);

                    moedaList.addAll(moedas.getLista());

                    adapterMoeda.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });

    }

    // Inicia componentes de tela
    private void iniciaComponentes(View view) {
        rvMoedas = view.findViewById(R.id.rvMoedas);
        progressBar = view.findViewById(R.id.progressBar);
        textInfo = view.findViewById(R.id.textInfo);
    }

}