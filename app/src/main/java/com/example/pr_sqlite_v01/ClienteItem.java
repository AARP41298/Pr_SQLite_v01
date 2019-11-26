package com.example.pr_sqlite_v01;

public class ClienteItem {
    private String nombre,direccion,telefono,ciudad;
    private int id;

    public ClienteItem(String nombre, String direccion, String telefono, String ciudad, int id) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public int getId() {
        return id;
    }
}
