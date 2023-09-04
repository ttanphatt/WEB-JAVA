/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntt.service;

import com.ntt.pojo.BinhLuan;
import java.util.List;

/**
 *
 * @author ThanhThuyen
 */
public interface BinhLuanService {
    List<BinhLuan> getBinhLuan(int idBaiViet);
    boolean addBinhLuan(BinhLuan binhluan);
    //PHAT
    List<Object> getBinhLuanByBV(int bvId);

}
