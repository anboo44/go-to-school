package com.uet.gts.report.service.impl;

import com.uet.gts.common.dto.report.ClassroomReportDTO;
import com.uet.gts.common.dto.report.StudentReportDTO;
import com.uet.gts.common.dto.report.TeacherReportDTO;
import com.uet.gts.common.exception.BusinessException;
import com.uet.gts.report.log.ActionLogPublisher;
import com.uet.gts.report.model.ClassroomEvent;
import com.uet.gts.report.model.StudentEvent;
import com.uet.gts.report.model.TeacherEvent;
import com.uet.gts.report.repository.ReportRepository;
import com.uet.gts.report.service.ReportService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ActionLogPublisher actionLogPublisher;

    @Autowired
    private ReportRepository reportRepository;

    @SneakyThrows
    @Override
    public StudentReportDTO makeStudentReport() {
        var f1 = CompletableFuture
                .supplyAsync(() -> reportRepository.findAllStudents())
                .thenApply(List::size);
        var f2 = CompletableFuture
                .supplyAsync(() -> reportRepository.findAllClassrooms())
                .thenApply(v -> v.stream().mapToInt(c -> c.getStudents().size()).sum());
        var allF = CompletableFuture.allOf(f1, f2);

        try {
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
        } catch (Exception e) {
            if (e.getCause() != null) {
                throw e.getCause();
            } else {
                throw new BusinessException(e.getMessage());
            }
        }
    }

    @SneakyThrows
    @Override
    public TeacherReportDTO makeTeacherReport() {
        var f1 = CompletableFuture
                .supplyAsync(() -> reportRepository.findAllTeachers())
                .thenApply(List::size);
        var f2 = CompletableFuture
                .supplyAsync(() -> reportRepository.findAllClassrooms())
                .thenApply(v -> v.stream().filter(st -> st.getTeacher() != null).count());
        var allF = CompletableFuture.allOf(f1, f2);

        try {
            allF.get();
            var teacherTotal = f1.get();
            var assignedTeacherTotal = (long)f2.get();
            var freeTeacherTotal = teacherTotal - assignedTeacherTotal;

            var reportDTO = TeacherReportDTO.builder()
                    .teacherTotal(teacherTotal)
                    .assignedTeacherTotal((int)assignedTeacherTotal)
                    .freeTeacherTotal((int)freeTeacherTotal)
                    .build();
            actionLogPublisher.sendMessage(new TeacherEvent(reportDTO));
            return reportDTO;
        } catch (Exception e) {
            if (e.getCause() != null) {
                throw e.getCause();
            } else {
                throw new BusinessException(e.getMessage());
            }
        }
    }

    @Override
    public ClassroomReportDTO makeClassroomReport() {
        var classrooms = reportRepository.findAllClassrooms();
        var reportDTO = new ClassroomReportDTO(classrooms);
        actionLogPublisher.sendMessage(new ClassroomEvent(reportDTO));
        return reportDTO;
    }

    public ClassroomReportDTO makeClassroomGrpcReport() {
        var classrooms = reportRepository.findAllClassroomsAsProtobuf();
        var reportDTO = new ClassroomReportDTO(new HashSet<>(classrooms));
        actionLogPublisher.sendMessage(new ClassroomEvent(reportDTO));
        return reportDTO;
    }
}
