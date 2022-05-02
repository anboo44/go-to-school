package com.uet.gts.report.model;

import com.uet.gts.common.dto.report.TeacherReportDTO;

public class TeacherEvent {
    private final TeacherReportDTO dto;

    public TeacherEvent(TeacherReportDTO dto) {
        this.dto = dto;
    }

    public TeacherReportDTO getData() {
        return dto;
    }

    public String toString() {
        return "TeacherEvent: " + dto.toString();
    }
}
