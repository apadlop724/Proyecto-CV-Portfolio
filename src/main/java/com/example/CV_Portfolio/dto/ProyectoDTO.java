package com.example.CV_Portfolio.dto;

import java.util.List;

public class ProyectoDTO {
    private String titulo;
    private String anio;
    private String categoria; // "Aplicaciones", "Sitios web" o "Diseño"
    private String descripcion;
    private String estado; // "En curso" o "Terminado"
    private List<String> tecnologias;
    private String enlace;
    private String imagen;

    public ProyectoDTO(String titulo, String anio, String categoria, String descripcion, String estado, List<String> tecnologias, String enlace, String imagen) {
        this.titulo = titulo;
        this.anio = anio;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.estado = estado;
        this.tecnologias = tecnologias;
        this.enlace = enlace;
        this.imagen = imagen;
    }

    // Getters y Setters
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getAnio() { return anio; }
    public void setAnio(String anio) { this.anio = anio; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public List<String> getTecnologias() { return tecnologias; }
    public void setTecnologias(List<String> tecnologias) { this.tecnologias = tecnologias; }
    public String getEnlace() { return enlace; }
    public void setEnlace(String enlace) { this.enlace = enlace; }
    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }
}

