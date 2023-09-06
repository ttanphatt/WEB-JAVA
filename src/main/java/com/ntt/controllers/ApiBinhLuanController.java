/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.controllers;

import com.ntt.pojo.BinhLuan;
import com.ntt.service.BinhLuanService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sun.jvm.hotspot.oops.ObjArray;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author ThanhThuyen >>>>>>> 3920839a004168c57b3fefe5f804b02063b2013d
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiBinhLuanController {

    @Autowired
    private BinhLuanService binhLuanService;

    @DeleteMapping("/thtin_bvietBinhLuan/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBinhLuanwpr(@PathVariable(value = "id") int id) {
        this.binhLuanService.deleteBinhLuan(id);
    }    
    
    @GetMapping("/listBinhLuanByBV/{id}")
    public ResponseEntity<List<Object>> listComment(@PathVariable(value = "id") int id) {
        List<Object> binhluan = this.binhLuanService.getBinhLuanByBV(id);
        return new ResponseEntity<>(binhluan, HttpStatus.OK);
    }
   
    
    @PostMapping("/binhluan/")
    public ResponseEntity<Object> addComment(@RequestBody BinhLuan binhluan){ 
        return new ResponseEntity<>(this.binhLuanService.addOrUpdateBinhLuan(binhluan),
                HttpStatus.CREATED);
    }
    
    @PostMapping("/binhluan/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") int id, @RequestBody BinhLuan binhluan) {
        BinhLuan c =  this.binhLuanService.getBinhLuanById(id);
        c.setNoiDung(binhluan.getNoiDung());
        return new ResponseEntity<>(this.binhLuanService.addOrUpdateBinhLuan(c),HttpStatus.OK);
    }
    
    @DeleteMapping("/binhluan/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable(value = "id") int id) {
        this.binhLuanService.deleteBinhLuan(id);
    }
    

}
