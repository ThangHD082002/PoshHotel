package com.poshhotel.repository;

import java.util.List;

import com.poshhotel.entity.BookingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRecordRepository extends JpaRepository<BookingRecord, Long> {

}
