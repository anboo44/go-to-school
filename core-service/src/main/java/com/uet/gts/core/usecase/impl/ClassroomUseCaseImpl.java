package com.uet.gts.core.usecase.impl;

import com.uet.gts.core.common.exception.ex.ExistedEntityException;
import com.uet.gts.core.model.dto.ClassroomDTO;
import com.uet.gts.core.model.dto.MessageDTO;
import com.uet.gts.core.service.ClassroomService;
import com.uet.gts.core.usecase.ClassroomUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.uet.gts.core.common.constant.ErrorList.*;
import static com.uet.gts.core.common.constant.MessageList.SUCCESS;

@Service
public class ClassroomUseCaseImpl implements ClassroomUseCase {

    @Autowired
    private ClassroomService classroomService;

    @Override
    public List<ClassroomDTO> findAll() {
        return classroomService
                .findAll()
                .stream()
                .map(ClassroomDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public MessageDTO create(ClassroomDTO dto) {
        var classroomOpt = classroomService.findByCode(dto.getCode());
        if (classroomOpt.isPresent()) {
            throw new ExistedEntityException(CLASSROOM_CODE_EXISTED);
        }

        classroomService.create(dto.toEntity());
        return new MessageDTO(SUCCESS);
    }
}
