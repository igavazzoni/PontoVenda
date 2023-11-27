package com.example.pontovenda;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pontovenda.adapter.VendasListAdapter;
import com.example.pontovenda.controller.VendasController;
import com.example.pontovenda.model.Vendas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class VendasActivity extends AppCompatActivity {

    private FloatingActionButton btCadastroVendas;
    private AlertDialog dialog;
    private VendasController controller;
    private EditText edPedido;
    private EditText edProduto;
    private EditText edQuant;
    private EditText edValor;
    private EditText edCliente;
    private View viewAlert;
    private RecyclerView rvVendas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendas);

        controller = new VendasController(this);
        rvVendas = findViewById(R.id.rvVendas);
        btCadastroVendas = findViewById(R.id.btCadastroVendas);

        btCadastroVendas.setOnClickListener(view -> abrirVenda());
        

        atualizarListaVendas();
    }

    private void abrirVenda() {

        viewAlert = getLayoutInflater()
                .inflate(R.layout.dialog_list_vendas, null);

        edPedido = viewAlert.findViewById(R.id.edPedido);
        edProduto = viewAlert.findViewById(R.id.edProduto);
        edQuant = viewAlert.findViewById(R.id.edQuant);
        edValor = viewAlert.findViewById(R.id.edValor);
        edCliente = viewAlert.findViewById(R.id.edCliente);


        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("CADASTRO VENDAS");
        builder.setView(viewAlert);
        builder.setCancelable(false);

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("Salvar", null);
        dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button bt = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        salvarDados();
                    }
                });
            }
        });
        dialog.show();

    }

    public void salvarDados(){
        String retorno = controller.salvarVenda(edPedido.getText().toString(),
                                                edProduto.getText().toString(),
                                                edQuant.getText().toString(),
                                                edValor.getText().toString(),
                                                edCliente.getText().toString());

        if(retorno != null){
            if(retorno.contains("PEDIDO")){
                edPedido.setError(retorno);
                edPedido.requestFocus();
            }
            if(retorno.contains("PRODUTO")){
                edProduto.setError(retorno);
                edProduto.requestFocus();
            }
            if(retorno.contains("QUANTIDADE")){
                edQuant.setError(retorno);
                edQuant.requestFocus();
            }
            if(retorno.contains("VALOR")){
                edValor.setError(retorno);
                edValor.requestFocus();
            }
            if(retorno.contains("CLIENTE")){
                edCliente.setError(retorno);
                edCliente.requestFocus();
            }

        }else{
            Toast.makeText(this,"Pedido Salvo "+edPedido.getText().toString()+ "com sucesso!", Toast.LENGTH_LONG).show();

            dialog.dismiss();
            atualizarListaVendas();
        }
    }

    private void atualizarListaVendas(){
        ArrayList<Vendas> listaVendas = controller.retornarTodasVendas();
        VendasListAdapter adapter = new VendasListAdapter(listaVendas, this);
        rvVendas.setLayoutManager(new LinearLayoutManager(this));
        rvVendas.setAdapter(adapter);
    }

}