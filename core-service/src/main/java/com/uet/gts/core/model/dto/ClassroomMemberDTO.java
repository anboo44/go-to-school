package com.uet.gts.core.model.dto;

import com.uet.gts.core.common.util.annotation.GreaterThan0;
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

    @GreaterThan0(message = "All studentIds must be greater than 0")
    private Set<Integer> studentIds;
}
