package com.uet.gts.common.dto.report;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentReportDTO {
    private Integer studentTotal;
    private Integer joinedClassStudentTotal;
    private Integer freeStudentTotal;
}
