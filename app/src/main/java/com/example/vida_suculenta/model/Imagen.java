package com.example.vida_suculenta.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Imagen {
private String urlimagen;
private String nombre;
private String descripcion;

    public Imagen( ) {

    }

    public Imagen(String urlimagen, String nombre, String descripcion, String precio) {
        this.urlimagen = urlimagen;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    private String precio;
    public String getUrlimagen() {
        return urlimagen;
    }

    public void setUrlimagen(String urlimagen) {
        this.urlimagen = urlimagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Imagen(JSONObject item) throws JSONException {
        urlimagen= item.getString("IMAGEN_REF");
        nombre= item.getString("NOMBRE_PRODUCTO");
        descripcion= item.getString("DESCRIPCION");
        precio= item.getString("PRECIO");
    }
    public static ArrayList<Imagen> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Imagen> imagens = new ArrayList<>();
        for (int i = 0; i < datos.length() ; i++) {
            imagens.add(new Imagen(datos.getJSONObject(i)));
        }
        return imagens;
    }

}
