package com.example.pontovenda.controller;

import android.content.Context;

import com.example.pontovenda.Dao.VendasDao;
import com.example.pontovenda.model.Vendas;

import java.util.ArrayList;

public class VendasController {

    private Context context;

    public VendasController(Context context) {
        this.context = context;
    }

    public String salvarVenda(String pedido, String produto, String quant, String valor, String cliente){
        try{
            if(pedido.equals("") || pedido.isEmpty()){

                return "Informe o codigo do pedido!";
            }
            if(produto.equals("") || produto.isEmpty()){
                return "Informe o Produto!";
            }
            if(quant.equals("") || quant.isEmpty()){
                return "Informe o Quantidade!";
            }
            if(valor.equals("") || valor.isEmpty()){
                return "Informe o Valor!";
            }
            if(cliente.equals("") || cliente.isEmpty()){
                return "Informe o Cliente ";
            }





            Vendas vendas = VendasDao.getInstancia(context).getById(Integer.parseInt(quant));
            if(vendas != null){
                return "O  " +pedido+ " já está cadastrado!";
            }else{
                vendas = new Vendas();
                vendas.setPedido(pedido);
                vendas.setProduto(produto);
                vendas.setQuant(Integer.parseInt(quant));
                vendas.setValor(Integer.parseInt(valor));
                vendas.setCliente(cliente);

                VendasDao.getInstancia(context).insert(vendas);
            }

        }catch (Exception ex){
            return  "Erro ao Gravar Venda.";
        }
        return null;
    }

    public ArrayList<Vendas> retornarTodasVendas(){
        return VendasDao.getInstancia(context).getAll();
    }
}


