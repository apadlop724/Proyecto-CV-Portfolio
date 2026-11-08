package com.example.CV_Portfolio.controller;

import com.example.CV_Portfolio.dto.ProyectoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class PortfolioController {

    // Datos simulados en memoria (al menos 3 proyectos)
    private final List<ProyectoDTO> todosLosProyectos = List.of(
            new ProyectoDTO(
                    "Your Seville Tour Guide",
                    "2025",
                    "Aplicaciones",
                    "Aplicación móvil desarrollada en React Native con TypeScript diseñada para enriquecer la " +
                            "experiencia turística en la ciudad de Sevilla. La aplicación permite a los usuarios gestionar " +
                            "sus propias rutas turísticas, visualizar mapas interactivos, generación de informes PDF y " +
                            "utilizar herramientas de asistencia inteligente.",
                    "Terminado",
                    List.of("React Native", "Supabase", "TypeScript"),
                    "https://github.com/apadlop724/YOURSEVILLETOURGUIDE.git",
                    "/images/React_Native.png"
            ),
            new ProyectoDTO(
                    "Entrega P3-P4 - Préstamo de Material del Aula Maker",
                    "2026",
                    "Sitios web",
                    "Aplicación web desarrollada con Spring Boot, Spring MVC y Thymeleaf para consultar " +
                            "materiales del aula maker y solicitar préstamos temporales sin persistencia.",
                    "En curso",
                    List.of("Spring Boot", "Spring MVC", "Thymeleaf"),
                    "https://github.com/apadlop724/SpringBoot.git",
                    "/images/Thymeleaf-Spring-Boot.png"
            ),
            new ProyectoDTO(
                    "Servicio Técnico",
                    "2024",
                    "Diseño",
                    "Gestión integral de un servicio técnico de reparación de electrodomésticos. La aplicación " +
                            "facilita la interacción entre los clientes y el servicio técnico, permitiendo un seguimiento " +
                            "detallado de cada reparación desde su registro hasta su cierre.",
                    "Terminado",
                    List.of("Android", " Volley", "PHP"),
                    "https://github.com/apadlop724/SERVICIOTECNICO.git",
                    "/images/Android.png"
            )
    );

    @GetMapping("/portfolio")
    public String verPortfolio(
            @RequestParam(required = false, defaultValue = "Todas") String categoria,
            @RequestParam(required = false) String anio,
            Model model) {

        List<ProyectoDTO> proyectosFiltrados = todosLosProyectos;
        Integer anioNum = null;
        String errorAnio = null;

        // Validación explícita del año recibido como texto
        if (anio != null && !anio.trim().isEmpty()) {
            try {
                anioNum = Integer.parseInt(anio.trim());
            } catch (NumberFormatException e) {
                errorAnio = "Introduce un año válido";
            }
        }

        // Llamada al método de filtrado requerido
        proyectosFiltrados = filtrarProyectos(todosLosProyectos, categoria, anioNum, errorAnio);

        // Pasar datos al modelo
        model.addAttribute("proyectos", proyectosFiltrados);
        model.addAttribute("categoriaSeleccionada", categoria);
        model.addAttribute("anioSeleccionado", anio != null ? anio : "");
        model.addAttribute("errorAnio", errorAnio);
        model.addAttribute("totalResultados", proyectosFiltrados.size());

        return "portfolio";
    }

    /**
     * Método Java exigido para filtrar la colección de proyectos.
     */
    public List<ProyectoDTO> filtrarProyectos(List<ProyectoDTO> lista, String categoria, Integer anioNum, String errorAnio) {
        // Si hay error en el año, devolvemos la lista sin filtrar por año (o vacía según criterio, aquí filtramos solo por categoría si el año falla)
        return lista.stream().filter(p -> {
            boolean coincideCategoria = categoria == null || categoria.equals("Todas") || p.getCategoria().equalsIgnoreCase(categoria);

            boolean coincideAnio = true;
            if (errorAnio == null && anioNum != null) {
                try {
                    int pAnio = Integer.parseInt(p.getAnio());
                    coincideAnio = (pAnio == anioNum);
                } catch (NumberFormatException e) {
                    coincideAnio = false;
                }
            } else if (errorAnio != null) {
                // Si el año introducido no es válido, ignoramos el filtro de año pero mantenemos la categoría
                coincideAnio = true;
            }

            return coincideCategoria && coincideAnio;
        }).collect(Collectors.toList());
    }
}
