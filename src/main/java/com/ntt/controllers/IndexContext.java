/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.controllers;

import com.ntt.pojo.NguoiDung;
import com.ntt.service.BaiVietService;
import com.ntt.service.LoaiBaiVietService;
import com.ntt.service.TaiKhoanService;
import java.util.Map;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
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
    @Transactional
    public String index(Model model, NguoiDung nguoidung, Authentication authen) {
        if (authen != null) {
            model.addAttribute("baiviet", this.baivietService.getBaiViet().get(0));
            model.addAttribute("taikhoan", this.taikhoan.getTaiKhoan(authen.getName()).get(0));
        }
        model.addAttribute("baiviet", this.baivietService.getBaiViet());
        model.addAttribute("baiviet_1", this.baivietService.getBaiVietByType("1"));
        model.addAttribute("baiviet_2", this.baivietService.getBaiVietByType("2"));
        return "index";
    }
    
    
}
