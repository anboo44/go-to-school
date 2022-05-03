package com.uet.gts.core.model.mapper;

import com.uet.gts.common.constant.DateFormatter;
import com.uet.gts.common.dto.core.TeacherDTO;
import com.uet.gts.common.proto.TeacherProtobuf;
import com.uet.gts.core.model.entity.Teacher;

import java.text.ParseException;

public class TeacherMapper {

    public static TeacherDTO convert2DTO(Teacher teacher) {
        if (teacher == null) return null;
        return TeacherDTO.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .age(teacher.getAge())
                .expYear(teacher.getExpYear())
                .build();
    }

    public static Teacher convert2Entity(TeacherDTO dto) throws ParseException {
        var teacher = new Teacher();

        teacher.setName(dto.getName());
        teacher.setAge(dto.getAge());
        teacher.setWorkStartDate(DateFormatter.DATE.parse(dto.getWorkStartDate()));

        return teacher;
    }

    public static TeacherProtobuf convert2Protobuf(Teacher teacher) {
        if (teacher == null) return null;
        return TeacherProtobuf.newBuilder()
                .setId(teacher.getId())
                .setName(teacher.getName())
                .setAge(teacher.getAge())
                .setExpYear(teacher.getExpYear())
                .build();
    }
}
