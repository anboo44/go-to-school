package com.uet.gts.core.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.uet.gts.core.model.entity.Teacher;
import lombok.*;

import javax.validation.constraints.*;
import java.text.SimpleDateFormat;

/**
 * Should separate RequestDTO & ResponseDTO.
 * Don't recommend share in a file
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {

    @JsonIgnore
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd");

    //===============[ Constructor ]=============================
    public TeacherDTO(Teacher teacher) {
        this.id = teacher.getId();
        this.name = teacher.getName();
        this.age = teacher.getAge();
        this.expYear = teacher.getExpYear();
    }

    //====[ Can use TeacherMapper.class for flexible ]==========
    //===============[ Converter Method ]=======================
    @SneakyThrows
    public Teacher toEntity(boolean withId) {
        var teacher = new Teacher();

        teacher.setId(withId ? id : null);
        teacher.setName(name);
        teacher.setAge(age);
        teacher.setWorkStartDate(dateFormatter.parse(workStartDate));

        return teacher;
    }

    //===============[ Field Definition ]========================
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Integer id;

    @NotNull(message = "name is required")
    @Size(max = 255, message = "name is too long")
    @Size(min = 1, message = "name cannot be empty")
    private String name;

    @NotNull(message = "age is required")
    @Min(value = 1, message = "age must be from 1")
    @Max(value = 100, message = "age is too big")
    private Integer age;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    @NotNull(message = "dateOfBirth is required")
    @Pattern(regexp = "^\\d{4}/(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])$", message = "workStartDate must be yyyy/MM/dd")
    private String workStartDate;

    private Integer expYear;
}
