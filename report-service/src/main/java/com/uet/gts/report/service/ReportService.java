package com.uet.gts.report.service;

import com.uet.gts.common.dto.report.ClassroomReportDTO;
import com.uet.gts.common.dto.report.StudentReportDTO;
import com.uet.gts.common.dto.report.TeacherReportDTO;

public interface ReportService {
    StudentReportDTO makeStudentReport();
    TeacherReportDTO makeTeacherReport();
    ClassroomReportDTO makeClassroomReport();
    ClassroomReportDTO makeClassroomGrpcReport();
}
