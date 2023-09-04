/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.controllers;

import com.ntt.pojo.NguoiDung;
import com.ntt.service.BaiVietService;
import com.ntt.service.NguoiDungService;
import com.ntt.service.TaiKhoanService;
import java.util.Map;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ThanhThuyen
 */
@Controller
@Transactional
public class DangNhapController {

    @Autowired
    private TaiKhoanService taikhoan;
    @Autowired
    private LocalSessionFactoryBean facrory;
    @Autowired
    private BaiVietService baivietService;
    @Autowired
    private BaiVietService baiviet;
    @Autowired
    private NguoiDungService nguoidung;

    @RequestMapping("/dangnhap")
    public String dangNhap() {
        return "dangnhap";
    }

    @RequestMapping("/canhan")
//    @Transactional
    public String dangNhapCaNhan(Model model, Authentication authen) {
        model.addAttribute("taikhoan", new NguoiDung());
        if (authen != null) {
            UserDetails user = this.taikhoan.loadUserByUsername(authen.getName());
            NguoiDung u = this.taikhoan.getTaiKhoanbyTenTK(user.getUsername());
            model.addAttribute("taikhoan", u);
            model.addAttribute("baiviet", this.baiviet.getBaiVietByIdNgDung(u));
        }
        return "canhan";
    }


    @RequestMapping("/admin")
    public String dangNhapAdmin(Model model, Authentication authen) {
        model.addAttribute("taikhoan", this.taikhoan.getTaiKhoan(authen.getName()).get(0));
        return "admin";
    }
    
    @GetMapping("/doimatkhau")
    public String doiMatKhau(Model model, @RequestParam Map<String, String> params, Authentication authen){
//        model.addAttribute("ngdung", this.nguoidung.doiMatKhau(params));
        model.addAttribute("ngdung", new NguoiDung());
        if (authen != null) {
            UserDetails user = this.taikhoan.loadUserByUsername(authen.getName());
            NguoiDung u = this.taikhoan.getTaiKhoanbyTenTK(user.getUsername());
            model.addAttribute("taikhoan", u);
            model.addAttribute("ngdung", this.nguoidung.doiMatKhau(params));
        }
        return "doimatkhau";
    }
}
