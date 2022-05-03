package com.uet.gts.core.model.mapper;

import com.uet.gts.common.dto.core.ClassroomDTO;
import com.uet.gts.common.proto.ClassroomProtobuf;
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

    public static ClassroomProtobuf convert2Protobuf(Classroom classroom) {
        if (classroom == null) return null;

        var studentsProtobuf = classroom.getStudents().stream()
                .map(StudentMapper::convert2Protobuf)
                .collect(Collectors.toList());
        var builder = ClassroomProtobuf.newBuilder()
                .setId(classroom.getId())
                .setCode(classroom.getCode())
                .setMaxStudent(classroom.getSize())
                .addAllStudents(studentsProtobuf);
        if (classroom.getTeacher() != null) {
            builder.setTeacher(TeacherMapper.convert2Protobuf(classroom.getTeacher()));
        }

        return builder.build();
    }
}
