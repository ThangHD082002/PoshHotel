package com.poshhotel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "fullname", nullable = false, length = 100)
    private String fullname;

    @Column(name = "eid", nullable = false, length = 15)
    private String eid;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "phonenumber", nullable = false, length = 20)
    private String phonenumber;

    @Column(name = "password", nullable = false, length = 300)
    private String password;

    @Lob
    @Column(name = "description", nullable = false)
    private String description = "";

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role = new Role();

}