/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ntt.pojo.BaiViet;
import com.ntt.pojo.BinhLuan;
import com.ntt.pojo.Follow;
import com.ntt.pojo.NguoiDung;
import com.ntt.pojo.TrangThaiBaiViet;
import com.ntt.service.BaiVietService;
import com.ntt.service.BinhLuanService;
import com.ntt.service.FollowService;
import com.ntt.service.LoaiBaiVietService;
import com.ntt.service.LoaiTrangThaiService;
import com.ntt.service.NguoiDungService;
import com.ntt.service.TaiKhoanService;
import com.ntt.service.impl.LoaiTrangThaiServiceImpl;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Past;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author ThanhThuyen
 */
@Controller
@Transactional
public class BaiVietController {

    @Autowired
    private BaiVietService baivietService;
    @Autowired
    private LoaiBaiVietService loaiBaiViet;
    @Autowired
    private TaiKhoanService taikhoan;
    @Autowired
    private NguoiDungService ngdungService;
    @Autowired
    private BinhLuanService binhluanService;
    @Autowired
    private FollowService followService;
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private LoaiTrangThaiService loaiTrangThaiService;
    private Long editingId;

    @GetMapping("/dangbai")
    public String list(Model model, Authentication authen, @RequestParam Map<String, String> params) {
        model.addAttribute("nguoidung", this.taikhoan.getTaiKhoan(authen.getName()).get(0));
        model.addAttribute("baiviet_role", this.loaiBaiViet.getLoaiBaiViet());
        model.addAttribute("taikhoan", this.taikhoan.getTaiKhoan(authen.getName()).get(0));
        model.addAttribute("baiviet", new BaiViet());
        return "dangbai";

    }
    
    @ModelAttribute
    public void commonAttr(Model model) {
        model.addAttribute("trangThai_role", this.loaiTrangThaiService.getLoaiTrangThai());
    }

    @RequestMapping("/thtin_bviet")
    public String bvietThTin(Model model, @RequestParam Map<String, String> params, Authentication authen) {
        String errMsg = "";
        int id = Integer.parseInt(params.get("baivietId"));
        BaiViet bv = (BaiViet) this.baivietService.getBaiVietById(id);
        if (authen != null) {
            model.addAttribute("taikhoan", this.taikhoan.getTaiKhoan(authen.getName()).get(0));
            NguoiDung nd = this.taikhoan.getTaiKhoan(authen.getName()).get(0);
            model.addAttribute("follows", this.followService.getFollowsKhachHang(nd).get(0));
            model.addAttribute("followlist", this.followService.getFollowsKhachHang(nd));
            model.addAttribute("nguoidung", this.taikhoan.getTaiKhoan(authen.getName()).get(0));

        }
        model.addAttribute("BaiViet", this.baivietService.getBaiVietById(id));
        model.addAttribute("binhluan", new BinhLuan());
        model.addAttribute("binhluans", this.binhluanService.getBinhLuan(id));
        model.addAttribute("flChuTro", this.followService.getFollowsChuTro(bv.getIdNguoiDung()));
        model.addAttribute("follow", new Follow());

        return "thtin_bviet";
    }

    @PostMapping("/thtin_bviet_bl")
    public String addBinhLuan(Model model, @ModelAttribute(value = "binhluan") BinhLuan binhluan, Authentication authen, @RequestParam Map<String, String> params) {
        String errMsg = "";
        String ms = "";
        int id = Integer.parseInt(params.get("baivietId"));
        if (authen.getName() != null) {
            if (this.binhluanService.addBinhLuan(binhluan) == true) {
                return "forward:/thtin_bviet";
            } else {
                ms = "?ã có l?i xãy ra";
            }
        }
        return "thtin_bviet";
    }

    @PostMapping("/thtin_bviet_fl")
    public String addFollow(Model model, @ModelAttribute(value = "follow") Follow follow, Authentication authen, @RequestParam Map<String, String> params) {
        String ms = "";
        if (authen.getName() != null) {
            if (this.followService.addFollow(follow) == true) {

                return "redirect:/";//redirect:/
            } else {
                ms = "?ã có l?i xãy ra";
            }
        }
        return "index";
    }
    
