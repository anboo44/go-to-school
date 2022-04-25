package com.uet.gts.core.usecase.impl;

import com.uet.gts.core.model.dto.MessageDTO;
import com.uet.gts.core.model.dto.TeacherDTO;
import com.uet.gts.core.service.TeacherService;
import com.uet.gts.core.usecase.TeacherUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static com.uet.gts.core.common.constant.ErrorList.*;
import static com.uet.gts.core.common.constant.MessageList.SUCCESS;

@Service
public class TeacherUseCaseImpl implements TeacherUseCase {

    @Autowired
    private TeacherService teacherService;

    @Override
    public List<TeacherDTO> findAll() {
        return teacherService
                .findAll()
                .stream()
                .map(TeacherDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public TeacherDTO getById(Integer id) {
        var teacherOpt = teacherService.findById(id);
        if (teacherOpt.isPresent()) {
            return new TeacherDTO(teacherOpt.get());
        } else {
            throw new EntityNotFoundException(TEACHER_NOT_FOUND);
        }
    }

    @Override
    public MessageDTO deleteById(Integer id) {
        var teacherOpt = teacherService.findById(id);
        if (teacherOpt.isPresent()) {
            teacherService.deleteById(id);
            return new MessageDTO(SUCCESS);
        } else {
            throw new EntityNotFoundException(TEACHER_NOT_FOUND);
        }
    }

    @Override
    public MessageDTO create(TeacherDTO dto) {
        teacherService.create(dto.toEntity(false));
        return new MessageDTO(SUCCESS);
    }
}
