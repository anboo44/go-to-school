package com.uet.gts.core.usecase.impl;

import com.uet.gts.core.common.exception.ex.ExistedEntityException;
import com.uet.gts.core.common.exception.ex.NotFoundEntityException;
import com.uet.gts.core.model.dto.ClassroomDTO;
import com.uet.gts.core.model.dto.ClassroomMemberDTO;
import com.uet.gts.core.model.dto.MessageDTO;
import com.uet.gts.core.model.entity.Student;
import com.uet.gts.core.service.ClassroomService;
import com.uet.gts.core.service.StudentService;
import com.uet.gts.core.service.TeacherService;
import com.uet.gts.core.usecase.ClassroomUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.uet.gts.core.common.constant.ErrorList.*;
import static com.uet.gts.core.common.constant.MessageList.SUCCESS;

@Service
public class ClassroomUseCaseImpl implements ClassroomUseCase {

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @Override
    public List<ClassroomDTO> findAll() {
        return classroomService
                .findAll()
                .stream()
                .map(ClassroomDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public MessageDTO create(ClassroomDTO dto) {
        var classroomOpt = classroomService.findByCode(dto.getCode());
        if (classroomOpt.isPresent()) {
            throw new ExistedEntityException(CLASSROOM_CODE_EXISTED);
        }

        classroomService.create(dto.toEntity());
        return new MessageDTO(SUCCESS);
    }

    @Override
    public MessageDTO addMembers(ClassroomMemberDTO dto, Integer classroomId) {
        var classroomOpt = classroomService.findById(classroomId);
        if (classroomOpt.isEmpty())
            throw new NotFoundEntityException("Not found classroom with id: " + classroomId);

        var teacherIdOpt = Optional.ofNullable(dto.getTeacherId());
        var teacher = teacherIdOpt.map(id -> {
            var teacherOpt = teacherService.findById(id);
            if (teacherOpt.isEmpty())
                throw new NotFoundEntityException("Not found teacher with id: " + id);
            return teacherOpt.get();
        }).orElse(null);

        var jdbcStudents = new HashSet<>(studentService.findByIds(dto.getStudentIds()));
        var jdbcStudentIds = jdbcStudents.stream().map(Student::getId).collect(Collectors.toSet());
        var notFoundStudentIdList = dto.getStudentIds().stream()
                .filter(v -> !jdbcStudentIds.contains(v))
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        if (!notFoundStudentIdList.isEmpty())
            throw new NotFoundEntityException("Not found student with ids: [" + notFoundStudentIdList + "]");

        classroomService.addMembers(classroomOpt.get(), teacher, jdbcStudents);
        return new MessageDTO(SUCCESS);
    }

    @Override
    public ClassroomDTO getById(Integer id) {
        var classroomOpt = classroomService.findById(id);
        if (classroomOpt.isEmpty())
            throw new NotFoundEntityException("Not found classroom with id" + id);

        return new ClassroomDTO(classroomOpt.get());
    }
}
