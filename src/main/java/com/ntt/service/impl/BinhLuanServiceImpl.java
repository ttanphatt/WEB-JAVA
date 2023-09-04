/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.service.impl;

import com.ntt.pojo.BaiViet;
import com.ntt.pojo.BinhLuan;
import com.ntt.pojo.NguoiDung;
import com.ntt.repository.BaiVietRepository;
import com.ntt.repository.BinhLuanRepository;
import com.ntt.repository.TaiKhoanRepository;
import com.ntt.service.BinhLuanService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ThanhThuyen
 */
@Service
public class BinhLuanServiceImpl implements BinhLuanService{
  @Autowired
    private BinhLuanRepository binhluan;
    @Autowired
    private TaiKhoanRepository taikhoan;
    @Autowired
    private BaiVietRepository baiviet;
    @Override
    public List<BinhLuan> getBinhLuan(int idBaiViet) {
        return this.binhluan.getBinhLuan(idBaiViet);
    }

    @Override
    public boolean addBinhLuan(BinhLuan binhluan) {
        NguoiDung b = this.taikhoan.getTaiKhoan(binhluan.getTenNguoiDangBai()).get(0);
        binhluan.setIdNguoiDung(b);
        BaiViet v= (BaiViet) this.baiviet.getBaiVietById(binhluan.getIdBaiVietBinhLuan());
        binhluan.setIdBaiViet(v);
        
        
       return this.binhluan.addBinhLuan(binhluan);
    }

    @Override
    public List<Object> getBinhLuanByBV(int bvId) {
        return this.binhluan.getBinhLuanByBV(bvId);
    }
    
}
