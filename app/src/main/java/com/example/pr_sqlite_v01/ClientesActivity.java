package com.example.pr_sqlite_v01;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.sql.SQLException;

import entidades.Cliente;
import logica.AdminClientes;

public class ClientesActivity extends AppCompatActivity {
    EditText etNom, etDom, etTel, etCd, etEdo;
    ImageButton guardar, cancelar;
    AdminClientes admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);
        cargarControles();
        cargarEventos();
    }

    private void cargarEventos() {
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    EjectarAlta(view);
                    onDestroy();
                    Intent intent = new Intent(ClientesActivity.this, AdminClientesActivity.class);
                    startActivity(intent);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClientesActivity.this, AdminClientesActivity.class);
                startActivity(intent);
                onDestroy();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void cargarControles() {
        etNom = (EditText) findViewById(R.id.et_mod2_nom);
        etDom = (EditText) findViewById(R.id.et_mod2_dir);
        etTel = (EditText) findViewById(R.id.et_mod2_tel);
        etCd = (EditText) findViewById(R.id.et_mod2_cd);
        etEdo = (EditText) findViewById(R.id.et_mod2_estado);
        guardar = (ImageButton) findViewById(R.id.btn_mod2_save);
        cancelar = (ImageButton) findViewById(R.id.btn_mod2_cancel);
        admin = new AdminClientes(this);
    }

    public void EjectarAlta(View v) throws SQLException {
        String nom = etNom.getText().toString();
        String dom = etDom.getText().toString();
        String tel = etTel.getText().toString();
        String cd = etCd.getText().toString();
        String edo = etEdo.getText().toString();
        long camposAfectados;
        if (nom.isEmpty() || dom.isEmpty() || tel.isEmpty() || cd.isEmpty() || edo.isEmpty()) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Campos Vacio");
            alert.setMessage("Faltan Datos");
            alert.setPositiveButton("Ok", null);
            alert.create();
            alert.show();
        } else {
            try {
                Cliente cli = new Cliente();
                cli.setIdCliente(0);
                cli.setNombre(nom);
                cli.setDireccion(dom);
                cli.setTelefono(tel);
                cli.setCiudad(cd);
                cli.setEstado(edo);
                if ((camposAfectados = admin.AgregarCliente(cli)) > 0) {
                    Toast.makeText(this, "El cliente fue almacenado satisfactoriamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Los datos no se guardaron correctamente", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Error");
                alert.setMessage(e.getMessage());
                alert.setPositiveButton("ok", null);
                alert.create();
                alert.show();
            }
        }
    }
}
