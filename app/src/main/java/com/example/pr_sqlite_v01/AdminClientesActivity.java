package com.example.pr_sqlite_v01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Cliente;
import logica.AdminClientes;

public class AdminClientesActivity extends AppCompatActivity {

    ImageButton btnAgregar, btnEliminar, btnBuscar, btnEditar;
    //ListView listView;
    EditText etBusqueda;
    String campoBusqueda;

    RecyclerView mRecyclerView;
    AdaptadorClienteItem mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    List<Cliente> listaCliente;

    ArrayAdapter<Cliente> arrayGlobal;

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_clientes);
        cargarControles();
        cargarEventos();
        //cargarLista();
        cargarListaRV();


    }

    private void cargarListaRV() {

        AdminClientes admin = new AdminClientes(this);

        try {
            listaCliente= admin.ObtenerLista();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mRecyclerView = findViewById(R.id.rv_admincli);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdaptadorClienteItem(listaCliente);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new AdaptadorClienteItem.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(AdminClientesActivity.this, listaCliente.get(position).getNombre(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*
        private void cargarLista() {
            AdminClientes admin = new AdminClientes(this);
            try {
                ArrayAdapter<Cliente> adapter = new ArrayAdapter<Cliente>(this, android.R.layout.simple_list_item_1, admin.ObtenerLista());
                arrayGlobal = adapter;
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Cliente cli = (Cliente) parent.getAdapter().getItem(position);
                        Toast.makeText(getBaseContext(), cli.getIdCliente() + ": " + cli.getNombre() + " - " + cli.getCiudad() + " - " + cli.getTelefono() + " - " + cli.getDireccion(), Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
     */
    private void cargarEventos() {
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminClientesActivity.this, ClientesActivity.class);
                startActivity(intent);
                finish();

            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminClientesActivity.this, EliminarActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminClientesActivity.this, ModificarActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                campoBusqueda = etBusqueda.getText().toString();
                buscar();
            }
        });
    }

    private void buscar() {
        Cliente cliBusq;
        for (int i = 0; i < arrayGlobal.getCount(); i++) {
            cliBusq = arrayGlobal.getItem(i);

            //if (cliBusq.getIdCliente() == Integer.parseInt(campoBusqueda)) {
            if (cliBusq.getIdCliente() == Integer.parseInt(campoBusqueda)) {
                Toast.makeText(this, "Resultado" + cliBusq.getNombre(), Toast.LENGTH_SHORT).show();
                break;
            } else if (cliBusq.getNombre() == campoBusqueda) {
                Toast.makeText(this, "Resultado" + cliBusq.getNombre(), Toast.LENGTH_SHORT).show();
                break;
            } else if (cliBusq.getDireccion() == campoBusqueda) {
                Toast.makeText(this, "Resultado" + cliBusq.getDireccion(), Toast.LENGTH_SHORT).show();
                break;
            } else if (cliBusq.getTelefono() == campoBusqueda) {
                Toast.makeText(this, "Resultado" + cliBusq.getTelefono(), Toast.LENGTH_SHORT).show();
                break;
            } else if (cliBusq.getCiudad() == campoBusqueda) {
                Toast.makeText(this, "Resultado" + cliBusq.getCiudad(), Toast.LENGTH_SHORT).show();
                break;
            } else if (cliBusq.getCiudad() == campoBusqueda) {
                Toast.makeText(this, "Resultado" + cliBusq.getCiudad(), Toast.LENGTH_SHORT).show();
                break;
            } else {
                Toast.makeText(this, "No se encontro", Toast.LENGTH_SHORT).show();

            }
            /*if (cliBusq.getIdCliente() == Integer.parseInt(campoBusqueda) ||
                    cliBusq.getNombre() == campoBusqueda ||
                    cliBusq.getDireccion() == campoBusqueda ||
                    cliBusq.getTelefono() == campoBusqueda ||
                    cliBusq.getCiudad() == campoBusqueda ||
                    cliBusq.getCiudad() == campoBusqueda) {

                Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show();
            }            */
        }
    }

    public void cargarControles() {
        btnAgregar = findViewById(R.id.btn_admincli_agregar);
        btnEliminar = findViewById(R.id.btn_admincli_eliminar);
        btnBuscar = findViewById(R.id.btn_admincli_buscar);
        btnEditar = findViewById(R.id.btn_admincli_refresh);
        //listView = findViewById(R.id.lv_admincli);
        mRecyclerView=findViewById(R.id.rv_admincli);
        

        etBusqueda = findViewById(R.id.et_admincli_busca);
    }
}
