package com.uet.gts.common.dto.report;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
class ClassroomDTO {
    private String code;
    private String teacherName;
    private Integer currentStudentSize;
}
