package com.uet.gts.common.dto.core;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomDTO implements Serializable {
    private static final long serialVersionUID = 3L;

    private Integer id;

    @NotNull(message = "code is required")
    @Size(max = 5, message = "code is too long")
    @Size(min = 1, message = "code cannot be empty")
    private String code;

    @NotNull(message = "maxStudent is required")
    @Min(value = 1, message = "maxStudent must be from 1")
    @Max(value = 45, message = "maxStudent is 45")
    private Integer maxStudent;

    private TeacherDTO teacher;

    private List<StudentDTO> students;
}
