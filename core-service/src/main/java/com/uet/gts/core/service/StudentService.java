package com.uet.gts.core.service;

import com.uet.gts.core.model.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface StudentService {
    Optional<Student> getById(Integer sid);

    Boolean isExistedById(Integer sid);

    void deleteById(Integer sid);

    Integer countWithName(String name);

    void create(Student student);

    List<Student> getByMultiParams(String name, String orderBy, Integer limit, Integer offset);

    Page<Student> getByMultiParamsV2(String name, String orderBy, Integer limit, Integer offset);

    List<Student> findByIds(Set<Integer> ids);
}
