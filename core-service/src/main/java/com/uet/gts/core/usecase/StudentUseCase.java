package com.uet.gts.core.usecase;

import com.uet.gts.core.model.dto.MessageDTO;
import com.uet.gts.core.model.dto.ResponseDTO;
import com.uet.gts.core.model.dto.StudentDTO;

public interface StudentUseCase {
    StudentDTO getById(Integer sid);

    MessageDTO deleteById(Integer sid);

    ResponseDTO getByMultiParams(String name, String orderBy, Integer limit, Integer offset);

    MessageDTO create(StudentDTO dto);
}
