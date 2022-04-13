package com.uet.gts.core.model.entity;

import com.uet.gts.core.model.base.DateAudit;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "teacher")
public class Teacher extends DateAudit implements Serializable {
    private static final long serialVersionUID = -1322067681459879159L;

    //==============[ Field Definition ]=============================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    @Length(max = 255)
    private String name;

    @Range(min = 1, max = 150)
    @Column(name = "age", nullable = false)
    private int age;

    @Temporal(TemporalType.DATE)
    @Past
    @Column(name = "work_start_date", nullable = false)
    private Date workStartDate;

    //================[ Method Definition ]============================
    public int getExpYear() {
        ZoneId timeZone = ZoneId.systemDefault();
        var workStartYear = workStartDate.toInstant().atZone(timeZone).toLocalDate().getYear();
        var currentYear = Year.now().getValue();

        return currentYear - workStartYear;
    }
}
