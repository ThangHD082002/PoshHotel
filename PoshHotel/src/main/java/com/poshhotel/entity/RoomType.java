package com.poshhotel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "room_type")
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "type", nullable = false, length = 250)
    private String type;

    @Column(name = "price", nullable = false, precision = 15, scale = 2)
    private BigDecimal price;

    @Column(name = "numspeople", nullable = false)
    private Integer numspeople;

    @Lob
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "roomType")
    private Set<BookingRecord> bookingRecords = new LinkedHashSet<>();

    @OneToMany(mappedBy = "roomType")
    private Set<Room> rooms = new LinkedHashSet<>();

}