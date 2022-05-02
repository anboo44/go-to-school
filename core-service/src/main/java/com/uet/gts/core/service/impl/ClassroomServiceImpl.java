package com.uet.gts.core.service.impl;

import com.uet.gts.common.exception.BusinessException;
import com.uet.gts.core.model.entity.Classroom;
import com.uet.gts.core.model.entity.Student;
import com.uet.gts.core.model.entity.Teacher;
import com.uet.gts.core.repository.ClassroomRepository;
import com.uet.gts.core.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Override
    public Optional<Classroom> findById(Integer id) {
        return classroomRepository.findById(id);
    }

    @Override
    public void addMembers(Classroom classroom, Teacher teacher, Set<Student> students) {
        if (teacher != null && classroom.getTeacher() != null)
            throw new BusinessException("Classroom have another teacher");
        else
            teacher = classroom.getTeacher();
        var existedClassroomStudentIdList = students.stream()
                .filter(st -> st.getClassroom() != null)
                .map(Student::getId)
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        if (!existedClassroomStudentIdList.isEmpty()) {
            throw new BusinessException("Student joined in classroom with id: [" + existedClassroomStudentIdList + "]");
        }
        students.forEach(st -> st.setClassroom(classroom));

        classroom.setTeacher(teacher);
        classroom.setStudents(students);
        classroomRepository.save(classroom);
    }
}
