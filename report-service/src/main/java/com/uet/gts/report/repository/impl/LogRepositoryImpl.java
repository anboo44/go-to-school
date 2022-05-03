package com.uet.gts.report.repository.impl;

import com.uet.gts.common.dto.KafkaEventDTO;
import com.uet.gts.common.dto.report.ClassroomReportDTO;
import com.uet.gts.common.dto.report.StudentReportDTO;
import com.uet.gts.common.dto.report.TeacherReportDTO;
import com.uet.gts.report.repository.LogRepository;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Repository;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Repository
public class LogRepositoryImpl implements LogRepository {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private NewTopic topic;

    @Override
    public void logStudentReport(StudentReportDTO report) {
        this.sendMessage(new KafkaEventDTO(KafkaEventDTO.EventType.STUDENT, report));
    }

    @Override
    public void logTeacherReport(TeacherReportDTO report) {
        this.sendMessage(new KafkaEventDTO(KafkaEventDTO.EventType.TEACHER, report));
    }

    @Override
    public void logClassroomReport(ClassroomReportDTO report) {
        this.sendMessage(new KafkaEventDTO(KafkaEventDTO.EventType.CLASSROOM, report));
    }

    private void sendMessage(KafkaEventDTO dto) {
        kafkaTemplate.send(topic.name(), dto).addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                System.out.println("Sent message=[" + dto.getData().toString() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=[" + dto.getData().toString() + "] due to : " + ex.getMessage());
            }
        });
    }
}
