package com.uet.gts.common.dto.report;

import com.uet.gts.common.proto.ClassroomProtobuf;
import lombok.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClassroomReportDTO {
    private Integer classroomTotal;
    private List<ClassroomDTO> classroom;

    public ClassroomReportDTO(List<com.uet.gts.common.dto.core.ClassroomDTO> classrooms) {
        this.classroomTotal = classrooms.size();
        this.classroom = classrooms.stream().map(v -> {
            var teacherName = v.getTeacher() != null ? v.getTeacher().getName(): null;
            return ClassroomDTO.builder()
                    .code(v.getCode())
                    .teacherName(teacherName)
                    .currentStudentSize(v.getStudents().size())
                    .build();
        }).collect(Collectors.toList());
    }

    public ClassroomReportDTO(Set<ClassroomProtobuf> classrooms) {
        this.classroomTotal = classrooms.size();
        this.classroom = classrooms.stream().map(v -> {
            var teacherName = v.getTeacher().getName();
            return ClassroomDTO.builder()
                    .code(v.getCode())
                    .teacherName(teacherName.isEmpty() ? null: teacherName)
                    .currentStudentSize(v.getStudentsCount())
                    .build();
        }).collect(Collectors.toList());
    }
}
