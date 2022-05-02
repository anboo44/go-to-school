package com.uet.gts.report.service.impl;

import com.uet.gts.common.dto.report.ClassroomReportDTO;
import com.uet.gts.common.dto.report.StudentReportDTO;
import com.uet.gts.common.dto.report.TeacherReportDTO;
import com.uet.gts.report.log.ActionLogPublisher;
import com.uet.gts.report.model.StudentEvent;
import com.uet.gts.report.repository.ReportRepository;
import com.uet.gts.report.service.ReportService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ActionLogPublisher actionLogPublisher;

    @Autowired
    private ReportRepository reportRepository;

    @Override
    @SneakyThrows
    public StudentReportDTO makeStudentReport() {
        var f1 = CompletableFuture
                .supplyAsync(() -> reportRepository.findAllStudents())
                .thenApply(List::size);
        var f2 = CompletableFuture
                .supplyAsync(() -> reportRepository.findAllClassrooms())
                .thenApply(v -> v.stream().mapToInt(c -> c.getStudents().size()).sum());
        var allF = CompletableFuture.allOf(f1, f2);
        allF.get();

        var studentTotal = f1.get();
        var joinedClassStudentTotal = f2.get();
        var freeStudentTotal = studentTotal - joinedClassStudentTotal;

        var reportDTO = StudentReportDTO.builder()
                .studentTotal(studentTotal)
                .joinedClassStudentTotal(joinedClassStudentTotal)
                .freeStudentTotal(freeStudentTotal)
                .build();

        actionLogPublisher.sendMessage(new StudentEvent(reportDTO));
        return reportDTO;
    }

    @Override
    public TeacherReportDTO makeTeacherReport() {
        return null;
    }

    @Override
    public ClassroomReportDTO makeClassroomReport() {
        return null;
    }
}
