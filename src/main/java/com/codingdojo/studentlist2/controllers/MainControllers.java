package com.codingdojo.studentlist2.controllers;

import com.codingdojo.studentlist2.models.Alumn;
import com.codingdojo.studentlist2.models.Dorm;
import com.codingdojo.studentlist2.services.MainService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainControllers {
    private final MainService mainService;

    public MainControllers(MainService mainService){
        this.mainService = mainService;
    }

    //Controlador DORMITORIOS--------------------
    @GetMapping("/")
    public String renderIndex(Model modelView){
       List<Dorm> dorms = mainService.allDorms();
       modelView.addAttribute("dormitorios", dorms);
       return "index";
    }

    @GetMapping("/dorms/new")
    public String renderFormDorm(@ModelAttribute("dorm") Dorm dorm){
        return "dormnew";
    }

    @PostMapping("/dorms/new")
    public String newDormPost(@Valid @ModelAttribute("dorm") Dorm dorm, BindingResult result){
        if(result.hasErrors()){
            System.out.println("error");
            return "redirect:/dorms/new";
        }else{
            mainService.createDorm(dorm);
            return "redirect:/";
        }
    }

    @GetMapping("/dorms/{id}")
    public String detalleDorm(@PathVariable("id") Long id, Model model){
        model.addAttribute("dormitorio", mainService.buscarDorm(id));
        model.addAttribute("alumnos", mainService.alumnsSinDorm());
        model.addAttribute("alumnosPorDorm", mainService.alumnsPorDormitorio(id));
        return "detaildorm";
    }

    

    //Controlador ALUMNOS---------------------------------
    @GetMapping("/alumns/new")
    public String renderFormAlumno(@ModelAttribute("alumn") Alumn alumn){
        return "alumnnew";
    }

    @PostMapping("/alumns/new")
    public String newAlumnPost(@Valid @ModelAttribute("alumn") Alumn alumn, BindingResult result){
        if(result.hasErrors()){
            return "redirect:/alumns/new";
        }else{
            mainService.createAlumn(alumn);
            return "redirect:/";
        }
    }

    @PostMapping("/alumns/asignar/{id}")
    public String postAsignar(@PathVariable("id") Long id, @RequestParam("alumno") Alumn alumno){//El request param trae el alumno del select
        System.out.println(id);
        System.out.println(alumno);
        Dorm dormitorioPorId = mainService.buscarDorm(id);
        // List<Alumn> alumnos = dormitorioPorId.getAlumns();
        // alumnos.add(alumno);
        // dormitorioPorId.setAlumns(alumnos);
        // System.out.println(dormitorioPorId.getAlumns());
        //  mainService.createDorm(dormitorioPorId);
        alumno.setDorm(dormitorioPorId);
        mainService.createAlumn(alumno);
        return "redirect:/dorms/"+id;
    }

    @PostMapping("/alumns/desvincular")
    public String desvincularAlumno(@RequestParam("alumnoId") Long alumnoId,@RequestParam("dormitorioId") Long dormitorioId){
        Alumn alumno = mainService.buscarAlumn(alumnoId);
        System.out.println(alumno.getDorm().getId());
        alumno.setDorm(null);
        mainService.createAlumn(alumno);
        // Realiza las operaciones necesarias para guardar los cambios

        return "redirect:/dorms/"+dormitorioId;
    }

}
