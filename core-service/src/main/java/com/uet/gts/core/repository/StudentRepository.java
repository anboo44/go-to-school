package com.uet.gts.core.repository;

import com.uet.gts.core.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("select count(e) from Student e where e.id = ?1")
    Integer countById(Integer id);

    @Query("select count(e) from Student e")
    Integer countAll();

    @Query("select count(e) from Student e where e.name = ?1")
    Integer countByName(String name);
}
