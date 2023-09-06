/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntt.service;

import com.ntt.pojo.BaiViet;
import com.ntt.pojo.NguoiDung;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ThanhThuyen
 */
public interface BaiVietService  {
    List<BaiViet> getBaiViet();
    List<BaiViet> getBaiViet2(String tenBaiViet);
    BaiViet loadBaiViet(String tenBaiViet);
    boolean addBaiViet(BaiViet baiviet);
    boolean updateBaiViet(BaiViet baiviet);
    boolean deleteBaiViet(int id);
    BaiViet getBaiVietById(int id);
    List<Object> getBaiVietByType(String loaiBViet);
    List<Object> getBaiViet2Type (int loaiBViet);
    List<Object> getBaiVietByIdNgDung(NguoiDung idNgDung);
    //PHAT
    BaiViet addBaiVietAPI(Map<String, String> params, MultipartFile hinhanh);
    
    //THUYEN
    List<BaiViet> getBaiVietByGiaThue(BigDecimal gia);
    List<BaiViet> getBaiVietAll();
    List<BaiViet> getBaiVietGia(Map<String, String> params);
    List<BaiViet> getBaiVietGiaChuaDuyet();
    boolean updateTrangThai(BaiViet idBaiViet);
    void saveBaiViet(BaiViet baiviet);
    void deleteBaiVietByNguoiDung(NguoiDung nguoidung);
    List<BaiViet> getBaiVietTK(String address, BigDecimal price, Integer soNguoi);


}
