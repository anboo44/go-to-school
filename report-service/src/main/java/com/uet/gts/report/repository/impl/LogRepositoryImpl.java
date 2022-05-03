package com.uet.gts.report.repository.impl;

import com.uet.gts.common.dto.report.ClassroomReportDTO;
import com.uet.gts.common.dto.report.StudentReportDTO;
import com.uet.gts.common.dto.report.TeacherReportDTO;
import com.uet.gts.report.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LogRepositoryImpl implements LogRepository {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private final static String LOG_TOPIC_KAFKA = "quickstart-events";

    @Override
    public void logStudentReport(StudentReportDTO report) {
        kafkaTemplate.send(LOG_TOPIC_KAFKA, report);
    }

    @Override
    public void logTeacherReport(TeacherReportDTO report) {
        kafkaTemplate.send(LOG_TOPIC_KAFKA, report);
    }

    @Override
    public void logClassroomReport(ClassroomReportDTO report) {
        kafkaTemplate.send(LOG_TOPIC_KAFKA, report);
    }
}
