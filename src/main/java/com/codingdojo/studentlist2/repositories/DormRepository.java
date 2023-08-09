package com.codingdojo.studentlist2.repositories;

import com.codingdojo.studentlist2.models.Dorm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DormRepository extends CrudRepository<Dorm, Long> {
    List<Dorm> findAll();
}
