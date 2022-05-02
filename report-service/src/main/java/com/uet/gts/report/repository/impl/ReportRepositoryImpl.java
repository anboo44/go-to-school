package com.uet.gts.report.repository.impl;

import com.uet.gts.common.dto.ResponseDTO;
import com.uet.gts.common.dto.core.ClassroomDTO;
import com.uet.gts.common.dto.core.StudentDTO;
import com.uet.gts.common.dto.core.TeacherDTO;
import com.uet.gts.report.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Repository
public class ReportRepositoryImpl implements ReportRepository {

    @Value("${service.gateway.address}")
    private String basePath;

    @Autowired
    private RestTemplate restTemplate;

    private final String STUDENTS_PATH = "/core/api/v1/students";
    private final String TEACHERS_PATH = "/core/api/v1/teachers";
    private final String CLASSROOMS_PATH = "/core/api/v1/classrooms";

    @Override
    public List<StudentDTO> findAllStudents() {
        String studentsApi = basePath + STUDENTS_PATH;

        ResponseEntity<ResponseDTO> response
                = restTemplate.getForEntity(studentsApi, ResponseDTO.class);
        var studentResponse = (ResponseDTO<List<StudentDTO>>) response.getBody();
        return studentResponse.getData();
    }

    @Override
    public List<TeacherDTO> findAllTeachers() {
        String studentsApi = basePath + TEACHERS_PATH;

        ResponseEntity<ResponseDTO> response
                = restTemplate.getForEntity(studentsApi, ResponseDTO.class);
        var studentResponse = (ResponseDTO<List<TeacherDTO>>) response.getBody();
        return studentResponse.getData();
    }

    @Override
    public List<ClassroomDTO> findAllClassrooms() {
        String studentsApi = basePath + CLASSROOMS_PATH;

        ResponseEntity<ResponseDTO<List<ClassroomDTO>>> response
                = restTemplate.exchange(
                        studentsApi,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<ResponseDTO<List<ClassroomDTO>>>() {}
                );
        var studentResponse = (ResponseDTO<List<ClassroomDTO>>) response.getBody();
        return studentResponse.getData();
    }
}
