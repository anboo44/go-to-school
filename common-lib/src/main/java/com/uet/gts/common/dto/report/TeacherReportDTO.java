package com.uet.gts.common.dto.report;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeacherReportDTO {
    private Integer teacherTotal;
    private Integer assignedTeacherTotal;
    private Integer freeTeacherTotal;
}
