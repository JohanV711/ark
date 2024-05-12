package com.cumn.ark;

public class mascotas {
    private String nombre;
    private String tipo;
    private String raza;
    private String genero;
    private String peso;

    public mascotas() {
        // Constructor vacío requerido por Firebase
    }

    public mascotas(String nombre, String tipo, String raza, String genero, String peso) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.raza = raza;
        this.genero = genero;
        this.peso = peso;
    }

    // Getters y setters para los campos de la mascota
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

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }
}
