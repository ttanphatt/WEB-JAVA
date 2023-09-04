/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntt.service.BaiVietService;
import com.ntt.pojo.BaiViet;
import com.ntt.pojo.NguoiDung;
import com.ntt.service.TaiKhoanService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admins
 */
@RestController
@RequestMapping("/api")
public class ApiBaiVietController {

    @Autowired
    private BaiVietService baivietService;
    @Autowired
    private TaiKhoanService taikhoanService;

    @DeleteMapping("/capnhat1")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteBViet(@RequestParam Map<String, String> params) {
        int id = Integer.parseInt(params.get("baivietId").toString());
        this.baivietService.deleteBaiViet(id);
        return "capnhat1";
    }

    @GetMapping("/baiviet/")
    @CrossOrigin
    public ResponseEntity<List<BaiViet>> list() {
        return new ResponseEntity<>(this.baivietService.getBaiViet(), HttpStatus.OK);
    }

    @GetMapping("/getBaiViet2Type/{id}")
    @CrossOrigin
    public ResponseEntity<List<Object>> getBaiViet2Type(@PathVariable(value = "id") int id) {
        List<Object> baiviet = this.baivietService.getBaiViet2Type(id);
//        System.out.println(baiviet.size());
        return new ResponseEntity<>(baiviet, HttpStatus.OK);
    }

    @GetMapping("/getThTinBViet/{id}")
    @CrossOrigin
    public Object ThTinBViet(Model model, @RequestParam Map<String, String> params,
            @PathVariable(value = "id") int id) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String i = String.valueOf(id);
        BaiViet b = (BaiViet) this.baivietService.getBaiVietById(id);
        Object baivietJson = objectMapper.writeValueAsString(b);
        return new ResponseEntity<>(baivietJson, HttpStatus.OK);
    }
//    @GetMapping("/getThTinBViet/{id}")
//    @CrossOrigin
//    public Object ThTinBViet(@PathVariable(value = "id") int id) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        String i = String.valueOf(id);
//        BaiViet b = (BaiViet) this.baivietService.getBaiVietById(id);
//        Object baivietJson = objectMapper.writeValueAsString(b);
//        return new ResponseEntity<>(baivietJson, HttpStatus.OK);
//    }

    @GetMapping("/getBVietByIdNgDung/{id}")
    @CrossOrigin
    public ResponseEntity<List<Object>> getBVietByIdNgDung(@PathVariable(value = "id") int id) {
        NguoiDung u = this.taikhoanService.getTaiKhoanId(id);
        List<Object> baiviet = this.baivietService.getBaiVietByIdNgDung(u);
        System.out.println(baiviet.size());
        return new ResponseEntity<>(baiviet, HttpStatus.OK);
    }

    @PostMapping(path = "/dangbai/",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<BaiViet> addBaiVietAPI(@RequestParam Map<String, String> params,
            @RequestPart MultipartFile hinhanh) {
        BaiViet bv = this.baivietService.addBaiVietAPI(params, hinhanh);
        System.out.println(bv);
        return new ResponseEntity<>(bv, HttpStatus.CREATED);
    }

    @PostMapping(path = "/capnhatbaiviet/{id}",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin
    public ResponseEntity<BaiViet> updateBaiVietAPI(Model model, @RequestParam Map<String, String> params,
            @RequestPart MultipartFile hinhanh, @PathVariable(value = "id") int id) {
        BaiViet bv = this.baivietService.addBaiVietAPI(params, hinhanh);
        String i = String.valueOf(id);
        BaiViet b = (BaiViet) this.baivietService.getBaiVietById(id);
        return new ResponseEntity<>(bv, HttpStatus.CREATED);
    }

}
