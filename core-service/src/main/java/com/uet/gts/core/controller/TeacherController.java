package com.uet.gts.core.controller;

import com.uet.gts.core.model.dto.ResponseDTO;
import com.uet.gts.core.usecase.TeacherUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/teachers",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class TeacherController {

    @Autowired
    private TeacherUseCase teacherUseCase;

    @GetMapping("")
    public ResponseDTO list() {
        return new ResponseDTO(
          teacherUseCase.findAll()
        );
    }

    @PostMapping("")
    public ResponseDTO create() {
        return new ResponseDTO(
                null
        );
    }

    @GetMapping("/{id}")
    public ResponseDTO getById(@PathVariable("id") Integer id) {
        return new ResponseDTO(
                teacherUseCase.getById(id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseDTO deleteById(@PathVariable("id") Integer id) {
        return new ResponseDTO(
                teacherUseCase.deleteById(id)
        );
    }
}