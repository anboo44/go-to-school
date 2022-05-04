package com.uet.gts.auth.model.entity;

import com.uet.gts.auth.model.enumeration.UserStatus;
import com.uet.gts.auth.model.enumeration.converter.UserStatusConverter;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = -1322009681459879159L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Convert(converter = UserStatusConverter.class)
    @Column(name = "user_status", nullable = false )
    @Builder.Default
    private UserStatus userStatus = UserStatus.ACTIVATED;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private UserAuthority userAuthority;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date created_at;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", insertable = false)
    private Date updated_at;
}
