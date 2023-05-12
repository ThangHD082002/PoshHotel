package com.poshhotel.service.impl;

import com.poshhotel.entity.Admin;
import com.poshhotel.entity.Role;
import com.poshhotel.hash.PBKDFTwoHash;
import com.poshhotel.repository.AdminRepository;
import com.poshhotel.repository.RoleRepository;
import com.poshhotel.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;
    private final PBKDFTwoHash pbkdfTwoHash;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, RoleRepository roleRepository, PBKDFTwoHash pbkdfTwoHash) {
        this.adminRepository = adminRepository;
        this.roleRepository = roleRepository;
        this.pbkdfTwoHash = pbkdfTwoHash;
    }

    @Override
    public void saveAdmin(Admin admin) throws NoSuchAlgorithmException, InvalidKeySpecException {
        admin.setPassword(pbkdfTwoHash.hashPassword(admin.getPassword()));
        adminRepository.save(admin);
    }



    @Override
    public List<Admin> findAllByRoleId(Long role_id) {
        Role r = roleRepository.getReferenceById(role_id);
        return r.getAdmins().stream().toList();
    }

    @Override
    public Admin login(String eid, String password) {

        Admin a = adminRepository.findByEid(eid).orElse(null);
        try {
            if(a != null && pbkdfTwoHash.ValidatePassword(password, a.getPassword())) {
                return a;
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("An unexpected error occured, please try again later!");
        }
        return null;
    }
}
