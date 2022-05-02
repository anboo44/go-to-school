package com.uet.gts.core.model.mapper;

import com.uet.gts.common.constant.DateFormatter;
import com.uet.gts.common.dto.core.StudentDTO;
import com.uet.gts.core.model.entity.Student;
import com.uet.gts.core.model.vo.Gender;
import com.uet.gts.core.model.vo.GroupType;

import java.text.ParseException;

public class StudentMapper {

    public static StudentDTO convert2DTO(Student student) {
        if (student == null) return null;
        return StudentDTO.builder()
                .id(student.getId())
                .name(student.getName())
                .gender(student.getGender().getCode())
                .dateOfBirth(DateFormatter.DATE.format(student.getDateOfBirth()))
                .parentName(student.getParentName())
                .groupType(student.getGroupType().toString().toLowerCase())
                .build();
    }

    public static Student convert2Entity(StudentDTO dto) throws ParseException {
        var student = new Student();

        student.setName(dto.getName());
        student.setGender(Gender.fromCode(dto.getGender()));
        student.setDateOfBirth(DateFormatter.DATE.parse(dto.getDateOfBirth()));
        student.setParentName(dto.getParentName());
        student.setGroupType(GroupType.fromValue(dto.getGroupType()));

        return student;
    }
}
