package com.uet.gts.report.service;

import com.uet.gts.common.dto.report.*;

public interface ReportService {
    StudentReportDTO makeStudentReport();
    TeacherReportDTO makeTeacherReport();
    ClassroomReportDTO makeClassroomReport();
}
