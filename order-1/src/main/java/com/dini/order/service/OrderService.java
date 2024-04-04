/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dini.order.service;

import com.dini.order.entity.Order;
import com.dini.order.repository.OrderRepository;
import com.dini.order.vo.Produk;
import com.dini.order.vo.ResponseTemplate;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author 
 */
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private RestTemplate restTemplate;
    
    public List<Order> getAll(){
        return orderRepository.findAll();
    }
    
    public void insert(Order order){
        orderRepository.save(order);
    }
    
    @Transactional
    public void update(Long orderId, Integer jumlah, String tanggal, String status) {
        
        Order order = orderRepository.findById(orderId).orElseThrow(() 
                -> new IllegalStateException("Order tidak ada"));
        if (jumlah != null) {
            order.setJumlah(jumlah);
        }
        if (tanggal != null && tanggal.length() > 0 
                && !Objects.equals(order.getTanggal(), tanggal)) {
            order.setTanggal(tanggal);
        }
        if (status != null && status.length() > 0 
                && !Objects.equals(order.getStatus(), status)) {
            order.setStatus(status);
        }

    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
    
    public List<ResponseTemplate> getOrderWithProdukById(Long id){
        List<ResponseTemplate> responseList = new ArrayList<>();
        Order order = getOrderById(id);
        if (order != null) {
            Produk produk= restTemplate.getForObject("http://localhost:9001/api/v1/produk/"
                    + order.getProdukId(), Produk.class);
            if (produk != null) {
                ResponseTemplate vo = new ResponseTemplate();
                vo.setOrder(order);
                vo.setProduk(produk);
                responseList.add(vo);
            }
        }
        return responseList;
    }
}
