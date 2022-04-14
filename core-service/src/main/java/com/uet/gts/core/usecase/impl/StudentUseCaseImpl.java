package com.uet.gts.core.usecase.impl;

import com.uet.gts.core.model.dto.*;
import com.uet.gts.core.service.StudentService;
import com.uet.gts.core.usecase.StudentUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.stream.Collectors;
import static com.uet.gts.core.common.constant.ErrorList.*;
import static com.uet.gts.core.common.constant.MessageList.*;

@Service
public class StudentUseCaseImpl implements StudentUseCase {

    @Autowired
    private StudentService studentService;

    @Override
    public StudentDTO getById(Integer sid) {
        var studentOpt = studentService.getById(sid);
        if (studentOpt.isEmpty()) {
            throw new EntityNotFoundException(STUDENT_NOT_FOUND);
        }

        return new StudentDTO(studentOpt.get());
    }

    @Override
    public MessageDTO deleteById(Integer sid) {
        var isExisted = studentService.isExistedById(sid);
        if (isExisted) {
            studentService.deleteById(sid);
            return new MessageDTO(SUCCESS);
        } else {
            throw new EntityNotFoundException(STUDENT_NOT_FOUND);
        }
    }

    @Override
    public ResponseDTO getByMultiParams(String name, String orderBy, Integer limit, Integer offset) {
        if (limit == null) limit = Integer.MAX_VALUE;
        if (offset == null) offset = 1;

        /* Handle and build response */
//        Integer totalStudents = studentService.countWithName(name);
//        var data = studentService.getByMultiParams(name, orderBy, limit, offset)
//                .stream().map(StudentDTO::new).collect(Collectors.toList());
        var pageData = studentService.getByMultiParamsV2(name, orderBy, limit, offset - 1);
        var data = pageData.getContent().stream().map(StudentDTO::new).collect(Collectors.toList());

        var meta = buildMeta(pageData.getTotalElements(), data.size(), limit, offset);
        return ResponseDTO.builder()
                .data(data)
                .meta(meta)
                .build();
    }

    @Override
    public MessageDTO create(StudentDTO dto) {
        studentService.create(dto.toEntity(false));
        return new MessageDTO(SUCCESS);
    }

    private MetaDTO buildMeta(long totalElements, int size, Integer limit, Integer offset) {
        float pageDiv = limit != null && limit < totalElements ? (float)totalElements / limit : 1f;
        int totalPages = pageDiv == (int)pageDiv ? (int)pageDiv : (int)(pageDiv + 1);

        int pageNumber = offset == null ? 1 : offset;
        if (pageNumber > totalPages) throw new IllegalArgumentException(INVALID_OFFSET);

        boolean isFirstPage = pageNumber == 1;
        boolean isLastPage = pageNumber == totalPages;

        var pagination = PaginationDTO.builder()
                .totalPages(totalPages)
                .totalElements((int)totalElements)
                .size(size)
                .pageNumber(pageNumber)
                .first(isFirstPage)
                .last(isLastPage)
                .build();

        return new MetaDTO((pagination));
    }
}
