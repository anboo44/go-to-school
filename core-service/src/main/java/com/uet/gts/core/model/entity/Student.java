package com.uet.gts.core.model.entity;

import com.uet.gts.core.model.base.DateAudit;
import com.uet.gts.core.model.vo.Gender;
import com.uet.gts.core.model.vo.GroupType;
import com.uet.gts.core.model.vo.converter.GenderConverter;
import com.uet.gts.core.model.vo.converter.GroupTypeConverter;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "student")
public class Student extends DateAudit implements Serializable {
    private static final long serialVersionUID = -1862067681459879159L;

    //==============[ Field Definition ]=============================
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    @Length(max = 255)
    private String name;

    @Column(name = "gender", length = 10, nullable = false)
    @Convert(converter = GenderConverter.class)
    private Gender gender;

    @Column(name = "date_of_birth", nullable = false)
    @Temporal(TemporalType.DATE)
    @Past
    private Date dateOfBirth;

    @Column(name = "parent_name", nullable = false)
    @Length(max = 255)
    private String parentName;

    @Column(name = "group_type", length = 10, nullable = false)
    @Convert(converter = GroupTypeConverter.class)
    private GroupType groupType;
}
