/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dini.pembayaran.controller;

import com.dini.pembayaran.entity.Pembayaran;
import com.dini.pembayaran.service.PembayaranService;
import com.dini.pembayaran.vo.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author 
 */
@RestController
@RequestMapping("api/v1/pembayaran")
public class PembayaranController {

    @Autowired
    private PembayaranService pembayaranService;

    @GetMapping
    public List<Pembayaran> getAll() {
        return pembayaranService.getAll();
    }
    
      @GetMapping(path = "/produk/{id}")
    public List<ResponseTemplate> getPembayaranWithProdukById(@PathVariable("id") Long id) {
        return pembayaranService.getPembayaranWithProdukById(id);
    }
    
    @GetMapping(path = "{id}")
    public Pembayaran getPembayaran(@PathVariable("id") Long id){
        return pembayaranService.getPembayaran(id);
    }

}