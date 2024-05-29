/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.dini.produk.service;
/**
 *
 * @Author Dini
 */
import com.dini.produk.entity.Produk;
import com.dini.produk.repository.ProdukRepository;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProdukService {
    @Autowired
    private ProdukRepository produkRepository;
    
    public List<Produk>getAll(){
        return produkRepository.findAll();
    }
    
    public Produk getProduk(Long id){
        return produkRepository.findById(id).get();
    }
     public void insert(Produk produk){
        produkRepository.save(produk);
    }
    @Transactional
    public void update (Long produkId, String kode, String nama, String satuan){
        Produk produk = produkRepository.findById(produkId)
                .orElseThrow(
                        ()->new IllegalStateException("Produk Tidak ada")
                );
        if(kode != null && kode.length()>0
                && !Objects.equals(produk.getKode(), kode)){
            produk.setKode(kode);
        }
        if(nama != null && nama.length()>0
                && !Objects.equals(produk.getNama(), nama)){
            produk.setNama(nama);
        }
        if(satuan != null && satuan.length()>0
                && !Objects.equals(produk.getSatuan(), satuan)){
            produk.setSatuan(satuan);
        }
    }
   
    public void deleteProduk(Long id) {
        produkRepository.deleteById(id);
    }
    

}
