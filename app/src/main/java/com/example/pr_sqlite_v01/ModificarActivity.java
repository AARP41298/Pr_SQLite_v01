package com.example.pr_sqlite_v01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.List;

import entidades.Cliente;
import logica.AdminClientes;

public class ModificarActivity extends AppCompatActivity {

    ListView lista;
    ImageButton cancelar;
    AdminClientes admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        lista = (ListView) findViewById(R.id.lv_modificar);
        cancelar = findViewById(R.id.btn_mod_cancel);
        cargarEventos();

        admin = new AdminClientes(this);
        try {
            ArrayAdapter<Cliente> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, admin.ObtenerLista());
            lista.setAdapter(adapter);
            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Cliente obj = (Cliente) parent.getAdapter().getItem(position);
                    ModificarDatos(obj);
                    finish();
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void cargarEventos() {
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ModificarActivity.this, AdminClientesActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void ModificarDatos(Cliente cliente) {
        int idcliente = cliente.getIdCliente();
        Intent intent = new Intent(this, Modificar2Activity.class);
        intent.putExtra("id", idcliente);
        startActivity(intent);
    }
}
