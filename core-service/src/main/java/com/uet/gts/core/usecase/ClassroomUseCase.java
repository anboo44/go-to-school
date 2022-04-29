package com.uet.gts.core.usecase;

import com.uet.gts.core.model.dto.ClassroomDTO;
import com.uet.gts.core.model.dto.ClassroomMemberDTO;
import com.uet.gts.core.model.dto.MessageDTO;

import java.util.List;

public interface ClassroomUseCase {
    List<ClassroomDTO> findAll();
    MessageDTO create(ClassroomDTO dto);
    MessageDTO addMembers(ClassroomMemberDTO dto, Integer classroomId);
    ClassroomDTO getById(Integer id);
}
