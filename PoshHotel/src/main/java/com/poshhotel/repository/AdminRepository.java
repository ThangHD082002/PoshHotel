package com.poshhotel.repository;

import com.poshhotel.entity.Admin;
import com.poshhotel.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByEid(String eid);

    Admin findByEidAndPassword(String eid, String password);
}
