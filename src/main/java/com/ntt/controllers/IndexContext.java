/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.controllers;

import com.ntt.pojo.NguoiDung;
import com.ntt.service.BaiVietService;
import com.ntt.service.LoaiBaiVietService;
import com.ntt.service.TaiKhoanService;
import java.math.BigDecimal;

import java.util.HashMap;
import java.util.List;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ThanhThuyen
 */
@Controller
@Transactional
public class IndexContext {

    @Autowired
    private TaiKhoanService taikhoan;
    @Autowired
    private BaiVietService baivietService;

    @RequestMapping("/")
    public String index(Model model, Authentication authen) {

        model.addAttribute("baiviet", this.baivietService.getBaiVietAll());
        if (authen != null) {
            UserDetails user = this.taikhoan.loadUserByUsername(authen.getName());
            NguoiDung u = this.taikhoan.getTaiKhoanbyTenTK(user.getUsername());
            model.addAttribute("taikhoan", u);
        }
        return "index";

    }

    @RequestMapping("/timkiem")
    public String timKiem(Model model, NguoiDung nguoidung, Authentication authen, @RequestParam(name = "address", required = false) String address,
            @RequestParam(name = "price", required = false) BigDecimal price,
            @RequestParam(name = "soNguoi", required = false) Integer soNguoi) {

        if (authen != null) {
            model.addAttribute("taikhoan", this.taikhoan.getTaiKhoan(authen.getName()).get(0));
            if (address != null || price != null || soNguoi != null) {
                model.addAttribute("baiviet", this.baivietService.getBaiVietTK(address, price, soNguoi));
            } else {
                model.addAttribute("baiviet", this.baivietService.getBaiVietAll());
            }
        }

        model.addAttribute("baiviet_1", this.baivietService.getBaiVietByType("1"));
        model.addAttribute("baiviet_2", this.baivietService.getBaiVietByType("2"));
        return "index";
    }

    @PostMapping("/")
    public String index(Model model, Authentication authen, @RequestParam("gia") int gia) {
        BigDecimal giaBigDecimal = new BigDecimal(gia);
        model.addAttribute("baiviet_1", this.baivietService.getBaiVietByGiaThue(giaBigDecimal));
//        model.addAttribute("baiviet_2", this.baivietService.getBaiVietByGiaThue(giaBigDecimal));
        UserDetails user = this.taikhoan.loadUserByUsername(authen.getName());
        NguoiDung u = this.taikhoan.getTaiKhoanbyTenTK(user.getUsername());
        model.addAttribute("taikhoan", u);
        return "index";
    }
    
    @RequestMapping("/bando")
    public String bando(Model model, NguoiDung nguoidung, Authentication authen) {

        model.addAttribute("dsBaiViet", this.baivietService.getBaiVietAll());
        UserDetails user = this.taikhoan.loadUserByUsername(authen.getName());
        NguoiDung u = this.taikhoan.getTaiKhoanbyTenTK(user.getUsername());
        model.addAttribute("taikhoan", u);
        return "bando";
    }
}
