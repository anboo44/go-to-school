package com.uet.gts.core.usecase;

import com.uet.gts.common.dto.MessageDTO;
import com.uet.gts.common.dto.core.TeacherDTO;

import java.util.List;

public interface TeacherUseCase {
    List<TeacherDTO> findAll();

    TeacherDTO getById(Integer id);

    MessageDTO deleteById(Integer id);

    MessageDTO create(TeacherDTO dto);
}
