package com.uet.gts.core.service.impl;

import com.uet.gts.core.model.entity.Classroom;
import com.uet.gts.core.repository.ClassroomRepository;
import com.uet.gts.core.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Override
    public List<Classroom> findAll() {
        return classroomRepository.findAll();
    }

    @Override
    public void create(Classroom classroom) {
        classroomRepository.save(classroom);
    }

    @Override
    public Optional<Classroom> findByCode(String code) {
        return classroomRepository.findByCode(code);
    }
}
