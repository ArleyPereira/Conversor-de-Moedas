package com.example.conversormoedas.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.conversormoedas.R;
import com.example.conversormoedas.model.Moeda;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdapterMoeda extends RecyclerView.Adapter<AdapterMoeda.MyViewHolder> {

    private List<Moeda> moedaList;

    public AdapterMoeda(List<Moeda> moedaList) {
        this.moedaList = moedaList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adapter, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        NumberFormat nf = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));

        Moeda moeda = moedaList.get(position);
        holder.textMoeda.setText(moeda.getName());
        holder.textValorCompra.setText("R$ " + nf.format(moeda.getBuy()));
        holder.textValorVenda.setText("R$ " + nf.format(moeda.getSell()));

    }

    @Override
    public int getItemCount() {
        return moedaList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textMoeda, textValorCompra, textValorVenda;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textMoeda = itemView.findViewById(R.id.textMoeda);
            textValorCompra = itemView.findViewById(R.id.textValorCompra);
            textValorVenda = itemView.findViewById(R.id.textValorVenda);
        }
    }

}
