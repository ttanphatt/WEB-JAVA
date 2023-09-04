/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntt.service;

import com.ntt.pojo.BaiViet;
import com.ntt.pojo.NguoiDung;
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
    Object getBaiVietById(int id);
    List<Object> getBaiVietByType(String loaiBViet);
    List<Object> getBaiViet2Type (int loaiBViet);
    List<Object> getBaiVietByIdNgDung(NguoiDung idNgDung);
    BaiViet addBaiVietAPI(Map<String, String> params
            , MultipartFile hinhanh);

}

