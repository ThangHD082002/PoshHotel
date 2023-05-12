package com.poshhotel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "fullname", nullable = false, length = 100)
    private String fullname;


    @Column(name = "phonenumber", nullable = false, length = 20)
    private String phonenumber;

    @Column(name = "email")
    private Integer email;

    @OneToMany(mappedBy = "customer")
    private Set<Booking> bookings = new LinkedHashSet<>();

}