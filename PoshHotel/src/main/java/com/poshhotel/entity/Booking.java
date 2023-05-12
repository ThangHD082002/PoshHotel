package com.poshhotel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Lob
    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "start", nullable = false)
    private Instant start;

    @Column(name = "end", nullable = false)
    private Instant end;

    @Lob
    @Column(name = "note")
    private String note;

    @Column(name = "total_price", nullable = false, precision = 15, scale = 2)
    private BigDecimal totalPrice;

    @OneToMany(mappedBy = "booking")
    private Set<BookingRecord> bookingRecords = new LinkedHashSet<>();

}