package com.uet.gts.core.model.entity;

import com.uet.gts.core.model.base.DateAudit;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "classroom")
public class Classroom  extends DateAudit implements Serializable {
    private static final long serialVersionUID = -1322009681459879159L;

    //==============[ Field Definition ]=============================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "code", nullable = false, unique = true)
    @Length(max = 5)
    private String code;

    @Column(name = "classroom_size", nullable = false)
    @Range(min = 0, max = 45)
    private int size;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;

    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("id asc")
    @Builder.Default
    private Set<Student> students = new HashSet<Student>();
}
