package com.uet.gts.core.controller;

import com.uet.gts.core.model.dto.ResponseDTO;
import com.uet.gts.core.model.dto.StudentDTO;
import com.uet.gts.core.usecase.StudentUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/students",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class StudentController {

    @Autowired
    private StudentUseCase studentUseCase;

    @GetMapping("")
    public ResponseDTO list(String name, String orderBy, Integer limit, Integer offset) {
        return studentUseCase.getByMultiParams(name, orderBy, limit, offset);
    }

    @GetMapping("/{id}")
    public ResponseDTO getById(@PathVariable("id") Integer sid) {
        return new ResponseDTO(
                studentUseCase.getById(sid)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseDTO deleteById(@PathVariable("id") Integer sid) {
        return new ResponseDTO(
                studentUseCase.deleteById(sid)
        );
    }

    @PostMapping("")
    public ResponseDTO create(@RequestBody @Valid StudentDTO dto) {
        return new ResponseDTO(
                studentUseCase.create(dto)
        );
    }
}
