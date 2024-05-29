/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dini.pembayaran.service;

import com.dini.pembayaran.entity.Pembayaran;
import com.dini.pembayaran.repository.PembayaranRepository;
import com.dini.pembayaran.vo.Produk;
import com.dini.pembayaran.vo.Order;
import com.dini.pembayaran.vo.ResponseTemplate;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Dini
 */
@Service
public class PembayaranService {
    @Autowired
    private PembayaranRepository pembayaranRepository;
     @Autowired
    private RestTemplate restTemplate;
    
    public List<Pembayaran> getAll() {
        return pembayaranRepository.findAll();
    }

    public Pembayaran getPembayaran(Long id){
        return pembayaranRepository.findById(id).get();
    }
    
    @Transactional
    public void insert(Pembayaran pembayaran){
        pembayaranRepository.save(pembayaran);
    }
    public Pembayaran getPembayaranById(Long id) {
        return pembayaranRepository.findById(id).get();
    }
    
     public List<ResponseTemplate> getPembayaranWithOrderkById(Long id){
        List<ResponseTemplate> responseList = new ArrayList<>();
        Pembayaran pembayaran = getPembayaranById(id);
         Order order = restTemplate.getForObject("http://localhost:9002/api/v1/order/" 
                + pembayaran.getOrder_Id(), Order.class);  
        Produk produk = restTemplate.getForObject("http://localhost:9001/api/v1/produk/"
                + pembayaran.getOrder_Id(),Produk.class);
        ResponseTemplate vo = new ResponseTemplate();
        vo.setOrder(order);
        vo.setProduk(produk);
        vo.setPembayaran(pembayaran);
        responseList.add(vo);
        return responseList;
    }
     
    @Transactional
public void update(Long id, Long order_Id, String mode_pemabayaran, Integer ref_number, String tgl_pembayaran, String status, Double total) {
    // Mencari entitas Pembayaran berdasarkan ID yang benar (gunakan parameter id)
    Pembayaran pembayaran = pembayaranRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("Pembayaran tidak ada"));
    
    // Memperbarui entitas Pembayaran jika ada perubahan data
    if (mode_pemabayaran != null && mode_pemabayaran.length() > 0 && !Objects.equals(pembayaran.getMode_pemabayaran(), mode_pemabayaran)) {
        pembayaran.setMode_pemabayaran(mode_pemabayaran);
    }
    if (ref_number != null) {
        pembayaran.setRef_number(ref_number);
    }
    if (tgl_pembayaran != null && tgl_pembayaran.length() > 0 && !Objects.equals(pembayaran.getTgl_pembayaran(), tgl_pembayaran)) {
        pembayaran.setTgl_pembayaran(tgl_pembayaran);
    }
    if (status != null && status.length() > 0 && !Objects.equals(pembayaran.getStatus(), status)) {
        pembayaran.setStatus(status);
    }
    if (total != null && !Objects.equals(pembayaran.getTotal(), total)) {
        pembayaran.setTotal(total);
    }
}
 
     
    public void delete(Long produkId){
        pembayaranRepository.deleteById(produkId);
    }
}
