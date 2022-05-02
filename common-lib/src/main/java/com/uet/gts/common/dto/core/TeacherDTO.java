package com.uet.gts.common.dto.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * Should separate RequestDTO & ResponseDTO.
 * Don't recommend share in a file
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO implements Serializable {
    private static final long serialVersionUID = 3L;

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
