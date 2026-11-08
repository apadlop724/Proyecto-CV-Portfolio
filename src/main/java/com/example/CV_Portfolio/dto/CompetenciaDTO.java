package com.example.CV_Portfolio.dto;

public class CompetenciaDTO {
    private String nombre;
    private String nivel; // "alto", "medio" o "bajo"

    public CompetenciaDTO(String nombre, String nivel) {
        this.nombre = nombre;
        this.nivel = nivel;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }
}

