package com.uet.gts.common.dto.core;

import com.uet.gts.common.annotation.SetIdGreaterThan0;
import lombok.*;

import javax.validation.constraints.Min;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomMemberDTO {

    @Min(value = 1, message = "TeacherId must be greater than 0")
    private Integer teacherId;

    @SetIdGreaterThan0(message = "All studentIds must be greater than 0")
    private Set<Integer> studentIds;
}
