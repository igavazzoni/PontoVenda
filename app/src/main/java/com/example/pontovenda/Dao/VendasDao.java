package com.example.pontovenda.Dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.pontovenda.helper.SQLiteDataHelper;
import com.example.pontovenda.model.Vendas;

import java.util.ArrayList;

public class VendasDao implements IGenericDao<Vendas> {
    private SQLiteOpenHelper openHelper;

    private SQLiteDatabase baseDados;

    private String[]colunas = {"PEDIDO", "PRODUTO", "QUANTIDADE", "VALOR", "CLIENTE"};

    private String tabela = "VENDAS";

    private Context context;

    private static VendasDao instancia;

    public static VendasDao getInstancia(Context context) {
        if (instancia == null) {
            return instancia = new VendasDao(context);
        } else {
            return instancia;
        }
    }

    private VendasDao(Context context) {
        this.context = context;

        openHelper = new SQLiteDataHelper(this.context,
                "UNIPAR_BD", null, 1);

        baseDados = openHelper.getWritableDatabase();
    }

    @Override
    public long insert(Vendas obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[0], obj.getPedido());
            valores.put(colunas[1], obj.getProduto());
            valores.put(colunas[2], obj.getQuant());
            valores.put(colunas[3], obj.getValor());
            valores.put(colunas[4], obj.getCliente());


            return baseDados.insert(tabela, null, valores);


        } catch (SQLException ex) {
            Log.e("UNIPAR", "ERRO: VendasDao.insert() " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Vendas obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put(colunas[1], obj.getCliente());

            String[] identificador = {String.valueOf(obj.getProduto())};

            return baseDados.update(tabela, valores,
                    colunas[0] + "= ?", identificador);

        } catch (SQLException ex) {
            Log.e("UNIPAR", "ERRO: VendasDao.update() " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Vendas obj) {
        try {
            String[] identificador = {String.valueOf(obj.getCliente())};

            return baseDados.delete(tabela,
                    colunas[0] + "= ?", identificador);
        } catch (SQLException ex) {
            Log.e("UNIPAR", "ERRO: AlunoDao.delete() " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<Vendas> getAll() {
        ArrayList<Vendas> lista = new ArrayList<>();
        try {
            Cursor cursor = baseDados.query(tabela,
                    colunas, null,
                    null, null,
                    null, colunas[0] + " desc");

            if (cursor.moveToFirst()) {
                do {
                    Vendas vendas = new Vendas();
                    vendas.setPedido(cursor.getString(0));
                    vendas.setProduto(cursor.getString(1));
                    vendas.setQuant(cursor.getInt(2));
                    vendas.setValor(cursor.getInt(3));
                    vendas.setCliente(cursor.getString(4));

                    lista.add(vendas);

                } while (cursor.moveToNext());
            }

        } catch (SQLException ex) {
            Log.e("UNIPAR", "ERRO: VendasDao.getAll() " + ex.getMessage());
        }

        return lista;
    }

    @Override
    public Vendas getById(int id) {
        try{
            String[]identificador = {String.valueOf(id)};
            Cursor cursor = baseDados.query(tabela, colunas,
                    colunas[0]+"= ?", identificador,
                    null, null, null);

            if(cursor.moveToFirst()){
                Vendas vendas = new Vendas();
                vendas.setPedido(cursor.getString(0));
                vendas.setProduto(cursor.getString(1));
                vendas.setQuant(cursor.getInt(2));
                vendas.setValor(cursor.getInt(3));
                vendas.setCliente(cursor.getString(4));

                return vendas;
            }

        }catch (SQLException ex){
            Log.e("UNIPAR", "ERRO: VendasDao.getById() "+ex.getMessage());
        }
        return null;
    }

}





