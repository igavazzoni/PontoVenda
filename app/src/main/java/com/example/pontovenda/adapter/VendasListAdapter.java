package com.example.pontovenda.adapter;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pontovenda.R;
import com.example.pontovenda.model.Vendas;

import java.util.ArrayList;

public class VendasListAdapter extends
        RecyclerView.Adapter<VendasListAdapter.ViewHolder>{

    private ArrayList<Vendas> listaVendas;
    private Context context;

    public VendasListAdapter(ArrayList<Vendas> listaVendas, Context context) {
        this.listaVendas = listaVendas;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.item_list_vendas,
                parent, false);

        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Vendas vendaSelecionado = listaVendas.get(position);
        holder.tvPedido.setText(String.valueOf(vendaSelecionado.getPedido()));
        holder.tvProduto.setText(vendaSelecionado.getProduto());
        holder.tvQuant.setText(Integer.toString(vendaSelecionado.getQuant()));
        holder.tvValor.setText(Integer.toString(vendaSelecionado.getValor()));
        holder.tvCliente.setText(vendaSelecionado.getCliente());
    }

    @Override
    public int getItemCount() {

        return this.listaVendas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvPedido;
        public TextView tvProduto;
        public TextView tvQuant;
        public TextView tvValor;
        public TextView tvCliente;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tvPedido = itemView.findViewById(R.id.tvPedido);
            this.tvProduto = itemView.findViewById(R.id.tvProduto);
            this.tvQuant = itemView.findViewById(R.id.tvQuant);
            this.tvValor = itemView.findViewById(R.id.tvValor);
            this.tvCliente = itemView.findViewById(R.id.tvCliente);

        }
    }




}