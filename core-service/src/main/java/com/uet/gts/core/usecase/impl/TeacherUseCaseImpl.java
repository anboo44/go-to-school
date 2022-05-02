package com.uet.gts.core.usecase.impl;

import com.uet.gts.common.dto.MessageDTO;
import com.uet.gts.common.dto.core.TeacherDTO;
import com.uet.gts.core.model.mapper.TeacherMapper;
import com.uet.gts.core.service.TeacherService;
import com.uet.gts.core.usecase.TeacherUseCase;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import static com.uet.gts.common.constant.ErrorList.TEACHER_NOT_FOUND;
import static com.uet.gts.common.constant.MessageList.SUCCESS;

@Service
public class TeacherUseCaseImpl implements TeacherUseCase {

    @Autowired
    private TeacherService teacherService;

    @Override
    public List<TeacherDTO> findAll() {
        return teacherService
                .findAll()
                .stream()
                .map(TeacherMapper::convert2DTO)
                .collect(Collectors.toList());
    }

    @Override
    public TeacherDTO getById(Integer id) {
        var teacherOpt = teacherService.findById(id);
        if (teacherOpt.isPresent()) {
            return TeacherMapper.convert2DTO(teacherOpt.get());
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
    @SneakyThrows
    public MessageDTO create(TeacherDTO dto) {
        teacherService.create(TeacherMapper.convert2Entity(dto));
        return new MessageDTO(SUCCESS);
    }
}
