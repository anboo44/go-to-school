package com.uet.gts.common.dto.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO implements Serializable {
    private static final long serialVersionUID = 2L;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Integer id;

    @NotNull(message = "name is required")
    @Size(max = 255, message = "name is too long")
    @Size(min = 1, message = "name cannot be empty")
    private String name;

    @NotNull(message = "gender is required")
    @Pattern(regexp = "^male$|^female$", message = "gender is must be male or female")
    private String gender;

    @NotNull(message = "dateOfBirth is required")
    @Pattern(regexp = "^\\d{4}/(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])$", message = "dateOfBirth must be yyyy/MM/dd")
    private String dateOfBirth;

    @NotNull(message = "parentName is required")
    @Size(max = 255, message = "parentName is too long")
    @Size(min = 1, message = "parentName cannot be empty")
    private String parentName;

    @NotNull(message = "groupType is required")
    @Pattern(regexp = "^normal$|^special$", message = "groupType is must be normal or special")
    private String groupType;
}
