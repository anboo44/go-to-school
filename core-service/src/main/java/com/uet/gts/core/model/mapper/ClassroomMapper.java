package com.uet.gts.core.model.mapper;

import com.uet.gts.common.dto.core.ClassroomDTO;
import com.uet.gts.core.model.entity.Classroom;

import java.util.stream.Collectors;

public class ClassroomMapper {

    public static ClassroomDTO convert2DTO(Classroom classroom) {
        if (classroom == null) return null;
        return ClassroomDTO.builder()
                .id(classroom.getId())
                .code(classroom.getCode())
                .teacher(TeacherMapper.convert2DTO(classroom.getTeacher()))
                .students(classroom.getStudents().stream().map(StudentMapper::convert2DTO).collect(Collectors.toList()))
                .maxStudent(classroom.getSize())
                .build();
    }

    public static Classroom convert2Entity(ClassroomDTO dto) {
        var classroom = new Classroom();

        classroom.setCode(dto.getCode());
        classroom.setSize(dto.getMaxStudent());

        return classroom;
    }
}
