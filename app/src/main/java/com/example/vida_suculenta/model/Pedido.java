package com.example.vida_suculenta.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Pedido {
 private String nombre_producto;
 private String fecha;
 private String estado;
 private String precio;
 private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Pedido () {

    }

    public Pedido(String nombre_producto, String fecha, String estado, String precio, String url) {
        this.nombre_producto = nombre_producto;
        this.fecha = fecha;
        this.estado = estado;
        this.precio = precio;
        this.url = url;
    }

}
