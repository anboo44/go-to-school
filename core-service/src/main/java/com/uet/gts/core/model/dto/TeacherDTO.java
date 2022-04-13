package com.uet.gts.core.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.uet.gts.core.model.entity.Teacher;
import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {

    //===============[ Constructor ]=============================
    public TeacherDTO(Teacher teacher) {
        this.id = teacher.getId();
        this.name = teacher.getName();
        this.age = teacher.getAge();
        this.expYear = teacher.getExpYear();
    }

    //===============[ Field Definition ]========================
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Integer id;

    @NotNull(message = "name is required")
    @NotBlank(message = "name cannot be empty")
    @Size(min = 1, max = 255, message = "name is too long")
    private String name;

    @NotNull(message = "age is required")
    @Min(value = 1, message = "age must be from 1")
    @Max(value = 150, message = "age is too big")
    private Integer age;

    private Integer expYear;
}
