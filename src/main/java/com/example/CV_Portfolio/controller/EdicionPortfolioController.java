package com.example.CV_Portfolio.controller;

import com.example.CV_Portfolio.dto.FormularioCvDTO;
import com.example.CV_Portfolio.dto.ProyectoFormDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@Controller
@RequestMapping("/portfolio/editar")
public class EdicionPortfolioController {

    @Value("${portfolio.max-proyectos:5}")
    private int maxProyectos;

    // 1. Mostrar el formulario por primera vez
    @GetMapping
    public String mostrarFormulario(Model model) {
        model.addAttribute("formDTO", new FormularioCvDTO());
        model.addAttribute("maxProyectos", maxProyectos);
        return "portfolio-editar";
    }

    // 2. Procesar acciones de filas (Añadir / Quitar) o Vista Previa
    @PostMapping
    public String procesarFormulario(
            @ModelAttribute("formDTO") FormularioCvDTO formDTO,
            @RequestParam(name = "action", required = false) String action,
            @RequestParam(name = "index", required = false) Integer index,
            Model model) {

        model.addAttribute("maxProyectos", maxProyectos);

        // Si se pulsó el botón de añadir proyecto
        if ("add".equals(action)) {
            gestionarFilas(formDTO, "add", null);
            return "portfolio-editar";
        }

        // Si se pulsó el botón de quitar proyecto en una fila concreta
        if (action != null && action.startsWith("remove_")) {
            int idx = Integer.parseInt(action.split("_")[1]);
            gestionarFilas(formDTO, "remove", idx);
            return "portfolio-editar";
        }

        // Si se pulsó el botón de generar vista previa -> Validamos
        if ("preview".equals(action)) {
            boolean hayErrores = validarDatos(formDTO, model);
            if (hayErrores) {
                model.addAttribute("mensajeErrorGeneral", "Revisa los campos indicados");
                return "portfolio-editar";
            }

            // Limpiamos filas completamente vacías antes de mostrar la vista previa
            formDTO.getProyectos().removeIf(ProyectoFormDTO::estaVacio);

            return "portfolio-preview";
        }

        return "portfolio-editar";
    }

    /**
     * Método Java separado para añadir o quitar filas del formulario.
     */
    public void gestionarFilas(FormularioCvDTO formDTO, String operacion, Integer index) {
        if ("add".equals(operacion)) {
            if (formDTO.getProyectos().size() < maxProyectos) {
                formDTO.getProyectos().add(new ProyectoFormDTO());
            }
        } else if ("remove".equals(operacion) && index != null) {
            // Permitir quitar siempre que queden más de 1 fila
            if (formDTO.getProyectos().size() > 1 && index >= 0 && index < formDTO.getProyectos().size()) {
                formDTO.getProyectos().remove(index.intValue());
            }
        }
    }

    /**
     * Método Java separado para validar los datos personales y los proyectos.
     */
    public boolean validarDatos(FormularioCvDTO formDTO, Model model) {
        boolean error = false;

        if (formDTO.getNombre() == null || formDTO.getNombre().trim().isEmpty()) {
            model.addAttribute("errorNombre", "El nombre completo es obligatorio.");
            error = true;
        }
        if (formDTO.getTitular() == null || formDTO.getTitular().trim().isEmpty()) {
            model.addAttribute("errorTitular", "El titular profesional es obligatorio.");
            error = true;
        }
        if (formDTO.getPresentacion() == null || formDTO.getPresentacion().trim().isEmpty()) {
            model.addAttribute("errorPresentacion", "La presentación es obligatoria.");
            error = true;
        }

        // Validación de correo opcional pero con formato válido
        if (formDTO.getCorreo() != null && !formDTO.getCorreo().trim().isEmpty()) {
            if (!formDTO.getCorreo().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
                model.addAttribute("errorCorreo", "El formato del correo electrónico no es válido.");
                error = true;
            }
        }

        // Validar proyectos (si hay filas completadas parcialmente o límite excedido)
        if (formDTO.getProyectos().size() > maxProyectos) {
            model.addAttribute("errorProyectos", "Se supera el límite máximo de proyectos permitidos.");
            error = true;
        }

        int i = 0;
        for (ProyectoFormDTO p : formDTO.getProyectos()) {
            // Si la fila está totalmente vacía, la omitimos de la validación estricta (el servidor la descarta)
            if (!p.estaVacio()) {
                if (p.getTitulo() == null || p.getTitulo().trim().isEmpty() ||
                        p.getAnio() == null || p.getAnio().trim().isEmpty() ||
                        p.getCategoria() == null || p.getCategoria().isEmpty() ||
                        p.getEstado() == null || p.getEstado().isEmpty() ||
                        p.getDescripcion() == null || p.getDescripcion().trim().isEmpty()) {

                    model.addAttribute("errorProyecto_" + i, "Todos los campos del proyecto son obligatorios si se rellena.");
                    error = true;
                }
            }
            i++;
        }

        return error;
    }
}



