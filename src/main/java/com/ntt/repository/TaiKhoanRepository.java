/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntt.repository;

import com.ntt.pojo.LoaiTaiKhoan;
import com.ntt.pojo.NguoiDung;
import java.util.List;

/**
 *
 * @author ThanhThuyen
 */
public interface TaiKhoanRepository {
    boolean addTaiKhoan(NguoiDung nguoidung);
    List<NguoiDung> getTaiKhoan(String username);
    LoaiTaiKhoan getLoaiTaiKhoan (String tenLoaiTaiKhoan);
    NguoiDung getTaiKhoanbyTenTK(String tenTK);
    NguoiDung getTaiKhoanId(int id);
}
