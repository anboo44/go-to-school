package com.uet.gts.core.usecase;

import com.uet.gts.common.dto.MessageDTO;
import com.uet.gts.common.dto.core.ClassroomDTO;
import com.uet.gts.common.dto.core.ClassroomMemberDTO;
import com.uet.gts.common.proto.ClassroomProtobuf;

import java.util.List;

public interface ClassroomUseCase {
    List<ClassroomDTO> findAll();
    MessageDTO create(ClassroomDTO dto);
    MessageDTO addMembers(ClassroomMemberDTO dto, Integer classroomId);
    ClassroomDTO getById(Integer id);
    List<ClassroomProtobuf> findAllAsProto();
}
