package com.uet.gts.report.controller;

import com.uet.gts.common.dto.ResponseDTO;
import com.uet.gts.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/report", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/student")
    public ResponseDTO getStudentReport() {
        return new ResponseDTO(
                reportService.makeStudentReport()
        );
    }

    @GetMapping("/teacher")
    public ResponseDTO getTeacherReport() {
        return new ResponseDTO(
                reportService.makeTeacherReport()
        );
    }

    @GetMapping("/classroom")
    public ResponseDTO getClassroomReport(@RequestParam("type") Optional<String> typeOpt) {
        return new ResponseDTO(
                typeOpt.map(t -> t.equals("grpc") ? reportService.makeClassroomGrpcReport(): null)
                       .orElse(reportService.makeClassroomReport())
        );
    }
}
