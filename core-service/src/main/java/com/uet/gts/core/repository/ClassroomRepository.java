package com.uet.gts.core.repository;

import com.uet.gts.core.model.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {
    Optional<Classroom> findByCode(String code);
}
