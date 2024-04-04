/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dini.pembayaran.vo;
import com.dini.pembayaran.entity.Pembayaran;

/**
 *
 * @author Dell
 */
public class ResponseTemplate {
    Produk produk;
    Pembayaran pembayaran;
    
    public ResponseTemplate(){
        
    }

    public ResponseTemplate(Pembayaran pembayaran, Produk produk) {
        this.pembayaran = pembayaran;
        this.produk = produk;
    }

    public Pembayaran getPembayaran() {
        return pembayaran;
    }

    public void setPembayaran(Pembayaran pembayaran) {
        this.pembayaran = pembayaran;
    }

    public Produk getProduk() {
        return produk;
    }

    public void setProduk(Produk produk) {
        this.produk = produk;
    }
    
    @Override
    public String toString() {
        return "ResponseTemplate{" + "produk=" + produk + ", pembayaran=" + pembayaran + '}';
    }
}