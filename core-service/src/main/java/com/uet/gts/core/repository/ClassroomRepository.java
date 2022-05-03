package com.uet.gts.core.repository;

import com.uet.gts.core.model.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// View this site to get detail custom jpa
// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.repositories
@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {
    Optional<Classroom> findByCode(String code);
    List<Classroom> findAllByOrderByIdAsc();
    List<Classroom> findTop2ByOrderByIdAsc();
}