    @PostMapping("/thtin_bviet_xn")
    public String xacNhan(Model model, Authentication authen, @RequestParam Map<String, String> params) {
        String ms = "";
        int id = Integer.parseInt(params.get("baivietId"));
        BaiViet idBaiViet = (BaiViet) this.baivietService.getBaiVietById(id);
        if (authen != null) {
            if (this.baivietService.updateTrangThai(idBaiViet) == true) {
                return "forward:/thtin_bviet";
            } else {
                ms = "?ã có l?i xãy ra";
            }

        }
        return "index";
    }

    
    @PostMapping("/dangbai")
    public String add(Model model, @ModelAttribute(value = "baiviet") BaiViet baiviet, @RequestParam Map<String, String> params, Authentication authen) {
        String errMsg = "";

//<<<<<<< HEAD
//        if (this.baivietService.addBaiViet(baiviet) == true) {
//
//            return "redirect:/";
//        } else {
//            errMsg = "?ã có l?i xãy ra";
//=======
        if (authen.getName() != null) {

            if (this.baivietService.addBaiViet(baiviet) == true) {

                return "redirect:/";
            } else {
                errMsg = "?ã có l?i xãy ra";
            }
        }

        return "baiviet";
    }

    @RequestMapping("/capnhat")
    public String bvietCapNhatThTin(Model model, @RequestParam Map<String, String> params, Authentication authen
    ) {
        int id = Integer.parseInt(params.get("baivietId").toString());
        if (authen != null) {
            model.addAttribute("taikhoan", this.taikhoan.getTaiKhoan(authen.getName()).get(0));
        }
        model.addAttribute("BaiViet", this.baivietService.getBaiVietById(id));
        return "capnhat";
    }

    @PostMapping("/capnhat")
    public String update(Model model, @ModelAttribute(value = "baiviet") BaiViet baiviet
    ) {
        String errMsg = "";
        System.out.println(baiviet.getId());
        System.out.println(baiviet.getHinhAnh());
        if (this.baivietService.updateBaiViet(baiviet) == true) {
            return "redirect:/canhan";
        } else {
            errMsg = "?ã có l?i xãy ra";
        }

        return "capnhat";
    }

    
    @PostMapping("/thtin_bviet_tt")
    public String capNhatTrangThai(@RequestParam Map<String, String> params, Authentication authen) {
        int id = Integer.parseInt(params.get("baivietId").toString());
        BaiViet baiviet = (BaiViet)this.baivietService.getBaiVietById(id);
        NguoiDung ndChuTro = this.taikhoan.getTaiKhoanId(baiviet.getIdNguoiDung().getId());
        List<Follow> fls = this.followService.getFollowsChuTro(ndChuTro);
        List<TrangThaiBaiViet> lsts = this.loaiTrangThaiService.getLoaiTrangThaiAll();
        for (TrangThaiBaiViet lst : lsts) {
            if (lst.getId() == 1) {
                baiviet.setLoaiTrangThai(lst);
                baivietService.saveBaiViet(baiviet);
                SimpleMailMessage message = new SimpleMailMessage();
                for (Follow fl : fls) {
                    message.setTo(fl.getIdKhachHang().getEmail());
                    message.setSubject("Xong mail r á (Sài Mail API)");
                    message.setText("Nguoi dung ?ã ??ng bai m?i!!! Vào Xem");
                    emailSender.send(message);
                }

                return "redirect:/admin";
            }
        }

        return "thtin_bviet";
    }

