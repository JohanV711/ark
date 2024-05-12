package com.cumn.ark.anim;
public class Pet {
    private String nombre;
    private String tipo;
    private String raza;
    private String genero;
    private double peso;

    // Constructor
    public Pet(String nombre, String tipo, String raza, String genero, double peso) {
        this.setNombre(nombre);
        this.setTipo(tipo);
        this.setRaza(raza);
        this.setGenero(genero);
        this.setPeso(peso);
    }

    // Métodos getters y setters
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRaza() {
        return this.raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getPeso() {
        return this.peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}
