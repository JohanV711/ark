package com.cumn.ark;

public class mascotas {
    private String nombre;
    private String tipo;
    private String raza;
    private String genero;
    private String peso;
    private String imagenURL;
    private String usuarioID;

    // Constructor vacío requerido para Firebase
    public mascotas() {
    }

    public mascotas(String nombre, String tipo, String raza, String genero, String peso, String imagenURL, String usuarioID) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.raza = raza;
        this.genero = genero;
        this.peso = peso;
        this.imagenURL = imagenURL;
        this.usuarioID = usuarioID;
    }

    // Métodos getter y setter para acceder a los campos de la mascota
    // (puedes generarlos automáticamente en Android Studio)

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public  String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getImagenURL() {
        return imagenURL;
    }

    public void setImagenURL(String imagenURL) {
        this.imagenURL = imagenURL;
    }

    public String getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(String usuarioID) {
        this.usuarioID = usuarioID;
    }
}

