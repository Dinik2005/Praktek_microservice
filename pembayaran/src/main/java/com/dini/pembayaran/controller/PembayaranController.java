/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dini.pembayaran.controller;

import com.dini.pembayaran.Repository.PembayaranRepository;
import com.dini.pembayaran.entity.Pembayaran;
import com.dini.pembayaran.service.PembayaranService;
import com.dini.pembayaran.vo.Order;
import com.dini.pembayaran.vo.Produk;
import com.dini.pembayaran.vo.ResponseTemplate;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Dini
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
    
    @GetMapping(path = "{id}")
    public Pembayaran getPembayaran(@PathVariable("id") Long id){
        return pembayaranService.getPembayaran(id);
    }

    @PostMapping
    public void insertPembayaran(@RequestBody Pembayaran pembayaran) {
        pembayaranService.insert(pembayaran);
    } 
    
     @GetMapping(path = "/order/produk/{id}")
    public List<ResponseTemplate>getPembayaranWithOrderkById(@PathVariable("id") Long id) {
        return pembayaranService.getPembayaranWithOrderkById(id);
    }
    
    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") Long Id){
        pembayaranService.delete(Id);
    }
    
    @PutMapping(path = "{id}")
    public void update (@PathVariable("id") Long id,
        @RequestParam(required = false) Long order_Id,
        @RequestParam(required = false) String mode_pemabayaran,
        @RequestParam(required = false) int ref_number,
        @RequestParam(required = false) String tgl_pembayaran,
        @RequestParam(required = false) String status,
        @RequestParam(required = false) Double total) {
        pembayaranService.update(id, order_Id, mode_pemabayaran, ref_number, tgl_pembayaran, status, total);
    }
}