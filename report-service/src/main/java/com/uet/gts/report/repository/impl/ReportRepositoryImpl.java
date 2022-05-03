package com.uet.gts.report.repository.impl;

import com.google.protobuf.Empty;
import com.uet.gts.common.dto.ResponseDTO;
import com.uet.gts.common.dto.core.ClassroomDTO;
import com.uet.gts.common.dto.core.StudentDTO;
import com.uet.gts.common.dto.core.TeacherDTO;
import com.uet.gts.common.exception.ExternalApiCallException;
import com.uet.gts.common.proto.ClassroomProtobuf;
import com.uet.gts.common.proto.ClassroomServiceGrpc.ClassroomServiceBlockingStub;
import com.uet.gts.report.config.LaborServiceConfig;
import com.uet.gts.report.repository.ReportRepository;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class ReportRepositoryImpl implements ReportRepository {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LaborServiceConfig laborServiceConfig;

    @GrpcClient("gts-core-service")
    private ClassroomServiceBlockingStub classroomGrpcService;

    private final String STUDENTS_PATH = "/core/api/v1/students";
    private final String TEACHERS_PATH = "/core/api/v1/teachers";
    private final String CLASSROOMS_PATH = "/core/api/v1/classrooms";

    @Override
    public List<StudentDTO> findAllStudents() throws ExternalApiCallException {
        try {
            String studentsApi = laborServiceConfig.getCorePath() + STUDENTS_PATH;

            ResponseEntity<ResponseDTO> response
                    = restTemplate.getForEntity(studentsApi, ResponseDTO.class);
            var studentResponse = (ResponseDTO<List<StudentDTO>>) response.getBody();
            return studentResponse.getData();
        } catch (HttpServerErrorException e) {
            log.error(String.format("Get students api is failed: %s", e.getMessage()));
            throw new ExternalApiCallException(e.getRawStatusCode(), "Get students api is failed");
        }
    }

    @Override
    public List<TeacherDTO> findAllTeachers() throws ExternalApiCallException {
        try {
            String studentsApi = laborServiceConfig.getCorePath() + TEACHERS_PATH;

            ResponseEntity<ResponseDTO> response
                    = restTemplate.getForEntity(studentsApi, ResponseDTO.class);
            var studentResponse = (ResponseDTO<List<TeacherDTO>>) response.getBody();
            return studentResponse.getData();
        } catch (HttpServerErrorException e) {
            log.error(String.format("Get teachers api is failed: %s", e.getMessage()));
            throw new ExternalApiCallException(e.getRawStatusCode(), "Get teachers api is failed");
        }
    }

    @Override
    public List<ClassroomDTO> findAllClassrooms() throws ExternalApiCallException {
        try {
            String studentsApi = laborServiceConfig.getCorePath() + CLASSROOMS_PATH;

            ResponseEntity<ResponseDTO<List<ClassroomDTO>>> response
                    = restTemplate.exchange(
                    studentsApi,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ResponseDTO<List<ClassroomDTO>>>() {}
            );
            var studentResponse = (ResponseDTO<List<ClassroomDTO>>) response.getBody();
            return studentResponse.getData();
        } catch (HttpServerErrorException e) {
            log.error(String.format("Get classrooms api is failed: %s", e.getMessage()));
            throw new ExternalApiCallException(e.getRawStatusCode(), "Get classrooms api is failed");
        }
    }

    public List<ClassroomProtobuf> findAllClassroomsAsProtobuf() {
        var classroomList = new ArrayList<ClassroomProtobuf>();
        var response = this.classroomGrpcService.getList(Empty.newBuilder().build());

        while(response.hasNext()) {
            classroomList.add(response.next());
        }
        return classroomList;
    }
}
