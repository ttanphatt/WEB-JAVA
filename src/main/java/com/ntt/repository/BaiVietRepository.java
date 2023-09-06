/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntt.repository;

import com.ntt.pojo.BaiViet;
import com.ntt.pojo.NguoiDung;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ThanhThuyen
 */
public interface BaiVietRepository {
    List<BaiViet> getBaiViet();
    List<BaiViet> getBaiViet2(String tenBaiViet);
    List<BaiViet> getBaiVietTK(String address, BigDecimal price, Integer soNguoi);
    List<BaiViet> getBaiViet(String tenBaiViet);
    boolean addBaiViet(BaiViet baiviet);
    boolean updateBaiViet(BaiViet baiviet);
    boolean deleteBaiViet(Integer id);
    List<Object> getBaiVietByType (String loaiBViet);
    List<Object> getBaiViet2Type (int loaiBViet);

    //Lấy bài viết theo id bài viết
    BaiViet getBaiVietById(int id);
    //Lấy bài viết theo id ngư�?i dùng
    List<Object> getBaiVietByIdNgDung(NguoiDung idNgDung);
    BaiViet addBaiVietAPI(BaiViet baiviet);
    //THUYỀN MỚI PUSH
    List<BaiViet> getBaiVietByGiaThue(BigDecimal gia);
    List<BaiViet> getBaiVietAll();
    List<BaiViet> getBaiVietGia(Map<String, String> params);
    List<BaiViet> getBaiVietGiaChuaDuyet();
    boolean updateTrangThai(BaiViet idBaiViet);
    void saveBaiViet(BaiViet baiviet);
    void deleteBaiVietByNguoiDung(NguoiDung nguoidung);
    
}
