package com.example.CV_Portfolio.controller;

import com.example.CV_Portfolio.dto.CompetenciaDTO;
import com.example.CV_Portfolio.dto.ExperienciaDTO;
import com.example.CV_Portfolio.dto.FormacionDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CurriculumController {

    @Value("${cv.nombre}")
    private String nombreCv;

    @GetMapping("/curriculum")
    public String verCurriculum(Model model) {
        // Datos de perfil
        model.addAttribute("nombre", nombreCv);
        model.addAttribute("titular", "Estudiante de Desarrollo de Aplicaciones Web");
        model.addAttribute("presentacion", "Apasionado por el desarrollo web y la administración de sistemas. Enfocado en la creación de aplicaciones robustas con Spring Boot y Linux.");

        // Contacto (si es null o vacío, Thymeleaf ocultará la sección entera)
        model.addAttribute("contacto", "antonio@gmail.com");

        // Formación
        List<FormacionDTO> formacion = List.of(
                new FormacionDTO("Grado Superior en DAW", "IES Velázquez", "En curso"),
                new FormacionDTO("Grado Superior en DAM", "IES Velázquez", "2026")
        );
        model.addAttribute("formacion", formacion);

        // Experiencia (Vacía para cumplir con "En busca de mi primera experiencia")
        List<ExperienciaDTO> experiencia = new ArrayList<>();
        model.addAttribute("experiencia", experiencia);

        // Habilidades (mínimo 4)
        List<CompetenciaDTO> habilidades = List.of(
                new CompetenciaDTO("Java & Spring Boot", "medio"),
                new CompetenciaDTO("Linux / Proxmox", "medio"),
                new CompetenciaDTO("HTML & CSS", "alto"),
                new CompetenciaDTO("SQL / MySQL", "alto")
        );
        model.addAttribute("habilidades", habilidades);

        // Idiomas (mínimo 2, incluido inglés)
        List<CompetenciaDTO> idiomas = List.of(
                new CompetenciaDTO("Español", "alto"),
                new CompetenciaDTO("Inglés", "bajo")
        );
        model.addAttribute("idiomas", idiomas);

        return "curriculum";
    }
}
