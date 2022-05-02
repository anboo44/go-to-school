package com.uet.gts.report.model;

import com.uet.gts.common.dto.report.StudentReportDTO;

public class StudentEvent {
    private final StudentReportDTO dto;

    public StudentEvent(StudentReportDTO dto) {
        this.dto = dto;
    }

    public StudentReportDTO getData() {
        return dto;
    }

    public String toString() {
        return "StudentEvent: " + dto.toString();
    }
}
