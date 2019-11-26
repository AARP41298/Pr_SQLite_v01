package com.example.pr_sqlite_v01;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;

import entidades.Cliente;
import logica.AdminClientes;

public class EliminarActivity extends AppCompatActivity {

    private ListView listaCli;
    ImageButton cancelar;
    AdminClientes admin;
    public int regAfectados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        listaCli = (ListView) findViewById(R.id.lv_eliminar);
        cancelar = findViewById(R.id.btn_eliminar_eliminar);
        cargarEvento();
        cargarLista();

    }

    private void cargarLista() {
        admin = new AdminClientes(this);
        try {
            ArrayAdapter<Cliente> adapter = new ArrayAdapter<Cliente>(this, android.R.layout.simple_list_item_1, admin.ObtenerLista());
            listaCli.setAdapter(adapter);
            listaCli.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Cliente obj = (Cliente) parent.getAdapter().getItem(position);
                    Confirmar(obj);
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void cargarEvento() {
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EliminarActivity.this, AdminClientesActivity.class);
                startActivity(intent);
                onDestroy();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void Confirmar(final Cliente cliente) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Confirmacion");
        alert.setMessage("Seguro que deseas eliminarlo?");
        alert.setCancelable(false);
        alert.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    Aceptar(cliente);
                    cargarLista();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        alert.setNegativeButton("No", null);
        alert.show();
    }

    private void Aceptar(Cliente cliente) throws SQLException {
        regAfectados = admin.EliminarCliente(cliente);
        if (regAfectados > 0) {
            Toast.makeText(this, "Los datos fueron eliminados correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Ocurrio un error al tratar de eliminar los datos", Toast.LENGTH_SHORT).show();
        }
    }
}
