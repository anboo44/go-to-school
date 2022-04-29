package com.uet.gts.core.service;

import com.uet.gts.core.model.entity.Classroom;
import com.uet.gts.core.model.entity.Student;
import com.uet.gts.core.model.entity.Teacher;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ClassroomService {
    List<Classroom> findAll();

    void create(Classroom classroom);

    Optional<Classroom> findByCode(String code);

    Optional<Classroom> findById(Integer id);

    void addMembers(Classroom classroom, Teacher teacher, Set<Student> students);
}
