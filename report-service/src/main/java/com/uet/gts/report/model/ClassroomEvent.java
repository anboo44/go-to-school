package com.uet.gts.report.model;

import com.uet.gts.common.dto.report.ClassroomReportDTO;

public class ClassroomEvent {

    private final ClassroomReportDTO dto;

    public ClassroomEvent(ClassroomReportDTO dto) {
        this.dto = dto;
    }

    public ClassroomReportDTO getData() {
        return dto;
    }

    public String toString() {
        return "ClassroomEvent: " + dto.toString();
    }
}
