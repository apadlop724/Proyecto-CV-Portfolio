package com.example.CV_Portfolio.dto;

public class FormacionDTO {
    private String estudios;
    private String centro;
    private String anioFin; // "En curso" o año de finalización (ej: "2024")

    public FormacionDTO(String estudios, String centro, String anioFin) {
        this.estudios = estudios;
        this.centro = centro;
        this.anioFin = anioFin;
    }

    public String getEstudios() { return estudios; }
    public void setEstudios(String estudios) { this.estudios = estudios; }
    public String getCentro() { return centro; }
    public void setCentro(String centro) { this.centro = centro; }
    public String getAnioFin() { return anioFin; }
    public void setAnioFin(String anioFin) { this.anioFin = anioFin; }
}

