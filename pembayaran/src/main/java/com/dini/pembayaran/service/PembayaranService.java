/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dini.pembayaran.service;

import com.dini.pembayaran.repository.PembayaranRepository;
import com.dini.pembayaran.entity.Pembayaran;
import com.dini.pembayaran.vo.Produk;
import com.dini.pembayaran.vo.ResponseTemplate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PembayaranService {
    @Autowired
    private PembayaranRepository pembayaranRepository;
    
    @Autowired
    private RestTemplate restTemplate;
    
    public List<Pembayaran> getAll() {
        return pembayaranRepository.findAll();
    }
    
    public void insert(Pembayaran pembayaran) {
        pembayaranRepository.save(pembayaran);
    }
    
    public Pembayaran getPembayaran(Long id) {
        return pembayaranRepository.findById(id).orElse(null);
    }

    public Pembayaran getPembayaranById(Long id) {
        return pembayaranRepository.findById(id).orElse(null);
    }
    
    public List<ResponseTemplate> getPembayaranWithProdukById(Long id) {
        List<ResponseTemplate> responseList = new ArrayList<>();
        Pembayaran pembayaran = getPembayaranById(id);
        if (pembayaran != null) {
            Produk produk = restTemplate.getForObject("http://localhost:9001/api/v1/produk/" 
                    + pembayaran.getProdukId(), Produk.class);
            if (produk != null) {
                ResponseTemplate vo = new ResponseTemplate();
                vo.setPembayaran(pembayaran);
                vo.setProduk(produk);
                responseList.add(vo);
            }
        }
        return responseList;
    }
}
