package com.poshhotel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "room_num", nullable = false, length = 4)
    private String roomNum;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "room_type", nullable = false)
    private RoomType roomType;

    @Column(name = "floor", nullable = false)
    private Integer floor;

    @Lob
    @Column(name = "status", nullable = false)
    private String status;

    @Lob
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "room")
    private Set<BookingRecord> bookingRecords = new LinkedHashSet<>();

}