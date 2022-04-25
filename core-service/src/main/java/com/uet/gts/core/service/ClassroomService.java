package com.uet.gts.core.service;

import com.uet.gts.core.model.entity.Classroom;

import java.util.List;
import java.util.Optional;

public interface ClassroomService {
    List<Classroom> findAll();

    void create(Classroom classroom);

    Optional<Classroom> findByCode(String code);
}
