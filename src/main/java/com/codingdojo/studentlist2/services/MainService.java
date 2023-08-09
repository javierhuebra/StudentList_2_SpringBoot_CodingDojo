package com.codingdojo.studentlist2.services;

import com.codingdojo.studentlist2.models.Alumn;
import com.codingdojo.studentlist2.models.Dorm;
import com.codingdojo.studentlist2.repositories.AlumnRepository;
import com.codingdojo.studentlist2.repositories.DormRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MainService {
    private final AlumnRepository alumnRepository;
    private final DormRepository dormRepository;

    public MainService(AlumnRepository alumnRepository, DormRepository dormRepository){
        this.alumnRepository = alumnRepository;
        this.dormRepository = dormRepository;
    }

    //SERVICIOS DE DORMITORIO------------------------------------------

    //Obtener todos los dormitorios
    public List<Dorm> allDorms(){
        return dormRepository.findAll();
    }

    //Crear dormitorio
    public Dorm createDorm(Dorm d){
        return dormRepository.save(d);
    }

    //Buscar dorm por id
    public Dorm buscarDorm(Long id){
        Optional<Dorm> optionalDorm = dormRepository.findById(id);
        return optionalDorm.orElse(null);
    }

    //SERVICIOS DE ALUMNO-----------------------------------------------

    //Obtener todos los alumnos
    public List<Alumn> allAlumns(){
        return alumnRepository.findAll();
    }

    //Obtener alumnos sin dormitorios
    public List<Alumn> alumnsSinDorm(){
        return alumnRepository.findByDormIdIsNull();
    }

    //Obtener alumnos por id de dormitorio
    public List<Alumn> alumnsPorDormitorio(Long dormId){
        return alumnRepository.findAllByDormId(dormId);
    }

    //Crear alumno
    public Alumn createAlumn(Alumn a){
        return alumnRepository.save(a);
    }

    //Buscar alumno por id
    public Alumn buscarAlumn(Long id){
        Optional<Alumn> optionalAlumn = alumnRepository.findById(id);
        return optionalAlumn.orElse(null);
    }


}