    @PostMapping("/thtin_bviet_tuchoi")
    public String capNhatTrangThaiTuChoi(@RequestParam Map<String, String> params) {
        int id = Integer.parseInt(params.get("baivietId").toString());
        System.out.println(id);
        BaiViet baiviet = (BaiViet) this.baivietService.getBaiVietById(id);
        System.out.println(baiviet.getTenBaiViet());
        List<TrangThaiBaiViet> lsts = this.loaiTrangThaiService.getLoaiTrangThaiAll();
        for (TrangThaiBaiViet lst : lsts) {
            if (lst.getId() == 3) {
                baiviet.setLoaiTrangThai(lst);
                baivietService.saveBaiViet(baiviet);
                return "redirect:/admin";
            }
        }

        return "thtin_bviet";
    }




//    
//    @RequestMapping(value = "DeleteBViet/{baivietId}")
//    public String deleteBViet(HttpServletRequest request, Http)


//    @PostMapping("/thtin_bviet_tt")
//    public String capNhatTrangThai(@RequestParam Map<String, String> params, Authentication authen) {
//        int id = Integer.parseInt(params.get("baivietId").toString());
//        BaiViet baiviet = this.baivietService.getBaiVietById(id);
//        NguoiDung ndChuTro = this.taikhoan.getTaiKhoanId(baiviet.getIdNguoiDung().getId());
//        List<Follow> fls = this.followService.getFollowsChuTro(ndChuTro);
//        List<TrangThaiBaiViet> lsts = this.loaiTrangThaiService.getLoaiTrangThaiAll();
//        for (TrangThaiBaiViet lst : lsts) {
//            if (lst.getId() == 1) {
//                baiviet.setLoaiTrangThai(lst);
//                baivietService.saveBaiViet(baiviet);
//                SimpleMailMessage message = new SimpleMailMessage();
//                for (Follow fl : fls) {
//                    message.setTo(fl.getIdKhachHang().getEmail());
//                    message.setSubject("Xong mail r á (Sài Mail API)");
//                    message.setText("Nguoi dung ?ã ??ng bai m?i!!! Vào Xem");
//                    emailSender.send(message);
//                }
//
//                return "redirect:/admin";
//            }
//        }
//
//        return "thtin_bviet";
//    }
//
//    @PostMapping("/thtin_bviet_tuchoi")
//    public String capNhatTrangThaiTuChoi(@RequestParam Map<String, String> params) {
//        int id = Integer.parseInt(params.get("baivietId").toString());
//        System.out.println(id);
//        BaiViet baiviet = this.baivietService.getBaiVietById(id);
//        System.out.println(baiviet.getTenBaiViet());
//        List<TrangThaiBaiViet> lsts = this.loaiTrangThaiService.getLoaiTrangThaiAll();
//        for (TrangThaiBaiViet lst : lsts) {
//            if (lst.getId() == 3) {
//                baiviet.setLoaiTrangThai(lst);
//                baivietService.saveBaiViet(baiviet);
//                return "redirect:/admin";
//            }
//        }
//
//        return "thtin_bviet";
//    }
//
//    @PostMapping("/thtin_bviet_chinhsua")
//    public String chinhsuaBinhLuan(@RequestParam Map<String, String> params, @ModelAttribute("binhLuanForm") BinhLuan binhLuanForm) {
//        BinhLuan binhLuan = binhluanService.getBinhLuanById(binhLuanForm.getId());
//        binhLuan.setNoiDung(binhLuanForm.getNoiDungMoi());
//        binhluanService.saveBinhLuan(binhLuan);
//        return "thtin_bviet";
//    }
//    @PostMapping("/thtin_bviet_edit")
//    public String editBinhLuan(@RequestParam Map<String, String> params) {
//        String idParam = params.get("id");
//        String editedNoiDung = params.get("editedNoiDung");
//
//        if (idParam != null && editedNoiDung != null) {
//            Integer id = Integer.parseInt(idParam);
//            BinhLuan binhLuan = binhluanService.getBinhLuanById(id);
//            
//            if (binhLuan != null) {
//                binhLuan.setNoiDung(editedNoiDung);
//                binhluanService.updateBinhLuan(binhLuan); // G?i service ?? c?p nh?t bình lu?n
//            }
//        }
//        editingId = null; // T?t ch? ?? ch?nh s?a
//
//        return "thtin_bviet"; // ?i?u h??ng v? danh sách bình lu?n
//    }
}
