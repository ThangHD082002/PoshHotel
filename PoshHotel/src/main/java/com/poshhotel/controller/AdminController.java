package com.poshhotel.controller;

import com.poshhotel.entity.Admin;
import com.poshhotel.repository.AdminRepository;
import com.poshhotel.repository.RoleRepository;
import com.poshhotel.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class AdminController {

    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminRepository adminRepository, AdminService adminService, RoleRepository roleRepository) {
        this.adminRepository = adminRepository;
        this.adminService = adminService;
        this.roleRepository = roleRepository;
    }

    // Xác thực đăng nhập
    @PostMapping("/admin/login/auth")
    public String authLogin(@ModelAttribute("admin") Admin admin, HttpServletRequest request, Model model) {

        Admin a = adminService.login(admin.getEid(), admin.getPassword());
        if (a == null) {
            return "redirect:/admin/login";
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", a);
        return "redirect:/admin";

    }

    // Vào trang đăng nhập
    @GetMapping("/admin/login")
    public String login(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (!checkNullSession(session)) {
            return "redirect:/admin";
        }
        return "admin/login";
    }

    // Trang chủ Admin
    @GetMapping({"/admin/index", "/admin"})
    public String home(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(checkNullSession(session)) {
            return "redirect:/admin/login";
        }
        return "admin/index";
    }

    // Đăng xuất
    @PostMapping("/admin/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (!checkNullSession(session)) {
            session.invalidate();
        }
        return "redirect:/admin/login";
    }

    // Xem danh sách nhân viên
    @GetMapping("/admin/staffs")
    public String staffListView(Model model, HttpServletRequest request) {

        if (checkNullSession(request.getSession())) {
            return "redirect:/admin/login";
        }

        List<Admin> adminList = adminRepository.findAll();
        model.addAttribute("adminList", adminList);
        return "admin/staff/list";
    }

    // Vào trang thêm staff
    @GetMapping("/admin/staff/add")
    public String staffAddView(HttpServletRequest request, Model model) {

        if (checkNullSession(request.getSession())) {
            return "redirect:/admin/login";
        }
        model.addAttribute("roles", roleRepository.findAll());
        return "admin/staff/create";
    }

    // Lưu Staff
    @PostMapping("/admin/staff/add/save")
    public String staffAdd(HttpServletRequest request, @RequestParam("role") String role, @ModelAttribute("admin") Admin admin) {
        if (checkNullSession(request.getSession())) {
            return "redirect:/admin/login";
        }
        admin.setRole(roleRepository.getReferenceById(Long.valueOf(role)));
        adminRepository.save(admin);
        return "redirect:/admin/staffs";
    }


    @GetMapping("/admin/info")
    public String staffInfo(HttpServletRequest request) {

        if (checkNullSession(request.getSession())) {
            return "redirect:/admin/login";
        }

        return "admin/information";
    }

    @PostMapping("/admin/info/save")
    public String staffInfoSave(HttpServletRequest request) {

        if (checkNullSession(request.getSession())) {
            return "redirect:/admin/login";
        }

        return "redirect:/admin/info";
    }

    @GetMapping("/admin/bookings")
    public String bookingList(HttpServletRequest request) {

        if (checkNullSession(request.getSession())) {
            return "redirect:/admin/login";
        }

        return "admin/listBooking";
    }

    @GetMapping("/admin/customers")
    public String customersList(HttpServletRequest request) {

        if (checkNullSession(request.getSession())) {
            return "redirect:/admin/login";
        }

        return "admin/listCustomer";
    }

    @GetMapping("/admin/rooms")
    public String roomList(HttpServletRequest request) {

        if (checkNullSession(request.getSession())) {
            return "redirect:/admin/login";
        }

        return "admin/listRoom";
    }

    @ModelAttribute(value = "admin")
    public Admin newAdmin() {
        return new Admin();
    }

    public boolean checkNullSession(HttpSession session) {
        return (session == null) || (session.getAttribute("user") == null);
    }

}
