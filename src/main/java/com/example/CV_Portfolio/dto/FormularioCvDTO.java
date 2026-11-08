package com.example.CV_Portfolio.dto;

import java.util.ArrayList;
import java.util.List;

public class FormularioCvDTO {
    private String nombre;
    private String titular;
    private String presentacion;
    private String ciudad;
    private String correo;

    // Lista de proyectos del formulario (inicialmente con una fila vacía)
    private List<ProyectoFormDTO> proyectos = new ArrayList<>();

    public FormularioCvDTO() {
        proyectos.add(new ProyectoFormDTO()); // Fila vacía inicial
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTitular() { return titular; }
    public void setTitular(String titular) { this.titular = titular; }
    public String getPresentacion() { return presentacion; }
    public void setPresentacion(String presentacion) { this.presentacion = presentacion; }
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public List<ProyectoFormDTO> getProyectos() { return proyectos; }
    public void setProyectos(List<ProyectoFormDTO> proyectos) { this.proyectos = proyectos; }
}

