/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ntt.pojo.LoaiTaiKhoan;
import com.ntt.pojo.NguoiDung;
import com.ntt.repository.FollowRepository;
import com.ntt.repository.LoaiTaiKhoanRepository;
import com.ntt.repository.NguoiDungRepository;
import com.ntt.service.NguoiDungService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javassist.compiler.TokenId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admins
 */
@Service

public class NguoiDungServiceImpl implements NguoiDungService {

    @Autowired
    private NguoiDungRepository ngdungRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private LoaiTaiKhoanRepository LoaiTaiKhoanRepository;

    @Override
    public List<NguoiDung> getTTNgDung(String tenNguoiDangNhap) {
        return this.ngdungRepo.getTTNguoiDung(tenNguoiDangNhap);
    }

    @Override
    public NguoiDung getTaiKhoanbyTenTK(String username) {
        return this.ngdungRepo.getTaiKhoanbyTenTK(username);
    }

    @Override
    public boolean authUser(String tenTaiKhoan, String matKhau) {
        return this.ngdungRepo.authUser(tenTaiKhoan, matKhau);
    }

    @Override
    public NguoiDung addUser(Map<String, String> params, MultipartFile avatar) {
        NguoiDung u = new NguoiDung();
        u.setTenNguoiDung(params.get("name"));
        u.setEmail(params.get("email"));
        u.setSdt(params.get("phone"));
        u.setTenTaiKhoan(params.get("username"));
        u.setMatKhau(this.passwordEncoder.encode(params.get("password")));

        List<LoaiTaiKhoan> listLTK = this.LoaiTaiKhoanRepository.getLoaiTaiKhoan();
        String usertype = params.get("usertype");
        for (LoaiTaiKhoan c : listLTK) {
            switch (usertype) {
                case "ROLE_CHUTRO":
                    if (c.getId() == 2) {
                        u.setIdLoaiTaiKhoan(c);
                    }   break;
                case "ROLE_KHACHHANG":
                    if (c.getId() == 3) {
                        u.setIdLoaiTaiKhoan(c);
                    }   break;
                default:
                    System.err.println("ERROR");
                    break;
            }

        }

        if (!avatar.isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(avatar.getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                u.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                System.err.println("== ADD TaiKhoan ==" + ex.getMessage());
            }
        }

        this.ngdungRepo.addUser(u);
        return u;
    }

    @Override
    public Object getNgDungById(int id) {
        return this.ngdungRepo.getNgDungById(id);
    }

    @Override
    public NguoiDung doiMatKhau(Map<String, String> params) {
        NguoiDung ng = this.ngdungRepo.getTaiKhoanbyTenTK(params.get("tenTaiKhoan").toString());
        ng.setMatKhau(this.passwordEncoder.encode(params.get("matKhauMoi")));
        this.ngdungRepo.doiMatKhau(ng);
        return ng;
            
    }
}
