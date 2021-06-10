package com.javigu.repasofinal;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Provincias {
    private String id;
    private String nombre;
    private String coordenadas;
    private String descripcion;
    private String imagen;
    ArrayList<Provincias> provincias_json;

    public Provincias() {
    }

    public Provincias(String id, String nombre, String coordenadas, String descripcion, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.coordenadas = coordenadas;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public ArrayList<Provincias> llenarProvinciasJSON(){
        //cargar el json
        InputStream raw = MainActivity.context().getResources().openRawResource(R.raw.provincias);
        Reader rd = new BufferedReader(new InputStreamReader(raw));
        Type listType = new TypeToken<List<Provincias>>() {}.getType();
        Gson gson = new Gson();
        provincias_json = gson.fromJson(rd, listType);
        return provincias_json;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
