package com.uet.gts.common.dto.report;

import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomReportDTO {
    private Integer classroomTotal;
    private List<ClassroomDTO> classroom;
}
