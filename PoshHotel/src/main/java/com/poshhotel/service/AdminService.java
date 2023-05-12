package com.poshhotel.service;

import com.poshhotel.entity.Admin;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@Service
public interface AdminService {

    void saveAdmin(Admin admin) throws NoSuchAlgorithmException, InvalidKeySpecException;

    List<Admin> findAllByRoleId(Long role_id);

    Admin login(String username, String password);
}
