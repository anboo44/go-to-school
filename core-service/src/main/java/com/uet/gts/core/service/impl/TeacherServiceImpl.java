package com.uet.gts.core.service.impl;

import com.uet.gts.core.model.entity.Teacher;
import com.uet.gts.core.repository.TeacherRepository;
import com.uet.gts.core.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Optional<Teacher> findById(Integer id) {
        return teacherRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        teacherRepository.deleteById(id);
    }
}
