package com.uet.gts.core.service;

import com.uet.gts.core.model.entity.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    List<Teacher> findAll();

    Optional<Teacher> findById(Integer id);

    void deleteById(Integer id);

    void create(Teacher teacher);
}
