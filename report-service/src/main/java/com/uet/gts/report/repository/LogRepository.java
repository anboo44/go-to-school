package com.uet.gts.report.repository;

import com.uet.gts.common.dto.report.ClassroomReportDTO;
import com.uet.gts.common.dto.report.StudentReportDTO;
import com.uet.gts.common.dto.report.TeacherReportDTO;

public interface LogRepository {
    void logStudentReport(StudentReportDTO report);
    void logTeacherReport(TeacherReportDTO report);
    void logClassroomReport(ClassroomReportDTO report);
}
