package com.example.CV_Portfolio.dto;

public class ProyectoFormDTO {
    private String titulo;
    private String anio;
    private String categoria; // "Aplicaciones", "Sitios web" o "Diseño"
    private String estado;    // "En curso" o "Terminado"
    private String descripcion;

    // Método de apoyo para comprobar si la fila está completamente vacía
    public boolean estaVacio() {
        return (titulo == null || titulo.trim().isEmpty()) &&
                (anio == null || anio.trim().isEmpty()) &&
                (categoria == null || categoria.isEmpty()) &&
                (estado == null || estado.isEmpty()) &&
                (descripcion == null || descripcion.trim().isEmpty());
    }

    // Getters y Setters
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getAnio() { return anio; }
    public void setAnio(String anio) { this.anio = anio; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
