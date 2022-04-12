package com.uet.gts.core.service;

import com.uet.gts.core.model.entity.Student;

import java.util.List;
import java.util.Optional;


public interface StudentService {
    Optional<Student> getById(Integer sid);

    Boolean isExistedById(Integer sid);

    void deleteById(Integer sid);

    Integer countWithName(String name);

    List<Student> getByMultiParams(String name, String orderBy, Integer limit, Integer offset);
}
