package com.uet.gts.core.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.uet.gts.core.model.entity.Student;
import com.uet.gts.core.model.vo.Gender;
import com.uet.gts.core.model.vo.GroupType;
import lombok.*;
import javax.validation.constraints.*;
import java.text.SimpleDateFormat;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    @JsonIgnore
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy/MM/dd");

    //===============[ Constructor ]=============================
    public StudentDTO(Student student) {
        this.id          = student.getId();
        this.name        = student.getName();
        this.gender      = student.getGender().getCode();
        this.dateOfBirth = dateFormatter.format(student.getDateOfBirth());
        this.parentName  = student.getParentName();
        this.groupType   = student.getGroupType().toString().toLowerCase();
    }

    //===============[ Converter Method ]=======================
    @SneakyThrows
    public Student toEntity(boolean withId) {
        var student = new Student();

        student.setId(withId ? id : null);
        student.setName(name);
        student.setGender(Gender.fromCode(gender));
        student.setDateOfBirth(dateFormatter.parse(dateOfBirth));
        student.setParentName(parentName);
        student.setGroupType(GroupType.fromValue(groupType));

        return student;
    }

    //===============[ Field Definition ]========================
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
