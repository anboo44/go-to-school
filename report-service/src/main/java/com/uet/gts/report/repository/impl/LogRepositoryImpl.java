package com.uet.gts.report.repository.impl;

import com.uet.gts.common.dto.report.ClassroomReportDTO;
import com.uet.gts.common.dto.report.StudentReportDTO;
import com.uet.gts.common.dto.report.TeacherReportDTO;
import com.uet.gts.report.repository.LogRepository;
import org.springframework.stereotype.Repository;

@Repository
public class LogRepositoryImpl implements LogRepository {
    @Override
    public void logStudentReport(StudentReportDTO report) {
        
    }

    @Override
    public void logTeacherReport(TeacherReportDTO report) {

    }

    @Override
    public void logClassroomReport(ClassroomReportDTO report) {

    }
}
