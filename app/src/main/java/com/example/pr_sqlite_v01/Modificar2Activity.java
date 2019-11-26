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

public class Modificar2Activity extends AppCompatActivity {

    EditText nom, dom, tel, cd, edo;
    ImageButton guardar, cancelar;
    AdminClientes admin;
    public int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar2);

        cargarControles();
        cargarEventos();


        Bundle bundle = getIntent().getExtras();
        Cliente cli = new Cliente();
        try {
            id = bundle.getInt("id");
            cli = admin.ConsultaCliente(id);
            nom.setText(cli.getNombre());
            dom.setText(cli.getDireccion());
            tel.setText(cli.getTelefono());
            cd.setText(cli.getCiudad());
            edo.setText(cli.getEstado());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void cargarEventos() {
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModificarDatos(view);
                finish();
            }
        });
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Modificar2Activity.this, AdminClientesActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void ModificarDatos(View view) {
        String n = nom.getText().toString();
        String d = dom.getText().toString();
        String t = tel.getText().toString();
        String c = cd.getText().toString();
        String e = edo.getText().toString();
        int camposafectados;
        if (n.isEmpty() || d.isEmpty() || d.isEmpty() || d.isEmpty() || d.isEmpty()) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Campos Vacios");
            alert.setMessage("Faltan Datos");
            alert.setPositiveButton("Ok", null);
            alert.create();
            alert.show();
        } else {
            try {
                Cliente cli = new Cliente();
                cli.setIdCliente(id);
                cli.setNombre(n);
                cli.setDireccion(d);
                cli.setTelefono(t);
                cli.setCiudad(c);
                cli.setEstado(e);
                if ((camposafectados = admin.EditarCliente(cli)) > 0) {
                    Toast.makeText(this, "El cliente fue almacenado satisfactoriamente", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, "Los datos no se guardaron correctamente", Toast.LENGTH_SHORT).show();
                }
            } catch (SQLException ex) {
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Error");
                alert.setMessage(ex.getMessage());
                alert.setPositiveButton("Ok", null);
                alert.create();
                alert.show();
            }
        }
    }

    private void cargarControles() {
        nom = (EditText) findViewById(R.id.et_mod2_nom);
        dom = (EditText) findViewById(R.id.et_mod2_dir);
        tel = (EditText) findViewById(R.id.et_mod2_tel);
        cd = (EditText) findViewById(R.id.et_mod2_cd);
        edo = (EditText) findViewById(R.id.et_mod2_estado);
        guardar = (ImageButton) findViewById(R.id.btn_mod2_save);
        cancelar = (ImageButton) findViewById(R.id.btn_mod2_cancel);
        admin = new AdminClientes(this);
    }
}
