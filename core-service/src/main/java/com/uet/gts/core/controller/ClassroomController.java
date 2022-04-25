package com.uet.gts.core.controller;

import com.uet.gts.core.model.dto.ClassroomDTO;
import com.uet.gts.core.model.dto.ResponseDTO;
import com.uet.gts.core.usecase.ClassroomUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping(path = "/api/v1/classrooms",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class ClassroomController {

    @Autowired
    private ClassroomUseCase classroomUseCase;

    @GetMapping("")
    public ResponseDTO list() {
        return new ResponseDTO(
                classroomUseCase.findAll()
        );
    }

    @PostMapping("")
    public ResponseDTO create(@RequestBody @Valid ClassroomDTO dto) {
        return new ResponseDTO(
                classroomUseCase.create(dto)
        );
    }
}
