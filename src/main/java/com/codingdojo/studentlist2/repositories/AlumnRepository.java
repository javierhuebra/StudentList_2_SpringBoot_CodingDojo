package com.codingdojo.studentlist2.repositories;

import com.codingdojo.studentlist2.models.Alumn;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnRepository extends CrudRepository<Alumn,Long> {
    List<Alumn> findAll();

    List<Alumn> findByDormIdIsNull();

    List<Alumn> findAllByDormId(Long dormId);
}
